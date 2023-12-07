package com.example.EditMatch.controller.portfolio.mapper;

import com.example.EditMatch.controller.portfolio.dto.PortfolioCreateDto;
import com.example.EditMatch.controller.portfolio.dto.PortfolioResponseDto;
import com.example.EditMatch.entity.Editor;
import com.example.EditMatch.entity.Portifolio;
import com.example.EditMatch.entity.Usuario;
import com.example.EditMatch.repository.UserRepository;

import java.util.Optional;

public class PortfolioMapper {
    public static Portifolio of(PortfolioCreateDto portfolioCreateDto, UserRepository userRepository){
        Portifolio portifolio = new Portifolio();

        portifolio.setLinkYtVideoId(portfolioCreateDto.getLinkYtVideoId());
        portifolio.setTitle(portfolioCreateDto.getTitle());

        Optional<Usuario> editorOpt = userRepository.findById(portfolioCreateDto.getEditorId());
        if (editorOpt.isPresent()) {
            portifolio.setEditor((Editor) editorOpt.get());
        } else {
            throw new RuntimeException("Editor com id " + portfolioCreateDto.getEditorId() + " não encontrado.");
        }

        return portifolio;
    }


    public static PortfolioResponseDto of(Portifolio portifolio) {
        PortfolioResponseDto portfolioResponseDto = new PortfolioResponseDto();

        portfolioResponseDto.setEditorId(portifolio.getEditor().getId());
        portfolioResponseDto.setNomeEditor(portifolio.getEditor().getNome());
        portfolioResponseDto.setPhotoProfileData(portifolio.getEditor().getPhotoProfileData());
        portfolioResponseDto.setTitle(portifolio.getTitle());
        portfolioResponseDto.setSkills(portifolio.getEditor().getSkills());

        return portfolioResponseDto;
    }
}
