package com.example.EditMatch.controller.portfolio.mapper;

import com.example.EditMatch.controller.portfolio.dto.PortfolioCreateDto;
import com.example.EditMatch.controller.portfolio.dto.PortfolioResponseDto;
import com.example.EditMatch.entity.Portifolio;

public class PortfolioMapper {
    public static Portifolio of(PortfolioCreateDto portfolioCreateDto){
        Portifolio portifolio = new Portifolio();

        portifolio.setLinkYtVideoId(portfolioCreateDto.getLinkYtVideoId());
        portifolio.setDesc(portfolioCreateDto.getLinkLinkedin());
        portifolio.setLinkGit(portfolioCreateDto.getLinkGit());
        portifolio.setLinkLinkedin(portfolioCreateDto.getLinkLinkedin());

        return portifolio;
    }

    public static PortfolioResponseDto of(Portifolio portifolio) {
        PortfolioResponseDto portfolioResponseDto = new PortfolioResponseDto();

        portfolioResponseDto.setDesc(portifolio.getDesc());
        portfolioResponseDto.setLinkGit(portifolio.getLinkGit());
        portfolioResponseDto.setLinkLinkedin(portifolio.getLinkLinkedin());
        portfolioResponseDto.setLinkYtVideoId(portifolio.getLinkYtVideoId());
        portfolioResponseDto.setEditorId(portifolio.getEditor().getId());

        return portfolioResponseDto;
    }
}
