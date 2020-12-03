package uet.oop.bomberman.entities.bomb;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.tile.Brick;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.music.BombMusic;
import uet.oop.bomberman.music.BomberMusic;

import java.awt.*;


public class BomBang extends Entity {
    private int radius = 1, lengthExplode;
    private int direction, vertical, horizontal ;
    private boolean last;
    public BomBang(int x, int y, Image image, int direction){
        super(x, y);
        this._img = image;
        this.direction = direction;
    }

    public BomBang(int x, int y, Image image){
        super(x, y);
        this._img = image;
    }

    public BomBang(int x, int y){
        super(x,y);
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public void update(){
        if(_animated < 20){
            _animated ++;
            if(vertical ==0 && horizontal ==0) {
                _img = Sprite.movingSprite(Sprite.bomb_exploded, Sprite.bomb_exploded1,
                        Sprite.bomb_exploded2, _animated, 20).getFxImage();
            }
            else if (vertical == 0 && horizontal ==1) {
                _img = Sprite.movingSprite(Sprite.explosion_horizontal, Sprite.explosion_horizontal1
                        ,Sprite.explosion_horizontal2,_animated,20).getFxImage();
            }
            else if (vertical ==0 && horizontal ==2) {
                _img = Sprite.movingSprite(Sprite.explosion_horizontal_right_last, Sprite.explosion_horizontal_right_last1
                        ,Sprite.explosion_horizontal_right_last2, _animated,20).getFxImage();
            }
            else if (vertical == 0 && horizontal == -2) {
                _img = Sprite.movingSprite(Sprite.explosion_horizontal_left_last, Sprite.explosion_horizontal_left_last1
                        ,Sprite.explosion_horizontal_left_last2, _animated,20).getFxImage();
            }
            else if (vertical ==1 && horizontal ==0) {
                _img = Sprite.movingSprite(Sprite.explosion_vertical, Sprite.explosion_vertical1
                        ,Sprite.explosion_vertical2, _animated,20).getFxImage();
            }
            else if (vertical ==2 && horizontal ==0) {
                _img = Sprite.movingSprite(Sprite.explosion_vertical_top_last, Sprite.explosion_vertical_top_last1
                        ,Sprite.explosion_vertical_top_last2, _animated,20).getFxImage();
            }
            else if(vertical == -2 && horizontal ==0) {
                _img = Sprite.movingSprite(Sprite.explosion_vertical_down_last, Sprite.explosion_vertical_down_last1
                        ,Sprite.explosion_vertical_down_last2, _animated,20).getFxImage();

            }
        }else
            BombermanGame.bomBangs.remove(this);
    }

    public void render(){
        FlameSegment();
    }



    @Override
    public boolean collide(Entity e) {
        // vị trí của Bomb đã nổ
        if (e instanceof Bomber)
            return false;
        if (e instanceof Enemy)
            return false;
        return true;
    }
    public boolean getCollide(Entity e) {
        // TODO: xử lý va chạm với Bomber, Enemy. Chú ý đối tượng này có vị trí chính là
        // vị trí của Bomb đã nổ
        if (e instanceof Bomber)
            return false;
        if (e instanceof Enemy)
            return false;
        return true;
    }

    private void FlameSegment(){

        if(isFlameItem) radius= 2;

        for (int i = 0; i < radius; i++) {
            Rectangle rectangle = new Rectangle(_x + 32*(i + 1), _y, 32, 32);
            /*
            if (collisionWall(rectangle)) {
                lengthExplode = i;
            }else {
                if(!isFlameItem) lengthExplode = i + 1;
                else lengthExplode= i+2;
            }
             */
            if(collisionWall(rectangle) && i==0) {
                lengthExplode= 0;
                break;
            }
            else if(collisionBrick(rectangle) && i==0) {
                lengthExplode= 1;
                break;
            }
            else if(!collisionWall(rectangle) && i==0) lengthExplode= 1;
            else if(collisionWall(rectangle) && i==1) lengthExplode= 1;
            else lengthExplode= 2;

        }
        BombermanGame.bomBangs.add(new BomBang(_x, _y, Sprite.bomb_exploded.getFxImage(),0));
        for(int i = 0; i < lengthExplode; i++){
            BomBang bomBang = new BomBang(_x + 32*(i + 1), _y);
            if(i == lengthExplode - 1) {
                bomBang._img = Sprite.explosion_horizontal_right_last.getFxImage();
                bomBang.vertical = 0;
                bomBang.horizontal =2;
            } else{
                bomBang._img = Sprite.explosion_horizontal.getFxImage();
                bomBang.vertical = 0;
                bomBang.horizontal =1;
            }
            BombermanGame.bomBangs.add(bomBang);
        }


        for (int i = 0; i < radius; i++) {
            Rectangle rectangle = new Rectangle(_x - 32*(i + 1), _y, 32, 32);
            if(collisionWall(rectangle) && i==0) {
                lengthExplode= 0;
                break;
            }
            else if(collisionBrick(rectangle) && i==0) {
                lengthExplode= 1;
                break;
            }
            else if(!collisionWall(rectangle) && i==0) lengthExplode= 1;
            else if(collisionWall(rectangle) && i==1) lengthExplode= 1;
            else lengthExplode= 2;
        }

        for(int i = 0; i < lengthExplode; i++){
            BomBang bomBang = new BomBang(_x - 32*(i + 1), _y);
            if(i == lengthExplode - 1) {
                bomBang._img = Sprite.explosion_horizontal_left_last.getFxImage();
                bomBang.vertical = 0;
                bomBang.horizontal = -2;
            } else{
                bomBang._img = Sprite.explosion_horizontal.getFxImage();
                bomBang.vertical = 0;
                bomBang.horizontal = 1;
            }
            BombermanGame.bomBangs.add(bomBang);
        }

        for (int i = 0; i < radius; i++) {
            Rectangle rectangle = new Rectangle(_x, _y - 32*(i + 1), 32, 32);

            if(collisionWall(rectangle) && i==0) {
                lengthExplode= 0;
                break;
            }
            else if(collisionBrick(rectangle) && i==0) {
                lengthExplode= 1;
                break;
            }
            else if(!collisionWall(rectangle) && i==0) lengthExplode= 1;
            else if(collisionWall(rectangle) && i==1) lengthExplode= 1;
            else lengthExplode= 2;

        }

        for(int i = 0; i < lengthExplode; i++){
            BomBang bomBang = new BomBang(_x , _y - 32*(i + 1));
            if(i == lengthExplode - 1) {
                bomBang._img = Sprite.explosion_vertical_top_last.getFxImage();
                bomBang.vertical = 2;
                bomBang.horizontal =0;
            } else{
                bomBang._img = Sprite.explosion_vertical.getFxImage();
                bomBang.vertical = 1;
                bomBang.horizontal = 0;
            }
            BombermanGame.bomBangs.add(bomBang);
        }


        for (int i = 0; i < radius; i++) {
            Rectangle rectangle = new Rectangle(_x, _y + 32*(i + 1), 32, 32);
            if(collisionWall(rectangle) && i==0) {
                lengthExplode= 0;
                break;
            }
            else if(collisionBrick(rectangle) && i==0) {
                lengthExplode= 1;
                break;
            }
            else if(!collisionWall(rectangle) && i==0) lengthExplode= 1;
            else if(collisionWall(rectangle) && i==1) lengthExplode= 1;
            else lengthExplode= 2;
        }
        for(int i = 0; i < lengthExplode; i++){
            BomBang bomBang = new BomBang(_x, _y + 32*(i + 1));
            if(i == lengthExplode - 1) {
                bomBang._img = Sprite.explosion_vertical_down_last.getFxImage();
                bomBang.vertical = -2;
                bomBang.horizontal = 0;
            } else{
                bomBang._img = Sprite.explosion_vertical.getFxImage();
                bomBang.vertical = 1;
                bomBang.horizontal =0;
            }
            BombermanGame.bomBangs.add(bomBang);
        }
    }

    private BombMusic MusicBomb = new BombMusic();
    public void isCollision() {
        Rectangle r1 = this.getRectangle();
        for (int j = 0; j < BombermanGame.stillObjects.size(); j++) {
            Rectangle r2 = BombermanGame.stillObjects.get(j).getRectangle();
            if(r1.intersects(r2)  )
                BombermanGame.stillObjects.get(j).set_alive(false);
        }
        for(int j = 0; j <BombermanGame.enemies.size(); j++){
            Rectangle r2 = BombermanGame.enemies.get(j).getRectangle();
            if(r1.intersects(r2)) {
                BombermanGame.enemies.get(j).set_alive(false);
                MusicBomb.bombKillEnemy();
            }

        }
        Rectangle r2 = BombermanGame.player.getRectangle();
        if(r1.intersects(r2)) {
            BombermanGame.player.set_alive(false);
            BombermanGame.player.dead();

            BombermanGame.player = new Bomber(32, 32, Sprite.player_right.getFxImage());
        }
    }

    public boolean collisionBrick(Rectangle rectangle){
        for(Entity entity : BombermanGame.stillObjects){
            Rectangle r2 = entity.getRectangle();
            if(rectangle.intersects(r2)){
                if(entity instanceof Brick)
                    return true;
            }
        }
        return false;
    }

}
