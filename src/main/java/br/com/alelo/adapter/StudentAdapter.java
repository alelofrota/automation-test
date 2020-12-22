package br.com.alelo.adapter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.alelo.controller.dto.StudentDTO;
import br.com.alelo.domain.Student;

@Component
public class StudentAdapter {

    @Autowired
    private BookAdapter bookAdapter;
    
    public Student studentDtoToEntity(StudentDTO studentDTO) {
        
        return Student.builder()
                .name( studentDTO.getName() )
                .email( studentDTO.getEmail() )
                .years( studentDTO.getYears() )
                .cpf( studentDTO.getCpf() )
                .books( bookAdapter.bookDtoToEntity( studentDTO.getBooks() ) )
                .build();
    }
    
    public StudentDTO studentEntityToDto(Student student) {
        
        return StudentDTO.builder()
                .name( student.getName() )
                .email( student.getEmail() )
                .years( student.getYears() )
                .cpf( student.getCpf() )
                .books( bookAdapter.bookEntityToDto( student.getBooks() ) )
                .build();
    }

    public List<StudentDTO> studentEntityToDtoForList( List<Student> students ) {

        List<StudentDTO> listStudentsDto = new ArrayList<>();

        if (students == null) {
            return listStudentsDto;
        }

        students.forEach( student -> {

            StudentDTO studentDTO = StudentDTO.builder()
                    .name( student.getName() )
                    .email( student.getEmail() )
                    .years( student.getYears() )
                    .cpf( student.getCpf() )
                    .books( bookAdapter.bookEntityToDto( student.getBooks() ) )
                    .build();

            listStudentsDto.add( studentDTO );
        } );

        return listStudentsDto;
    }
}
