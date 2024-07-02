import enums.*;
import model.Card;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Collection<Card> cards = new ArrayList<>();
        for (CardValue value : CardValue.values()) {
            for (Suit suit : Suit.values()) {
                cards.add(new Card(suit, value));
            }
        }

        for (Card card : cards) {
            System.out.println(card);
        }

        System.out.println(cards.size());
    }
}
