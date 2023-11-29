package com.example.EditMatch.Repository;

import com.example.EditMatch.Entity.Ability;
import com.example.EditMatch.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AbilityRepository extends JpaRepository<Ability, Integer> {

    @Override
    Optional<Ability> findById(Integer integer);
    List<Ability> findByUsuario(Usuario usuario);

}
