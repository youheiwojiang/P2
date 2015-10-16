package project2;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TimeZone;
//import java.util.function.ToDoubleBiFunction;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
//import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.io.InputStream;
import java.lang.Integer;
import java.lang.NumberFormatException;

//import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

public class UWmail {
  private static UWmailDB uwmailDB = new UWmailDB();
  private static final Scanner stdin = new Scanner(System.in);

  public static void main(String args[]) {
    if (args.length != 1) {
      System.out.println("Usage: java UWmail [EMAIL_ZIP_FILE]");
      System.exit(0);
    }

    loadEmails(args[0]);

    displayInbox();
  }

  private static Date convert(String obj){
	  String[] active  = obj.split(" ");
	  TimeZone timezone = TimeZone.getTimeZone("GMT-05:00");
	  int date = Integer.parseInt(active[2]);
	  int year = Integer.parseInt(active[4]);
	  String []time = active[5].split(":");
	  int hour = Integer.parseInt(time[0]);
	  int minute =  Integer.parseInt(time[1]);
	  int second =  Integer.parseInt(time[2]);
	  String Month = "JanFebMarAprMayJunJulAugSepOctNovDec";
	  int  month = Month.indexOf(active[1])/3;
	  Calendar cal = Calendar.getInstance(timezone);
	  cal.set(year, month, date, hour, minute, second);
	  
	  return cal.getTime();
	  
  }
  
 /* private static boolean isReply(Scanner input){
	  String line = input.nextLine();
	  String[] active = line.split(" ");
	  if(active[0] .equals("Date:"))
		  return false;
	 
	  return true;
  }*/
  
  
  
  
  
  
  
  
  
  
  
  
  private static void loadEmails(String fileName) {
    //TODO
	 
      try (ZipFile zf = new ZipFile(fileName);) {
          //follow this approach for using <? extends ZipEntry>, even though we will not cover this in class.
          Enumeration<? extends ZipEntry> entries = zf.entries();
          while(entries.hasMoreElements()) {
            ZipEntry ze = entries.nextElement();
            if(ze.getName().endsWith(".txt")) {
            	
              ListADT<String>ref = new DoublyLinkedList<String>();
              ListADT<String>body = new DoublyLinkedList<String>();
           	  Date tmp = null;
              String inreplyto=null;
              InputStream in = zf.getInputStream(ze);
              Scanner sc = new Scanner(in);
             // System.out.println(ze.getName());
              //System.out.println("-----------------------------");
              String line = sc.nextLine();
        	  String[] active = line.split(" ");
        	  if(!active[0] .equals("Date:")){
        		  //ref.add(active[1]);
        		  inreplyto = active[1];
            	  line = sc.nextLine();//ListADT<Conversation> conversation = uwmailDB.getInbox();
              	//Iterator<Conversation> itr = conversation.iterator();
              	
            	  active = line.split(":");
            	  ref.add(active[1]);
            	  line = sc.nextLine();
            	  while(line.charAt(0)=='<'){
            		  ref.add(line);
            		  line = sc.nextLine(); 
            	  }
            	 // active = line.split("")
            	  tmp = convert(line);
            	  String []aa = {};
            	  for(int i=0;i<4;i++){
             		 line = sc.nextLine();
             		 aa[i]= line.split(" ")[1];
             	 }
            	  while(sc.hasNextLine()){
            		  body.add(sc.nextLine());
            	  }
            	  sc.close();
            	  uwmailDB.addEmail(new Email(tmp ,aa[0],aa[1],aa[2],aa[3],body,inreplyto,ref));
            	}
              else{
            	 //line = sc.nextLine();
            	  tmp = convert(line);
            	 line = sc.nextLine();
            	// String [] aa = new String[]();
            	 String[]aa = {};
            	 for(int i=0;i<4;i++){
            		 line = sc.nextLine();
            		 aa[i]= line.split(" ")[1];
            	 }
            	
            	 while(sc.hasNextLine()) {
            		 
            		 body.add(sc.nextLine());
            		 
            	 }
            	sc.close();
            	 uwmailDB.addEmail(new Email(tmp,aa[0], aa[1], aa[2], aa[3], body));
              }
            }
          }
        } catch (ZipException e) {
          System.out.println("A .zip format error has occurred for the file.");
          System.exit(1);
        } catch (IOException e) {
          System.out.println("An I/O error has occurred for the file.");
          System.exit(1);
        } catch (SecurityException e) {
          System.out.println("Unable to obtain read access for the file.");
          System.exit(1);
        }

	  

	  
	 
	
	  
	  
	  
	  
	  
  }

