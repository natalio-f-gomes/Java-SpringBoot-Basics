package com.nataliogomes.chronicblog.Services;

import com.nataliogomes.chronicblog.Models.AccountModel;
import com.nataliogomes.chronicblog.Models.MovieModel;
import com.nataliogomes.chronicblog.Repositories.AccountRepository;
import com.nataliogomes.chronicblog.Repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<MovieModel> getAllMovies() {
        return movieRepository.findAll();
    }

    public Optional<MovieModel> getMovieById(int id) {
        return movieRepository.findById(id);
    }

    public MovieModel saveMovie(MovieModel movie) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        AccountModel owner = accountRepository.findByUsername(username);

        if (owner == null) {
            throw new RuntimeException("User not found");
        }

        movie.setOwner(owner);
        return movieRepository.save(movie);
    }

    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }

    public List<MovieModel> findMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<MovieModel> findMoviesByAuthor(String author) {
        return movieRepository.findByAuthorContainingIgnoreCase(author);
    }



}





