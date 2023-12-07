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
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;
    private final UserRepository userRepository;

    public Portifolio getPortfolioByEditorId(Integer editorId) {
        Optional<Usuario> editorOptional = userRepository.findById(editorId);

        if (editorOptional.isEmpty()) {
            throw new PortfolioException("Editor não encontrado");
        }

        Usuario editor = editorOptional.get();
        return portfolioRepository.findByEditor(editor)
                .orElseThrow(() -> new PortfolioException("Portfólio não encontrado para o editor especificado"));
    }

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

        existingPortfolio.setLinkYtVideoId(updatedPortfolioDto.getLinkYtVideoId());
        existingPortfolio.setTitle(updatedPortfolioDto.getTitle());

        return portfolioRepository.save(existingPortfolio);
    }

    public void deletePortfolio(Integer portfolioId) {
        if (!portfolioRepository.existsById(portfolioId)) {
            throw new PortfolioException("Portifolio não encontrado");
        }
        portfolioRepository.deleteById(portfolioId);
    }


}
