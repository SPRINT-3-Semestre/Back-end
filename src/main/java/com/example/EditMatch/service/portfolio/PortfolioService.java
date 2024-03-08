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

        // Salve o portfólio para obter o ID atribuído pelo banco de dados
        Portfolio savedPortfolio = portfolioRepository.save(portfolio);

        // Atualize o ID do editor no portfólio
        savedPortfolio.setEditor(editor);

        // Salve novamente o portfólio para garantir que o ID do editor esteja configurado corretamente
        return portfolioRepository.save(savedPortfolio);
    }

    public Portfolio findPortfolioByEditorId(Integer id) {
        return portfolioRepository.findByEditorId(id);
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
