package com.example.EditMatch.controller.carteira;

import com.example.EditMatch.controller.carteira.dto.CarteiraResponseDto;
import com.example.EditMatch.controller.carteira.mapper.CarteiraMapper;
import com.example.EditMatch.entity.Usuario;
import com.example.EditMatch.entity.carteira.Carteira;
import com.example.EditMatch.repository.UserRepository;
import com.example.EditMatch.service.carteira.CarteiraService;
import com.example.EditMatch.service.editor.EditorService;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/carteira")
public class CarteiraController {

    @Autowired
    private CarteiraService carteiraService;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<CarteiraResponseDto> findByIdUsuario(@PathVariable Integer idUsuario) {
        Carteira carteira = carteiraService.findByIdUsuario(idUsuario);

        CarteiraMapper carteiraMapper = new CarteiraMapper();
        CarteiraResponseDto carteiraResponseDto = carteiraMapper.of(carteira);

        try {
            return ResponseEntity.ok(carteiraResponseDto);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

