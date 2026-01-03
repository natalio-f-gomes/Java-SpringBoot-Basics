package com.nataliogomes.jobsearchrest.services;


import com.nataliogomes.jobsearchrest.models.JobPost;
import com.nataliogomes.jobsearchrest.repositories.JobRepo;
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
public class JobService {
    @Autowired
    private JobRepo jobRepo;

    public void addJob(JobPost jobPost){
        jobRepo.save(jobPost);
    }

    public List<JobPost> findall() {
        return  jobRepo.findAll();
    }

    public Optional<JobPost> findById(int id) {
        return jobRepo.findById(id);
    }


    public void deleteById(int id) {
        jobRepo.deleteById(id);
    }

    public void save(JobPost jobPost) {
        jobRepo.save(jobPost);
    }
}
