package com.t03g06.model;

import com.t03g06.model.entities.Bird;
import com.t03g06.model.managers.CoinManager;
import com.t03g06.model.managers.PipeManager;
import com.t03g06.model.managers.SpeedModifierManager;
import com.t03g06.model.managers.CollisionManager;
import com.t03g06.model.menu.Leaderboard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GameModelTest {
    private GameModel model;
    private Bird bird;
    private CoinManager coinManager;
    private PipeManager pipeManager;
    private SpeedModifierManager speedModifierManager;
    private CollisionManager collisionManager;
    private Leaderboard leaderboard;

    @BeforeEach
    void setUp() {
        bird = mock(Bird.class);
        coinManager = mock(CoinManager.class);
        pipeManager = mock(PipeManager.class);
        speedModifierManager = mock(SpeedModifierManager.class);
        collisionManager = mock(CollisionManager.class);
        leaderboard = mock(Leaderboard.class);

        model = new GameModel(bird, coinManager, pipeManager, speedModifierManager, collisionManager, leaderboard);
    }

    @Test
    void testInitialValues() {
        assertFalse(model.isGameOver());
        assertEquals(0, model.getScore());
        assertNotNull(model.getBird());
    }

    @Test
    void testJumpBird() {
        model.jumpBird();
        verify(bird).jump(anyInt());
    }

    @Test
    void testUpdateGameNotStarted() {
        clearInvocations(coinManager, pipeManager, speedModifierManager);

        model.updateGame();

        verifyNoInteractions(coinManager, pipeManager, speedModifierManager);
    }

    @Test
    void testUpdateGameStarted() {
        model.jumpBird();
        model.updateGame();

        verify(pipeManager).update(anyInt());
        verify(coinManager).update(anyInt());
        verify(speedModifierManager).update(anyInt());
    }

    @Test
    void testGameOverOnCollision() {
        when(collisionManager.checkPipeCollision(bird, pipeManager.getPipes()))
                .thenReturn(true);

        model.jumpBird();
        model.updateGame();

        assertTrue(model.isGameOver());
        verify(leaderboard).addScore(anyInt());
    }

    @Test
    void testScoreIncrementOnCoinCollision() {
        when(collisionManager.checkCoinCollision(bird, coinManager.getCoins()))
                .thenReturn(true);

        model.jumpBird();
        int initialScore = model.getScore();
        model.updateGame();

        assertTrue(model.getScore() > initialScore);
        verify(coinManager, atLeastOnce()).getCoins();
    }

    @Test
    void testResetGame() {
        model.resetGame();

        verify(bird, times(2)).reset();
        verify(coinManager, times(2)).reset();
        verify(pipeManager, times(2)).reset();
        verify(speedModifierManager, times(2)).reset();
    }
}
