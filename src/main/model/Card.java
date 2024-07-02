package model;

import enums.*;

public class Card {
    private final Suit suit;
    private final CardValue value;

    public Card(Suit suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }
}
