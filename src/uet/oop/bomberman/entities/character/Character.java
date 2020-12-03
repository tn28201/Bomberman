package uet.oop.bomberman.entities.character;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.AnimatedEntitiy;
import uet.oop.bomberman.entities.Entity;

import java.awt.*;

public abstract class Character extends AnimatedEntitiy {
    protected int _moveX,_moveY;
    protected int _moveLength ;
    protected Image img;

    public Character(int x, int y, Image image) {
        this._x = x*32;
        this._y = y*32;
        this._img = image;
        _alive = true;
    }
    public void active() {
        _x = _moveX;
        _y = _moveY;
    }
    public void stand() {
        _moveX = _x;
        _moveY = _y;
    }
    public Rectangle getRectangle() {
        return new Rectangle(_moveX, _moveY, 32, 32);
    }
    public void set_moveLength(int _moveLength) {
        this._moveLength = _moveLength;
    }
    public void left() {
        _moveX = _x - _moveLength;
    }
    public void right() {
        _moveX = _x + _moveLength;
    }
    public void up() {
        _moveY = _y - _moveLength;
    }
    public void down() {
        _moveY =_y + _moveLength;
    }

    @Override
    public boolean collide(Entity e) {
        return true;
    }

    @Override
    public boolean getCollide(Entity e) {
        return true;
    }
    public void setImg(Image img) {
        this.img = img;
    }
}
