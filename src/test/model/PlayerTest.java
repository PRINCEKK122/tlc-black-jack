package model;

import enums.CardValue;
import enums.Suit;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testTotalCardsValue() {
        var player = new Player("p1");
        player.addCard(new Card(Suit.CLUBS, CardValue.FIVE));
        player.addCard(new Card(Suit.CLUBS, CardValue.SIX));
        player.addCard(new Card(Suit.DIAMONDS, CardValue.ACE));

        assertEquals(22, player.totalCardsValue());
    }
}