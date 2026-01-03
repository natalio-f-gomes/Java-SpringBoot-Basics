package com.nataliogomes.chronicblog.Repositories;

import com.nataliogomes.chronicblog.Models.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieModel, Integer> {
    List<MovieModel> findByTitleContainingIgnoreCase(String title);

    List<MovieModel> findByAuthorContainingIgnoreCase(String author);


}
