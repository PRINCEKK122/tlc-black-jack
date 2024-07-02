import enums.*;
import model.Card;
import model.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        var p1 = new Player("p1");
        p1.addCard(new Card(Suit.CLUBS, CardValue.EIGHT));
        p1.addCard(new Card(Suit.DIAMONDS, CardValue.SIX));

        System.out.println(p1.totalCardsValue());
    }
}
