package com.books.utilities;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class MailUtil {
	
		public static void sendMessage(String subject, String text, String destinataire, String copyDest) { 

		// 1 -> Création de la session 
	    Properties properties = new Properties(); 
	    properties.setProperty("mail.transport.protocol", "smtp");
	    properties.setProperty("mail.smtp.starttls.enable", "true");
	    properties.setProperty("mail.smtp.host", "smtp.gmail.com"); 
	    properties.setProperty("mail.smtp.port", "587");
	    properties.setProperty("mail.smtp.user", "tkieffer67@gmail.com"); 
	    Session session = Session.getInstance(properties); 
	 
	 // 2 -> Création du message 
	    MimeMessage message = new MimeMessage(session); 
	    try { 
	        message.setText(text); 
	        message.setSubject(subject); 
	        message.addRecipients(Message.RecipientType.TO, destinataire); 
	        message.addRecipients(Message.RecipientType.CC, copyDest); 
	    } catch (MessagingException e) { 
	        e.printStackTrace(); 
	    } 
	    
	 // 3 -> Envoi du message 
	    
	    Transport transport = null;
	    try { 
	    	transport = session.getTransport("smtp");
	        transport.connect("tkieffer67@gmail.com", "OFLjul74"); 
	        transport.sendMessage(message, new Address[] { new InternetAddress(destinataire), 
	                                                        new InternetAddress(copyDest) }); 
	    } catch (MessagingException e) { 
	        e.printStackTrace(); 
	    } finally { 
	        try { 
	            if (transport != null) { 
	                transport.close(); 
	            } 
	        } catch (MessagingException e) { 
	            e.printStackTrace(); 
	        } 
	    } 
	} 
}
