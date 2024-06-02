package com.example.testspringboot.mapper;

import com.example.testspringboot.dto.ActeurDto;
import com.example.testspringboot.entity.Acteur;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

public interface ActeurMapper {
    ActeurMapper INSTANCE = Mappers.getMapper(ActeurMapper.class);

    @Mapping(source = "nom", target = "nom", ignore = true)
    @Mapping(source = "prenom", target = "prenom", ignore = true)
    default ActeurDto acteurToActeurDTO(Acteur acteur) {
        if ( acteur == null ) {
            return null;
        }

        ActeurDto acteurDto = new ActeurDto();

        acteurDto.setNom(acteur.getNom());
        acteurDto.setPrenom(acteur.getPrenom());

        return acteurDto;
    }

    @Mapping(source = "nom", target = "nom", ignore = true)
    @Mapping(source = "prenom", target = "prenom", ignore = true)
    default Acteur acteurDtoToActeur(ActeurDto acteurDto) {
        if ( acteurDto == null ) {
            return null;
        }

        Acteur acteur = new Acteur();

        acteur.setNom(acteurDto.getNom());
        acteur.setPrenom(acteurDto.getPrenom());

        return acteur;
    }


}
