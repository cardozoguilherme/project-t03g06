package com.t03g06.model.entities;

import com.t03g06.model.entities.Pipe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PipeTest {

    @Test
    void testPipeInitialization() {
        Pipe pipe = new Pipe(80, 10, 12); // x, gapStart, gapSize
        assertEquals(80, pipe.getX());
        assertEquals(10, pipe.getGapStart());
        assertEquals(12, pipe.getGapSize());
        assertFalse(pipe.isScored());
    }

    @Test
    void testMoveLeft() {
        Pipe pipe = new Pipe(80, 10, 12);
        pipe.moveLeft(1);
        assertEquals(79, pipe.getX());
    }

    @Test
    void testIsOutOfScreen() {
        Pipe pipe = new Pipe(-10, 10, 12); // cria pipe fora da tela
        assertTrue(pipe.isOutOfScreen());
    }

    @Test
    void testSetScored() {
        Pipe pipe = new Pipe(80, 10, 12);
        pipe.setScored(true);
        assertTrue(pipe.isScored());
    }
}
