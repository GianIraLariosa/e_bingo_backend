package com.example.demo.Controller;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.http.MediaType;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Service.Bingo_cardService;
import com.example.demo.Service.GameService;
import com.example.demo.Model.Bingo_card;
import com.example.demo.Model.Card;
import com.example.demo.Model.Game;
import com.example.demo.Model.Pulled_nums;
import com.example.demo.Repositories.Pulled_numsRepository;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @Autowired
    private Pulled_numsRepository pulled_numsRepository;

    @Autowired
    private Bingo_cardService bingo_cardService;

    @PostMapping("/add_game")
    public Game addGame(){
        return gameService.addGame();
    }

    @PostMapping("/next_ball")
    public ResponseEntity<Integer> getNextBall(@RequestParam String game_code){
        ArrayList<Pulled_nums> availableNums = pulled_numsRepository.findBygamecodeAndPickedFalse(game_code);

        if(availableNums.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        Random random = new Random();
        Pulled_nums selectedNumber = availableNums.get(random.nextInt(availableNums.size()));

        selectedNumber.setPicked(true);
        pulled_numsRepository.save(selectedNumber);

        return ResponseEntity.ok(selectedNumber.getBingoNum());
    }

    @PostMapping(value = "/add_card", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Card> generateBingoCard(@RequestParam String game_code) {
        Card bingoCard = bingo_cardService.add_bingo_card(game_code);
        return new ResponseEntity<>(bingoCard, HttpStatus.OK);
    }

    @GetMapping("/check_win")
    public ResponseEntity<Boolean> check_win(@RequestParam String playcardToken) {
        boolean cards = bingo_cardService.checkBingo(playcardToken);
        return ResponseEntity.ok(cards);
    }
}
