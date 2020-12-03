package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;

public abstract class Tile extends Entity {
    public Tile(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    public boolean collide(Entity e) {
        return false;
    }

    @Override
    public boolean getCollide(Entity e) {
        return collide(e);
    }
}
