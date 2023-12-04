package com.example.EditMatch.service;

import com.example.EditMatch.controller.cart.dto.CartCreateDto;
import com.example.EditMatch.controller.cart.mapper.CartMapper;
import com.example.EditMatch.entity.Cart;
import com.example.EditMatch.entity.ClientFinal;
import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.repository.CartRepository;
import com.example.EditMatch.repository.ClientFinalRepository;
import com.example.EditMatch.repository.EditorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final EditorRepository editorRepository;
    private final ClientFinalRepository clientFinalRepository;

    public Cart add(CartCreateDto cartCreateDto){
        ClientFinal clientFinal = clientFinalRepository.findById(cartCreateDto.getClientFinal())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        Editor editor = editorRepository.findById(cartCreateDto.getEditor())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return cartRepository.save(CartMapper.toCart(cartCreateDto,clientFinal,editor));
    }
    public List<Cart> cartClient(Integer id){
        ClientFinal clientFinal = clientFinalRepository.findById(id).orElseThrow(
                ()->new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        List<Cart> carrinho = cartRepository.cartClient(clientFinal);

        return carrinho;
    }
    public void edit(Integer id, Integer valorTotal){
        cartRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        cartRepository.editCart(id,valorTotal);
    }
    public void emptyCart(Integer id){
        ClientFinal clientFinal = clientFinalRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        cartRepository.emptyCart(clientFinal);
    }
    public void removeEditor(Integer id){
        cartRepository.findById(id).orElseThrow(
                ()-> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
        cartRepository.deleteById(id);
    }
    public Cart getCartById(Integer cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
