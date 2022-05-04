package com.example.coursebooking;

import com.example.coursebooking.model.AppUser;
import com.example.coursebooking.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class CourseBookingApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void saveAdmin() {
        AppUser admin = new AppUser();
        admin.setRole("ROLE_ADMIN");
        admin.setEmail("admin@gmail.com");
        admin.setPassword(passwordEncoder.encode("adminpass"));
        admin.setLastName("adminlast");
        admin.setFirstName("adminfirst");
        appUserRepository.save(admin);
    }

}
