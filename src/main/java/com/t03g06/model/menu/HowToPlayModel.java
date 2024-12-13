package com.t03g06.model.menu;

import java.util.Arrays;
import java.util.List;

public class HowToPlayModel {
    private List<String> instructions;

    public HowToPlayModel() {
        this.instructions = Arrays.asList(
                "1. Press SPACE to jump, ESC to quit, or R to restart.",
                "2. Avoid hitting the pipes, ceiling, or ground.",
                "3. The pipes are green, the speed modifiers are red and",
                        "   the coins are yellow.",
                "4. You have only one life.",
                "5. Speed modifiers make the game twice as fast.",
                "6. Passing through a pipe earns you 1 point.",
                "7. Collecting coins earns you 5 points.",
                "8. The top 5 scores are saved to the leaderboard."
        );
    }

    public List<String> getInstructions() {
        return instructions;
    }
}
