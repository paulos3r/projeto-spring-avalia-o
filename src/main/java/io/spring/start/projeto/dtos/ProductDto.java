package io.spring.start.projeto.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class ProductDto {

    @NotBlank
    @NotEmpty
    @NotNull
    private String name;
    @NotBlank
    private String description;
    @NotBlank
    @NotEmpty
    @NotNull
    private String price;
}