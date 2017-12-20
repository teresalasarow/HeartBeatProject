package org.htw.fiw.vs.implementation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Timer;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class HeartBeatClient implements Remote {

	List<Integer> beats = Arrays.asList(121, 120, 100, 90, 85, 83, 80, 90, 79, 90);
	Integer o = 1;
	
	public static void main(String[] args) {
		try {
			HeartBeatImpl heartBeat = HeartBeatImpl.getInstance();
			Timer timer = new Timer(1000, new MyTimerActionListener());
			
			timer.start();
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {}
			timer.stop();
			
			/*System.out.println(heartBeat.getHeartBeat());
			heartBeat.setHeartBeat(90);
			System.out.println(heartBeat.getHeartBeat());*/
			Registry registry = LocateRegistry.createRegistry(8080);
			registry.bind("HeartBeat", heartBeat);
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

class MyTimerActionListener implements ActionListener {
	  public void actionPerformed(ActionEvent e) {

	    System.out.println("asdf");

	  }
	}
