package model;

import enums.*;
import exceptions.PlayerAlreadyExists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private final List<Card> deckOfCards = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private int cardCounter = 0;

    public Game() {
        start();
        shuffleCards();
    }

    private void start() {
        for (CardValue value : CardValue.values()) {
            for (Suit suit : Suit.values()) {
                deckOfCards.add(new Card(suit, value));
            }
        }
    }

    private void shuffleCards() {
        Collections.shuffle(deckOfCards);
    }

    public void addPlayer(Player player) throws PlayerAlreadyExists {
        var newPlayer = players
                .stream()
                .map(Player::getName)
                .anyMatch(name -> name.equals(player.getName()));

        if (newPlayer) {
            throw new PlayerAlreadyExists("Player already exists!");
        }

        players.add(player);

        for (int i = 1; i < 3; i++) {

        }
    }

    public void serveCard() {

    }
}
