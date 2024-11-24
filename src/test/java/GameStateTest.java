import com.t03g06.model.GameState;
import com.t03g06.model.Pipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    private GameState gameState;

    @BeforeEach
    public void setUp(){
        gameState = new GameState();
    }

    @Test
    void testInitialization() {
        assertNotNull(gameState.getBird());
        assertEquals(10, gameState.getPipes().size()); // inicializa o jogo com 10 canos
        assertEquals(0, gameState.getScore());
        assertFalse(gameState.isGameOver());
    }

    @Test
    void testJumpBird() {
        gameState.jumpBird();
        assertFalse(gameState.isGameOver());
        assertTrue(gameState.getBird().getY() < GameState.getHeight());
    }

    @Test
    void testAddNewPipe() {
        int initialSize = gameState.getPipes().size();
        gameState.addNewPipe();
        assertEquals(initialSize + 1, gameState.getPipes().size());
    }

    @Test
    void testUpdateGameCollisionWithPipe() {
        gameState.jumpBird();
        Pipe pipe = new Pipe(GameState.WIDTH/4, 10, 5);
        gameState.getPipes().clear();
        gameState.getPipes().add(pipe);
        gameState.getBird().setY(3);
        gameState.updateGame();
        assertTrue(gameState.isGameOver());
    }


    @Test
    void testUpdateGameCollisionWithGround() {
        gameState.jumpBird();
        while(gameState.getBird().getY()<GameState.getHeight()-1){
            gameState.updateGame();
        }
        gameState.updateGame();
        assertTrue((gameState.isGameOver()));
    }

    @Test
    void testUpdateGameCollisionWithCeiling() {
        gameState.jumpBird();
        while(gameState.getBird().getY()>0){
            gameState.getBird().jump();
            gameState.updateGame();}
        gameState.updateGame();
        assertTrue(gameState.isGameOver());
    }

    @Test
    void testUpdateGameWithoutCollision() {
        gameState.getBird().applyGravity();
        gameState.updateGame();
        assertFalse(gameState.isGameOver());
    }

    @Test
    void testResetGame() {
        gameState.jumpBird();
        gameState.updateGame();
        gameState.resetGame();
        assertEquals(0, gameState.getScore());
        assertFalse(gameState.isGameOver());
        assertEquals(10, gameState.getPipes().size());
    }
}
