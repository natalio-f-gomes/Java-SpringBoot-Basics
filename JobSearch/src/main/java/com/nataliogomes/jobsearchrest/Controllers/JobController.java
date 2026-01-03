package com.nataliogomes.jobsearchrest.Controllers;


import com.nataliogomes.jobsearchrest.models.JobPost;
import com.nataliogomes.jobsearchrest.services.JobService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;


@RestController
public class JobController {

    @Autowired
    private JobService jobService;

    private JobPost jobPost;


    @GetMapping({"job"})
    public List<JobPost> home(HttpServletRequest request){

        return (jobService.findall());
    }

    @PostMapping("/job")
    public void postJob(@RequestBody JobPost jobPost, Principal principal){
        jobService.addJob(jobPost);

    }

    @DeleteMapping("/job/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable("id") int id, Principal principal) {
        Optional<JobPost> job = jobService.findById(id);

        if (job.isPresent()) {
            jobService.deleteById(id);

            // Confirm deletion
            Optional<JobPost> checkDeleted = jobService.findById(id);
            if (checkDeleted.isEmpty()) {
                return ResponseEntity.ok("Job deleted successfully");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Failed to delete the job.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Job not found");
        }
    }

    @PutMapping("job/{id}")
    public ResponseEntity<String> updateJob(@PathVariable("id") int id, @RequestBody JobPost jobPost, Principal principal){
        Optional<JobPost> job = jobService.findById(id);
        if (job.isPresent()){
            jobService.save(jobPost);
            return ResponseEntity.ok("Job updated successfully");
        }else{
            return ResponseEntity.ok("Job not found");
        }
    }

    @GetMapping("job/{id}")
    public Optional<JobPost> getJob(@PathVariable("id") int id, @RequestBody JobPost jobPost, Principal principal){
        Optional<JobPost> job = jobService.findById(id);
        if (job.isPresent()){
            return Optional.ofNullable(jobPost);
        }else{
            return Optional.empty();
        }
    }
}
