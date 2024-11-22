# Descrição
O jogo é inspirado no famoso jogo “Flappy Bird”. On
de o jogador tem como objetivo passar pela maior quantidade de obstáculos que conseguir e obter pontos pelo caminho. A dificuldade do jogo cresce à medida que o jogador soma pontos, pois a velocidade aumenta. O jogo não possui final, acabando apenas quando o jogador colide com um dos obstáculos durante o caminho.

# Lista de features

- Menu - Quando o jogo começa, a tela mostra o menu inicial onde jogador pode iniciar o jogo ou acessar o placar de classificação.
- Controle do jogador - Ao pressionar a barra de espaço, o jogador pula
- Pássaro - O jogador
- Velocidade do jogo - Quando o usuário passa por um obstáculo, a velocidade do jogo cresce
- Movimentação dos canos - O canos move-se para a esquerda
- Randomização do tamanho dos canos - Obstáculos são gerados aleatoriamente  
- Moedas - Moedas podem ser coletadas entre os obstáculos para incrementar a pontuação do jogador
- Pontuação - A pontuação é incrementada quando o jogador passa um obstáculo ou coleta uma moeda
- Música - Quando o jogo começa, uma música é tocada
- Game over - Quando o jogador perde, aparece uma tela de game over com a sua pontuação final
- Tabela de classificação - Exibe a lista de jogadores com maiores pontuações

# Progresso de implementação das features
- Menu - não implementado
- Controle do jogador - implementado
- Pássaro - implementado
- Velocidade do jogo - não implementado
- Movimentação dos canos - implementado
- Randomização do tamanho dos canos - não implementado
- Moedas - não implementado
- Pontuação - implementado
- Música - não implementado
- Game over - implementado
- Tabela de classificação - não implementado

# Mockups

![MockupMENU.png](Docs%2FImages%2FMockupMENU.png)
![MockupLEADERBOARD.png](Docs%2FImages%2FMockupLEADERBOARD.png)
![MockupGAME.png](Docs%2FImages%2FMockupGAME.png)

# Design patterns escolhidos

- Observer: Permite que objetos atualizem seu status e que objetos dependentes desse sejam notificados (e.g., aumento da pontuação ou colisão com um obstáculo) e atualizados.
- State: Permite a organização do jogo em diferentes estados. Cada estado tem um comportamento diferente (e.g., started ou gameOver).
- Strategy: Permite diferentes comportamentos de movimentos ou física, permitindo a alteração fácil do pássaro ou dos canos que se movem.

# Diagrama UML

![FlappyBirdLanternaUML.png](Docs%2FImages%2FFlappyBirdLanternaUML.png)