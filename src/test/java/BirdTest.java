import com.t03g06.model.Bird;

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
        bird.jump();
        bird.reset();
        Assertions.assertEquals(10, bird.getY());
    }

    @Test
    public void testJump() {
        bird.jump();
        Assertions.assertEquals(7, bird.getY());
    }

    @Test
    public void testApplyGravity() {
        bird.applyGravity();
        Assertions.assertEquals(11, bird.getY());
    }
}
