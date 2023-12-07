package com.example.EditMatch.controller.portfolio;

import com.example.EditMatch.controller.portfolio.dto.PortfolioCreateDto;
import com.example.EditMatch.controller.portfolio.dto.PortfolioResponseDto;
import com.example.EditMatch.controller.portfolio.mapper.PortfolioMapper;
import com.example.EditMatch.entity.Portifolio;
import com.example.EditMatch.service.portfolio.PortfolioService;
import com.example.EditMatch.service.YouTubeService;
import com.example.EditMatch.service.portfolio.exception.PortfolioException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/portfolios")
public class PortfolioController {
    @Autowired
    private PortfolioService portfolioService;
    @Autowired
    private YouTubeService youTubeService;

    @GetMapping("/editor/{editorId}")
    public ResponseEntity<Portifolio> getPortfolioByEditorId(@PathVariable Integer editorId) {
        try {
            Portifolio portfolio = portfolioService.getPortfolioByEditorId(editorId);
            return ResponseEntity.ok(portfolio);
        } catch (PortfolioException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
    @GetMapping("/{portfolioId}")
    public ResponseEntity<PortfolioResponseDto> getPortfolioById(@PathVariable Integer portfolioId) {
        Portifolio portfolio = portfolioService.getPortfolioById(portfolioId);
        PortfolioResponseDto portfolioResponseDto = PortfolioMapper.of(portfolio);
        return ResponseEntity.ok(portfolioResponseDto);
    }
    @PostMapping
    public ResponseEntity<PortfolioResponseDto> add(@Valid @RequestBody PortfolioCreateDto portfolioCreateDto){
        Portifolio add = portfolioService.add(portfolioCreateDto);
        PortfolioResponseDto portfolioResponseDto = PortfolioMapper.of(add);
        return ResponseEntity.ok(portfolioResponseDto);
    }
    @PutMapping("/{portfolioId}")
    public ResponseEntity<PortfolioResponseDto> updatePortfolio(@PathVariable Integer portfolioId,
                                            @RequestBody PortfolioCreateDto updatedPortfolioDto) {
        Portifolio updatedPortfolio = portfolioService.updatePortfolio(portfolioId, updatedPortfolioDto);
        PortfolioResponseDto portfolioResponseDto = PortfolioMapper.of(updatedPortfolio);
        return ResponseEntity.ok(portfolioResponseDto);
    }
    @DeleteMapping("/{portfolioId}")
    public ResponseEntity<Void> deletePortfolio(@PathVariable Integer portfolioId) {
        portfolioService.deletePortfolio(portfolioId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/videos-youtube")
    public Portifolio createPortfolioYt(@RequestBody Portifolio portifolio) throws IOException {
        String videoId = portifolio.getLinkYtVideoId();
        String embedUrl = youTubeService.getVideoEmbedUrl(videoId);
        portifolio.setLinkYtVideoId(embedUrl);

        return portfolioService.savePortfolio(portifolio);
    }
}
