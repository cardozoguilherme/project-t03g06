package com.t03g06.model.menu;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Leaderboard {
    private final Map<String, Integer> scores;
    private final int MAX_SIZE = 5;  // Máximo de 5 jogadores no leaderboard

    public Leaderboard() {
        this.scores = new HashMap<>();
        this.scores.put("Jogador1", 50);
        this.scores.put("Jogador2", 25);
        this.scores.put("Jogador3", 12);
        this.scores.put("Jogador4", 6);
        this.scores.put("Jogador5", 3);
    }

    public int getMaxSize() {
        return MAX_SIZE;
    }

    // Adiciona ou atualiza a pontuação do jogador
    public void addScore(String playerName, int score) {
        if (scores.containsKey(playerName)) {
            // Atualiza a pontuação do jogador existente se for maior
            scores.put(playerName, Math.max(scores.get(playerName), score));
        } else {
            // Verifica se o leaderboard está cheio
            if (scores.size() < MAX_SIZE) {
                // Adiciona o jogador diretamente
                scores.put(playerName, score);
            } else {
                // Encontra o jogador com a menor pontuação
                String lowestScorePlayer = null;
                int lowestScore = Integer.MAX_VALUE;

                for (Map.Entry<String, Integer> entry : scores.entrySet()) {
                    if (entry.getValue() < lowestScore) {
                        lowestScore = entry.getValue();
                        lowestScorePlayer = entry.getKey();
                    }
                }

                // Substitui o jogador com a menor pontuação se a nova pontuação for maior
                if (score > lowestScore && lowestScorePlayer != null) {
                    scores.remove(lowestScorePlayer);
                    scores.put(playerName, score);
                }
            }
        }
    }



    public List<Map.Entry<String, Integer>> getTopScores() {
        List<Map.Entry<String, Integer>> scoreList = new ArrayList<>(scores.entrySet());
        // Ordena as pontuações em ordem decrescente
        scoreList.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Garante que só retorna no máximo 5 jogadores
        return scoreList.size() > MAX_SIZE ? scoreList.subList(0, MAX_SIZE) : scoreList;
    }
}
