package com.example.demo.Repositories;

import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.Pulled_nums;

public interface Pulled_numsRepository extends JpaRepository<Pulled_nums, Long> {
    ArrayList<Pulled_nums> findBygamecodeAndPickedFalse(String game_code);
    ArrayList<Pulled_nums> findBygamecodeAndPickedTrue(String game_code);
}
