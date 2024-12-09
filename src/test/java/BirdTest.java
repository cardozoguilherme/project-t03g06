import com.t03g06.model.Bird;

import com.t03g06.model.GameModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BirdTest {

    private Bird bird;

    @BeforeEach
    public void setUp() {
        bird = new Bird(10);
    }

    @Test
    public void testGetY() {
        Assertions.assertEquals(10, bird.getY());
    }

    @Test
    public void testReset() {
        bird.jump(GameModel.JUMP_HEIGHT);
        bird.reset();
        Assertions.assertEquals(10, bird.getY());
    }

    @Test
    public void testJump() {
        bird.jump(GameModel.JUMP_HEIGHT);
        Assertions.assertEquals(7, bird.getY());
    }

    @Test
    public void testApplyGravity() {
        bird.applyGravity(GameModel.GRAVITY);
        Assertions.assertEquals(11, bird.getY());
    }

    @Test
    public void TestSetY(){
        bird.setY(18);
        Assertions.assertEquals(18,bird.getY());
    }
}
