package com.t03g06;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.t03g06.model.menu.MenuModel;
import com.t03g06.states.MenuState;
import com.t03g06.states.State;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Game {
    private State<?> currentState;
    private final Screen screen;
    private final TextGraphics tg;

    public Game(Screen screen, TextGraphics tg) {
        this.screen = screen;
        this.tg = tg;
    }

    public void setState(State<?> state) {
        this.currentState = state;
    }

    public TextGraphics getTextGraphics() {
        return tg;
    }

    public KeyStroke getInput() throws IOException {
        return screen.pollInput();
    }

    public void start() throws IOException {
        int FPS = 10;
        int frameTime = 1000 / FPS;

        System.currentTimeMillis();

        while (true) {
            long currentTime = System.currentTimeMillis();

            currentState.step(this, screen);

            long sleepTime = frameTime - (System.currentTimeMillis() - currentTime);
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            // personaliza a fonte
            URL resource = Game.class.getClassLoader().getResource("square.ttf");
            assert resource != null;
            File fontFile = new File(resource.toURI());
            Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
            Font loadedFont = font.deriveFont(Font.PLAIN, 16);
            AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);

            // configura o terminal
            DefaultTerminalFactory factory = new DefaultTerminalFactory();
            factory.setTerminalEmulatorFontConfiguration(fontConfig);
            factory.setForceAWTOverSwing(true);
            factory.setInitialTerminalSize(new TerminalSize(80, 40));

            Terminal terminal = factory.createTerminal();
            ((AWTTerminalFrame) terminal).setTitle("Flappy Bird");
            Screen screen = new TerminalScreen(terminal);
            screen.startScreen();
            screen.setCursorPosition(null);

            TextGraphics tg = screen.newTextGraphics();

            // inicialização do jogo no menu
            MenuModel menuModel = new MenuModel();
            Game game = new Game(screen, tg);
            game.setState(new MenuState(menuModel, tg));

            // executa o jogo
            game.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
