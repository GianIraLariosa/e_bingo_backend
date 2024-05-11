package com.example.demo.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Bingo_card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bingo_card_id;
    private int B;
    private int I;
    private int N;
    private int G;
    private int O;
    
    @Column(name = "game_code")
    private String gamecode;

    @Column(name = "playcard_token")
    private String playcardtoken;
    private int bingo_card_count;

    public Bingo_card(){

    }

    public Bingo_card(int B, int I, int N, int G, int O, String gamecode, String playcard_token, int bingo_card_count){
        this.B = B;
        this.I = I;
        this.N = N;
        this.G = G;
        this.O = O;
        this.gamecode = gamecode;
        this.playcardtoken = playcard_token;
        this.bingo_card_count = bingo_card_count;
    }

    public Long getBingo_card_id() {
        return bingo_card_id;
    }

    public int getB() {
        return B;
    }

    public int getI() {
        return I;
    }

    public int getN() {
        return N;
    }

    public int getG() {
        return G;
    }

    public int getO() {
        return O;
    }

    public String getGamecode() {
        return gamecode;
    }

    public String getPlaycard_token() {
        return playcardtoken;
    }

    public int getBingo_card_count() {
        return bingo_card_count;
    }

    public void setB(int b) {
        B = b;
    }

    public void setI(int i) {
        I = i;
    }

    public void setN(int n) {
        N = n;
    }

    public void setG(int g) {
        G = g;
    }

    public void setO(int o) {
        O = o;
    }

    public void setGamecode(String gamecode) {
        this.gamecode = gamecode;
    }

    public void setPlaycard_token(String playcard_token) {
        this.playcardtoken = playcard_token;
    }
    
    public void setBingo_card_count(int bingo_card_count) {
        this.bingo_card_count = bingo_card_count;
    }
}
