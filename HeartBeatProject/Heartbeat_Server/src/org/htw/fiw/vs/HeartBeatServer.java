package org.htw.fiw.vs;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmi.interfaces.IHeartBeatObserver;
import rmi.interfaces.IHeartBeatSubject;


public class HeartBeatServer extends UnicastRemoteObject implements IHeartBeatObserver {

	private static final long serialVersionUID = 1L;

	protected HeartBeatServer() throws RemoteException {
		super();
	}

	public static void main(String[] args) {
		try {
			IHeartBeatSubject hbsubject = (IHeartBeatSubject) Naming.lookup("//localhost:8080/HeartBeat");

			HeartBeatServer hbserver = new HeartBeatServer();
			hbsubject.subscribeObject(hbserver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Commented out the player part because it is not implemented yet.
	@Override
	public void update(int heartrate) {
		try {
			// IPlayer player = (IPlayer) Naming.lookup("//localhost:8080/Player");
			if (heartrate < 79) {
				System.out.println("Kill music!");
				// player.killMusic();
			}
			if (heartrate > 79 && heartrate < 120) {
				int volume = heartrate / 2;
				System.out.println("Turn Volume down to: " + volume);
				// player.turnVolumeDownTo(volume);
			}
			else {
				System.out.println("Don't do anything!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

