package project2;
import java.text.SimpleDateFormat;
//import java.util.Calendar;
import java.util.Date;
//import java.util.TimeZone;

public class Email {
	 //TODO private member variables
	private Date date;
	String messageID,subject,from,to,inReplyto;
	ListADT<String> body;
	ListADT<String> references;
	
	  public Email(Date date, String messageID, String subject, String from, String to, ListADT<String> body) {
	    //TODO implement constructor
		  this.date = date;
		  this.messageID = messageID;
		  this.subject = subject;
		  this.from = from;
		  this.to = to;
		  this.body = body;
	  }

	  public Email(Date date, String messageID, String subject, String from, String to, ListADT<String> body, String inReplyTo, ListADT<String> references) {
		  this.date = date;
		  this.messageID = messageID;
		  this.subject = subject;
		  this.from = from;
		  this.to = to;
		  this.body = body;
		  this.references = references;
		  this.inReplyto = inReplyTo;
	  }


	  public String getDate() {
	  	 SimpleDateFormat datewithouttime = new SimpleDateFormat("yyyyMMdd");
	  	 SimpleDateFormat sdf1 = new SimpleDateFormat("h:mm a");
	  	  SimpleDateFormat sdf2 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
		  String date= null;
		  Date now = new Date();
		 
		  if(datewithouttime.format(date).equals(datewithouttime.format(now))){
		  //SimpleDateFormat sdf = new SimpleDateFormat("h:mm a");
		  date = sdf1.format(this.date);
		  }
		  else{
			  //SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
			  date = sdf2.format(this.date);
		  }
	    return date; 
	  }

	  public String getMessageID() {
	    return messageID;
	  }

	  public String getSubject() {
	    return subject;
	  }

	  public String getFrom() {
	    return from;
	  }

	  public String getTo() {
	   return to;
	  }

	  public ListADT<String> getBody() {
	    return body;
	  }

	  public String getInReplyTo() {
	    return inReplyto;
	  }

	  public ListADT<String> getReferences() {
	   return references;
	  }
}
