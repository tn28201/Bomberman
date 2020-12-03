package uet.oop.bomberman.entities.character.ai;

public class AILow extends AI {

    @Override
    public int calculateDirection() {
        int randomInt = (int) (Math.random() * 4);
        return randomInt;
    }

}
