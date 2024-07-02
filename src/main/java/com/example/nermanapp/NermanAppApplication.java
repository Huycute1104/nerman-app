package com.example.nermanapp;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.nermanapp.auth.AuthenticationService;
import com.example.nermanapp.auth.RegisterRequest;
import com.example.nermanapp.dto.Request.CategoryRequest.CreateCategoryRequest;
import com.example.nermanapp.dto.Request.OrderRequest.CreateOrderRequest;
import com.example.nermanapp.dto.Request.ProductRequest.ImageRequest;
import com.example.nermanapp.dto.Request.ProductRequest.ProductRequest;
import com.example.nermanapp.service.CategoryService;
import com.example.nermanapp.service.OrderService;
import com.example.nermanapp.service.ProductImageService;
import com.example.nermanapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.nermanapp.enums.Role.*;

@SpringBootApplication
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/")
public class NermanAppApplication {

    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
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
            ProductService productService,
            ProductImageService productImageService,
            OrderService orderService
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
                    .email("trihuynh1811@gmail.com")
                    .status(true)
                    .password("123")
                    .phone("1231231234")
                    .role(CUSTOMER)
                    .avatar("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719499363/wlimd1mgkqztk2ygdfgx.webp")
                    .build();
            service.register(customer3);

            var customer4 = RegisterRequest.builder()
                    .name("Phước Hữu")
                    .email("huuphuocnguyen2002@gmail.com")
                    .status(true)
                    .password("123")
                    .phone("1231231234")
                    .role(CUSTOMER)
                    .avatar("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719499363/wlimd1mgkqztk2ygdfgx.webp")
                    .build();
            service.register(customer4);

            var customer5 = RegisterRequest.builder()
                    .name("Lương")
                    .email("camkionline@gmail.com")
                    .status(true)
                    .password("123")
                    .phone("1231231234")
                    .role(CUSTOMER)
                    .avatar("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719499363/wlimd1mgkqztk2ygdfgx.webp")
                    .build();
            service.register(customer5);

            var customer6 = RegisterRequest.builder()
                    .name("Nhật")
                    .email("nle75234@gmail.com")
                    .status(true)
                    .password("123")
                    .phone("1231231234")
                    .role(CUSTOMER)
                    .avatar("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719499363/wlimd1mgkqztk2ygdfgx.webp")
                    .build();
            service.register(customer6);

            var customer7 = RegisterRequest.builder()
                    .name("Hải")
                    .email("haivv.se@gmail.com")
                    .status(true)
                    .password("123")
                    .phone("1231231234")
                    .role(CUSTOMER)
                    .avatar("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719499363/wlimd1mgkqztk2ygdfgx.webp")
                    .build();
            service.register(customer7);

            var customer8 = RegisterRequest.builder()
                    .name("Test")
                    .email("test@gmail.com")
                    .status(true)
                    .password("123")
                    .phone("1231231234")
                    .role(CUSTOMER)
                    .avatar("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719499363/wlimd1mgkqztk2ygdfgx.webp")
                    .build();
            service.register(customer8);
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

            var image1 = ImageRequest.builder()
                    .url("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719503393/ldagguk6z0cqcc4cpp3e.jpg")
                    .productId(1)
                    .build();
            productImageService.create(image1);

            var image2 = ImageRequest.builder()
                    .url("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719503389/bxkrvgjuluaa3vgd3k3c.jpg")
                    .productId(2)
                    .build();
            productImageService.create(image2);

            var image3 = ImageRequest.builder()
                    .url("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719503386/d2vksvwqzyxijktbivil.jpg")
                    .productId(3)
                    .build();
            productImageService.create(image3);

            var image4 = ImageRequest.builder()
                    .url("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719503397/ljh49snl1dm0xfkvajti.jpg")
                    .productId(4)
                    .build();
            productImageService.create(image4);

            var image5 = ImageRequest.builder()
                    .url("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719503382/cpzcampnetyiunmirbzk.jpg")
                    .productId(5)
                    .build();
            productImageService.create(image5);

