package com.t03g06.view.entities;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.t03g06.model.GameConstants;
import com.t03g06.model.entities.Pipe;
import com.t03g06.view.entities.PipeViewer;
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
        pipeViewer.draw();

        verify(tg).setBackgroundColor(TextColor.ANSI.GREEN);

        for (int y = 0; y < GameConstants.HEIGHT; y++) {
            if (y < pipe1.getGapStart() || y >= pipe1.getGapStart() + GameConstants.PIPE_GAP) {
                for (int w = 0; w < GameConstants.PIPE_WIDTH; w++) {
                    verify(tg).putString(pipe1.getX() + w, y, " ");
                }
            }
        }

        for (int y = 0; y < GameConstants.HEIGHT; y++) {
            if (y < pipe2.getGapStart() || y >= pipe2.getGapStart() + GameConstants.PIPE_GAP) {
                for (int w = 0; w < GameConstants.PIPE_WIDTH; w++) {
                    verify(tg).putString(pipe2.getX() + w, y, " ");
                }
            }
        }

        verify(tg).setBackgroundColor(TextColor.ANSI.BLUE);
    }
}
