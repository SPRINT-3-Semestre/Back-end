package com.example.EditMatch.Service;

import com.example.EditMatch.Entity.Ability;
import com.example.EditMatch.Entity.Usuario;
import com.example.EditMatch.Repository.AbilityRepository;
import com.example.EditMatch.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AbilityService {

    private final AbilityRepository abilityRepository;
    private final UserRepository userRepository;

    public List<Ability> saveAbilitys(List<Ability> habilidades , Integer id) {
        Optional<Usuario> usuarioOptional = userRepository.findById(id);

        if (usuarioOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Usuario usuario = usuarioOptional.get();

        for (Ability habilidade : habilidades) {
            habilidade.setUsuario(usuario);
        }

        return abilityRepository.saveAll(habilidades);
    }

    public List<Ability> updateAbilitys(List<Ability> habilidadesAtualizadas, Integer id) {
        Optional<Usuario> usuarioOptional = userRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            for (Ability habilidadeAtualizada : habilidadesAtualizadas) {
                Optional<Ability> existingAbilityOptional = abilityRepository.findById(habilidadeAtualizada.getId());
                if (existingAbilityOptional.isPresent()) {
                    Ability existingAbility = existingAbilityOptional.get();
                    if (habilidadeAtualizada.getDesc() != null) {
                        existingAbility.setDesc(habilidadeAtualizada.getDesc());
                    }
                    existingAbility.setUsuario(usuario);
                    abilityRepository.save(existingAbility);
                }
            }
            return habilidadesAtualizadas;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    public void deleteAbility(Integer id) {
        Optional<Ability> abilityOptional = abilityRepository.findById(id);
        if (abilityOptional.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        abilityRepository.deleteById(id);
    }

    public List<Ability> findById(Integer id) {
        Optional<Usuario> usuarioOptional = userRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            List<Ability> habilidades = abilityRepository.findByUsuario(usuario);
            return habilidades;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
