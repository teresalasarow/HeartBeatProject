package src.org.htw.fiw.vs;

import java.rmi.RemoteException;
import rmi.interfaces.IPlayer;

public class PlayerImpl extends java.rmi.server.UnicastRemoteObject implements IPlayer {

	private static final long serialVersionUID = 1L;

	protected PlayerImpl() throws RemoteException {
		super();
	}

	@Override
	public void turnVolumeDownTo(int volume) throws RemoteException {

	}

	@Override
	public void killMusic() throws RemoteException {

	}

}
