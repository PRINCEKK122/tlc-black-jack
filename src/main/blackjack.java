import exceptions.EmptyCardException;
import exceptions.PlayerAlreadyExistsException;
import model.Player;

import java.util.List;
import model.Game;

public class Blackjack {
    public static void main(String[] args) {
        var game = new Game();
//        List<Player> players = List.of(
//                new Player("p1"),
//                new Player("p2"),
//                new Player("p3"),
//                new Player("p4"),
//                new Player("p5")
//        );

        game.start();
        try {
          for (String arg : args) {
              game.addPlayer(new Player(arg));
          }
        } catch (PlayerAlreadyExistsException | EmptyCardException ex) {
            System.out.println(ex.getMessage());
        }


        game.inProgress();
        System.out.println("Arguments......");
        System.out.println(args.length);
    }
}
