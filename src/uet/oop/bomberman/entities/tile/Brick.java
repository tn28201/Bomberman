package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends Tile {
    public Brick(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
        _alive = true;
    }


    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public void update() {
        if(is_alive() == false) {
            if (_animated < 30) {
                _animated++;
                _img = Sprite.movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2, _animated, 30).getFxImage();
            }
            else {
                BombermanGame.stillObjects.remove(this);
            }
        }
    }
}
