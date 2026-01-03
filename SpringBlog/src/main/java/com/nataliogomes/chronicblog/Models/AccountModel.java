package com.nataliogomes.chronicblog.Models;



import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "account_model")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String email;
    private String password;

    @ManyToOne
    @JsonBackReference(value = "book-owner")
    @JoinColumn(name = "owner_id")
    private AccountModel owner;



    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "movie-owner")
    private List<MovieModel> movies;


}
