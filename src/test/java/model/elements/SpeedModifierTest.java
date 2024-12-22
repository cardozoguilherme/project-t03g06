package model.elements;

import com.t03g06.model.entities.SpeedModifier;
import com.t03g06.model.GameConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpeedModifierTest {

    private SpeedModifier speedModifier;

    @BeforeEach
    void setUp() {
        speedModifier = new SpeedModifier(50, 10);
    }

    @Test
    void testMoveLeft() {
        int initialX = speedModifier.getX();
        int speedModifierSpeed = 5;

        speedModifier.moveLeft(speedModifierSpeed);

        assertEquals(initialX - speedModifierSpeed, speedModifier.getX());
    }

    @Test
    void testMoveYMovesDown() {
        int initialY = speedModifier.getY();
        int maxY = GameConstants.HEIGHT;

        speedModifier.moveY(maxY);

        assertEquals(initialY + 1, speedModifier.getY());
    }

    @Test
    void testMoveYChangesDirectionAtMaxY() {
        int maxY = GameConstants.HEIGHT;
        speedModifier = new SpeedModifier(50, maxY - GameConstants.SPEED_MODIFIER_HEIGHT - GameConstants.SPEED_MODIFIER_MARGIN);

        speedModifier.moveY(maxY);

        assertFalse(speedModifier.isMovingDown());
    }

    @Test
    void testMoveYMovesUp() {
        int initialY = GameConstants.SPEED_MODIFIER_HEIGHT + 10;

        speedModifier = new SpeedModifier(50, initialY);

        speedModifier.setMovingDown(false);

        speedModifier.moveY(GameConstants.HEIGHT);

        assertEquals(initialY - 1, speedModifier.getY());
    }



    @Test
    void testMoveYChangesDirectionAtMinY() {

        speedModifier = new SpeedModifier(50, GameConstants.SPEED_MODIFIER_HEIGHT);

        speedModifier.moveY(40);

        assertTrue(speedModifier.isMovingDown());
    }

    @Test
    void testIsOutOfScreen() {
        speedModifier = new SpeedModifier(-GameConstants.SPEED_MODIFIER_WIDTH - 1, 10);

        assertTrue(speedModifier.isOutOfScreen());
    }



}