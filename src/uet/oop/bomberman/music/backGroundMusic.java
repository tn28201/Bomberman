package uet.oop.bomberman.music;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class backGroundMusic {
	private AudioInputStream audioInputStream;
	private Clip clip;
	
	public void getMusic(String path) {
		try {	
			
			audioInputStream = AudioSystem
					.getAudioInputStream(new File(path).getAbsoluteFile());
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void runBackGroundMusic() {
		getMusic("Pro_music/Stage_Theme.wav");
	}

	public void runEndGameMusic() {
		clip.stop();
		getMusic("Pro_music/Game_Over.wav");
	}

	public void runWinGameMusic() {
		clip.stop();
		getMusic("Pro_music/Ending_Theme.wav");
	}

	public void bonusStageMusic() {
		getMusic("Pro_music/Bonus_Stage.wav");
	}
	public void stop1() {
		clip.stop();
	}
}
