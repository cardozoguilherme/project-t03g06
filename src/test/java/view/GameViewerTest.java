package view;

import java.util.List;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.model.GameModel;
import com.t03g06.model.entities.Bird;
import com.t03g06.model.managers.CoinManager;
import com.t03g06.model.managers.PipeManager;
import com.t03g06.model.managers.SpeedModifierManager;
import com.t03g06.view.GameViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GameViewerTest {

    private TextGraphics tg;
    private GameViewer gameViewer;
    private Bird birdMock;
    private CoinManager coinManagerMock;
    private PipeManager pipeManagerMock;
    private SpeedModifierManager speedModifierManagerMock;

    @BeforeEach
    void setUp() {
        GameModel modelMock = mock(GameModel.class);
        tg = mock(TextGraphics.class);

        birdMock = mock(Bird.class);
        coinManagerMock = mock(CoinManager.class);
        pipeManagerMock = mock(PipeManager.class);
        speedModifierManagerMock = mock(SpeedModifierManager.class);

        when(modelMock.getBird()).thenReturn(birdMock);
        when(modelMock.getCoinManager()).thenReturn(coinManagerMock);
        when(modelMock.getPipeManager()).thenReturn(pipeManagerMock);
        when(modelMock.getSpeedModifierManager()).thenReturn(speedModifierManagerMock);
        when(modelMock.getScore()).thenReturn(42);

        // Mocka os retornos de listas vazias para evitar NullPointerExceptions
        when(coinManagerMock.getCoins()).thenReturn(List.of());
        when(pipeManagerMock.getPipes()).thenReturn(List.of());
        when(speedModifierManagerMock.getSpeedModifiers()).thenReturn(List.of());

        gameViewer = new GameViewer(modelMock, tg);
    }


    @Test
    void drawTest() {
        // Configura mocks para o `Bird` e outros objetos
        when(birdMock.getY()).thenReturn(10); // Retorna uma posição válida

        when(coinManagerMock.getCoins()).thenReturn(List.of()); // Lista vazia de moedas
        when(pipeManagerMock.getPipes()).thenReturn(List.of()); // Lista vazia de canos
        when(speedModifierManagerMock.getSpeedModifiers()).thenReturn(List.of()); // Lista vazia de modificadores de velocidade

        // Executa o método draw()
        gameViewer.draw();

        // Verifica chamadas para TextGraphics
        verify(tg).setBackgroundColor(TextColor.ANSI.BLUE);
        verify(tg).fill(' ');

        // Verifica o score desenhado
        verify(tg).setForegroundColor(TextColor.ANSI.WHITE);
        verify(tg).putString(2, 1, "SCORE: 42");

        // Verifica que não houve interações adicionais com TextGraphics
        verifyNoMoreInteractions(tg);
    }


}
