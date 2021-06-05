package br.com.alelo.controller.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
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
public class StudentDTO {

    @NotBlank( message = "{required.field}" )
    @Size(max = 20)
    private String name;
    
    @NotBlank( message = "{required.field}" )
    private String email;
    
    private int years;
    
    private String cpf;
    
    private List<BookDTO> books;
    
}
