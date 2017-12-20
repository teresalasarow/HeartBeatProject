package src.org.htw.fiw.vs;

import java.rmi.RemoteException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import rmi.interfaces.IPlayer;
import java.io.*;

public class PlayerImpl extends java.rmi.server.UnicastRemoteObject implements IPlayer {

	private static final long serialVersionUID = 1L;
	Clip clip;
	

	public Clip getClip() {
		return clip;
	}

	public void setClip(Clip clip) {
		this.clip = clip;
	}

	protected PlayerImpl() throws RemoteException {
		super();
	}

	@Override
	//public void turnVolumeDownTo(int volume) throws RemoteException {
		// gets control for Volume change
	//	FloatControl gainControl = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
		// Reduce volume by 10 decibels
	//	gainControl.setValue(-40.0f);

	//}

	public void turnVolumeDownTo(int volume) throws RemoteException {
		// gets control for Volume change
		FloatControl gainControl = (FloatControl) this.clip.getControl(FloatControl.Type.MASTER_GAIN);
		System.out.println("Set volume to: " + (float) volume);
		float max = gainControl.getMaximum();
        float min = gainControl.getMinimum(); // negative values all seem to be zero?
        float range = max - min;
        System.out.println(max);
        System.out.println(min);
        System.out.println(range);
	    gainControl.setValue((float) volume);
	}

	
	@Override
	public void startMusic() {
		// creates AudioInputStream
		//Clip newClip = getClip();
		AudioInputStream audioInputStream = null;
		try {
			// creates AudioInputStream
			audioInputStream = AudioSystem.getAudioInputStream(
					new File("/Users/leas/Desktop/Heartbeat_Projekt/Player_Service/resources/Sparrow.WAV"));
		} catch (UnsupportedAudioFileException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.clip.open(audioInputStream);
		} catch (LineUnavailableException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.clip.start();
		this.clip.loop(10);
		
	}

	@Override
	public void killMusic() throws RemoteException {
		this.clip.stop();
	}

}
