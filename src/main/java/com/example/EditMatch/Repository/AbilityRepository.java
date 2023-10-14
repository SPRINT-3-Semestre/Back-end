package com.example.EditMatch.Repository;

import com.example.EditMatch.Entity.Ability;
import com.example.EditMatch.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AbilityRepository extends JpaRepository<Ability, Integer> {

    List<Ability> findByIdAndDesc();
    List<Ability> findById(int id);
    List<Ability> findByDesc(String desc);
}
