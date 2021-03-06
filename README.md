## Tic-Tac-Toe
* Classic Tic-Tac-Toe, playable versus the always mighty [Minimax A.I.](https://en.wikipedia.org/wiki/Minimax) 
 
### Usage
_Requires JDK8 or later_

#### Compile & Run
1. In terminal, navigate to src/
2. Compile with `javac App.java logic/*.java model/*.java`
3. Run with `java com.ai.tic.tac.toe.App`

#### Notes
* The UI remains allows a user a user to select their move with zero-indexed x, y coordinates. For example, the top-most, middle cell is (0, 1)
* The Minimax A.I. works by considering every remaining, legal move. With an assumption the human player will play optimally, it will assign a score for the end board state (win, loss, draw), and return the highest
