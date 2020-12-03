package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;

import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.music.BombMusic;

import java.awt.*;

public class Bomb extends Character {
    public Bomb(int x, int y, Image image) {
        super(x, y, image);
    }
    private BombMusic Music = new BombMusic();
    @Override
    public void update() {
        if (_animated != 120) {
            _animated++;
        } else {
            //if(FlameItemCount > 0) FlameItemCount = FlameItemCount - 1;
            //else isFlameItem= false;
            BomBang e = new BomBang(_x, _y);
            Music.bombExplosion();
            e.render();
            _alive = false;
        }
        _img = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animated, 60).getFxImage();
    }


    public boolean collide(Character character) {
        Rectangle rec1 = getRectangle();
        Rectangle rec2;
        if (character instanceof Bomber) {
            Bomber bomber = (Bomber) character;
            rec2 = new Rectangle(bomber.get_x() + 5, bomber.get_y() + 5, Sprite.SCALED_SIZE * 3 / 4, Sprite.SCALED_SIZE * 3 / 4);
        } else {
            rec2 = new Rectangle(character.get_x(), character.get_y(), Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
        }
        return rec1.intersects(rec2);
    }

    @Override
    public int getPriority() {
        return 2;
    }

    public void collisions() {
        for (Enemy enemy : BombermanGame.enemies) {
            Rectangle r2 = enemy.getRectangle();
            for (Bomb bomb : BombermanGame.player.getBombs()) {
                Rectangle r3 = bomb.getRectangle();
                if (!bomb.collide(enemy) && r2.intersects(r3)) {
                    enemy.stand();
                    break;
                }
            }
        }
    }
}