package view.elements;

import com.t03g06.model.elements.Coin;
import com.t03g06.view.elements.CoinViewer;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class CoinViewerTest {
    private TextGraphics tg;
    private CoinViewer coinViewer;
    private List<Coin> coins;

    @BeforeEach
    void setUp() {
        tg = mock(TextGraphics.class);

        coins = Arrays.asList(
                new Coin(5, 10),
                new Coin(15, 20)
        );

        coinViewer = new CoinViewer(coins, tg);
    }

    @Test
    void testDraw() {
        coinViewer.draw();

        verify(tg).setBackgroundColor(TextColor.ANSI.YELLOW_BRIGHT);

        for (Coin coin : coins) {
            verify(tg).putString(coin.getX(), coin.getY(), "  ");
            verify(tg).putString(coin.getX(), coin.getY() + 1, "  ");
        }

        verify(tg).setBackgroundColor(TextColor.ANSI.BLUE);
    }
}
