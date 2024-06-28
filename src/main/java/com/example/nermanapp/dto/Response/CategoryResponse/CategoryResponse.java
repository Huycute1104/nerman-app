package com.example.nermanapp.dto.Response.CategoryResponse;

import com.example.nermanapp.model.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {
    private String status;
    private Category category;
}
