package br.com.alelo.controller;

import java.util.List;

import javax.validation.Valid;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alelo.adapter.StudentAdapter;
import br.com.alelo.controller.dto.StudentDTO;
import br.com.alelo.controller.dto.StudentUpdateDTO;
import br.com.alelo.repository.StudentRepository;
import br.com.alelo.service.StudentService;
import io.swagger.annotations.ApiParam;

@RestController()
@RequestMapping("/students")
public class StudentController implements StudentDefinition {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentAdapter studentAdapter;

    @Autowired
    private StudentService studentService;

    @GetMapping
    @Override
    public ResponseEntity<List<StudentDTO>> getAll() {

        return new ResponseEntity<>( studentAdapter.studentEntityToDtoForList( studentRepository.findAll() ),
                HttpStatus.OK );
    }

    @PostMapping
    @Override
    public ResponseEntity<StudentDTO> create( @RequestBody @Valid StudentDTO studentDTO ) {

        return new ResponseEntity<>( studentService.save( studentDTO ), HttpStatus.CREATED );
    }
    
    @PatchMapping( value = "/{cpf}")
    @Override
    public ResponseEntity<StudentDTO> update( @PathVariable @CPF String cpf, @Valid @RequestBody @ApiParam( name = "student", required = true ) StudentUpdateDTO studentUpdateDTO ) {

        return new ResponseEntity<>( studentService.update( cpf, studentUpdateDTO), HttpStatus.OK );
    }

}
