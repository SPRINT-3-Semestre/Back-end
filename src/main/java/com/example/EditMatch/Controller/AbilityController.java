package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Ability;
import com.example.EditMatch.Repository.AbilityRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    private AbilityRepository abilityRepository;
    @PostMapping
    public ResponseEntity<Ability> cadastrar(@RequestBody Ability ability){
        return ResponseEntity.of(Optional.of(abilityRepository.save(ability)));
    }
    @PutMapping("/{id}")
    @ApiOperation(value = "Lista ability", notes = "Retorna o ability atualizado")
    public ResponseEntity<Ability> atualizar(@PathVariable int id,
                                            @RequestBody Ability abilityAtualizado){
        List<Ability> ability = abilityRepository.findAll();
        ability.set(id, abilityAtualizado);
        return ResponseEntity.ok(abilityAtualizado);
    }
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta ability", notes = "asdasd")
    public ResponseEntity<Ability> deletar(@PathVariable int id){
        List<Ability> ability = abilityRepository.findAll();
        ability.remove(id);
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/{desc}")
    @ApiOperation(value = "Busca ability", notes = "Retorna o ability buscado")
    public ResponseEntity<Ability> buscar(@PathVariable String desc){
        List<Ability> ability1 = abilityRepository.findByDesc(desc);
        if(ability1.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok((Ability) ability1);
    }
}