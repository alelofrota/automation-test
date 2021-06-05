package br.com.alelo.controller.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonInclude( JsonInclude.Include.NON_NULL )
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    @JsonProperty("name")
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @Length( max = 20, message = "{invalid.name}" )
    private String name;
    
    @JsonProperty("title")
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @Length( max = 50, message = "{invalid.title}" )
    private String title;
    
    @JsonProperty("author")
    @NotNull(message = "cannot be null")
    @NotEmpty(message = "cannot be empty")
    @Length( max = 20, message = "{invalid.author}" )
    private String author;
    
}
