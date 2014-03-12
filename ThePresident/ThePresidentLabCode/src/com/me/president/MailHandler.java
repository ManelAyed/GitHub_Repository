package com.me.president;


import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






@SuppressWarnings("serial")
public class MailHandler extends HttpServlet{
	
    public void doPost(HttpServletRequest req, HttpServletResponse resp){ 
    	try {
    	Properties props = new Properties(); 
        Session session = Session.getDefaultInstance(props, null); 
        MimeMessage message = new MimeMessage(session, req.getInputStream());
        Comment.store((String)((Multipart)message.getContent()).getBodyPart(0).getContent()
            , ((InternetAddress)message.getFrom()[0]).getAddress());
        Message reply = message.reply(false);
        reply.setFrom(new InternetAddress("noreply@cloudpresident.appspotmail.com"));
        reply.setText("Thank you for your opinion.");
        Transport.send(reply);
		
    	} catch (MessagingException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      
        
    }

}
