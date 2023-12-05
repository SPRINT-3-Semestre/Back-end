package com.example.EditMatch.repository;

import com.example.EditMatch.entity.ClientFinal;
import com.example.EditMatch.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT o FROM Order o WHERE o.clientFinal =:id")
    List<Order> orderClient(ClientFinal id);

    @Query("UPDATE Order o SET o.clientFinal = :valorTotal WHERE o.id = :id")
    Integer editCart(Integer id, Integer valorTotal);

    @Modifying
    @Transactional
    @Query("DELETE FROM Order c WHERE c.clientFinal=:id")
    Integer emptyCart(ClientFinal id);
}
