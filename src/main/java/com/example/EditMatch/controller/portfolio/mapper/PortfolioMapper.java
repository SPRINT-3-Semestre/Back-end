package com.example.EditMatch.controller.portfolio.mapper;

import com.example.EditMatch.controller.portfolio.dto.PortfolioCreateDto;
import com.example.EditMatch.controller.portfolio.dto.PortfolioResponseDto;
import com.example.EditMatch.entity.Portifolio;

public class PortfolioMapper {
    public static Portifolio of(PortfolioCreateDto portfolioCreateDto){
        Portifolio portifolio = new Portifolio();

        portifolio.setLinkYtVideoId(portfolioCreateDto.getLinkYtVideoId());
        portifolio.setTitle(portfolioCreateDto.getTitle());

        return portifolio;
    }

    public static PortfolioResponseDto of(Portifolio portifolio) {
        PortfolioResponseDto portfolioResponseDto = new PortfolioResponseDto();

        portfolioResponseDto.setEditorId(portifolio.getEditor().getId());
        portfolioResponseDto.setNomeEditor(portifolio.getEditor().getNome());
        portfolioResponseDto.setPhotoProfileData(portifolio.getEditor().getPhotoProfileData());
        portfolioResponseDto.setTitle(portifolio.getTitle());

        return portfolioResponseDto;
    }
}
