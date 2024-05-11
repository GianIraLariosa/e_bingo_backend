package com.example.demo.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.Model.Game;

public interface GameRepository extends JpaRepository<Game, Long>{
    boolean existsBygamecode(String gameCode);
}
