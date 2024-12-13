package com.t03g06.model.menu;

import java.util.Arrays;
import java.util.List;

public class MenuModel {
    private final List<String> options = Arrays.asList("PLAY", "HOW TO PLAY" ,"LEADERBOARD", "QUIT"); // opções do menu
    private int currentOption = 0; // opção que inicia marcada

    public List<String> getOptions() {
        return options;
    }

    public int getCurrentOption() {
        return currentOption;
    }

    public void nextOption() {
        currentOption = (currentOption + 1) % options.size(); //
    }

    public void previousOption() {
        currentOption = (currentOption - 1 + options.size()) % options.size();
    }
}
