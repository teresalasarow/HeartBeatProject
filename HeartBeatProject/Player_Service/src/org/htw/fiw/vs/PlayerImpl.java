package src.org.htw.fiw.vs;

import java.rmi.RemoteException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import org.htw.fiw.vs.heartbeat.IPlayer;

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
		AudioInputStream audioInputStream = null;
		try {
			// creates AudioInputStream
			audioInputStream = AudioSystem.getAudioInputStream(
					new File("/Users/Shared/Reallusion/Template/CrazyTalk Animator 3 Template/Sound/Partners In Rhyme/Sparrow.WAV"));
		} catch (UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
		try {
			this.clip = AudioSystem.getClip();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		try {
			this.clip.open(audioInputStream);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}
		this.clip.start();
		this.clip.loop(10);
		
	}

	@Override
	public void killMusic() throws RemoteException {
		this.clip.stop();
	}

	@Override
	public void playTone(int value) throws RemoteException {
		byte[] buf = new byte[ 1 ];;
	    AudioFormat af = new AudioFormat((float )44100, 8, 1, true, false );
	    SourceDataLine sdl;
		try {
			sdl = AudioSystem.getSourceDataLine( af );
			sdl.open();
		    sdl.start();
		    for( int i = 0; i < 1000 * (float )44100 / 1000; i++ ) {
		        double angle = i / ( (float )44100 / 440 ) * value * Math.PI;
		        buf[ 0 ] = (byte )( Math.sin( angle ) * 100 );
		        sdl.write( buf, 0, 1 );
		    }
		    sdl.drain();
		    sdl.stop();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
}