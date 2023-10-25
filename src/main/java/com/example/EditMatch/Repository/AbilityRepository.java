package com.example.EditMatch.Repository;

import com.example.EditMatch.Entity.Ability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AbilityRepository extends JpaRepository<Ability, Integer> {

    @Override
    Optional<Ability> findById(Integer integer);

    Optional<Ability> findById(int id);

    Optional<Ability> deleteById(int id);

    List<Ability> findByDesc(String desc);
}
