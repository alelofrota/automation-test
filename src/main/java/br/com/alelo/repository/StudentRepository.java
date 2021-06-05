package br.com.alelo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alelo.domain.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findByCpf(String cpf);
}
