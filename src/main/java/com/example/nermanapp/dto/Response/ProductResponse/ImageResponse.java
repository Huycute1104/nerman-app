package com.example.nermanapp.dto.Response.ProductResponse;

import com.example.nermanapp.model.ProductImages;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ImageResponse {
    private ProductImages productImages;
}
