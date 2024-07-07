import exceptions.EmptyCardException;
import exceptions.PlayerAlreadyExistsException;
import model.Player;

import model.Game;

public class blackjack {
    public static void main(String[] args) {
        try {
            var game = new Game(args.length);
            game.start();

            for (String player : args) {
                game.addPlayer(new Player(player));
            }
            game.inProgress();
        } catch (PlayerAlreadyExistsException | EmptyCardException | IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
            System.out.println("Exiting...");
        }
    }
}
