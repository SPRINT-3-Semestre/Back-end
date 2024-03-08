package com.example.EditMatch.controller.portfolio.mapper;

import com.example.EditMatch.controller.portfolio.dto.PortfolioCreateDto;
import com.example.EditMatch.controller.portfolio.dto.PortfolioResponseDto;
import com.example.EditMatch.entity.Portfolio;

public class PortfolioMapper {
    public static Portfolio of(PortfolioCreateDto portfolioCreateDto){
        Portfolio portfolio = new Portfolio();

        return portfolio;
    }

    public static PortfolioResponseDto of(Portfolio portfolio) {
        PortfolioResponseDto portfolioResponseDto = new PortfolioResponseDto();

        portfolioResponseDto.setEditorId(portfolio.getEditor().getId());
        portfolioResponseDto.setNomeEditor(portfolio.getEditor().getNome());
        portfolioResponseDto.setPhotoProfileData(portfolio.getEditor().getPhotoProfileData());
        portfolioResponseDto.setTitle(portfolio.getTitle());

        return portfolioResponseDto;
    }
}