  private static void displayInbox(){
    boolean done = false;
    //TODO: print out the inbox here, according to the guidelines in the problem
    ListADT<Conversation>conversation = uwmailDB.getInbox();
    Iterator<Conversation>itr = conversation.iterator();
    SimpleDateFormat month = new SimpleDateFormat("MMM dd");
    Conversation tmp = null;
    int init = 0;
    while(itr.hasNext()){
    	tmp = itr.next();
    	System.out.println("["+init+"] "+tmp.get(0).getSubject()+" ("+ month.format(tmp.get(0).getDate())+")");
    }
    
    
    
    while (!done) 
    {
      System.out.print("Enter option ([#]Open conversation, [T]rash, " + 
          "[Q]uit): ");
      String input = stdin.nextLine();

      if (input.length() > 0) 
      {

        int val = 0;
        boolean isNum = true;

        try {
          val = Integer.parseInt(input);
        } catch (NumberFormatException e) {
          isNum = false;
        }

        if(isNum) {
          if(val < 0) {
            System.out.println("The value can't be negative!");
            continue;
          } else if (val >= uwmailDB.size()) {
            System.out.println("Not a valid number!");
            continue;
          } else {
            displayConversation(val);
            continue;
          }
          
        }
            
        if(input.length()>1)
        {
          System.out.println("Invalid command!");
          continue;
        }

        switch(input.charAt(0)){
          case 'T':
          case 't':
            displayTrash();
            break;

          case 'Q':
          case 'q':
            System.out.println("Quitting...");
            done = true;
            break;

          default:  
            System.out.println("Invalid command!");
            break;
        }
      } 
    } 
    System.exit(0);
  }

  private static void displayTrash(){
    boolean done = false;
    //TODO: print out the trash here according to the problem specifications
    //
    while (!done) 
    {
      System.out.print("Enter option ([I]nbox, [Q]uit): ");
      String input = stdin.nextLine();

      if (input.length() > 0) 
      {
        if(input.length()>1)
        {
          System.out.println("Invalid command!");
          continue;
        }

        switch(input.charAt(0)){
          case 'I':
          case 'i':
            displayInbox();
            break;

          case 'Q':
          case 'q':
            System.out.println("Quitting...");
            done = true;
            break;

          default:  
            System.out.println("Invalid command!");
            break;
        }
      } 
    } 
    System.exit(0);
  }

  private static void displayConversation(int val) {
    //TODO: Check whether val is valid as a conversation number. If not, take
    //the user back to the inbox view and continue processing commands.
    //
    boolean done = false;
    //TODO: Print the conversation here, according to the problem specifications
    //
    while (!done) 
    {
      System.out.print("Enter option ([N]ext email, [P]revious email, " + 
          "[J]Next conversation, [K]Previous conversation, [I]nbox, [#]Move " +
          "to trash, [Q]uit): ");
      String input = stdin.nextLine();

      if (input.length() > 0) 
      {

        if(input.length()>1)
        {
          System.out.println("Invalid command!");
          continue;
        }

        switch(input.charAt(0)){
          case 'P':
          case 'p':
            //TODO: for this conversation, move the current email pointer back 
            //  using Conversation.moveCurrentBack().
            
        	  
        	  
        	  
        	
        	
            displayConversation(val);
            break;
          case 'N':
          case 'n':
            //TODO: for this conversation, move the current email pointer 
            //  forward using Conversation.moveCurrentForward().
            //
            displayConversation(val);
            break;
          case 'J':
          case 'j':
            //TODO: Display the next conversation
            break;

          case 'K':
          case 'k':
            //TODO: Display the previous conversation
            break;

          case 'I':
          case 'i':
            displayInbox();
            return;

          case 'Q':
          case 'q':
            System.out.println("Quitting...");
            done = true;
            break;

          case '#':
            //TODO: add delete conversation functionality. This conversation
            //should be moved to the trash when # is entered, and you should
            //take the user back to the inbox and continue processing input.
            //
            return;

          default:  
            System.out.println("Invalid command!");
            break;
        }
      } 
    } 
    System.exit(0);
  }

}
