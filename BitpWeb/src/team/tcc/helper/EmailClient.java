package team.tcc.helper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.json.JSONObject;



public class EmailClient {
	 
		private static Logger logger = Logger.getLogger(EmailClient.class) ;
	    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	    private static final String SMTP_AUTH_USER = "teamtcc6@gmail.com";
	    private static final String SMTP_AUTH_PWD = "@password@";
		private String methodname = "" ;
		
		public JSONObject postMail(String toRecipients[],String ccRecipients[],String bccRecipients[], String subject, String message) throws IOException, MessagingException{
			methodname = "postMail";
			logger.info(this.getClass()+" Entry--->"+methodname);
			JSONObject jObjR = new JSONObject();
			Properties props = new Properties();
			props.put("mail.smtp.host", SMTP_HOST_NAME);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable","true");
			props.put("mail.smtp.port", 587); // as required by Gmail
			props.put("mail.smtp.debug", "true");
			
			Authenticator auth = new SMTPAuthenticator();
			Session session = Session.getInstance(props,auth);
			
			//create a message
			Message msg = new MimeMessage(session);
			
			//Set the from 
			InternetAddress addressFrom = new InternetAddress(SMTP_AUTH_USER);
			msg.setFrom(addressFrom);
			//setting to address
			InternetAddress[] addressTo = new InternetAddress[toRecipients.length];
			for (int i = 0; i < toRecipients.length; i++) {
				addressTo[i] = new InternetAddress(toRecipients[i]);
		    }
		    msg.setRecipients(Message.RecipientType.TO, addressTo);
		    InternetAddress[] addressCC = new InternetAddress[ccRecipients.length];
		    if(ccRecipients.length > 0){
		    	for(int i = 0; i < ccRecipients.length; i++ ){
		    		addressCC[i] = new InternetAddress(ccRecipients[i]);
		    	}
		    	msg.setRecipients(Message.RecipientType.CC, addressCC);
		    	
		    }
		    //Setting bcc 
		    InternetAddress[] addressBCC = new InternetAddress[bccRecipients.length];
		    
		    if(bccRecipients.length > 0){
		    	for(int i = 0; i < bccRecipients.length; i++){
		    		addressBCC[i] = new InternetAddress(bccRecipients[i]);
		    	}
		    	msg.setRecipients(Message.RecipientType.BCC, addressBCC);
		    }
		    
		    // Setting the Subject and Content Type
	        msg.setSubject(subject);
	      //use a MimeMultipart as we need to handle the file attachments
	        Multipart multipart = new MimeMultipart();        
	        //add the message body to the mime message
	        BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setText(message);
	        multipart.addBodyPart(messageBodyPart);        
	        // add any file attachments to the message
	        //addAtachments(attachments, multipart);
	        
	        // Put all message parts in the message
	        msg.setContent(multipart);
	        
	        String html = message;

	        // HTMLDataSource is an inner class
            msg.setDataHandler(new DataHandler(new HTMLDataSource(html)));
	        
	        
	        Transport.send(msg);
			
			logger.info(this.getClass()+" Exit--->"+methodname);
			try{
			
			
			jObjR.put("status_code", "200");
			jObjR.put("msg", "Email sent successfully");
			return jObjR;
			}catch(Exception e){
				e.printStackTrace();
				
			}
			
		    return jObjR;
		    
		        
			
		}
	    public void postMail(String recipients[], String subject, String message) throws  IOException, MessagingException {
			methodname = "postMail" ;
	    	logger.info(this.getClass()+" Entry--->"+methodname);
	        //boolean debug = false;

	        //Set the host smtp address
	       Properties props = new Properties();
	       props.put("mail.smtp.host", SMTP_HOST_NAME);
	       props.put("mail.smtp.auth", "true");
	       props.put("mail.smtp.starttls.enable","true");
	       props.put("mail.smtp.port", 587); // as required by Gmail
	       props.put("mail.smtp.debug", "true");

	        Authenticator auth = new SMTPAuthenticator();
	        Session session = Session.getInstance(props, auth);
	     
	        //session.setDebug(debug);

	        // create a message
	        Message msg = new MimeMessage(session);

	        // set the from and to address
	        InternetAddress addressFrom = new InternetAddress(SMTP_AUTH_USER);
	        msg.setFrom(addressFrom);

	        InternetAddress[] addressTo = new InternetAddress[recipients.length];
	        for (int i = 0; i < recipients.length; i++) {
	            addressTo[i] = new InternetAddress(recipients[i]);
	        }
	        msg.setRecipients(Message.RecipientType.TO, addressTo);
	        
	        

	        // Setting the Subject and Content Type
	        msg.setSubject(subject);
	       // msg.setContent(message, "text/plain");
	        
	        //use a MimeMultipart as we need to handle the file attachments
	        Multipart multipart = new MimeMultipart();        
	        //add the message body to the mime message
	        BodyPart messageBodyPart = new MimeBodyPart();
	        messageBodyPart.setText(message);
	        multipart.addBodyPart(messageBodyPart);        
	        // add any file attachments to the message
	        //addAtachments(attachments, multipart);
	        
	        // Put all message parts in the message
	        msg.setContent(multipart);
	        
	       
	        
	        String html = "<html><head><title>" +
            msg.getSubject() +
            "</title></head><body>" +
            "<div>"+message+"</div> <br/><br/><br/>"+
            "<div><font color=\"#666666\"> Thanks</font></div><br/>"+"<div><font color=\"#666666\"> BITP Admin</font></div>"+
            "</body></html>";

	        // HTMLDataSource is an inner class
            msg.setDataHandler(new DataHandler(new HTMLDataSource(html)));
	        
	        
	        Transport.send(msg);
			
			logger.info(this.getClass()+" Exit--->"+methodname);
	    }//end of postMail

	    
	    static class HTMLDataSource implements DataSource {
	        private String html;

	        public HTMLDataSource(String htmlString) {
	            html = htmlString;
	        }

	        // Return html string in an InputStream.
	        // A new stream must be returned each time.
	        public InputStream getInputStream() throws IOException {
	            if (html == null) throw new IOException("Null HTML");
	            return new ByteArrayInputStream(html.getBytes());
	        }

	        public OutputStream getOutputStream() throws IOException {
	            throw new IOException("This DataHandler cannot write HTML");
	        }

	        public String getContentType() {
	            return "text/html";
	        }

	        public String getName() {
	            return "JAF text/html dataSource to send e-mail only";
	        }
	    }
	    private class SMTPAuthenticator extends javax.mail.Authenticator {

	        public PasswordAuthentication getPasswordAuthentication() {
	            String username = SMTP_AUTH_USER;
	            String password = SMTP_AUTH_PWD;
	            return new PasswordAuthentication(username, password);
	        }
	    }

	    protected void addAtachments(String[] attachments, Multipart multipart)
	    throws MessagingException, AddressException
		{
		for(int i = 0; i<= attachments.length -1; i++)
		{
		String filename = attachments[i];
		MimeBodyPart attachmentBodyPart = new MimeBodyPart();
		
		//use a JAF FileDataSource as it does MIME type detection
		DataSource source = new FileDataSource(filename);
		attachmentBodyPart.setDataHandler(new DataHandler(source));
		
		//assume that the filename you want to send is the same as the
		//actual file name - could alter this to remove the file path
		attachmentBodyPart.setFileName(filename);
		
		//add the attachment
		multipart.addBodyPart(attachmentBodyPart);
		}
		}
}
