package org.htw.fiw.vs;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.htw.fiw.vs.heartbeat.IHeartBeatObserver;
import org.htw.fiw.vs.heartbeat.IHeartBeatSubject;
import org.htw.fiw.vs.heartbeat.IPlayer;


public class HeartBeatServer extends UnicastRemoteObject implements IHeartBeatObserver {

	private static final long serialVersionUID = 1L;
	private static IPlayer player;

	protected HeartBeatServer() throws RemoteException {
		super();
	}

	public static void main(String[] args) {
		try {			
			Registry registry = LocateRegistry.getRegistry("141.45.152.61", 1099);
			IBinder binder = (IBinder) registry.lookup("binder");
											
			IHeartBeatSubject hbsubject = (IHeartBeatSubject) binder.lookup("team3/HeartBeat");
			
			player = (IPlayer) binder.lookup("team3/PlayerService");
	
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
			if (heartrate <= 79) {
				System.out.println("Kill music!");
				player.killMusic();
			}
			else if (heartrate < 120) {
				float x;
				float y;
				x = heartrate / 0.6f;
				y = -214 + x;
				player.turnVolumeDownTo(Math.round(y));
			}
			else if (heartrate >= 120) {
				int x, y;
				x = heartrate / 6;
				y = -34 + x; 	
				player.turnVolumeDownTo(y);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}