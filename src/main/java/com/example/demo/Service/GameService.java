package com.example.demo.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repositories.GameRepository;
import com.example.demo.Repositories.Pulled_numsRepository;
import com.example.demo.Model.Game;
import com.example.demo.Model.Pulled_nums;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private Pulled_numsRepository pulled_numsRepository;

    public Game addGame(){
        String gameCode = null;
        boolean isUnique = false;
        while (!isUnique) {
            gameCode = generateRandomGameCode();
            if(!gameRepository.existsBygamecode(gameCode)){
                isUnique = true;
            }
        }
        Game game = new Game();
        game.setgame_code(gameCode);
        gameRepository.save(game);

        for(int i = 1; i < 76; i++){
            Pulled_nums pulled_nums = new Pulled_nums();
            pulled_nums.setGameCode(game.getgame_code());
            pulled_nums.setBingoNum(i);
            pulled_nums.setInCard(false);
            pulled_nums.setPicked(false);
            pulled_numsRepository.save(pulled_nums);
        }
        
        return game;
    }

    private String generateRandomGameCode(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(8);
        Random random = new Random();
        for(int i = 0; i < 8; i++){
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }
}
