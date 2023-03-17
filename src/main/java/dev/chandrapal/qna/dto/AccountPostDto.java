package dev.chandrapal.qna.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountPostDto {

    @NotBlank(message = "Name can't be empty")
    @Size(message = "Name can't be more 50 characters", max = 50)
    private String name;

    @NotBlank(message = "Email can't be empty")
    @Email(message = "Invalid email")
    @Size(message = "Email can't be more 100 characters", max = 100)
    private String email;

    @NotBlank(message = "Password can't be empty")
    @Size(message = "Password can't be less 6 characters", min = 6)
    private String password;

    @NotBlank(message = "Password confirm can't be empty")
    private String passwordConfirm;

}
