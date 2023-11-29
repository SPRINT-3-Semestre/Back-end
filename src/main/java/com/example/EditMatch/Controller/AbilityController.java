package com.example.EditMatch.Controller;

import com.example.EditMatch.Entity.Ability;
import com.example.EditMatch.Service.AbilityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/abilitys")
@Api(value = "AbilityController", description = "Controladora de ability")
public class AbilityController {

    private final AbilityService abilityService;

    @PostMapping("/{id}")
    public ResponseEntity<List<Ability>> cadastrarHabilidades(@RequestBody List<Ability> habilidades, @PathVariable Integer id) {
        List<Ability> abilities = abilityService.saveAbilitys(habilidades, id);
        return ResponseEntity.ok(abilities);
    }

    @PutMapping("/atualizar/{id}")
    @ApiOperation(value = "Atualiza habilidades", notes = "Retorna as habilidades atualizadas")
    public ResponseEntity<List<Ability>> atualizarHabilidades(@PathVariable int id, @RequestBody List<Ability> habilidadesAtualizadas) {
        List<Ability> abilitiesUpdatedAt = abilityService.updateAbilitys(habilidadesAtualizadas, id);
        return ResponseEntity.ok().body(abilitiesUpdatedAt);
    }

    @DeleteMapping()
    @ApiOperation(value = "Deleta ability", notes = "asdasd")
    public ResponseEntity<Void> deletar(@RequestParam int id){
        abilityService.deleteAbility(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar/{id}")
    @ApiOperation(value = "Busca habilidades por ID de usuário", notes = "Retorna a lista de habilidades de um usuário")
    public ResponseEntity<List<Ability>> buscarHabilidadesPorIdUsuario(@PathVariable int id) {
        List<Ability> listUser = abilityService.findById(id);
        return ResponseEntity.ok().body(listUser);
    }

}