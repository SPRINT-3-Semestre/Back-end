package com.example.EditMatch.repository;

import com.example.EditMatch.entity.Cart;
import com.example.EditMatch.entity.ClientFinal;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("SELECT c FROM Cart c WHERE c.clientFinal =:id")
    List<Cart> cartClient(ClientFinal id);

    @Query("UPDATE Cart c SET c.valorTotal = :valorTotal WHERE c.id = :id")
    Integer editCart(Integer id, Integer valorTotal);

    @Modifying
    @Transactional
    @Query("DELETE FROM Cart c WHERE c.clientFinal=:id")
    Integer emptyCart(ClientFinal id);
}
