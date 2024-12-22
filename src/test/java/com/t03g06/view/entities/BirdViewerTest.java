package com.t03g06.view.entities;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.TextColor;
import com.t03g06.model.entities.Bird;
import com.t03g06.view.entities.BirdViewer;
import com.t03g06.model.GameConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.*;

class BirdViewerTest {

    @Mock
    private TextGraphics tg;

    private Bird bird;
    private BirdViewer birdViewer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        bird = new Bird(10);
        birdViewer = new BirdViewer(bird, tg);
    }

    @Test
    void testDraw() {
        birdViewer.draw();

        verify(tg).setForegroundColor(TextColor.ANSI.YELLOW_BRIGHT);

        verify(tg).putString(GameConstants.WIDTH / 4, bird.getY(), "O");
    }
}
