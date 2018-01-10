package org.htw.fiw.vs.implementation;

import java.net.URI;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.htw.fiw.vs.IBinder;

public class HeartBeatClient implements Remote {

	public static void main(String[] args) {
		try {
			HeartBeatImpl heartBeat = HeartBeatImpl.getInstance();
			Registry registry = LocateRegistry.getRegistry("141.45.152.61", 1099);
			//Registry registry = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
			IBinder binder = (IBinder) registry.lookup("binder");
					
			binder.bind("team3/HeartBeat", heartBeat);
			
			//Registry registry = LocateRegistry.createRegistry(8080);
			//registry.bind("HeartBeat", heartBeat);
			HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:9080"),
					new ResourceConfig(HeartBeatRest.class));

			System.out.println("Press any key to close");
			System.in.read();
			server.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}