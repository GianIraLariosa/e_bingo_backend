package com.example.demo.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "game")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long game_id;

    @Column(name = "game_code")
    private String gamecode;


    public Game(){

    }

    public Game(Long game_id, String game_code){
        this.gamecode = game_code;
    }

    public void setgame_id(Long game_id){
        this.game_id = game_id;
    }

    public void setgame_code(String game_code){
        this.gamecode = game_code;
    }

    public Long getgame_id(){
        return this.game_id;
    }

    public String getgame_code(){
        return this.gamecode;
    }
}
