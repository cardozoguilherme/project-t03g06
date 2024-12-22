package model.managers;

import com.t03g06.model.managers.SpeedModifierManager;

import com.t03g06.model.GameConstants;
import com.t03g06.model.entities.SpeedModifier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SpeedModifierManagerTest {

    private SpeedModifierManager speedModifierManager;

    @BeforeEach
    void setUp() {
        speedModifierManager = new SpeedModifierManager();
    }

    @Test
    void testReset() {
        speedModifierManager.addModifier(100, 200);
        speedModifierManager.addModifier(300, 400);

        speedModifierManager.reset();

        assertTrue(speedModifierManager.getSpeedModifiers().isEmpty());
    }

    @Test
    void testAddModifier() {
        speedModifierManager.addModifier(100, 200);

        List<SpeedModifier> speedModifiers = speedModifierManager.getSpeedModifiers();

        assertEquals(1, speedModifiers.size());
        assertEquals(100, speedModifiers.getFirst().getX());
        assertEquals(200, speedModifiers.getFirst().getY());
    }

    @Test
    void testUpdateMovesSpeedModifiers() {
        SpeedModifier speedModifierMock = mock(SpeedModifier.class);
        speedModifierManager.getSpeedModifiers().add(speedModifierMock);

        int speed = 5;

        speedModifierManager.update(speed);

        verify(speedModifierMock, times(1)).moveLeft(speed);
        verify(speedModifierMock, times(1)).moveY(GameConstants.HEIGHT);
    }

    @Test
    void testUpdateRemovesOutOfScreenSpeedModifiers() {
        SpeedModifier smMock1 = mock(SpeedModifier.class);
        SpeedModifier smMock2 = mock(SpeedModifier.class);

        when(smMock1.isOutOfScreen()).thenReturn(false);
        when(smMock2.isOutOfScreen()).thenReturn(true);

        speedModifierManager.getSpeedModifiers().add(smMock1);
        speedModifierManager.getSpeedModifiers().add(smMock2);

        speedModifierManager.update(5);

        List<SpeedModifier> speedModifiers = speedModifierManager.getSpeedModifiers();

        assertEquals(1, speedModifiers.size());
        assertSame(smMock1, speedModifiers.getFirst());
    }
}
