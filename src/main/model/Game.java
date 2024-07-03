package model;

import enums.*;
import exceptions.EmptyCardException;
import exceptions.PlayerAlreadyExistsException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Game {
    private final List<Card> deckOfCards = new ArrayList<>();
    private List<Player> players = new ArrayList<>();
    private Collection<Player> duplicatePlayers = new ArrayList<>();

    private boolean gameOver = false;

    public void start() {
        for (CardValue value : CardValue.values()) {
            for (Suit suit : Suit.values()) {
                deckOfCards.add(new Card(suit, value));
            }
        }

        shuffleCards();
    }

    private void shuffleCards() {
        Collections.shuffle(deckOfCards);
    }

    public void addPlayer(Player player) throws
            PlayerAlreadyExistsException, EmptyCardException {
        var newPlayer = players
                .stream()
                .map(Player::getName)
                .anyMatch(name -> name.equals(player.getName()));

        if (newPlayer)
            throw new PlayerAlreadyExistsException("Player already exists!");

        if (deckOfCards.isEmpty())
            throw new EmptyCardException("Deck of cards is empty!");

        player.addCard(deckOfCards.remove(deckOfCards.size() - 1));
        player.addCard(deckOfCards.remove(deckOfCards.size() - 1));
        players.add(player);
    }

    public void inProgress() {
        duplicatePlayers = players.stream().toList();

        isGameOver();
        while (!gameOver) {
            players.stream()
                    .filter(p -> p.getPlayerStrategy().equals(PlayerStrategy.HIT))
                    .forEach(this::drawCard);

            isGameOver();
        }
        determineWinner();
    }

    private void determineWinner() {
        System.out.println("Winner(s): " + players.toString());
    }

    private void drawCard(Player player) {
        player.addCard(deckOfCards.remove(deckOfCards.size() - 1));
    }

    private void isGameOver() {
        var winner = players.stream().anyMatch(p -> p.getPlayerStrategy().equals(PlayerStrategy.WINNER));
        if (winner) {
           gameOver = true;
           return;
       }

        var allStick = players.stream().allMatch(p -> p.getPlayerStrategy().equals(PlayerStrategy.STICK));
        if (allStick) {
            gameOver = true;
            return;
       }

           players = players.stream()
                   .filter(p -> !(p.getPlayerStrategy().equals(PlayerStrategy.GO_BUST)))
                   .collect(Collectors.toList());

           if (players.size() == 1) gameOver = true;

//           inProgress();
    }

    @Override
    public String toString() {
        return duplicatePlayers.toString();
    }
}
