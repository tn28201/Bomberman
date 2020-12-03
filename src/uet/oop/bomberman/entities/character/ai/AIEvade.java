package uet.oop.bomberman.entities.character.ai;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;

/**
 * AI né bom.
 */
public class AIEvade extends AI {
    private Enemy _e;


    public AIEvade(Bomber bomber, Enemy e) {
        _e = e;
    }

    @Override
    public int calculateDirection() {
        int directionBomb = directionHasBomb(2);

        if (directionBomb == 0) return random.nextInt(3) + 1;       // random: 1, 2, 3 not 0.
        if (directionBomb == 1) return (random.nextInt(3) + 2) % 4; // random: 0, 2, 3 not 1.
        if (directionBomb == 2) return (random.nextInt(3) + 3) % 4; // random: 0, 1, 3 not 2.
        if (directionBomb == 3) return random.nextInt(3);           // random: 0, 1, 2 not 3.

        return -1;
    }

    private int directionHasBomb(int distance) {
        int x = _e.getXTile();
        int y = _e.getYTile();

        for (int i = 0; i < distance; i++)
            if (BombermanGame.getBombAt(x, y - 1 - i) != null) return 0;
        for (int i = 0; i < distance; i++)
            if (BombermanGame.getBombAt(x + 1 + i, y) != null) return 1;
        for (int i = 0; i < distance; i++)
            if (BombermanGame.getBombAt(x, y + 1 + i) != null) return 2;
        for (int i = 0; i < distance; i++)
            if (BombermanGame.getBombAt(x - 1 - i, y) != null) return 3;

        return -1;
    }
}
