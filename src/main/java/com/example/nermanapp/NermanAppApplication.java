package com.example.nermanapp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.nermanapp.auth.AuthenticationService;
import com.example.nermanapp.auth.RegisterRequest;
import com.example.nermanapp.dto.Request.CategoryRequest.CreateCategoryRequest;
import com.example.nermanapp.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;

import static com.example.nermanapp.enums.Role.*;

@SpringBootApplication
public class NermanAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(NermanAppApplication.class, args);
    }

    @Bean
    BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public Cloudinary cloudinary() {
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dpxs39hkb",
                "api_key", "679575712278322",
                "api_secret", "KJfkzpiXRnmkPCeRwH6TUAmFGks"));
        return cloudinary;
    }

    @GetMapping("")
    public String greeting() {
        return "Nerman Application From PRMProject";
    }

    @Bean
    public CommandLineRunner commandLineRunner(
            AuthenticationService service,
            CategoryService categoryService
    ) {
        return args -> {
            //Admin
            var admin = RegisterRequest.builder()
                    .name("Admin")
                    .email("Admin@gmail.com")
                    .password("123")
                    .phone("0392272536")
                    .status(true)
                    .role(ADMIN)
                    .avatar("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719499363/wlimd1mgkqztk2ygdfgx.webp")
                    .build();
            System.out.println("Admin token :" + service.register(admin).getAccessToken());

            //staff
            var staff = RegisterRequest.builder()
                    .name("Staff")
                    .email("staff@gmail.com")
                    .status(true)
                    .password("123")
                    .phone("0854512367")
                    .role(STAFF)
                    .avatar("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719499363/wlimd1mgkqztk2ygdfgx.webp")
                    .build();
            System.out.println("Staff token :" + service.register(staff).getAccessToken());

            //Customer
            var customer = RegisterRequest.builder()
                    .name("Trần Huy")
                    .email("huypt110402@gmail.com")
                    .status(true)
                    .password("123")
                    .phone("0854512367")
                    .role(CUSTOMER)
                    .avatar("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719499363/wlimd1mgkqztk2ygdfgx.webp")
                    .build();
            System.out.println("Customer token :" + service.register(customer).getAccessToken());

            var customer3 = RegisterRequest.builder()
                    .name("Minh Trí")
                    .email("tri@gmail.com")
                    .status(true)
                    .password("123")
                    .phone("1231231234")
                    .role(CUSTOMER)
                    .avatar("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719499363/wlimd1mgkqztk2ygdfgx.webp")
                    .build();
            service.register(customer3);

            var customer4 = RegisterRequest.builder()
                    .name("Phước Hữu")
                    .email("phuoc@gmail.com")
                    .status(true)
                    .password("123")
                    .phone("1231231234")
                    .role(CUSTOMER)
                    .avatar("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719499363/wlimd1mgkqztk2ygdfgx.webp")
                    .build();
            service.register(customer4);

            var customer5 = RegisterRequest.builder()
                    .name("Lương")
                    .email("luong@gmail.com")
                    .status(true)
                    .password("123")
                    .phone("1231231234")
                    .role(CUSTOMER)
                    .avatar("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719499363/wlimd1mgkqztk2ygdfgx.webp")
                    .build();
            service.register(customer5);

            var customer6 = RegisterRequest.builder()
                    .name("Nhật")
                    .email("nhat@gmail.com")
                    .status(true)
                    .password("123")
                    .phone("1231231234")
                    .role(CUSTOMER)
                    .avatar("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719499363/wlimd1mgkqztk2ygdfgx.webp")
                    .build();
            service.register(customer6);

            var customer7 = RegisterRequest.builder()
                    .name("Hải")
                    .email("hai@gmail.com")
                    .status(true)
                    .password("123")
                    .phone("1231231234")
                    .role(CUSTOMER)
                    .avatar("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719499363/wlimd1mgkqztk2ygdfgx.webp")
                    .build();
            service.register(customer7);

            var category = CreateCategoryRequest.builder()
                    .categoryName("Chăm sóc cơ thể")
                    .build();
            categoryService.createCategory(category);

            var category1 = CreateCategoryRequest.builder()
                    .categoryName("Chăm sóc da")
                    .build();
            categoryService.createCategory(category1);

            var category2 = CreateCategoryRequest.builder()
                    .categoryName("Nước hoa")
                    .build();
            categoryService.createCategory(category2);

        };
    }
}
