package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Ability;
import com.example.EditMatch.Repository.AbilityRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Controller
@RequestMapping("/abilitys")
@Api(value = "AbilityController", description = "Controladora de ability")
public class AbilityController {
    @Autowired
    private AbilityRepository abilityRepository;

    private Ability ability;

    @PostMapping
    public ResponseEntity<Ability> cadastrar(@RequestBody Ability ability){
        return ResponseEntity.of(Optional.of(abilityRepository.save(ability)));
    }
    @PutMapping("/{id}")
    @ApiOperation(value = "Lista ability", notes = "Retorna o ability atualizado")
    public ResponseEntity<Ability> atualizar(@PathVariable int id,
                                            @RequestBody Ability abilityAtualizado){
        Optional<Ability> ability = abilityRepository.findById(id);
        if(ability.isPresent()){
            if (abilityAtualizado.getDesc() != null){
                ability.get().setDesc(abilityAtualizado.getDesc());
            }
            Ability ability1 = abilityRepository.save(ability.get());
            return ResponseEntity.ok().body(ability1);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping()
    @ApiOperation(value = "Deleta ability", notes = "asdasd")
    public ResponseEntity<Void> deletar(@RequestParam int id){
        Optional<Ability> abilityOptional = abilityRepository.findById(id);
        if (abilityOptional.isPresent()){
            abilityRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping()
    @ApiOperation(value = "Busca ability", notes = "Retorna o ability buscado")
    public ResponseEntity<List<Ability>> buscar(@RequestParam String desc){
        List<Ability> ability1 = abilityRepository.findByDesc(desc);
        if(ability1.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(ability1);
    }
}