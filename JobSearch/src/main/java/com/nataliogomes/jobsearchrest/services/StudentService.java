package com.nataliogomes.jobsearchrest.services;


import com.nataliogomes.jobsearchrest.models.StudentModel;
import com.nataliogomes.jobsearchrest.repositories.StudentRepo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public List<StudentModel> findAll() {
        return studentRepo.findAll();
    }

    public void save(StudentModel studentModel) {
        studentRepo.save(studentModel);
    }

    public Optional<StudentModel> findById(int id) {
        return studentRepo.findById(id);
    }

    public void deleteById(int id) {
        studentRepo.deleteById(id);
    }
}
