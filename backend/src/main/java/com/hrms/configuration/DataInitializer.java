package com.hrms.configuration;

import com.hrms.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final AuthService authService;

    @Override
    public void run(String... args) {
        // Seed a Super Admin if none exists in DB
        authService.seedSuperAdmin(
                "Super Admin",
                "superadmin@hrms.com",
                "super@123"         // change this in production!
        );
        log.info("=== HRMS Application Started ===");
        log.info("Super Admin: superadmin@hrms.com / super@123");
    }
}
