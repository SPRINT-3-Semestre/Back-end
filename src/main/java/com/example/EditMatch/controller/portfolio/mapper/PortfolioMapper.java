package com.example.EditMatch.controller.portfolio.mapper;

import com.example.EditMatch.controller.portfolio.dto.PortfolioCreateDto;
import com.example.EditMatch.controller.portfolio.dto.PortfolioResponseDto;
import com.example.EditMatch.entity.Portfolio;
import com.example.EditMatch.entity.Usuario;

public class PortfolioMapper {
    public static Portfolio of(PortfolioCreateDto portfolioCreateDto){
        Portfolio portfolio = new Portfolio();
        // Mapeie os valores do DTO para o objeto Portfolio, se necessário

        return portfolio;
    }

    public static PortfolioResponseDto of(Portfolio portfolio, Usuario usuario) {
        PortfolioResponseDto portfolioResponseDto = new PortfolioResponseDto();

        portfolioResponseDto.setEditorId(portfolio.getEditor().getId());
        portfolioResponseDto.setNomeEditor(portfolio.getEditor().getNome());
        portfolioResponseDto.setPhotoProfileData(portfolio.getEditor().getPhotoProfileData());
        portfolioResponseDto.setTitle(portfolio.getTitle());
        portfolioResponseDto.setValor(portfolio.getEditor().getValorHora());
        portfolioResponseDto.setLinkYtVideoId(usuario.getLinkYtVideoId());

        return portfolioResponseDto;
    }
}
