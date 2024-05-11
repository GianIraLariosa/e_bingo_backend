package com.example.demo.Model;

import java.util.ArrayList;

public class Card {
    private String playcardtoken;
    private ArrayList<Integer> b;
    private ArrayList<Integer> i;
    private ArrayList<Integer> n;
    private ArrayList<Integer> g;
    private ArrayList<Integer> o;

    public Card(){

    }

    public Card(String playcardtoken, ArrayList<Integer> b, ArrayList<Integer> i, ArrayList<Integer> n, ArrayList<Integer> g, ArrayList<Integer> o){
        this.playcardtoken = playcardtoken;
        this.b = b;
        this.i = i;
        this.n = n;
        this.g = g;
        this.o = o;
    }

    public String getPlaycardtoken() {
        return playcardtoken;
    }

    public ArrayList<Integer> getB() {
        return b;
    }

    public ArrayList<Integer> getG() {
        return g;
    }

    public ArrayList<Integer> getI() {
        return i;
    }

    public ArrayList<Integer> getN() {
        return n;
    }

    public ArrayList<Integer> getO() {
        return o;
    }

    public void setPlaycardtoken(String playcardtoken) {
        this.playcardtoken = playcardtoken;
    }

    public void setB(ArrayList<Integer> b) {
        this.b = b;
    }

    public void setG(ArrayList<Integer> g) {
        this.g = g;
    }

    public void setI(ArrayList<Integer> i) {
        this.i = i;
    }

    public void setN(ArrayList<Integer> n) {
        this.n = n;
    }

    public void setO(ArrayList<Integer> o) {
        this.o = o;
    }
}
