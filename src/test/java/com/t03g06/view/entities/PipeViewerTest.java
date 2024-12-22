package com.t03g06.view.entities;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.model.GameConstants;
import com.t03g06.model.entities.Pipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class PipeViewerTest {
    private TextGraphics tg;
    private Pipe pipe1;
    private Pipe pipe2;
    private PipeViewer pipeViewer;

    @BeforeEach
    void setUp() {
        tg = mock(TextGraphics.class);

        pipe1 = mock(Pipe.class);
        when(pipe1.getX()).thenReturn(10);
        when(pipe1.getGapStart()).thenReturn(5);

        pipe2 = mock(Pipe.class);
        when(pipe2.getX()).thenReturn(20);
        when(pipe2.getGapStart()).thenReturn(8);

        List<Pipe> pipes = Arrays.asList(pipe1, pipe2);

        pipeViewer = new PipeViewer(pipes, tg);
    }

    @Test
    void testDraw() {
        when(pipe1.getX()).thenReturn(5);
        when(pipe1.getGapStart()).thenReturn(10);
        when(pipe2.getX()).thenReturn(20);
        when(pipe2.getGapStart()).thenReturn(15);

        pipeViewer.draw();

        verify(tg).setBackgroundColor(TextColor.ANSI.GREEN);

        int pipe1X = 5;
        int pipe1GapStart = 10;
        int pipe2X = 20;
        int pipe2GapStart = 15;

        for (int y = 0; y < GameConstants.HEIGHT; y++) {
            if (y < pipe1GapStart || y >= pipe1GapStart + GameConstants.PIPE_GAP) {
                for (int w = 0; w < GameConstants.PIPE_WIDTH; w++) {
                    verify(tg).putString(eq(pipe1X + w), eq(y), eq(" "));
                }
            }
        }

        for (int y = 0; y < GameConstants.HEIGHT; y++) {
            if (y < pipe2GapStart || y >= pipe2GapStart + GameConstants.PIPE_GAP) {
                for (int w = 0; w < GameConstants.PIPE_WIDTH; w++) {
                    verify(tg).putString(eq(pipe2X + w), eq(y), eq(" "));
                }
            }
        }

        verify(tg).setBackgroundColor(TextColor.ANSI.BLUE);
    }

}
