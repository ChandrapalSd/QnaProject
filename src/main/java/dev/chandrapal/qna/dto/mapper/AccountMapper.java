package dev.chandrapal.qna.dto.mapper;

//import org.mapstruct.*;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import dev.chandrapal.qna.dto.AccountDto;
import dev.chandrapal.qna.dto.AccountPostDto;
import dev.chandrapal.qna.dto.QuestionDto;
import dev.chandrapal.qna.entities.Account;
import dev.chandrapal.qna.entities.Question;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class AccountMapper {

	
	public final ModelMapper mapper = new ModelMapper();;
	
    
    public AccountDto toAccountDto(Account account) {
    	return mapper.map(account, AccountDto.class);
    }

    
    public QuestionDto toQuestionDto(Question question) {
    	return mapper.map(question, QuestionDto.class);
    }

    
    public Account toAccount(AccountDto accountDto) {
    	return mapper.map(accountDto, Account.class);
    }

    
    public Account postDtoToAccount(AccountPostDto accountPostDto) {
    	return mapper.map(accountPostDto, Account.class);
    }

}
