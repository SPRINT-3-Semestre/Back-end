package com.example.EditMatch.controller.portfolio;

import com.example.EditMatch.controller.portfolio.dto.PortfolioCreateDto;
import com.example.EditMatch.controller.portfolio.dto.PortfolioResponseDto;
import com.example.EditMatch.controller.portfolio.mapper.PortfolioMapper;
import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.entity.Portfolio;
import com.example.EditMatch.repository.UserRepository;
import com.example.EditMatch.repository.UsuarioRepositoryJWT;
import com.example.EditMatch.service.portfolio.PortfolioService;
import com.example.EditMatch.service.YouTubeService;
import com.example.EditMatch.service.portfolio.exception.PortfolioException;
import com.example.EditMatch.service.usuario.UsuarioService;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portfolios")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;

    @Autowired
    private UserRepository userRepository;
    @GetMapping("/{id}")
    public ResponseEntity<PortfolioResponseDto> findPortfolioByEditorId(@PathVariable Integer id) {



        PortfolioResponseDto portfolioResponseDto = PortfolioMapper.of(portfolioService.findPortfolioByEditorId(id), userRepository.findById(id).orElseThrow(() -> new PortfolioException("Usuário não encontrado")));
        return ResponseEntity.ok(portfolioResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Portfolio> updatePortfolio(@PathVariable Integer id, @RequestBody @Valid PortfolioCreateDto portfolioCreateDto) {
        Portfolio portfolio = portfolioService.updatePortfolio(id, portfolioCreateDto);
        return ResponseEntity.ok(portfolio);
    }
}
