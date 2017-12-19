package src.org.htw.fiw.vs;

public interface SpotifyService extends java.rmi.Remote {
	
		public int adaptVolume(int heartbeat)
				throws java.rmi.RemoteException;
}
