package com.example.EditMatch.controller.cart.mapper;

import com.example.EditMatch.controller.cart.dto.CartCreateDto;
import com.example.EditMatch.entity.Cart;
import com.example.EditMatch.entity.ClientFinal;
import com.example.EditMatch.entity.Editor;


public class CartMapper {
    public static Cart toCart(CartCreateDto cartCreateDto, ClientFinal clientFinal, Editor editor) {
        return new Cart(
                null,
                cartCreateDto.getValorTotal(),
                editor,
                clientFinal
        );
    }
}
