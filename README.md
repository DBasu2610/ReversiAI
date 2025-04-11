# ♻️ Reversi AI — Play Against an AI with Minimax & Alpha-Beta Pruning

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white)
![Swing UI](https://img.shields.io/badge/Swing%20UI-AI%20Game-blueviolet)
![Minimax](https://img.shields.io/badge/AI-Minimax%20%2B%20Alpha--Beta%20Pruning-green)

> A Java-based Reversi (Othello) game with a smart AI opponent using the **Minimax algorithm** and **Alpha-Beta pruning**. Play as Black against a reactive AI built from scratch.

---

## 🧠 Features

- Interactive **Reversi gameplay** using Java Swing
- AI built using **Minimax + Alpha-Beta Pruning**
- Clean, modular structure with `game`, `ai`, `ui`, and `utils` packages
- AI plays automatically after your move
- Game-over checker with score-based result popup

---

## 📁 Project Structure
ReversiAI/
├── src/
│   ├── Main.java                     // Launches the app
│   ├── ui/
│   │   ├── GameFrame.java           // JFrame for the game
│   │   └── BoardPanel.java          // JPanel that draws the board
│   ├── game/
│   │   ├── ReversiBoard.java        // 8x8 board and game logic
│   │   └── Move.java                // Represents a move
│   ├── ai/
│   │   ├── AIPlayer.java            // AI wrapper
│   │   └── Minimax.java             // Minimax + alpha-beta
│   └── utils/
│       └── Constants.java           // Player constants, board size, etc.


📸 Screenshot


![alt text](<Screenshot 2025-04-11 101500-1.png>)
![alt text](<Screenshot 2025-04-11 100804.png>)
![alt text](<Screenshot 2025-04-11 101500.png>)
