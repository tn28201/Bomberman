package uet.oop.bomberman.entities;

import javafx.scene.image.Image;

/**
 * Entity có hiệu ứng hoạt hình
 */
public abstract class AnimatedEntitiy extends Entity {

	protected final int MAX_ANIMATE = 7500;

	protected void animate() {
		if(_animated < MAX_ANIMATE) _animated++;
		else _animated = 0;
	}

}
