package src.org.htw.fiw.vs;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;


import rmi.interfaces.IHeartBeatObserver;
import rmi.interfaces.IHeartBeatSubject;
import rmi.interfaces.IPlayer;

public class HeartBeatServer extends UnicastRemoteObject implements IHeartBeatObserver {

	private static final long serialVersionUID = 1L;
	private static IPlayer player;

	protected HeartBeatServer() throws RemoteException {
		super();
	}

	public static void main(String[] args) {
		try {
			IHeartBeatSubject hbsubject = (IHeartBeatSubject) Naming.lookup("//localhost:8080/HeartBeat");
			player = (IPlayer) Naming.lookup("//localhost:8080/Player");
			HeartBeatServer hbserver = new HeartBeatServer();
			hbsubject.subscribeObject(hbserver);
			player.startMusic();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Commented out the player part because it is not implemented yet.
	@Override
	public void update(int heartrate) {
		try {
			if (heartrate < 79) {
				System.out.println("Kill music!");
				player.killMusic();
			}
			else if (heartrate < 120) {
				player.turnVolumeDownTo(-50);
			}
			else if (heartrate > 120) {
				player.turnVolumeDownTo(3);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
