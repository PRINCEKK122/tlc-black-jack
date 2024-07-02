package model;

import java.util.ArrayList;
import java.util.Collection;

public class Player {
    private final String name;
    private final Collection<Card> cards;

    public Player(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int totalCardsValue() {
        return cards.stream()
                .mapToInt(Card::getCardValue)
                .reduce(Integer::sum)
                .orElse(0);
    }

    @Override
    public String toString() {
        return name + cards;
    }
}
