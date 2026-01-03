package com.nataliogomes.jobsearchrest.repositories;


import com.nataliogomes.jobsearchrest.models.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<JobPost, Integer> {
}
