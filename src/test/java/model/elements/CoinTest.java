package model.elements;

import com.t03g06.model.elements.Coin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CoinTest {
    private Coin coin;

    @BeforeEach
    void setUp() {
        coin = new Coin(100, 50);
    }

    @Test
    void testMoveLeft() {
        coin.moveLeft(10);
        assertEquals(90, coin.getX());
    }

    @Test
    void testMoveYMovingDown() {
        final int maxY = 200;
        coin.moveY(maxY);
        assertEquals(51, coin.getY());
    }

    @Test
    void testMoveYDirectionChange() {
    }


    @Test
    void testIsOutOfScreen() {
        final int coinWidth = 2;

        assertFalse(coin.isOutOfScreen());

        coin.moveLeft(coin.getX() + coinWidth + 1);
        assertTrue(coin.isOutOfScreen());
    }
}