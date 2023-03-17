package dev.chandrapal.qna.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

import dev.chandrapal.qna.audit.Auditable;
import dev.chandrapal.qna.entities.Account;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto extends Auditable<Account> {

    private Long id;

    private String title;

    private String body;

    private Set<TagDto> tags;

    private Set<AccountDto> positiveVotes;

    private Set<AccountDto> negativeVotes;

    @NotNull(message = "Account NOT NULL")
    private AccountDto author;

    private List<AnswerDto> answers;

}
