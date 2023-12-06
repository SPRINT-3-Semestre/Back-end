package com.example.EditMatch.repository;

import com.example.EditMatch.entity.ClientFinal;
import com.example.EditMatch.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    @Query("SELECT o FROM Orders o WHERE o.clientFinal =:id")
    List<Orders> orderClient(ClientFinal id);

    @Query("UPDATE Orders o SET o.title = :title, o.desc = :desc, o.skills = :skills WHERE o.id = :id")
    Integer editOrder(Integer id, String title, String desc, String skills);

}
