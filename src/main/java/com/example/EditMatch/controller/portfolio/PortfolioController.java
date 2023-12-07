package com.example.EditMatch.controller.portfolio;

import com.example.EditMatch.controller.portfolio.dto.PortfolioCreateDto;
import com.example.EditMatch.controller.portfolio.dto.PortfolioResponseDto;
import com.example.EditMatch.controller.portfolio.mapper.PortfolioMapper;
import com.example.EditMatch.entity.Portifolio;
import com.example.EditMatch.service.portfolio.PortifolioService;
import com.example.EditMatch.service.YouTubeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/portfolios")
public class PortfolioController {
    @Autowired
    private PortifolioService portifolioService;
    @Autowired
    private YouTubeService youTubeService;

    @GetMapping("/{portfolioId}")
    public ResponseEntity<PortfolioResponseDto> getPortfolioById(@PathVariable Integer portfolioId) {
        Portifolio portfolio = portifolioService.getPortfolioById(portfolioId);
        PortfolioResponseDto portfolioResponseDto = PortfolioMapper.of(portfolio);
        return ResponseEntity.ok(portfolioResponseDto);
    }
    @PostMapping
    public ResponseEntity<PortfolioResponseDto> add(@Valid @RequestBody PortfolioCreateDto portfolioCreateDto){
        Portifolio add = portifolioService.add(portfolioCreateDto);
        PortfolioResponseDto portfolioResponseDto = PortfolioMapper.of(add);
        return ResponseEntity.ok(portfolioResponseDto);
    }
    @PutMapping("/{portfolioId}")
    public ResponseEntity<PortfolioResponseDto> updatePortfolio(@PathVariable Integer portfolioId,
                                            @RequestBody PortfolioCreateDto updatedPortfolioDto) {
        Portifolio updatedPortfolio = portifolioService.updatePortfolio(portfolioId, updatedPortfolioDto);
        PortfolioResponseDto portfolioResponseDto = PortfolioMapper.of(updatedPortfolio);
        return ResponseEntity.ok(portfolioResponseDto);
    }
    @DeleteMapping("/{portfolioId}")
    public ResponseEntity<Void> deletePortfolio(@PathVariable Integer portfolioId) {
        portifolioService.deletePortfolio(portfolioId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/videos-youtube")
    public Portifolio createPortfolioYt(@RequestBody Portifolio portifolio) throws IOException {
        String videoId = portifolio.getLinkYtVideoId();
        String embedUrl = youTubeService.getVideoEmbedUrl(videoId);
        portifolio.setLinkYtVideoId(embedUrl);

        return portifolioService.savePortfolio(portifolio);
    }
}
