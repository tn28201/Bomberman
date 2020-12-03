package uet.oop.bomberman.input;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Balloon;
import uet.oop.bomberman.entities.character.enemy.Oneal;
import uet.oop.bomberman.entities.tile.Brick;
import uet.oop.bomberman.entities.tile.Grass;
import uet.oop.bomberman.entities.tile.Portal;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.item.BombItem;
import uet.oop.bomberman.entities.tile.item.FlameItem;
import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.graphics.Sprite;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class LoadLevel {
    public Scanner scanner;
    public void load(int level) {
        try {
            scanner = new Scanner(new FileReader("res/levels/level" + level + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        scanner.nextInt();
        BombermanGame.HEIGHT = scanner.nextInt();
        BombermanGame.WIDTH = scanner.nextInt();
        scanner.nextLine();
        createMap();
    }

    public void createMap() {
        for (int i = 0; i < BombermanGame.HEIGHT; i++) {
            String r = scanner.nextLine();
            for (int j = 0; j < BombermanGame.WIDTH; j++) {
                if (r.charAt(j) == '#') {
                    BombermanGame.stillObjects.add(new Wall(j, i, Sprite.wall.getFxImage()));
                } else {
                    BombermanGame.stillObjects.add(new Grass(j, i, Sprite.grass.getFxImage()));
                    if (r.charAt(j) == '*') {
                        BombermanGame.stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (r.charAt(j) == 'x') {
                        BombermanGame.stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                        BombermanGame.stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (r.charAt(j) == '1') {
                        BombermanGame.enemies.add(new Balloon(j, i, Sprite.balloom_left1.getFxImage()));
                    }
                    if (r.charAt(j) == '2') {
                        BombermanGame.enemies.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                    }
                    if (r.charAt(j) == 'b') {
                        BombermanGame.stillObjects.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                        BombermanGame.stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (r.charAt(j) == 'f') {
                        BombermanGame.stillObjects.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                        BombermanGame.stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (r.charAt(j) == 's') {
                        BombermanGame.stillObjects.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                        BombermanGame.stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                    }
                    if (r.charAt(j) == 'p'){
                        BombermanGame.player = new Bomber(j, i, Sprite.player_right.getFxImage());
                    }
                }
            }
        }
        BombermanGame.stillObjects.sort(this::compare);
    }

    public int compare(Entity o1, Entity o2) {
        return Integer.compare(o2.getPriority(), o1.getPriority());
    }
}
