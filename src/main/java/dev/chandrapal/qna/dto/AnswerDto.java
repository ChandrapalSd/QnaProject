package dev.chandrapal.qna.dto;

import lombok.*;

import java.util.Set;

import dev.chandrapal.qna.audit.Auditable;
import dev.chandrapal.qna.entities.Account;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDto extends Auditable<Account> {

    private Long id;

    private String content;

    private AccountDto author;

    private Boolean isAccepted;

    private Set<AccountDto> negativeVotes;

    private Set<AccountDto> positiveVotes;

    private QuestionDto question;

}
