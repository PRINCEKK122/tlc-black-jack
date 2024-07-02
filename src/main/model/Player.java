package model;

import java.util.ArrayList;
import java.util.Collection;

public class Player {
    private final String name;
    private Collection<Card> cards;

    public Player(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public Collection<Card> getCards() {
        return cards;
    }

    public int totalCardsValue() {
        return cards.stream()
                .mapToInt(Card::getCardValue)
                .reduce(Integer::sum)
                .orElse(0);
    }
}
