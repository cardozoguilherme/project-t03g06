import com.t03g06.model.GameConstants;
import com.t03g06.model.GameModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameStateTest {

    private GameModel gameModel;

    @BeforeEach
    public void setUp(){
        gameModel = new GameModel();
    }

    @Test
    void testInitialization() {
        assertNotNull(gameModel.getBird());
        assertEquals(10, gameModel.getPipeManager().getPipes().size()); // inicializa o jogo com 10 canos
        assertEquals(0, gameModel.getScore());
        assertFalse(gameModel.isGameOver());
    }

    @Test
    void testJumpBird() {
        gameModel.jumpBird();
        assertFalse(gameModel.isGameOver());
        assertTrue(gameModel.getBird().getY() < GameConstants.HEIGHT);
    }

    @Test
    void testAddNewPipe() {
        int initialSize = gameModel.getPipes().size();
        gameModel.addNewPipe();
        assertEquals(initialSize + 1, gameModel.getPipes().size());
    }

//    @Test
//    void testUpdateGameCollisionWithPipe() {
//        gameModel.jumpBird();
//        Pipe pipe = new Pipe(GameConstants.getWidth()/4, 10, 5);
//        gameModel.getPipes().clear();
//        gameModel.getPipes().add(pipe);
//        gameModel.getBird().setY(3);
//        gameModel.updateGame();
//        assertTrue(gameModel.isGameOver());
//    }


//    @Test
//    void testUpdateGameCollisionWithGround() {
//        gameModel.jumpBird();
//        while(gameModel.getBird().getY()< GameConstants.getHeight()-1){
//            gameModel.updateGame();
//        }
//        gameModel.updateGame();
//        assertTrue((gameModel.isGameOver()));
//    }

    @Test
    void testUpdateGameCollisionWithCeiling() {
        gameModel.jumpBird();
        while(gameModel.getBird().getY()>0){
            gameModel.getBird().jump(GameConstants.JUMP_HEIGHT);
            gameModel.updateGame();}
        gameModel.updateGame();
        assertTrue(gameModel.isGameOver());
    }

    @Test
    void testUpdateGameWithoutCollision() {
        gameModel.getBird().applyGravity(GameConstants.GRAVITY);
        gameModel.updateGame();
        assertFalse(gameModel.isGameOver());
    }

    @Test
    void testResetGame() {
        gameModel.jumpBird();
        gameModel.updateGame();
        gameModel.resetGame();
        assertEquals(0, gameModel.getScore());
        assertFalse(gameModel.isGameOver());
        assertEquals(10, gameModel.getPipes().size());
    }
}
