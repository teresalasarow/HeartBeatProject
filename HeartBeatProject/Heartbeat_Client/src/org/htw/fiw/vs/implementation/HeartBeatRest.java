package org.htw.fiw.vs.implementation;

import java.rmi.RemoteException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/HeartBeat")
public class HeartBeatRest {
	
	//no GET request - only POST request: Gets heartbeat number from frontend
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.TEXT_PLAIN})
	public String getHeartBeat(String msg) throws RemoteException {
		System.out.println("Heartbeat set to: "+ msg);
		
		
		//new HeartBeatImpl().setHeartBeat(heartbeat);
		
		return "ok";
	}

}
