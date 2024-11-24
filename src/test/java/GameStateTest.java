import com.t03g06.model.GameState;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    @Test
    void testInitialization() {
        GameState gameState = new GameState();
        assertNotNull(gameState.getBird());
        assertEquals(10, gameState.getPipes().size()); // inicializa o jogo com 10 canos
        assertEquals(0, gameState.getScore());
        assertFalse(gameState.isGameOver());
    }

    @Test
    void testJumpBird() {
        GameState gameState = new GameState();
        gameState.jumpBird();
        assertFalse(gameState.isGameOver());
        assertTrue(gameState.getBird().getY() < GameState.getHeight());
    }

    @Test
    void testAddNewPipe() {
        GameState gameState = new GameState();
        int initialSize = gameState.getPipes().size();
        gameState.addNewPipe();
        assertEquals(initialSize + 1, gameState.getPipes().size());
    }

    @Test
    void testUpdateGameCollision() {
        // TODO, testar o jogo no caso de ocorrer colisão
    }

    @Test
    void testUpdateGameWithoutCollision() {
        GameState gameState = new GameState();
        gameState.getBird().applyGravity();
        gameState.updateGame();
        assertFalse(gameState.isGameOver());
    }

    @Test
    void testResetGame() {
        GameState gameState = new GameState();
        gameState.jumpBird();
        gameState.updateGame();
        gameState.resetGame();
        assertEquals(0, gameState.getScore());
        assertFalse(gameState.isGameOver());
        assertEquals(10, gameState.getPipes().size());
    }
}
