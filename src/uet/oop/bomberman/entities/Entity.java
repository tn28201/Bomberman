package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    protected int _x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    protected int _y;
    protected Image _img;
    protected int _animated = 0;
    protected boolean _alive;
    protected static boolean isBombItem= false, isFlameItem= false, isSpeedItem= false;
    //protected static int FlameItemCount=0;

    public Entity() {
    }

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity(int xUnit, int yUnit, Image img) {
        this._x = xUnit * Sprite.SCALED_SIZE;
        this._y = yUnit * Sprite.SCALED_SIZE;
        this._img = img;
    }

    public Entity(int _x, int _y) {
        this._x = _x;
        this._y = _y;
    }

    public static boolean collisionWall(Rectangle rectangle){
        for(Entity entity : BombermanGame.stillObjects){
            Rectangle r2 = entity.getRectangle();
            if(rectangle.intersects(r2)){
                if(entity instanceof Wall)
                    return true;
            }
        }
        return false;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(_img, _x, _y);
    }

    public int get_x() {
        return _x;
    }

    public void set_x(int _x) {
        this._x = _x;
    }

    public int get_y() {
        return _y;
    }

    public void set_y(int _y) {
        this._y = _y;
    }

    public boolean is_alive() {
        return _alive;
    }

    public void set_alive(boolean _alive) {
        this._alive = _alive;
    }

    public Rectangle getRectangle() {
        return new Rectangle(_x, _y, Sprite.SCALED_SIZE, Sprite.SCALED_SIZE);
    }

    public abstract void update();

    public abstract int getPriority();


    public int getXTile() {
        return Coordinates.pixelToTile(_x + 16);
    }

    public int getYTile() {
        return Coordinates.pixelToTile(_y - 16);
    }


    public abstract boolean collide(Entity e);

    public abstract boolean getCollide(Entity e);
}
