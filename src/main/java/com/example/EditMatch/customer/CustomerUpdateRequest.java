package com.example.EditMatch.customer;

public record CustomerUpdateRequest(
        String name,
        String email,
        Integer age
) {
}
