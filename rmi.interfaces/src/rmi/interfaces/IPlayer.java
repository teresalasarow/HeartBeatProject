package rmi.interfaces;

public interface IPlayer extends java.rmi.Remote  {
	
	public void turnVolumeDownTo(int volume)
			throws java.rmi.RemoteException;
	
	public void killMusic()
			throws java.rmi.RemoteException;
 
}
