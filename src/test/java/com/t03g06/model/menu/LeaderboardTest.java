package com.t03g06.model.menu;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import com.t03g06.model.menu.Leaderboard;

import static org.junit.jupiter.api.Assertions.*;

public class LeaderboardTest {

    private static final String FILE_PATH = "src/main/java/com/t03g06/leaderboard/leaderboard.txt";
    private Leaderboard leaderboard;

    @BeforeEach
    void setUp() throws IOException {
        Files.deleteIfExists(Paths.get(FILE_PATH));
        leaderboard = new Leaderboard();
    }

    @Test
    void testLoadScoresFromFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("100\n");
            writer.write("200\n");
            writer.write("300\n");
        }

        leaderboard = new Leaderboard();

        List<Integer> topScores = leaderboard.getTopScores();
        assertEquals(3, topScores.size(), "O leaderboard deve ter 3 scores.");
        assertEquals(300, topScores.get(0), "O maior score deve ser 300.");
        assertEquals(200, topScores.get(1), "O segundo maior score deve ser 200.");
        assertEquals(100, topScores.get(2), "O terceiro maior score deve ser 100.");
    }

    @Test
    void testAddScoreAndLimitToMaxSize() {
        leaderboard.addScore(100);
        leaderboard.addScore(200);
        leaderboard.addScore(300);
        leaderboard.addScore(400);
        leaderboard.addScore(500);
        leaderboard.addScore(600);

        List<Integer> topScores = leaderboard.getTopScores();
        assertEquals(5, topScores.size(), "O leaderboard deve ter no máximo 5 scores.");
        assertEquals(600, topScores.get(0), "O maior score deve ser 600.");
        assertEquals(500, topScores.get(1), "O segundo maior score deve ser 500.");
        assertEquals(400, topScores.get(2), "O terceiro maior score deve ser 400.");
        assertEquals(300, topScores.get(3), "O quarto maior score deve ser 300.");
        assertEquals(200, topScores.get(4), "O quinto maior score deve ser 200.");
    }

    @Test
    void testSaveScoresToFile() throws IOException {
        leaderboard.addScore(100);
        leaderboard.addScore(200);
        leaderboard.addScore(300);

        leaderboard.saveScores();

        List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
        assertEquals(3, lines.size(), "O arquivo de leaderboard deve ter 3 linhas.");
        assertEquals("300", lines.get(0).trim(), "O maior score no arquivo deve ser 300.");
        assertEquals("200", lines.get(1).trim(), "O segundo maior score no arquivo deve ser 200.");
        assertEquals("100", lines.get(2).trim(), "O terceiro maior score no arquivo deve ser 100.");
    }

    @Test
    void testGetTopScores() {
        leaderboard.addScore(100);
        leaderboard.addScore(200);
        leaderboard.addScore(300);

        List<Integer> topScores = leaderboard.getTopScores();
        assertEquals(3, topScores.size(), "O leaderboard deve ter 3 scores.");
        assertEquals(300, topScores.get(0), "O maior score deve ser 300.");
        assertEquals(200, topScores.get(1), "O segundo maior score deve ser 200.");
        assertEquals(100, topScores.get(2), "O terceiro maior score deve ser 100.");
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(FILE_PATH));
    }
}
