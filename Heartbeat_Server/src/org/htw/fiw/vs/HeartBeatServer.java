package src.org.htw.fiw.vs;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmi.interfaces.IHeartBeatObserver;
import rmi.interfaces.IHeartBeatSubject;
import rmi.interfaces.IPlayer;

public class HeartBeatServer extends UnicastRemoteObject implements IHeartBeatObserver {
	
	protected HeartBeatServer() throws RemoteException {
		super();
	}

	public static void main(String[] args) {
		try {
			IHeartBeatSubject hbsubject = (IHeartBeatSubject) Naming.lookup("//localhost:8080/HeartBeat");
			
			HeartBeatServer hbserver = new HeartBeatServer();
			hbsubject.subscribeObject(hbserver);
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void update(int heartrate) {
		System.out.println("Got heartrate: " + heartrate);
		
		try {
			IPlayer player = (IPlayer) Naming.lookup("//localhost:8080/Player");
			if(heartrate < 79) {
				player.killMusic();
			}
			if(heartrate > 79 && heartrate < 120) {
				int volume = heartrate/2;
				player.turnVolumeDownTo(volume);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}

