package io.spring.start.projeto.dtos;

import io.spring.start.projeto.model.ClientModel;
import io.spring.start.projeto.model.ProductModel;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Data
public class SalesDto {
    @NotBlank
    private UUID client;
    @NotBlank
    private UUID product;
    @NotBlank
    private String  amount;
}