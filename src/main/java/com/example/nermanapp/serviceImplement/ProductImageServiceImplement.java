package com.example.nermanapp.serviceImplement;
import com.example.nermanapp.dto.Request.ProductRequest.ImageRequest;
import com.example.nermanapp.dto.Response.ProductResponse.ImageResponse;
import com.example.nermanapp.model.ProductImages;
import com.example.nermanapp.repository.ProductImageRepo;
import com.example.nermanapp.repository.ProductRepo;
import com.example.nermanapp.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductImageServiceImplement implements ProductImageService {
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ProductImageRepo productImageRepo;
    @Override
    public ImageResponse create(ImageRequest request) {
        var product = productRepo.findProductByProductID(request.getProductId()).orElse(null);
        if (product == null) {
            return ImageResponse.builder()
                    .productImages(null)
                    .build();
        }
        ProductImages productImages = ProductImages.builder()
                .imageUrl(request.getUrl())
                .product(product)
                .build();
        productImageRepo.save(productImages);
        return ImageResponse.builder()
                .productImages(productImages)
                .build();
    }
}
