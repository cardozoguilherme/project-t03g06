package com.t03g06.view;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.model.GameModel;
import com.t03g06.model.entities.Bird;
import com.t03g06.model.managers.CoinManager;
import com.t03g06.model.managers.PipeManager;
import com.t03g06.model.managers.SpeedModifierManager;
import com.t03g06.view.entities.BirdViewer;
import com.t03g06.view.entities.CoinViewer;
import com.t03g06.view.entities.PipeViewer;
import com.t03g06.view.entities.SpeedModifierViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class GameViewerTest {
    private TextGraphics tg;
    private GameViewer gameViewer;
    private BirdViewer birdViewerMock;
    private CoinViewer coinViewerMock;
    private PipeViewer pipeViewerMock;
    private SpeedModifierViewer speedModifierViewerMock;

    @BeforeEach
    void setUp() {
        GameModel modelMock = mock(GameModel.class);
        tg = mock(TextGraphics.class);

        birdViewerMock = mock(BirdViewer.class);
        coinViewerMock = mock(CoinViewer.class);
        pipeViewerMock = mock(PipeViewer.class);
        speedModifierViewerMock = mock(SpeedModifierViewer.class);

        Bird birdMock = mock(Bird.class);
        CoinManager coinManagerMock = mock(CoinManager.class);
        PipeManager pipeManagerMock = mock(PipeManager.class);
        SpeedModifierManager speedModifierManagerMock = mock(SpeedModifierManager.class);

        when(modelMock.getBird()).thenReturn(birdMock);
        when(modelMock.getCoinManager()).thenReturn(coinManagerMock);
        when(modelMock.getPipeManager()).thenReturn(pipeManagerMock);
        when(modelMock.getSpeedModifierManager()).thenReturn(speedModifierManagerMock);
        when(modelMock.getScore()).thenReturn(42);

        when(birdMock.getY()).thenReturn(10);

        when(coinManagerMock.getCoins()).thenReturn(List.of());
        when(pipeManagerMock.getPipes()).thenReturn(List.of());
        when(speedModifierManagerMock.getSpeedModifiers()).thenReturn(List.of());

        gameViewer = new GameViewer(modelMock, tg, birdViewerMock, coinViewerMock, pipeViewerMock, speedModifierViewerMock);
    }

    @Test
    void drawTest() {
        gameViewer.draw();

        verify(tg, atLeastOnce()).fill(' ');
        verify(tg, atLeast(1)).setBackgroundColor(TextColor.ANSI.BLUE);

        verify(birdViewerMock).draw();
        verify(coinViewerMock).draw();
        verify(pipeViewerMock).draw();
        verify(speedModifierViewerMock).draw();

        verify(tg).setForegroundColor(TextColor.ANSI.WHITE);
        verify(tg).putString(2, 1, "SCORE: 42");
    }
}