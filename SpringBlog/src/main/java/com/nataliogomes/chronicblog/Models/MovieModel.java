package com.nataliogomes.chronicblog.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "movies")  // Explicitly naming the table
@AllArgsConstructor
@NoArgsConstructor
public class MovieModel {

    @ManyToOne
    @JsonBackReference(value = "movie-owner")
    @JoinColumn(name = "owner_id", nullable = false)
    private AccountModel owner;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", length = 200, nullable = false)
    private String title;

    @Column(name = "author", length = 100, nullable = false)
    private String author;

    @Column(name = "publisher", length = 100)
    private String publisher;

    @Column(name = "publication_date")
    private LocalDate publicationDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private MovieGenres genre;  // Assuming MovieGenres is an enum

    @Enumerated(EnumType.STRING)  // Keeping this if Format is an enum
    @Column(name = "format")
    private Format format;

    @Column(name = "isbn", length = 13, unique = true)
    private String isbn;

    @Column(name = "pages")
    private Integer pages;

    // Removing @Enumerated since language is a String
    @Column(name = "language")
    private String language;

    @Column(name = "summary", columnDefinition = "TEXT")
    private String summary;

    @Column(name = "cover_image_path")
    private String coverImagePath;

    @Column(name = "rating", precision = 3, scale = 1)
    private BigDecimal rating;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum Format {
        DVD, BLU_RAY, DIGITAL
    }
}
