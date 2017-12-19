package src.org.htw.fiw.vs;

import java.rmi.RemoteException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SpotifyServiceImpl extends java.rmi.server.UnicastRemoteObject implements SpotifyService {
	protected SpotifyServiceImpl() throws RemoteException {
		super();
	}


	@Override
	public int adaptVolume(int heartbeat) throws RemoteException {
		return(heartbeat*2);
	}
}
