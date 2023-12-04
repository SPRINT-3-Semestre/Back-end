package com.example.EditMatch.controller.cart;

import com.example.EditMatch.controller.cart.dto.CartCreateDto;
import com.example.EditMatch.entity.Cart;
import com.example.EditMatch.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/carts")
public class CartController {

    private final CartService cartService;

    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCartDetails(@PathVariable Integer cartId) {
        Cart cart = cartService.getCartById(cartId);
        if (cart == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(cart);
    }
    @GetMapping
    public ResponseEntity<List<Cart>>cartClient(@RequestParam Integer id){
        List<Cart> cart = cartService.cartClient(id);
        if (cart.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(cart);
    }
    @PostMapping
    public ResponseEntity<Cart>addEditor(@Valid @RequestBody CartCreateDto carrinho){
        return ResponseEntity.ok(cartService.add(carrinho));
    }
    @PatchMapping("/{id}")
    public ResponseEntity<Void>edit(@PathVariable Integer id, @RequestParam Integer quantidade){
        cartService.edit(id,quantidade);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>removeEditor(@PathVariable Integer id){
        cartService.removeEditor(id);
        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/empty-cart/{id}")
    public ResponseEntity<Void>emptyCart(@PathVariable Integer id){
        cartService.emptyCart(id);
        return ResponseEntity.noContent().build();
    }
}
