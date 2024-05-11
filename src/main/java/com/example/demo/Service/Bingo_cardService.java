package com.example.demo.Service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Bingo_card;
import com.example.demo.Model.Card;
import com.example.demo.Model.Pulled_nums;
import com.example.demo.Repositories.Bingo_cardRepository;
import com.example.demo.Repositories.Pulled_numsRepository;

@Service
public class Bingo_cardService {
    @Autowired
    private Bingo_cardRepository bingo_cardRepository;

    @Autowired
    private Pulled_numsRepository pulled_numsRepository;

    public Card add_bingo_card(String gamecode){
        ArrayList<Bingo_card> bingocards = new ArrayList<>();
        int bingo_card_count = (int)(bingo_cardRepository.countBygamecode(gamecode) / 5) + 1;
        String cardToken = generateRandomToken();

        ArrayList<Integer> B = new ArrayList<>();
        ArrayList<Integer> I = new ArrayList<>();
        ArrayList<Integer> N = new ArrayList<>();
        ArrayList<Integer> G = new ArrayList<>();
        ArrayList<Integer> O = new ArrayList<>();
        ArrayList<Integer> numbers = generateUniqueNumbers();
        
        // for(int i = 0; i < 5; i++){
        //     Bingo_card bingo_card = new Bingo_card();
        //     bingo_card.setB(numbers.get(i));
        //     B.add(bingo_card.getB());

        //     bingo_card.setI(numbers.get(i + 1));
        //     I.add(bingo_card.getI());

        //     bingo_card.setN(numbers.get(i + 2));
        //     N.add(bingo_card.getN());
            
        //     bingo_card.setG(numbers.get(i + 3));
        //     G.add(bingo_card.getG());

        //     bingo_card.setO(numbers.get(i + 4));
        //     O.add(bingo_card.getO());

        //     bingo_card.setGamecode(gamecode);

        //     bingo_card.setPlaycard_token(cardToken);
        //     bingo_card.setBingo_card_count(bingo_card_count);
        //     bingo_cardRepository.save(bingo_card);
        //     bingocards.add(bingo_card);
        // }

        int i = 0;
        while(i < 25){
            Bingo_card bingo_card = new Bingo_card();

            bingo_card.setB(numbers.get(i));
            B.add(bingo_card.getB());

            i++;

            bingo_card.setI(numbers.get(i));
            I.add(bingo_card.getI());

            i++;

            bingo_card.setN(numbers.get(i));
            N.add(bingo_card.getN());

            i++;
            
            bingo_card.setG(numbers.get(i));
            G.add(bingo_card.getG());

            i++;

            bingo_card.setO(numbers.get(i));
            O.add(bingo_card.getO());

            i++;

            bingo_card.setGamecode(gamecode);

            bingo_card.setPlaycard_token(cardToken);
            bingo_card.setBingo_card_count(bingo_card_count);
            bingo_cardRepository.save(bingo_card);
            bingocards.add(bingo_card);

        }

        Card card = new Card(cardToken, B, I, N, G, O);


        return card;
    }

    // private boolean isCardExists(String gamecode, ArrayList<Integer> numbers) {
    //     ArrayList<Bingo_card> existingCards = bingo_cardRepository.findbygamecode(gamecode);
    //     for(Bingo_card card: existingCards){
    //         if(car)
    //     }
    // }

    private String generateRandomToken() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(16);
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    private ArrayList<Integer> generateUniqueNumbers() {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 75; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return new ArrayList<>(numbers.subList(0, 25));
    }

    public boolean checkBingo(String playcardToken) {
        ArrayList<Bingo_card> bingoCardrows = bingo_cardRepository.findByplaycardtoken(playcardToken);

        ArrayList<Pulled_nums> pickedNumbers = pulled_numsRepository.findBygamecodeAndPickedTrue(bingoCardrows.get(0).getGamecode());
        ArrayList<Integer> pickednums = new ArrayList<>();

        for (Pulled_nums pulled_nums : pickedNumbers) {
            pickednums.add(pulled_nums.getBingoNum());
        }

        ArrayList<Integer> B = new ArrayList<>();
        ArrayList<Integer> I = new ArrayList<>();
        ArrayList<Integer> N = new ArrayList<>();
        ArrayList<Integer> G = new ArrayList<>();
        ArrayList<Integer> O = new ArrayList<>();

        for(Bingo_card bingo : bingoCardrows){
            B.add(bingo.getB());
            I.add(bingo.getI());
            N.add(bingo.getN());
            G.add(bingo.getG());
            O.add(bingo.getO());
        }

        Card card = new Card(playcardToken, B, I, N, G, O);



        if (pickedNumbers.isEmpty() || bingoCardrows == null) {
            // return false; // No picked numbers or no bingo card found
        }

        int[][] cardNumbers = new int[5][5];

        for (int i = 0; i < 5; i++) {
            cardNumbers[i][0] = card.getB().get(i);
            cardNumbers[i][1] = card.getI().get(i);
            cardNumbers[i][2] = card.getN().get(i);
            cardNumbers[i][3] = card.getG().get(i);
            cardNumbers[i][4] = card.getO().get(i);
        }

        // return checkBingoWin(cardNumbers, pickedNumbers);
        return checkBingoWin(cardNumbers, pickednums);
    }

    private boolean checkBingoWin(int[][] cardNumbers, ArrayList<Integer> pickedNumbers) {
        // Check horizontal wins
        for (int row = 0; row < 5; row++) {
            boolean win = true;
            for (int col = 0; col < 5; col++) {
                System.out.println(cardNumbers[row][col]);
                if (!pickedNumbers.contains(cardNumbers[row][col])) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        // Check vertical wins
        for (int col = 0; col < 5; col++) {
            boolean win = true;
            for (int row = 0; row < 5; row++) {
                if (!pickedNumbers.contains(cardNumbers[row][col])) {
                    win = false;
                    break;
                }
            }
            if (win) return true;
        }

        // Check diagonal wins (top-left to bottom-right)
        boolean diagonalWin = true;
        for (int i = 0; i < 5; i++) {
            if (!pickedNumbers.contains(cardNumbers[i][i])) {
                diagonalWin = false;
                break;
            }
        }
        if (diagonalWin) return true;

        // Check diagonal wins (top-right to bottom-left)
        diagonalWin = true;
        for (int i = 0; i < 5; i++) {
            if (!pickedNumbers.contains(cardNumbers[i][4 - i])) {
                diagonalWin = false;
                break;
            }
        }
        if (diagonalWin) return true;

        return false;
    }
    
}
