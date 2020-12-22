package br.com.alelo.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.alelo.controller.dto.StudentDTO;
import br.com.alelo.controller.dto.StudentUpdateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags={ "Student" })
public interface StudentDefinition {
    
    @ApiOperation(value = "getAll", nickname = "getAll", notes = "getAll student")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error") })
    ResponseEntity<List<StudentDTO>> getAll();

    @ApiOperation(value = "create", nickname = "create", notes = "crated student")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Created"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 500, message = "Internal server error") })
    ResponseEntity<StudentDTO> create( @Valid @RequestBody StudentDTO studentDTO );
    
    @ApiOperation(value = "update", nickname = "update", notes = "crated student")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not fount"),
            @ApiResponse(code = 500, message = "Internal server error") })
    ResponseEntity<StudentDTO> update( @PathVariable @CPF String cpf, @Valid @RequestBody @ApiParam( name = "student", required = true ) StudentUpdateDTO studentUpdateDTO );
}
