package com.example.nermanapp.dto.Response.OrderDetailResponse;
import com.example.nermanapp.model.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailResponse {
    private OrderDetail orderDetail;
    private String status;

}
