package br.com.alelo.service;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alelo.adapter.StudentAdapter;
import br.com.alelo.controller.dto.StudentDTO;
import br.com.alelo.controller.dto.StudentUpdateDTO;
import br.com.alelo.domain.Student;
import br.com.alelo.enums.ExceptionsMessagesEnum;
import br.com.alelo.exception.BadRequest;
import br.com.alelo.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private StudentAdapter studentAdapter;
    
    public StudentDTO save(StudentDTO studentDTO) {
        
        BadRequest.checkThrow( Objects.nonNull( studentRepository.findByCpf( studentDTO.getCpf() ) ),
                ExceptionsMessagesEnum.CPF_ALREADY_REGISTERED );
        
        Student student = studentRepository.save( studentAdapter.studentDtoToEntity( studentDTO ) );
        
        return studentAdapter.studentEntityToDto( student );
    }
    
    public StudentDTO update(String cpf, StudentUpdateDTO studentUpdateDTO) {
        
        Student student = studentRepository.findByCpf( cpf );
        
        BadRequest.checkThrow( Objects.isNull( student ),
                ExceptionsMessagesEnum.CPF_NOT_FOUND );
        
        Student studentUpdate = studentRepository.saveAndFlush( mountUpdate( student, studentUpdateDTO ) );
        
        return studentAdapter.studentEntityToDto( studentUpdate );
    }
    
    private Student mountUpdate(Student student, StudentUpdateDTO studentUpdateDTO) {
        
        student.setName( studentUpdateDTO.getName() );
        student.setEmail( studentUpdateDTO.getEmail() );
        return student;
    }
}
