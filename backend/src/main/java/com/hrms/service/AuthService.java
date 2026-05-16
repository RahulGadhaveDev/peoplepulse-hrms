package com.hrms.service;

import com.hrms.Enum.Role;
import com.hrms.dto.AuthRequest;
import com.hrms.dto.AuthResponse;
import com.hrms.entity.User;
import com.hrms.repository.UserRepository;
import com.hrms.security.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * Authenticate user and return JWT token
     */
    public AuthResponse login(AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));
        String token = jwtUtil.generateToken(user.getEmail());
        return new AuthResponse(token, user.getEmail()
        );
    }
    public void seedSuperAdmin(String name, String email, String password) {
        if (!userRepository.existsByRole(Role.SUPER_ADMIN)) {
            User superAdmin = User.builder()
                    .email(email)
                    .password(passwordEncoder.encode(password))
                    .role(Role.SUPER_ADMIN)
                    .build();
            userRepository.save(superAdmin);
            log.info("Super Admin seeded: {}", email);
        }
    }

}
