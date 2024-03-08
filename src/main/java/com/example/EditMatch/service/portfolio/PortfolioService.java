package com.example.EditMatch.service.portfolio;

import com.example.EditMatch.controller.portfolio.dto.PortfolioCreateDto;
import com.example.EditMatch.controller.portfolio.mapper.PortfolioMapper;
import com.example.EditMatch.entity.*;
import com.example.EditMatch.repository.PortfolioRepository;
import com.example.EditMatch.service.portfolio.exception.PortfolioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PortfolioService {
    private final PortfolioRepository portfolioRepository;

    public Portfolio adicionar(PortfolioCreateDto portfolioCreateDto) {
        Usuario editor = portfolioCreateDto.getEditor();
        Portfolio portfolio = PortfolioMapper.of(portfolioCreateDto);
        portfolio.setEditor(editor);

        Portfolio savedPortfolio = portfolioRepository.save(portfolio);

        savedPortfolio.setEditor(editor);

        return portfolioRepository.save(savedPortfolio);
    }

    public Portfolio findPortfolioByEditorId(Integer id) {
        return portfolioRepository.findById(id).orElse(null);
    }

    public Portfolio updatePortfolio(Integer id, PortfolioCreateDto portfolioCreateDto) {

        if(portfolioRepository.findByEditorId(id) == null){
            throw new PortfolioException("Portfólio não encontrado");
        }

        Portfolio portfolio = PortfolioMapper.of(portfolioCreateDto);
        portfolio.setId(id);
        return portfolioRepository.save(portfolio);
    }

}
