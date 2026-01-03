package com.nataliogomes.jobsearchrest.repositories;


import com.nataliogomes.jobsearchrest.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentModel, Integer> {
}
