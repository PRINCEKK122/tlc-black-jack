package model;

import enums.*;
import exceptions.EmptyCardException;
import exceptions.PlayerAlreadyExistsException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Game {
    private final List<Card> deckOfCards = new ArrayList<>();
    private List<Player> players = new ArrayList<>();

    private final List<Player> originalPlayers = new ArrayList<>();

    public Game(int numOfPlayers) {
        setNumOfPlayers(numOfPlayers);
    }

    private void setNumOfPlayers(int value) {
        if (value == 0) {
            players = new ArrayList<>(3);
            for (int i = 1; i <= 3; i++)
                players.add(new Player("Player " + i));
            return;
        }

        if (value <= 1 || value > 6)
            throw new IllegalArgumentException("Number of players should be between 2 to 6 inclusive!");
    }

    private boolean gameOver = false;

    public void start() {
        for (CardValue value : CardValue.values()) {
            for (Suit suit : Suit.values()) {
                deckOfCards.add(new Card(suit, value));
            }
        }

        shuffleCards();
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

        drawCard(player);
        drawCard(player);
        players.add(player);
    }

    public void inProgress() {
        isGameOver();
        while (!gameOver) {
            players.stream()
                    .filter(p -> p.getPlayerStrategy().equals(PlayerStrategy.HIT))
                    .forEach(this::drawCard);
            isGameOver();
        }

        printResults();
    }

    private void shuffleCards() {
        Collections.shuffle(deckOfCards);
    }

    private void printResults() {
        originalPlayers.forEach(System.out::println);
        System.out.println("=".repeat(20));

        System.out.println("WINNER(S)");
        System.out.println("=".repeat(20));
        players.forEach(p -> System.out.println(p.getName() + ": " + p.totalCardsValue() + " points."));
    }

    private void drawCard(Player player) {
        if (players.size() <= 1)
            return;
        player.addCard(deckOfCards.remove(deckOfCards.size() - 1));
    }

    private void isGameOver() {
        players.forEach(p -> {
            if (!originalPlayers.contains(p)) {
                originalPlayers.add(p);
            }
        });

        players = players.stream()
                .filter(p -> !(p.getPlayerStrategy().equals(PlayerStrategy.GO_BUST)))
                .collect(Collectors.toList());

        if (players.size() == 1) {
            gameOver = true;
            return;
        }

        Predicate<Player> winners = p -> p.getPlayerStrategy().equals(PlayerStrategy.WINNER);
        var winner = players.stream().anyMatch(winners);
        if (winner) {
            players = players.stream().filter(winners).collect(Collectors.toList());
            gameOver = true;
            return;
        }

        var allStick = players.stream().allMatch(p -> p.getPlayerStrategy().equals(PlayerStrategy.STICK));

        if (allStick) {
            var highest = players.stream().mapToInt(Player::totalCardsValue).max().orElse(0);
            players = players.stream()
                    .filter(p -> p.totalCardsValue() == highest)
                    .collect(Collectors.toList());
            gameOver = true;
        }
    }
}
