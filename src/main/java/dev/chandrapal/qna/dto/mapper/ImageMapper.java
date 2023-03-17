package dev.chandrapal.qna.dto.mapper;

import org.mapstruct.Mapper;

import dev.chandrapal.qna.dto.ImageDto;
import dev.chandrapal.qna.entities.Image;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    Image toImage(ImageDto imageDto);

    ImageDto toImageDto(Image image);

}
