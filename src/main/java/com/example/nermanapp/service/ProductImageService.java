package com.example.nermanapp.service;

import com.example.nermanapp.dto.Request.ProductRequest.ImageRequest;
import com.example.nermanapp.dto.Response.ProductResponse.ImageResponse;
import com.example.nermanapp.model.ProductImages;

public interface ProductImageService {
    public ImageResponse create (ImageRequest request);
}
