package com.t03g06.model;

public class GameConstants {
    public static final int WIDTH = 80; // largura da janela
    public static final int HEIGHT = 40; // altura da janela
    public static final int PIPE_WIDTH = 5; // largura do pipe
    public static final int PIPE_GAP = 12; // espaço onde o bird passa
    public static final int PIPE_DISTANCE = 10; // distância dos canos na horizontal
    public static final int MARGIN = 4; // controla a distância minima do gap para o chão/teto
    public static final int GRAVITY = 1; // gravidade, move bird para baixo
    public static final int JUMP_HEIGHT = 3; // altura do pulo do bird
    public static final int PIPES_COUNT = 10; // quantidade de pipes que iniciam no jogo
    public static final int COIN_WIDTH = 2; // largura da coin
    public static final int COIN_HEIGHT = 2; // altura da coin
    public static final int PIPE_SCORE = 1; // pontuação por passar o pipe
    public static final int COIN_SCORE = 5; // pontuação por coletar coin
    public static final int COIN_MARGIN = 10; // distância mínima da coin para o chão/teto
    public static final int SPEED_MODIFIER_WIDTH = 2;
    public static final int SPEED_MODIFIER_HEIGHT = 3;
    public static final int SPEED_MODIFIER_MARGIN = 10;
    public static final int SPEED_MODIFIER_DURATION = 5000;
}
