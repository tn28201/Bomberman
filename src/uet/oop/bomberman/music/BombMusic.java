package uet.oop.bomberman.music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BombMusic {
	private AudioInputStream audioInputStream;
	private Clip clip;
	
	public void getMusic(String path) {
		try {
			audioInputStream = AudioSystem
					.getAudioInputStream(new File(path).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void bombExplosion() {
		getMusic("Pro_music/Sound/explosion.wav");
	}
	
	public void bombKillEnemy() {
		getMusic("Pro_music/Sound/explosion2.wav");
	}
}
