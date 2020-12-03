package uet.oop.bomberman.entities.tile;

import javafx.scene.image.Image;

public class Wall extends Tile{
    public Wall(int xUnit, int yUnit, Image img) {
        super(xUnit, yUnit, img);
    }

    @Override
    public int getPriority() {
        return 4;
    }

    @Override
    public void update() {

    }
}
