package uet.oop.bomberman.entities.character.ai;

import uet.oop.bomberman.entities.Coordinates;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.graphics.Sprite;

public class AIHigh extends  AI{
    Bomber _bomber;
    Enemy _e;
    int count = 0;

    public AIHigh(Bomber bomber, Enemy e) {
        _bomber = bomber;
        _e = e;
    }

    @Override
    public int calculateDirection() {
        count++;
        if (count >= 5 )
        {
            int TILES_SIZE = 16;
            double xa = _e.get_x(), ya = _e.get_y() - 16;
            if (_e.getDirection() == 0)
            {
                xa += TILES_SIZE / 2;
                ya += TILES_SIZE - 1;
            }
            if (_e.getDirection() == 1)
            {
                xa += 1;
                ya += TILES_SIZE / 2;
            }
            if (_e.getDirection() == 2)
            {
                xa += TILES_SIZE / 2;
                ya += 1;
            }
            if (_e.getDirection() == 3)
            {
                xa += TILES_SIZE - 1;
                ya += TILES_SIZE /2;
            }
            Entity e = null;
            int result = AStar.getResult(13, 31,  Coordinates.pixelToTile(xa), Coordinates.pixelToTile(ya));
            if (result != - 1)
            {
                return result;

            }
        }
        return random.nextInt(4);
    }
}
