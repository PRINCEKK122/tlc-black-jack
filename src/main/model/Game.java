package model;

import enums.*;
import exceptions.EmptyCardException;
import exceptions.PlayerAlreadyExistsException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {
    private final List<Card> deckOfCards = new ArrayList<>();
    private List<Player> players = new ArrayList<>();

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

    @Override
    public String toString() {
        return players.toString();
    }
}
