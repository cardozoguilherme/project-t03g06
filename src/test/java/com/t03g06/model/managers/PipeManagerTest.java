package com.t03g06.model.managers;

import com.t03g06.model.managers.*;

import com.t03g06.model.GameConstants;
import com.t03g06.model.entities.Pipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PipeManagerTest {

    private PipeManager pipeManager;

    @BeforeEach
    void setUp() {
        CoinManager coinManagerMock = mock(CoinManager.class);
        SpeedModifierManager speedModifierManagerMock = mock(SpeedModifierManager.class);
        pipeManager = new PipeManager(coinManagerMock, speedModifierManagerMock);
    }

    @Test
    void testResetInitializesPipes() {
        pipeManager.reset();

        List<Pipe> pipes = pipeManager.getPipes();

        assertEquals(GameConstants.PIPES_COUNT, pipes.size());
        for (int i = 0; i < pipes.size(); i++) {
            int expectedX = GameConstants.WIDTH / 2 + i * (GameConstants.PIPE_WIDTH + GameConstants.PIPE_DISTANCE);
            assertEquals(expectedX, pipes.get(i).getX());
        }
    }

    @Test
    void testUpdateMovesPipesAndAddsNewPipe() {
        pipeManager.reset();

        Pipe firstPipeMock = mock(Pipe.class);
        when(firstPipeMock.isOutOfScreen()).thenReturn(true);
        pipeManager.getPipes().set(0, firstPipeMock);

        int initialSize = pipeManager.getPipes().size();

        pipeManager.update(5);

        assertEquals(initialSize, pipeManager.getPipes().size());
        verify(firstPipeMock, times(1)).moveLeft(5);
    }

    @Test
    void testUpdateRemovesOutOfScreenPipe() {
        pipeManager.reset();

        Pipe firstPipeMock = mock(Pipe.class);
        when(firstPipeMock.isOutOfScreen()).thenReturn(true);
        pipeManager.getPipes().set(0, firstPipeMock);

        int initialSize = pipeManager.getPipes().size();

        pipeManager.update(5);

        assertEquals(initialSize, pipeManager.getPipes().size());
        assertFalse(pipeManager.getPipes().contains(firstPipeMock));
    }

    @Test
    void testUpdateScoreIncreasesScoreForPassedPipes() {
        pipeManager.reset();

        Pipe mockPipe = mock(Pipe.class);
        when(mockPipe.isScored()).thenReturn(false);
        when(mockPipe.getX()).thenReturn(GameConstants.WIDTH / 4 - GameConstants.PIPE_WIDTH - 1);

        pipeManager.getPipes().set(0, mockPipe);

        int score = pipeManager.updateScore();

        assertEquals(GameConstants.PIPE_SCORE, score);
        verify(mockPipe, times(1)).setScored(true);
    }

}
