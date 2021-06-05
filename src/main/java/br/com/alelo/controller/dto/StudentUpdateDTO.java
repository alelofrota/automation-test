package br.com.alelo.controller.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonInclude( JsonInclude.Include.NON_NULL )
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateDTO {

    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank( message = "{required.field}" )
    @Size(max = 20)
    private String name;
    
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @NotBlank( message = "{required.field}" )
    private String email;
}
