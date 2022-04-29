package Menkrep;

import Menkrep.Model.Game.Game;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GameTest {
    private static Game game;

    @BeforeAll
    static void initAll(){
        game = Game.getInstance();
    }

//    @Test
//    @DisplayName("")
}
