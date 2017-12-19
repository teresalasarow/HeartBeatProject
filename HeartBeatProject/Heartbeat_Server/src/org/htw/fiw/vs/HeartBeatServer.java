package org.htw.fiw.vs;

import java.rmi.Naming;
import java.rmi.RemoteException;

import rmi.interfaces.IHeartBeatObserver;
import rmi.interfaces.IHeartBeatSubject;


public class HeartBeatServer implements IHeartBeatObserver {
	public static void main(String[] args) {
		try {
			IHeartBeatSubject hbsubject = (IHeartBeatSubject) Naming.lookup("//localhost:8080/HeartBeat");
			HeartBeatServer hbserver = new HeartBeatServer();
			hbsubject.subscribeObject(hbserver);
		} catch (Exception e){
			System.out.println(e);
		}
	}

	@Override
	public void update(int heartrate) throws RemoteException {
		System.out.println("Got heartrate: " + heartrate);
		
	}
}

