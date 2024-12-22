package view;

import com.t03g06.view.GameViewer;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.model.GameModel;
import com.t03g06.model.entities.Bird;
import com.t03g06.model.managers.CoinManager;
import com.t03g06.model.managers.PipeManager;
import com.t03g06.model.managers.SpeedModifierManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class GameViewerTest {

    private TextGraphics tg;
    private GameViewer gameViewer;

    @BeforeEach
    void setUp() {
        // Mocks para o modelo e suas dependências
        GameModel model = mock(GameModel.class);
        tg = mock(TextGraphics.class);

        Bird bird = mock(Bird.class);
        CoinManager coinManager = mock(CoinManager.class);
        PipeManager pipeManager = mock(PipeManager.class);
        SpeedModifierManager speedModifierManager = mock(SpeedModifierManager.class);

        // Configura os mocks para retornar dependências simuladas
        when(model.getBird()).thenReturn(bird);
        when(model.getCoinManager()).thenReturn(coinManager);
        when(model.getPipeManager()).thenReturn(pipeManager);
        when(model.getSpeedModifierManager()).thenReturn(speedModifierManager);
        when(model.getScore()).thenReturn(42);

        // Cria a instância do GameViewer
        gameViewer = new GameViewer(model, tg);
    }

    @Test
    void drawTest() {
        // Chama o método draw()
        gameViewer.draw();

        // Verifica se o fundo foi desenhado
        verify(tg).setBackgroundColor(TextColor.ANSI.BLUE);
        verify(tg).fill(' ');

        // Verifica se o score foi desenhado
        verify(tg).setForegroundColor(TextColor.ANSI.WHITE);
        verify(tg).putString(2, 1, "SCORE: 42");
    }
}