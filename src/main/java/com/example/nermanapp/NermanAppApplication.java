package com.example.nermanapp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.nermanapp.auth.AuthenticationService;
import com.example.nermanapp.auth.RegisterRequest;
import com.example.nermanapp.dto.Request.CategoryRequest.CreateCategoryRequest;
import com.example.nermanapp.dto.Request.ProductRequest.ProductRequest;
import com.example.nermanapp.service.CategoryService;
import com.example.nermanapp.service.ProductService;
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
            CategoryService categoryService,
            ProductService productService
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

            var product1 = ProductRequest.builder()
                    .categoryId(1)
                    .productName("Sữa tắm gội nam giới hương nước hoa cao cấp")
                    .description("Sữa tắm gội nam giới hương nước hoa cao cấp Gentleman 3 in 1 Nerman 350ml")
                    .price(100000)
                    .quantity(100)
                    .build();
            productService.create(product1);
            var product2 = ProductRequest.builder()
                    .categoryId(1)
                    .productName("Dung dịch vệ sinh nam giới Nerman Elegant")
                    .description("Dung dịch vệ sinh nam giới Nerman Elegant Men Nerman hương nước hoa cao cấp 100ml/ chai")
                    .price(150000)
                    .quantity(100)
                    .build();
            productService.create(product2);
            var product3 = ProductRequest.builder()
                    .categoryId(1)
                    .productName("Combo lịch lãm Nerman")
                    .description("Combo lịch lãm Nerman - Sữa tắm gội cho nam 350ml & DDVS nam giới 100ml")
                    .price(500000)
                    .quantity(100)
                    .build();
            productService.create(product3);
            var product4 = ProductRequest.builder()
                    .categoryId(1)
                    .productName("Xịt thơm miệng Spray & Kiss Nerman")
                    .description("Xịt thơm miệng Spray & Kiss Nerman vị kẹo hoa quả bạc hà the mát cho nam nữ chai 10ml")
                    .price(70000)
                    .quantity(100)
                    .build();
            productService.create(product4);
            var product5 = ProductRequest.builder()
                    .categoryId(1)
                    .productName("Combo Badboy Nerman")
                    .description("Combo Badboy Nerman - Sữa tắm gội cho nam 350ml & Gel vệ sinh 60S Fresh 100ml & Xịt khử mùi toàn thân Good Day 100ml")
                    .price(90000)
                    .quantity(100)
                    .build();
            productService.create(product5);

            var product6 = ProductRequest.builder()
                    .categoryId(2)
                    .productName("Gel rửa mặt Nerman Nano Curcumin")
                    .description("Gel rửa mặt ngừa mụn nam Nerman Nano Curcumin 60s Fresh 100ml")
                    .price(100000)
                    .quantity(100)
                    .build();
            productService.create(product6);
            var product7 = ProductRequest.builder()
                    .categoryId(2)
                    .productName("Son dưỡng cho nam Nerman")
                    .description("Son dưỡng cho nam Nerman dưỡng ẩm môi,giảm nứt nẻ,không bết dính,hỗ trợ mờ thâm Men in Black 4g")
                    .price(150000)
                    .quantity(100)
                    .build();
            productService.create(product7);
            var product8 = ProductRequest.builder()
                    .categoryId(2)
                    .productName("Nerman Perfect Whitening")
                    .description("Kem dưỡng trắng, giảm thâm, mờ sẹo nam Nerman Perfect Whitening Cream 50g")
                    .price(500000)
                    .quantity(100)
                    .build();
            productService.create(product8);
            var product9 = ProductRequest.builder()
                    .categoryId(2)
                    .productName("'Combo trắng da cho nam Nerman Perfect Whitening")
                    .description("Combo trắng da cho nam Nerman Perfect Whitening - Gel rửa mặt 60s Fresh 100ml & Kem dưỡng trắng, giảm thâm, mờ sẹo 50g")
                    .price(70000)
                    .quantity(100)
                    .build();
            productService.create(product9);
            var product10 = ProductRequest.builder()
                    .categoryId(2)
                    .productName("Kem Che Khuyết Điểm Cho Nam Nerman BB Cream Invisible")
                    .description("Kem Che Khuyết Điểm Cho Nam Nerman BB Cream Invisible 3-In-1 Chống Nắng Spf 45+, Che Khuyết Điểm, Dưỡng Ẩm Nerman 50g")
                    .price(90000)
                    .quantity(100)
                    .build();
            productService.create(product10);

            var product11 = ProductRequest.builder()
                    .categoryId(3)
                    .productName("Xịt khử mùi nam giới hương nước hoa Good Day Nerman")
                    .description("Xịt khử mùi nam giới hương nước hoa Good Day Nerman 100ml, tặng xịt miệng cho combo 2 chai")
                    .price(100000)
                    .quantity(100)
                    .build();
            productService.create(product11);
            var product12 = ProductRequest.builder()
                    .categoryId(3)
                    .productName("Nước hoa nam Nerman")
                    .description("Nước hoa nam Nerman - hương thơm nhẹ nhàng, tinh tế hút nữ giới, hợp đi hẹn hò, đi chơi")
                    .price(150000)
                    .quantity(100)
                    .build();
            productService.create(product12);
        };
    }
}
