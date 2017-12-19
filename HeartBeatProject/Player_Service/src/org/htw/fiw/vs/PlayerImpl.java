package src.org.htw.fiw.vs;

import java.rmi.RemoteException;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import rmi.interfaces.IPlayer;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import java.io.*;
import sun.audio.*;

public class PlayerImpl extends java.rmi.server.UnicastRemoteObject implements IPlayer {

	private static final long serialVersionUID = 1L;

	protected PlayerImpl() throws RemoteException {
		super();
	}

	@Override
	public void turnVolumeDownTo(int volume) throws RemoteException {
		/*String bip = "bip.mp3";
		Media hit = new Media(new File(bip).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();*/
		
		{
			//creates AudioInputStream
		    AudioInputStream audioInputStream = null;
		    Clip clip = null;
			try {
				//creates AudioInputStream
				audioInputStream = AudioSystem.getAudioInputStream(new File("/Users/Berlina/git/HeartBeatProject/Player_Service/resources/Explosion.wav"));
			} catch (UnsupportedAudioFileException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				try {
					clip = AudioSystem.getClip();
				} catch (LineUnavailableException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    try {
				clip.open(audioInputStream);
			} catch (LineUnavailableException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    //gets control for Volume change
		    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		    // Reduce volume by 10 decibels
		    gainControl.setValue(-10.0f); 
		    clip.start();
		  }
	
	}

	@Override
	public void killMusic() throws RemoteException {

	}

}