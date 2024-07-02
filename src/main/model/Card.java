package model;

import enums.*;

public class Card {
    private final Suit suit;
    private final CardValue value;

    public Card(Suit suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }

    public int getCardValue() {
        return value.getCardValue();
    }

    @Override
    public String toString() {
        String result = " of " + suit;

        if (getCardValue() < 10 || value.equals(CardValue.TEN)) {
            return value.getCardValue() + result;
        }

        return value + result;
    }
}
