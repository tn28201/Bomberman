package uet.oop.bomberman.entities.character.ai;

import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;

public class AIMedium extends AI {
    private Bomber _bomber;
    private Enemy _e;
    private AILow aiLow;

    public AIMedium(Bomber bomber, Enemy e) {
        _bomber = bomber;
        _e = e;
        aiLow = new AILow();
    }

    @Override
    public int calculateDirection() {
        // @todo: cài đặt thuật toán tìm đường đi
        int random = (int) (Math.random() * 3);
        return calculateDirection(random);
    }

    public int calculateDirection(int random) {
        if (random == 0) return calculateColDirection();
        if (random == 1) return calculateRowDirection();

        return aiLow.calculateDirection();
    }

    // return 1, 3, -1.
    protected int calculateColDirection() {
        if (_bomber.getXTile() < _e.getXTile()) return 3;
        if (_bomber.getXTile() > _e.getXTile()) return 1;

        return (int) (Math.random() * 3);
    }

    // return 0, 2, -1.
    protected int calculateRowDirection() {
        if (_bomber.getYTile() < _e.getYTile()) return 0;
        if (_bomber.getYTile() > _e.getYTile()) return 2;

        return (int) (Math.random() * 3);
    }
}
