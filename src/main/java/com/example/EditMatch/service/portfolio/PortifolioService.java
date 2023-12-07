package com.example.EditMatch.service.portfolio;

import com.example.EditMatch.controller.portfolio.dto.PortfolioCreateDto;
import com.example.EditMatch.controller.portfolio.mapper.PortfolioMapper;
import com.example.EditMatch.entity.*;
import com.example.EditMatch.repository.PortfolioRepository;
import com.example.EditMatch.repository.UserRepository;
import com.example.EditMatch.service.portfolio.exception.PortfolioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortifolioService {
    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;

    public Portifolio add(PortfolioCreateDto portfolioCreateDto) {
        Optional<Usuario> isEditor = userRepository.findById(portfolioCreateDto.getEditorId());

        if(isEditor.isEmpty()) {
            throw new PortfolioException("Editor não encontrado");
        }
        Usuario usuario = isEditor.get();
        Portifolio portifolio = PortfolioMapper.of(portfolioCreateDto);
        portifolio.setEditor(usuario);

        return portfolioRepository.save(portifolio);
    }

    public Portifolio savePortfolio(Portifolio portifolio) {
        return portfolioRepository.save(portifolio);
    }

    public Portifolio getPortfolioById(Integer portfolioId) {
        return portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new PortfolioException("Portifolio não encontrado"));
    }

    public Portifolio updatePortfolio(Integer portfolioId, PortfolioCreateDto updatedPortfolioDto) {
        Portifolio existingPortfolio = portfolioRepository.findById(portfolioId)
                .orElseThrow(() -> new PortfolioException("Portifolio não encontrado"));

        existingPortfolio.setDesc(updatedPortfolioDto.getDesc());
        existingPortfolio.setLinkYtVideoId(updatedPortfolioDto.getLinkYtVideoId());
        existingPortfolio.setLinkGit(updatedPortfolioDto.getLinkGit());
        existingPortfolio.setLinkLinkedin(updatedPortfolioDto.getLinkLinkedin());

        return portfolioRepository.save(existingPortfolio);
    }

    public void deletePortfolio(Integer portfolioId) {
        if (!portfolioRepository.existsById(portfolioId)) {
            throw new PortfolioException("Portifolio não encontrado");
        }
        portfolioRepository.deleteById(portfolioId);
    }


}
