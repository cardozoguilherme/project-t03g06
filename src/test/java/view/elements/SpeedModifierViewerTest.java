package view.elements;

import com.t03g06.model.elements.SpeedModifier;
import com.t03g06.view.elements.SpeedModifierViewer;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class SpeedModifierViewerTest {
    private TextGraphics tg;
    private SpeedModifierViewer speedModifierViewer;
    private List<SpeedModifier> speedModifiers;

    @BeforeEach
    void setUp() {
        tg = mock(TextGraphics.class);

        speedModifiers = Arrays.asList(
                new SpeedModifier(5, 10),
                new SpeedModifier(20, 15)
        );

        speedModifierViewer = new SpeedModifierViewer(speedModifiers, tg);
    }

    @Test
    void testDraw() {
        speedModifierViewer.draw();

        verify(tg).setForegroundColor(TextColor.ANSI.RED_BRIGHT);

        for (SpeedModifier speedModifier : speedModifiers) {
            verify(tg).putString(speedModifier.getX(), speedModifier.getY(), ">>");
            verify(tg).putString(speedModifier.getX(), speedModifier.getY() + 1, ">>");
            verify(tg).putString(speedModifier.getX(), speedModifier.getY() + 2, ">>");
        }

        verify(tg).setForegroundColor(TextColor.ANSI.BLUE);
    }
}
