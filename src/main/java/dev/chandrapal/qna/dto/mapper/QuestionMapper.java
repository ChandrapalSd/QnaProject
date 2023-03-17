package dev.chandrapal.qna.dto.mapper;

import org.mapstruct.*;

import dev.chandrapal.qna.dto.QuestionDto;
import dev.chandrapal.qna.entities.Question;

@Mapper(componentModel = "spring")
public interface QuestionMapper {

    Question toQuestion(QuestionDto questionDto);

    @Mappings({
        @Mapping(target = "author.questions", ignore = true),
        @Mapping(target = "author.answers", ignore = true),
    })
    QuestionDto toQuestionDto(Question question);

}
