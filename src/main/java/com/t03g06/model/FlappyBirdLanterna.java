package com.t03g06.model;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFrame;
import com.t03g06.controller.GameController;
import com.t03g06.view.GameView;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class FlappyBirdLanterna {
    public static void main(String[] args) {
        try {
            // personaliza a fonte
            URL resource = FlappyBirdLanterna.class.getClassLoader().getResource("square.ttf");
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
            ((AWTTerminalFrame) terminal).setTitle("Flappy Bird"); // título da janela
            Screen screen = new TerminalScreen(terminal);
            screen.startScreen();
            screen.setCursorPosition(null);

            // inicialização do jogo
            GameState gameState = new GameState();
            GameView gameView = new GameView();
            GameController gameController = new GameController(gameState, gameView, screen);

            // executa o jogo
            gameController.run();
        } catch (IOException | FontFormatException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
