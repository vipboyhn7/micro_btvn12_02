package com.example.productservice.order;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateOrderRequest(
        @NotNull Long productId,
        @NotNull @Min(1) Integer quantity,
        @NotBlank @Email String customerEmail
) {
}
