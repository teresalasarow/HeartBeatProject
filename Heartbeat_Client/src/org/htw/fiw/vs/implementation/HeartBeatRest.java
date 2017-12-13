package org.htw.fiw.vs.implementation;

import java.rmi.RemoteException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

@Path("/HeartBeat")
public class HeartBeatRest {

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	public void getHeartBeat(String msg) throws RemoteException, ParseException {

		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(msg);
		String rateString = (String) json.get("rate");
		
		int heartrate = Integer.parseInt(rateString);
		HeartBeatImpl heartBeat = HeartBeatImpl.getInstance();

		heartBeat.setHeartBeat(heartrate);
	}

}
