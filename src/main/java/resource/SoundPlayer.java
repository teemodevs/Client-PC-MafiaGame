package resource;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import exception.io.sound.SoundLoadException;

/**
 * 사운드 파일을 읽어서 소리를 재생시킴 
 */
public class SoundPlayer {
	private Clip clip;
	private AudioInputStream audioInputStream;
	
	public SoundPlayer(String filePath) {
		try {
			audioInputStream = AudioSystem.getAudioInputStream(ResourceLoader.getFileResource(filePath));
			clip = AudioSystem.getClip();
		} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
			e.printStackTrace();
			throw new SoundLoadException("Soundfile '" + filePath + "' Load failed");
		}
	}
	
	/**
	 * 사운드 재생 (1회)
	 */
	public SoundPlayer play() {
		try {
			clip.open(audioInputStream);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
			throw new SoundLoadException("Soundfile Play failed");
		}
		clip.start();
		return this;	
	}
	
	/**
	 * 사운드 무한 반복 재생 설정
	 */
	public SoundPlayer repeat() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
		return this;
	}
	
	/**
	 * 사운드 정지
	 */
	public void stop() {
		clip.stop();
		clip.close();
	}
	
}
