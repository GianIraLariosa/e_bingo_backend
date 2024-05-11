package com.example.demo.Repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Bingo_card;

public interface Bingo_cardRepository extends JpaRepository<Bingo_card, Long>  {
    int countBygamecode(String gamecode);
    ArrayList<Bingo_card> findByplaycardtoken(String token);
}
