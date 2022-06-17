package io.spring.start.projeto.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.sql.Date;

//validação das constrants
@Data
public class ClientDto {
    //tem que ter dependencia de validação!!!!!! sory
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private Date birth_date;
}