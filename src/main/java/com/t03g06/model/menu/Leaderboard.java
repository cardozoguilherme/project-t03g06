package com.t03g06.model.menu;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;

public class Leaderboard {
    private final Map<String, Integer> scores;
    private final int MAX_SIZE = 5;  // Máximo de 5 jogadores no leaderboard

    public Leaderboard() {
        this.scores = new HashMap<>();
        this.scores.put("Jogador1", 0);
        this.scores.put("Jogador2", 0);
        this.scores.put("Jogador3", 0);
        this.scores.put("Jogador4", 0);
        this.scores.put("Jogador5", 0);
    }

    // Adiciona ou atualiza a pontuação do jogador
    public void addScore(String playerName, int score) {
        // Atualiza a pontuação do jogador
        scores.put(playerName, scores.getOrDefault(playerName, 0) + score);

        // Ordena os jogadores por pontuação em ordem decrescente
        List<Map.Entry<String, Integer>> scoreList = new ArrayList<>(scores.entrySet());
        scoreList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Se o número de jogadores exceder o limite de 5, remove o jogador com a menor pontuação
        if (scoreList.size() > MAX_SIZE) {
            Map.Entry<String, Integer> lowestScore = scoreList.get(MAX_SIZE); // jogador com a menor pontuação
            scores.remove(lowestScore.getKey()); // Remove o jogador com a menor pontuação
        }
    }

    // Retorna o leaderboard em formato de lista
    public List<Map.Entry<String, Integer>> getScores() {
        List<Map.Entry<String, Integer>> scoreList = new ArrayList<>(scores.entrySet());
        scoreList.sort((a, b) -> b.getValue().compareTo(a.getValue()));  // Ordena pela pontuação
        return scoreList;
    }

    // Reseta as pontuações
    public void resetScores() {
        scores.clear();
    }

    // Retorna a maior pontuação
    public int getTopScore() {
        return scores.values().stream().max(Integer::compareTo).orElse(0);  // Retorna a maior pontuação
    }

    // Retorna o jogador com a maior pontuação
    public String getTopPlayer() {
        return scores.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse("Nenhum jogador");
    }
}
