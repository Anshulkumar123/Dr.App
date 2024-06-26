package com.practo.rpository;

import com.practo.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
