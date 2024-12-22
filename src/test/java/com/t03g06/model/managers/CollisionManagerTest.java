package com.t03g06.model.managers;

import com.t03g06.model.managers.CollisionManager;
import com.t03g06.model.GameConstants;
import com.t03g06.model.entities.Bird;
import com.t03g06.model.entities.Coin;
import com.t03g06.model.entities.Pipe;
import com.t03g06.model.entities.SpeedModifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CollisionManagerTest {

    private CollisionManager collisionManager;
    private Bird birdMock;

    @BeforeEach
    void setUp() {
        collisionManager = new CollisionManager();
        birdMock = mock(Bird.class);
    }

    @Test
    void checkPipeCollisionTest() {
        List<Pipe> pipes = new ArrayList<>();
        Pipe pipeMock = mock(Pipe.class);
        pipes.add(pipeMock);

        when(pipeMock.getX()).thenReturn(GameConstants.WIDTH / 4); // Posição do pipe na horizontal
        when(pipeMock.getGapStart()).thenReturn(100);
        when(pipeMock.getGapSize()).thenReturn(50);
        when(birdMock.getY()).thenReturn(90); // Fora do gap

        boolean result = collisionManager.checkPipeCollision(birdMock, pipes);

        assertTrue(result);
    }


    @Test
    void checkCoinCollisionTest() {
        List<Coin> coins = new ArrayList<>();
        Coin coinMock = mock(Coin.class);
        coins.add(coinMock);

        when(coinMock.getX()).thenReturn(GameConstants.WIDTH / 4);
        when(coinMock.getY()).thenReturn(100);
        when(birdMock.getY()).thenReturn(100);

        boolean result = collisionManager.checkCoinCollision(birdMock, coins);

        assertTrue(result);
        assertTrue(coins.isEmpty()); // Coin foi removida
    }

    @Test
    void checkSpeedModifierCollisionTest() {
        List<SpeedModifier> speedModifiers = new ArrayList<>();
        SpeedModifier smMock = mock(SpeedModifier.class);
        speedModifiers.add(smMock);

        when(smMock.getX()).thenReturn(GameConstants.WIDTH / 4);
        when(smMock.getY()).thenReturn(100);
        when(birdMock.getY()).thenReturn(100);

        boolean result = collisionManager.checkSpeedModifierCollision(birdMock, speedModifiers);

        assertTrue(result);
        assertTrue(speedModifiers.isEmpty()); // SpeedModifier foi removido
    }

    @Test
    void checkBoundaryCollisionTest() {
        when(birdMock.getY()).thenReturn(-1);

        boolean result = collisionManager.checkBoundaryCollision(birdMock);

        assertTrue(result);

        when(birdMock.getY()).thenReturn(GameConstants.HEIGHT);

        result = collisionManager.checkBoundaryCollision(birdMock);

        assertTrue(result);
    }
}
