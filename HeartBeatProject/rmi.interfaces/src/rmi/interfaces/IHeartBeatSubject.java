package rmi.interfaces;



public interface IHeartBeatSubject extends java.rmi.Remote {
	
	public void subscribeObject(IHeartBeatObserver observer)
			throws java.rmi.RemoteException;
	
	public void removeObject(IHeartBeatObserver observer)
			throws java.rmi.RemoteException;
	
	public void notifyObservers()
			throws java.rmi.RemoteException;
	
	public int getHeartBeat()
			throws java.rmi.RemoteException;
	
}
