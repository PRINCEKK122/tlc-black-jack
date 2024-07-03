package model;

import enums.PlayerStrategy;

import java.util.ArrayList;
import java.util.Collection;

public class Player {
    private final String name;
    private final Collection<Card> cards;
    private PlayerStrategy strategy;

    public Player(String name) {
        this.name = name;
        cards = new ArrayList<>();
        strategy = PlayerStrategy.HIT;
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        cards.add(card);
        getPlayerStrategy();
    }

    public int totalCardsValue() {
        return cards.stream()
                .mapToInt(Card::getCardValue)
                .reduce(Integer::sum)
                .orElse(0);
    }

    public PlayerStrategy getPlayerStrategy() {
        int total = totalCardsValue();

        if (total >= PlayerStrategy.GO_BUST.getPoints())
            strategy = PlayerStrategy.GO_BUST;
        else if (total == PlayerStrategy.WINNER.getPoints())
            strategy = PlayerStrategy.WINNER;
        else if (total >= PlayerStrategy.STICK.getPoints())
            strategy = PlayerStrategy.STICK;

        return strategy;
    }

    @Override
    public String toString() {
        return "\n\t" + name + ": " + totalCardsValue()
                + ", strategy: " + getPlayerStrategy()
                + ", points ==> " + cards;
    }
}
