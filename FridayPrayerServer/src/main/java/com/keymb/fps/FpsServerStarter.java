package com.keymb.fps;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FpsServerStarter {

	final static Logger logger = LoggerFactory
			.getLogger(FpsServerStarter.class);

	public static void main(String[] args) throws Exception {
		// We will create our server running at http://localhost:8070

		Util util = new Util();

		String port = "2727" ;//util.getConfig("serverPort");

		Server server = new Server(Integer.valueOf(port));

		logger.info("WurflServer Starting Port Number :{}", port);

		Context root = new Context(server, "/", Context.SESSIONS);
		root.addServlet(new ServletHolder(new FpsServlet()), "/fps");

		server.start();
		server.join();
	}

}