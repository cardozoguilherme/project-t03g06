## LDTS_T03_G06 - FLAPPY BIRD

The game is inspired by the popular "Flappy Bird." Players control Flappy, a bird, with the goal of navigating through as many pipes as possible while collecting points. Points are earned in two ways: passing through pipes awards 1 point, and collecting coins grants 5 points. In addition to pipes and coins, there are modifiers that, when collected, increase the game’s speed for 5 seconds.

The game has no predefined end and only concludes when the player collides with an obstacle.

This project was developed by Guilherme Cardozo de Castro Melo (up202401169@edu.fe.up.pt), Tiago Zou Yin (up202306438@edu.fe.up.pt), and Fernando Oliveira (up201005231@edu.fe.up.pt) for LTDS 2024–2025.

------

### IMPLEMENTED FEATURES

- **Menu** - When the game starts, the screen displays the main menu where the player can choose to start the game, access the leaderboard, view the "how to play" menu, or quit the game.
- **Player Controls Ingame** - Spacebar = jump, R = restart, ESC = exit the game.
- **Player Controls Menu** - Arrow UP = previous option, Arrow DOWN = next option, ESC = return to the main menu.
- **Bird** - The character controlled by the player; it can jump and is affected by gravity.
- **Speed Modifier** - Collecting a speed modifier increases the game speed for 5 seconds.
- **Speed Modifier Movement** - Speed modifiers move vertically and to the left.
- **Pipes** - The player dies upon colliding with a pipe.
- **Pipe Movement** - Pipes move to the left across the screen.
- **Randomized Pipe Sizes** - Pipes are randomly generated to vary the challenge.
- **Coins** - Coins can be collected between obstacles to increase the player’s score.
- **Coin Movement** - Coins move vertically and to the left.
- **Scoring System** - The score increases when the player passes an obstacle or collects a coin.
- **Game Over** - A game-over screen is displayed when the player loses.
- **Leaderboard** - Displays a list of the highest scores achieved.

![preview.png](gifs%2Fpreview.gif)

<p align="center">
  <b><i>Gif 1. This gif shows the player controls ingame, bird, speed modfier, speedmodifiers movement, pipes, pipe movement, randomized pipe sizes, coins, coin movement, scoring system and game over.</i></b>
</p>

------

### PLANNED FEATURES

All features have been implemented. No additional features are pending implementation.

------

### DESIGN

#### STATE

**Problem in Context**

The game has multiple states (e.g., Menu, Game, Game Over), each with distinct behavior and interactions. Managing these states with conditional logic would lead to tightly coupled and less maintainable code.

**The Pattern**

The State Pattern was used to encapsulate each game state’s behavior in a subclass of the abstract State class. Each state defines its specific logic, isolating state behavior and simplifying the addition of new states, thereby enhancing modularity and scalability.

**Implementation**

The State Pattern was implemented to manage the game's various states (e.g., menu, game, game over) by encapsulating state-specific behavior into separate subclasses of the abstract State class. Each state defines its unique behavior by implementing the abstract methods getViewer and getController, which provide the appropriate viewer and controller for that state. The step method, defined in the State class, is shared across all states and ensures a consistent flow of input handling, logic updates, and rendering.

![StatePatternUML.png](images%2Fuml%2FStatePatternUML.png)

These classes can be found in the following files:

