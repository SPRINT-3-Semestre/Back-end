package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Ability;
import com.example.EditMatch.Entity.User;
import com.example.EditMatch.Repository.AbilityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Controller
@RequestMapping("/abilitys")
public class AbilityController {
    private AbilityRepository abilityRepository;
    @PostMapping("")
    public ResponseEntity<Ability> cadastrar(@RequestBody Ability ability){
        return ResponseEntity.of(Optional.of(abilityRepository.save(ability)));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Ability> atualizar(@PathVariable int id,
                                            @RequestBody Ability abilityAtualizado){
        List<Ability> ability = abilityRepository.findAll();
        ability.set(id, abilityAtualizado);
        return ResponseEntity.ok(abilityAtualizado);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Ability> deletar(@PathVariable int id){
        List<Ability> ability = abilityRepository.findAll();
        ability.remove(id);
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{desc}")
    public ResponseEntity<Ability> buscar(@PathVariable String desc){
        List<Ability> ability1 = abilityRepository.findByDesc(desc);
        if(ability1.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok((Ability) ability1);
    }
}