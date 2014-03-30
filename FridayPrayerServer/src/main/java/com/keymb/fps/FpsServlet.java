package com.keymb.fps;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FpsServlet extends HttpServlet {


	final static Logger logger = LoggerFactory.getLogger(FpsServlet.class);


	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String x = req.getParameter("x");
		String y = req.getParameter("y");
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");

//		String remoteId = req.getRemoteAddr();
//		String remoteHost = req.getRemoteHost();
		
		logger.debug("Request Parameters . X {} ,Y {}", x,
				y);

		

		try {
			// ApplicationContext context = new ClassPathXmlApplicationContext(
			// "application-context.xml");


			StringBuffer xmlStr = new StringBuffer();
			xmlStr.append("deneme");
			
			logger.debug("Response Message {}", xmlStr.toString());
			out.print(xmlStr.toString());
		} catch (Exception e) {
			logger.error("Error while processing", e);
			out.print("ERROR");
		}

	}
}