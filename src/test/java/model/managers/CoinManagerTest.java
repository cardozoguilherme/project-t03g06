package model.managers;

import com.t03g06.model.managers.CoinManager;

import com.t03g06.model.GameConstants;
import com.t03g06.model.elements.Coin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CoinManagerTest {

    private CoinManager coinManager;

    @BeforeEach
    void setUp() {
        coinManager = new CoinManager();
    }

    @Test
    void testAddCoin() {
        coinManager.addCoin(100, 200);
        coinManager.addCoin(300, 400);

        List<Coin> coins = coinManager.getCoins();

        assertEquals(2, coins.size());
        assertEquals(100, coins.get(0).getX());
        assertEquals(200, coins.get(0).getY());
        assertEquals(300, coins.get(1).getX());
        assertEquals(400, coins.get(1).getY());
    }

    @Test
    void testReset() {
        coinManager.addCoin(100, 200);
        coinManager.addCoin(300, 400);

        coinManager.reset();

        assertTrue(coinManager.getCoins().isEmpty());
    }

    @Test
    void testUpdateMovesCoins() {
        Coin coinMock = mock(Coin.class);
        coinManager.getCoins().add(coinMock);

        int coinSpeed = 5;

        coinManager.update(coinSpeed);

        verify(coinMock, times(1)).moveLeft(coinSpeed);
        verify(coinMock, times(1)).moveY(GameConstants.HEIGHT);
    }

    @Test
    void testUpdateRemovesOutOfScreenCoins() {
        Coin coinMock1 = mock(Coin.class);
        Coin coinMock2 = mock(Coin.class);

        when(coinMock1.isOutOfScreen()).thenReturn(false);
        when(coinMock2.isOutOfScreen()).thenReturn(true);

        coinManager.getCoins().add(coinMock1);
        coinManager.getCoins().add(coinMock2);

        coinManager.update(5);

        List<Coin> coins = coinManager.getCoins();

        assertEquals(1, coins.size());
        assertSame(coinMock1, coins.get(0));
    }

}
