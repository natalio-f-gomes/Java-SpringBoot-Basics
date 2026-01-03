package com.nataliogomes.jobsearchrest.Controllers;


import com.nataliogomes.jobsearchrest.models.StudentModel;
import com.nataliogomes.jobsearchrest.services.StudentService;
import jakarta.persistence.Id;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    List<StudentModel> studentList =  new ArrayList<>(List.of(
            new StudentModel(1,"Natalio",22),
            new StudentModel(2,"Jane",44)
            ));

    @GetMapping("student")
    public List<StudentModel> studentList(){
        return studentService.findAll();
    }


    @PostMapping("student")
    public String addStudent(@RequestBody StudentModel studentModel, Principal principal){
        try {
            studentService.save(studentModel);
            return "Student Added successfully";
        }
        catch (Exception e){
            return "There was an error. Description: " + e;
        }
    }

    @GetMapping("gettoken")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    @PutMapping("student/{id}")
    public String updateStudent(@PathVariable("id") int id, @RequestBody StudentModel studentModel, Principal principal){
        try{
            Optional<StudentModel> student = studentService.findById(id);
            if (student.isPresent()){
                studentService.save(studentModel);
                return "Student updated Successfully";
            }
            else{
                return "Student not found";
            }
        }
        catch (Exception e){
            return "There was an error. Description: " + e;
        }

    }

    @GetMapping("student/{id}")
    public Optional<StudentModel> getStudent(@PathVariable("id") int id, Principal principal ){
        Optional<StudentModel> student = studentService.findById(id);
        return student;
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable("id") int id, Principal principal){
        Optional<StudentModel> student = studentService.findById(id);
        if(student.isPresent()){
            studentService.deleteById(id);
            return ResponseEntity.ok("Student deleted successfully");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        }
    }

}