- [State](https://github.com/FEUP-LDTS-2024/project-t03g06/blob/master/src/main/java/com/t03g06/states/State.java)
- [MenuState](https://github.com/FEUP-LDTS-2024/project-t03g06/blob/master/src/main/java/com/t03g06/states/MenuState.java)
- [GameOverState](https://github.com/FEUP-LDTS-2024/project-t03g06/blob/master/src/main/java/com/t03g06/states/GameOverState.java)
- [GameState](https://github.com/FEUP-LDTS-2024/project-t03g06/blob/master/src/main/java/com/t03g06/states/GameState.java)
- [LeaderboardState](https://github.com/FEUP-LDTS-2024/project-t03g06/blob/master/src/main/java/com/t03g06/states/LeaderboardState.java)
- [HowToPlayState](https://github.com/FEUP-LDTS-2024/project-t03g06/blob/master/src/main/java/com/t03g06/states/HowToPlayState.java)
- [Game](https://github.com/FEUP-LDTS-2024/project-t03g06/blob/master/src/main/java/com/t03g06/Game.java)

**Consequences**

- Clear separation of responsibilities.
- Ease of adding new states.
- Improved readability.
- Increased number of classes. 
- Slightly more complexity in managing transitions.

#### GAME LOOP

**Problem in Context**

A game requires consistent updates to entities and rendering over time. As the game evolved and more entities were added, controlling update and frame rate became essential. Performance differences across systems risked inconsistent gameplay, making it crucial to standardize game speed and ensure smooth, fair experiences for all users.

**The Pattern**

The Game Loop Pattern ensures consistent updates and rendering across different systems. As the game evolved, controlling the frame rate became essential to maintaining smooth gameplay. The loop standardizes update intervals, ensuring consistent performance regardless of the user’s system. Additionally, the game loop interacts with different states, calling their respective step methods to handle logic, updates and rendering.

**Implementation**

In the Game class, the game loop is implemented in the start() method, where it controls the flow of the game by repeatedly calling the current state's step() method.

![GameLoopPatternUML.png](images%2Fuml%2FGameLoopPatternUML.png)

These classes can be found in the following files:

- [Game](https://github.com/FEUP-LDTS-2024/project-t03g06/blob/master/src/main/java/com/t03g06/Game.java)
- [State](https://github.com/FEUP-LDTS-2024/project-t03g06/blob/master/src/main/java/com/t03g06/states/State.java)

**Consequences**

- Consistent game behavior.
- Easy-to-maintain structure.

#### FACTORY METHOD

**Problem in Context**

Managing different states in a game requires each state to have its own Viewer and Controller for handling specific behavior and rendering. Without a structured approach, instantiating these components manually would lead to tight coupling between the State class and its concrete implementations, making the code less flexible and harder to extend. For example, adding a new state would require modifications to the existing State logic to accommodate the new Viewer and Controller.

**The Pattern**

The Factory Method Pattern was applied to delegate the creation of Viewer and Controller instances to subclasses of the abstract State class. Each subclass implements the abstract methods getViewer() and getController(), returning instances specific to its behavior. This pattern fits well in the context, as it decouples the State class from the details of creating these components, promoting modularity and extensibility.

**Implementation**

The abstract class State defines the factory methods getViewer() and getController() as abstract, ensuring each subclass provides its specific implementation.

![FactoryMethodPatternUML.png](images%2Fuml%2FFactoryMethodPatternUML.png)

These classes can be found in the following files: 
- [State](https://github.com/FEUP-LDTS-2024/project-t03g06/blob/master/src/main/java/com/t03g06/states/State.java)
- [MenuState](https://github.com/FEUP-LDTS-2024/project-t03g06/blob/master/src/main/java/com/t03g06/states/MenuState.java)
- [GameOverState](https://github.com/FEUP-LDTS-2024/project-t03g06/blob/master/src/main/java/com/t03g06/states/GameOverState.java)
- [GameState](https://github.com/FEUP-LDTS-2024/project-t03g06/blob/master/src/main/java/com/t03g06/states/GameState.java)
- [LeaderboardState](https://github.com/FEUP-LDTS-2024/project-t03g06/blob/master/src/main/java/com/t03g06/states/LeaderboardState.java)
- [HowToPlayState](https://github.com/FEUP-LDTS-2024/project-t03g06/blob/master/src/main/java/com/t03g06/states/HowToPlayState.java)

**Consequences**

- Decouples the State class from concrete implementations of Viewer and Controller.
- Simplifies adding new states, as each state manages its own Viewer and Controller.
- Enhances code modularity and adheres to the open/closed principle.
- Slightly increases the number of classes, as each state requires its own implementation.

------

### KNOWN CODE SMELLS

The GameModel class has three key code smells: Long Method, Large Class, and Feature Envy. The updateGame() method is too lengthy, handling updates, gravity, speed modifiers, and collision detection, violating readability. The class takes on excessive responsibilities, such as managing entities, collisions, and the leaderboard, making it a Large Class. Additionally, it exhibits Feature Envy by performing tasks like collision detection and speed updates that should be delegated to managers or entities. Refactoring these issues would improve cohesion and maintainability.

------

### TESTING

**Screenshot of coverage report**

![CoverageReport.png](tests%2Fscreenshots%2FCoverageReport.png)

[Link to coverage report](https://github.com/FEUP-LDTS-2024/project-t03g06/tree/master/docs/tests/report/coverage-report)

**Screenshot of mutation testing report**

![CoverageReport.png](tests%2Fscreenshots%2FMutationTestReport.png)

[Link to mutation testing report](https://github.com/FEUP-LDTS-2024/project-t03g06/tree/master/docs/tests/report/pitest)

------

### UML DIAGRAM

![FinalUML.png](images%2Fuml%2FFinalUML.png)

### SELF-EVALUATION

- Guilherme Cardozo: 33.33%
- Tiago Zou Yin: 33.33%
- Fernando Oliveira: 33.33%
