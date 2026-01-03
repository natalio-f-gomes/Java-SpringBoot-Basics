package com.nataliogomes.chronicblog.Controlllers;

import com.nataliogomes.chronicblog.Models.MovieModel;
import com.nataliogomes.chronicblog.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<List<MovieModel>> getAllMovies() {
        List<MovieModel> movies = movieService.getAllMovies();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<MovieModel> getMovieById(@PathVariable int id) {
        Optional<MovieModel> movie = movieService.getMovieById(id);
        return movie.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/saveMovie")
    public ResponseEntity<MovieModel> saveMovie(@RequestBody MovieModel movie) {
        MovieModel savedMovie = movieService.saveMovie(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

    @PutMapping("movie/{id}")
    public ResponseEntity<MovieModel> updateMovie(@PathVariable int id, @RequestBody MovieModel movie) {
        Optional<MovieModel> existingMovie = movieService.getMovieById(id);
        if (existingMovie.isPresent()) {
            movie.setId( id);
            MovieModel updatedMovie = movieService.saveMovie(movie);
            return new ResponseEntity<>(updatedMovie, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("deleteMovie/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id) {
        Optional<MovieModel> movie = movieService.getMovieById(id);
        if (movie.isPresent()) {
            movieService.deleteMovie(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<MovieModel>> findMoviesByTitle(@RequestParam String title) {
        List<MovieModel> movies = movieService.findMoviesByTitle(title);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/search/author")
    public ResponseEntity<List<MovieModel>> findMoviesByAuthor(@RequestParam String author) {
        List<MovieModel> movies = movieService.findMoviesByAuthor(author);
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }


}
