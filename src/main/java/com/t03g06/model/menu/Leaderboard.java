package com.t03g06.model.menu;

import java.io.*;
import java.util.*;

public class Leaderboard {
    private final List<Integer> scores = new ArrayList<>();
    private final int MAX_SIZE = 5;  // máximo de 5 jogadores no leaderboard
    private final String filePath = "src/main/java/com/t03g06/leaderboard/leaderboard.txt";

    public Leaderboard() {
        loadScores();
    }

    private void loadScores() {
        File file = new File(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scores.add(Integer.parseInt(line.trim()));
            }

            scores.sort(Collections.reverseOrder());
            if (scores.size() > MAX_SIZE) {
                scores.subList(MAX_SIZE, scores.size()).clear();
            }
        } catch (IOException e) {
            e.setStackTrace(e.getStackTrace());
        }
    }

    public void saveScores() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int score : scores) {
                writer.write(score + System.lineSeparator());
            }
        } catch (IOException e) {
            e.setStackTrace(e.getStackTrace());
        }
    }

    public List<Integer> getTopScores() {
        return new ArrayList<>(scores);
    }

    public void addScore(int score) {
        scores.add(score);
        scores.sort(Collections.reverseOrder());
        if (scores.size() > MAX_SIZE) {
            scores.removeLast();
        }
        saveScores();
    }
}
