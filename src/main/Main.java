import enums.*;
import exceptions.EmptyCardException;
import exceptions.PlayerAlreadyExistsException;
import model.Card;
import model.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.Game;

public class Main {
    public static void main(String[] args) {
        var game = new Game();
        List<Player> players = List.of(
                new Player("p1"),
                new Player("p2"),
                new Player("p3")
//                new Player("p4")
        );
        game.start();

        try {
          for (Player player : players)
              game.addPlayer(player);
          game.inProgress();
        } catch (PlayerAlreadyExistsException | EmptyCardException ex) {
            System.out.println(ex.getMessage());
        }

        System.out.println(game);
    }
}
