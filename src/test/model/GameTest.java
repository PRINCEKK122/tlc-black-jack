package model;

import exceptions.PlayerAlreadyExistsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    void testAddPlayerDoesNotThrowExceptionWhenNewPlayerIsAdded() {
        assertDoesNotThrow(() -> {
            var game = new Game();
            game.start();
            game.addPlayer(new Player("p1"));
            game.addPlayer(new Player("p2"));
            game.addPlayer(new Player("p3"));
        });
    }

    @Test
    void testAddPlayerThrowsAnExceptionIfPlayerAlreadyInGame() {
        assertAll(() -> {
            var message = assertThrows(PlayerAlreadyExistsException.class, () -> {
                var game = new Game();
                game.addPlayer(new Player("p1"));
                game.addPlayer(new Player("p1"));
            });

            assertEquals("Player already exists!", message.getMessage());
            assertThrows(PlayerAlreadyExistsException.class, () -> {
                var game = new Game();
                game.addPlayer(new Player("p2"));
                game.addPlayer(new Player("p2"));
            });
        });

    }
}