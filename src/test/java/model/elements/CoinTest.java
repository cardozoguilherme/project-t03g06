package model.elements;

import com.t03g06.model.GameConstants;
import com.t03g06.model.elements.Coin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CoinTest {
    private Coin coin;

    @BeforeEach
    void setUp() {
        coin = new Coin(50, 10);
    }

    @Test
    void testMoveLeft() {
        int initialX = coin.getX();
        int coinSpeed = 5;

        coin.moveLeft(coinSpeed);

        assertEquals(initialX - coinSpeed, coin.getX());
    }

    @Test
    void testMoveYMovesDown() {
        int initialY = coin.getY();
        int maxY = GameConstants.HEIGHT;

        coin.moveY(maxY);

        assertEquals(initialY + 1, coin.getY());
    }

    @Test
    void testMoveYChangesDirectionAtMaxY() {
        int maxY = GameConstants.HEIGHT;
        coin = new Coin(50, maxY - GameConstants.COIN_HEIGHT - GameConstants.COIN_MARGIN);

        coin.moveY(maxY);

        assertFalse(coin.isMovingDown());
    }

    @Test
    void testMoveYMovesUp() {
        int initialY = GameConstants.COIN_HEIGHT + 10;

        coin = new Coin(50, initialY);

        coin.setMovingDown(false);

        coin.moveY(GameConstants.HEIGHT);

        assertEquals(initialY - 1, coin.getY());
    }

    @Test
    void testMoveYChangesDirectionAtMinY() {

        coin = new Coin(50, GameConstants.COIN_HEIGHT);

        coin.moveY(40);

        assertTrue(coin.isMovingDown());
    }


    @Test
    void testIsOutOfScreen() {
        final int coinWidth = 2;

        assertFalse(coin.isOutOfScreen());

        coin.moveLeft(coin.getX() + coinWidth + 1);
        assertTrue(coin.isOutOfScreen());
    }
}