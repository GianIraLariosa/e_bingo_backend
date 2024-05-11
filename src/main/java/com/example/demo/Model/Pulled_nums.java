package com.example.demo.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pulled_nums {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pulled_nums_id;

    @Column(name = "game_code")
    private String gamecode;

    private int bingo_num;
    private boolean in_card;
    private boolean picked;

    public Pulled_nums(){

    }

    public Pulled_nums(String game_code, int bingo_num, boolean in_card, boolean picked) {
        this.gamecode = game_code;
        this.bingo_num = bingo_num;
        this.in_card = in_card;
        this.picked = picked;
    }


    public Long getPulledNumsId() {
        return this.pulled_nums_id;
    }

    public void setPulledNumsId(Long pulledNumsId) {
        this.pulled_nums_id = pulledNumsId;
    }

    public String getGameCode() {
        return gamecode;
    }

    public void setGameCode(String gameCode) {
        this.gamecode = gameCode;
    }

    public int getBingoNum() {
        return bingo_num;
    }

    public void setBingoNum(int bingoNum) {
        this.bingo_num = bingoNum;
    }

    public boolean isInCard() {
        return in_card;
    }

    public void setInCard(boolean inCard) {
        this.in_card = inCard;
    }

    public boolean isPicked() {
        return picked;
    }

    public void setPicked(boolean picked) {
        this.picked = picked;
    }
}
