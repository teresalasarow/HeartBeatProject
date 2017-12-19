package org.htw.fiw.vs.implementation;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import rmi.interfaces.IHeartBeatObserver;
import rmi.interfaces.IHeartBeatSubject;

public class HeartBeatImpl extends java.rmi.server.UnicastRemoteObject implements IHeartBeatSubject {

	private static HeartBeatImpl instance = null;

	protected HeartBeatImpl() throws RemoteException {
		super();
	}

	public static HeartBeatImpl getInstance() throws RemoteException {
		if (instance == null) {
			instance = new HeartBeatImpl();
		}
		return instance;
	}

	private static final long serialVersionUID = 1L;
	int heartbeat;

	List<IHeartBeatObserver> observerList = new ArrayList<IHeartBeatObserver>();

	@Override
	public int getHeartBeat() throws RemoteException {
		return this.heartbeat;
	}

	// die funktion k�nnte von dem REST call aufgerufen werden? Und w�rde dann den
	// state setzen und die beaobachter benachrichtigen?
	public void setHeartBeat(int heartbeat) throws RemoteException {
		this.heartbeat = heartbeat;
		notifyObservers();
	}

	@Override
	public void subscribeObject(IHeartBeatObserver observer) throws RemoteException {
		this.observerList.add(observer);
	}

	@Override
	public void removeObject(IHeartBeatObserver observer) throws RemoteException {
		this.observerList.remove(observer);

	}

	@Override
	public void notifyObservers() throws RemoteException {
		for (IHeartBeatObserver observer : this.observerList) {
			observer.update(this.heartbeat);
		}
	}
}
