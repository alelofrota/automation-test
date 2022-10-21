package br.com.alelo.alelo.repository;

import br.com.alelo.alelo.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByCpf(String cpf);
}
