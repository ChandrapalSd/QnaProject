package dev.chandrapal.qna.dto.mapper;

import org.mapstruct.Mapper;

import dev.chandrapal.qna.dto.TagDto;
import dev.chandrapal.qna.entities.Tag;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagDto toTagDto(Tag tag);

    Tag toTag(TagDto tagDto);

    Set<TagDto> toTagsDto(Set<Tag> tags);

    Set<Tag> toTags(Set<TagDto> tagsDto);
}