            var image6 = ImageRequest.builder()
                    .url("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719503491/uqclachh3lxfdlxvypxj.jpg")
                    .productId(6)
                    .build();
            productImageService.create(image6);

            var image7 = ImageRequest.builder()
                    .url("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719503509/zdbzxuammerxwzv6j6s8.jpg")
                    .productId(7)
                    .build();
            productImageService.create(image7);

            var image8 = ImageRequest.builder()
                    .url("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719503500/xa5kcpcjbautwzftmvr8.jpg")
                    .productId(8)
                    .build();
            productImageService.create(image8);

            var image9 = ImageRequest.builder()
                    .url("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719503488/tsezuh3r13oumb6kxygh.jpg")
                    .productId(9)
                    .build();
            productImageService.create(image9);

            var image10 = ImageRequest.builder()
                    .url("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719503496/czfgrzvc6fkvt8ez5mmw.jpg")
                    .productId(10)
                    .build();
            productImageService.create(image10);

            var image11 = ImageRequest.builder()
                    .url("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719503512/pc9dferdn2q5i0wehxns.jpg")
                    .productId(11)
                    .build();
            productImageService.create(image11);

            var image12 = ImageRequest.builder()
                    .url("https://res.cloudinary.com/dpxs39hkb/image/upload/v1719503505/ufrmlgxfiziwps4pwhoa.jpg")
                    .productId(12)
                    .build();
            productImageService.create(image12);


            List<CreateOrderRequest.OrderProductRequest> orderItems = List.of(
                    CreateOrderRequest.OrderProductRequest.builder()
                            .productId(1)
                            .quantity(1)
                            .build(),
                    CreateOrderRequest.OrderProductRequest.builder()
                            .productId(2)
                            .quantity(1)
                            .build()
            );
            var order = CreateOrderRequest.builder()
                    .total(250000)
                    .customerId(3)
                    .customerName("Huy Phạm")
                    .customerAddress("Bình Định")
                    .customerPhone("0392272536")
                    .products(orderItems)
                    .build();
            orderService.createOrderWithDetails(order);

            List<CreateOrderRequest.OrderProductRequest> orderItems2 = List.of(
                    CreateOrderRequest.OrderProductRequest.builder()
                            .productId(8)
                            .quantity(2)
                            .build(),
                    CreateOrderRequest.OrderProductRequest.builder()
                            .productId(9)
                            .quantity(3)
                            .build()
            );
            var order2 = CreateOrderRequest.builder()
                    .total(1210000)
                    .customerId(4)
                    .customerName("Minh Trí")
                    .customerAddress("HCM")
                    .customerPhone("0321546587")
                    .products(orderItems2)
                    .build();
            orderService.createOrderWithDetails(order2);

            List<CreateOrderRequest.OrderProductRequest> orderItems3 = List.of(
                    CreateOrderRequest.OrderProductRequest.builder()
                            .productId(8)
                            .quantity(1)
                            .build()
            );
            var order3 = CreateOrderRequest.builder()
                    .total(500000)
                    .customerId(5)
                    .customerName("Hữu Phước")
                    .customerAddress("Phú Yên")
                    .customerPhone("0392123698")
                    .products(orderItems3)
                    .build();
            orderService.createOrderWithDetails(order3);

            List<CreateOrderRequest.OrderProductRequest> orderItems4 = List.of(
                    CreateOrderRequest.OrderProductRequest.builder()
                            .productId(10)
                            .quantity(2)
                            .build(),
                    CreateOrderRequest.OrderProductRequest.builder()
                            .productId(5)
                            .quantity(2)
                            .build()
            );
            var order4 = CreateOrderRequest.builder()
                    .total(360000)
                    .customerId(7)
                    .customerName("Nhật")
                    .customerAddress("HCM")
                    .customerPhone("0392545125")
                    .products(orderItems4)
                    .build();
            orderService.createOrderWithDetails(order4);

        };
    }
}
