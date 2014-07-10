package rcm;

import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.math.*;
import java.nio.file.Files;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
public class RCMUser extends RCM {
	
	private String rcmDetailsNewFileName = "C:\\Users\\kavya.rc\\Desktop\\Data\\Temp.txt";
	private String rcmDetailsOldFileName = "C:\\Users\\kavya.rc\\Desktop\\Data\\RCMDetails.txt";
	private String recyclableListFilename = "C:\\Users\\kavya.rc\\Desktop\\Data\\RecyclableItems.txt";
	private String rcmListFileName = "C:\\Users\\kavya.rc\\Desktop\\Data\\RCMList.txt";
	private Integer itemId=0;
	String allData="";
	String tempData="";
	BufferedReader bufferReader = null;
    BufferedWriter bufferWriter = null;
    RCM rcmObject = new RCM();
    RCMUser rcmUserObject =new RCMUser();
	
	public RCMUser() {
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean dropItem(){
		double weightOfTheItem;
		double amountToBePaid;
		String itemTypeAdded;
		boolean dropStatus=false;
		
		try{
			//generate the weight of the item added
			weightOfTheItem= generateRandomWeight();
			//get the type of item added
			itemTypeAdded=getItemTypeAdded();
			//check if the input item is acceptable
			if(checkIfItemAccepted(itemTypeAdded)){
				//item is accepted if max capacity not reached
				//get the inCapacity of the RCM
				double inCapacity=getInCapacity(rcmObject.getMachineId());
				if(totalCapacity>=(weightOfTheItem+inCapacity)){
					inCapacity+=weightOfTheItem;
					//set the inCapacity in the database
					setInCapacity("RCM4",inCapacity);
					//calculate the amount to be paid
					amountToBePaid= calculateAmountToBePaid(itemTypeAdded, weightOfTheItem);
					double moneyAvailable= getMoneyAvailable("RCM4");
					moneyAvailable-=amountToBePaid;
					//set moneyAvailable in database
					setMoneyAvailable("RCM4", moneyAvailable);
					//check if the required cash is available or not
					/*if(checkIfCashAvailable(amountToBePaid)){
						System.out.println(" Cash Available");
					}
					else{
						//no cash available. Dispatch coupon.
						System.out.println(" Cash Not Available");
					}*/
					//call a method to make an entry in the file
					boolean status= makeAnEntry(itemTypeAdded,weightOfTheItem,amountToBePaid);
					if(status){
						dropStatus= true;
					}
					else
						dropStatus=false;

					
				}
				else{
					//max capacity reached
				}
		}
			
			else{
				//Item not acceptable
			}
			//check if maximum capacity of the RCM is reached or not
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			return dropStatus;
		}
	}
	
	public boolean makeAnEntry(String itemTypeAdded,Double weightOfTheItem,Double amountToBePaid){
		try{
			bufferReader = new BufferedReader(new FileReader(rcmDetailsOldFileName));
			bufferWriter = new BufferedWriter(new FileWriter(rcmDetailsNewFileName));
			String line;
			
			while ((line=bufferReader.readLine()) != null) {
				//System.out.println("hi");
				tempData=line+"\n";
				allData=allData.concat(tempData);
			}
			if(allData !=null){
				bufferWriter.write(allData+"\r\n");
			}
			int itemID=getLastItemId("RCM4");
				bufferWriter.write("Item"+itemID+":"+"RCM4"+":"+itemTypeAdded+":"+weightOfTheItem+":"+amountToBePaid+":"+ new Date()+"\r\n" );
			//bufferWriter.write("RCM4"+":"+itemTypeAdded+":"+weightOfTheItem+":"+amountToBePaid+":"+getMoneyAvailable("RCM4")+":"+ new Date()+"\r\n" );
				itemID++;
				setLastItemId("RCM4", itemID);
				
	            return true;
	            
			
			
		}
		catch(Exception e){
			return false;
			
		}
		finally{
			try {
	            if(bufferReader != null)
	               bufferReader.close();
	            
	         } catch (IOException e) {
	            //
	         }
	         try {
	            if(bufferWriter != null)
	               bufferWriter.close();
	            bufferWriter.flush();
	         } catch (IOException e) {
	            //
	         }
	         // Once everything is complete, delete old file..
	            File oldFile = new File(rcmDetailsOldFileName);
	            oldFile.setWritable(true);
	           oldFile.delete();
	            	//System.out.println("Deleted");
	            //}
	            

	            // And rename tmp file's name to old file name
	            File newFile = new File(rcmDetailsNewFileName);
	            newFile.renameTo(oldFile);
			
	         System.gc();
	         
		}
	}
	
	@SuppressWarnings("finally")
	public Double generateRandomWeight(){
		double weight=0.0;
		try{
		//Assuming the min wt of a single item dropped is 0.1 pound and max wt is 50 pound 
		double lower = 0.1;
		double upper = 50;
		weight= Math.random() * (upper - lower) + lower;
		
		//System.out.println(weight);
		
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			return weight;
		}
	}
	
	public Integer generateRandomItemType(){
		int randomNumber=0;;
		Random random= new Random();
		try{
			int range= pricePaidForEachItem.size();
			randomNumber = random.nextInt(range);
			
		}
		catch(Exception e){
			e.printStackTrace();
		}	
		return randomNumber;
	}
	
	public String getItemTypeAdded(){
		int index=generateRandomItemType();
		int counter=0;
		String itemType="";
		Iterator<String> keySetIterator = pricePaidForEachItem.keySet().iterator();
		while(keySetIterator.hasNext()){
				
			   String key = keySetIterator.next();
			   if(counter==index){
					itemType=key;
					break;
				}
			   else{
				   counter++;
			   }
		   }
		/*for(int i=0;i<pricePaidForEachItem.size();i++){
			if(counter==index){
				itemType=pricePaidForEachItem.
			}
			else{
				
			}
		}*/
		return itemType;
	}
	
	@SuppressWarnings("finally")
	public Double calculateAmountToBePaid(String itemType, Double weight){
		Double amountToBePaid=0.0;
		try{
			Double price=pricePaidForEachItem.get(itemType);
			amountToBePaid= price*weight;			
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(e.printStackTrace());
		}
		finally{
			return amountToBePaid;
		}
	}
	
	@SuppressWarnings("finally")
	public boolean checkIfCashAvailable(Double amountToBePaid,String rcmID){
		boolean result=false;
		try{
			double moneyAvailable= getMoneyAvailable("RCM4");
			if(moneyAvailable>=amountToBePaid){
				
				result=true;
			}
			else{
				result=false;
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(e.printStackTrace());
		}
		finally{
			return result;
		}
	}
	
	public Integer getLastItemId(String rcmID){
		int lastID=0;
		//Access RCM List file and get the money available
		File rcmListFile= new File(rcmListFileName);
		try {
			Scanner sc= new Scanner(rcmListFile);
			while(sc.hasNextLine()){
	             String line = sc.nextLine();
	             String[] details = line.split(":");
	             if(details[0].equals(rcmID)){
	            	 lastID=Integer.parseInt(details[5]);
	            	 break;
	             }
	         }
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lastID;
	}
	
	public void setLastItemId(String rcmID,Integer newID){
		int lastID=0;
		//Access RCM List file and get the money available
		File rcmListFile= new File(rcmListFileName);
		try {
			// Open the file that is the first
	        // command line parameter
	        FileInputStream fstream = new FileInputStream(rcmListFile);
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	        String strLine;
	        StringBuilder fileContent = new StringBuilder();
	        //Read File Line By Line
	        while ((strLine = br.readLine()) != null) {
	            // Print the content on the console
	           // System.out.println(strLine);
	            String tokens[] = strLine.split(":");
	            //String tokens1[] = strLine.split("|");
	            //System.out.println(tokens.length);
	            if (tokens.length > 0) {
	                // Here tokens[0] will have value of ID
	                if (tokens[0].equals(rcmID)) {
	                    tokens[5] = newID.toString();
	                   // tokens[2] = "499";
	                    String newLine = tokens[0] + ":" + tokens[1] + ":" + tokens[2] + ":" + tokens[3]+ ":"+tokens[4] + ":" + tokens[5]+":"+tokens[6];
	                    fileContent.append(newLine);
	                    fileContent.append("\r\n");
	                } else {
	                    // update content as it is
	                    fileContent.append(strLine);
	                    fileContent.append("\r\n");
	                }
	            }
	        }
	        // Now fileContent will have updated content , which you can override into file
	        FileWriter fstreamWrite = new FileWriter(rcmListFile);
	        BufferedWriter out = new BufferedWriter(fstreamWrite);
	        out.write(fileContent.toString());
	        out.close();
	        //Close the input stream
	       // in.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return moneyAvialable;
	}
	
	public double getInCapacity(String rcmID){
		double inCapacity=0.0;
		//Access RCM List file and get the money available
		File rcmListFile= new File(rcmListFileName);
		try {
			Scanner sc= new Scanner(rcmListFile);
			while(sc.hasNextLine()){
	             String line = sc.nextLine();
	             String[] details = line.split(":");
	             if(details[0].equals(rcmID)){
	            	 inCapacity=Double.parseDouble(details[4]);
	            	 break;
	             }
	         }
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return inCapacity;
	}
	
	public void setInCapacity(String rcmID,double capacity){
		int lastID=0;
		//Access RCM List file and get the money available
		File rcmListFile= new File(rcmListFileName);
		try {
			// Open the file that is the first
	        // command line parameter
	        FileInputStream fstream = new FileInputStream(rcmListFile);
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
	        String strLine;
	        StringBuilder fileContent = new StringBuilder();
	        //Read File Line By Line
	        while ((strLine = br.readLine()) != null) {
	            // Print the content on the console
	           // System.out.println(strLine);
	            String tokens[] = strLine.split(":");
	            //String tokens1[] = strLine.split("|");
	            //System.out.println(tokens.length);
	            if (tokens.length > 0) {
	                // Here tokens[0] will have value of ID
	                if (tokens[0].equals(rcmID)) {
	                    tokens[4] = Double.toString(capacity);
	                   // tokens[2] = "499";
	                    String newLine = tokens[0] + ":" + tokens[1] + ":" + tokens[2] + ":" + tokens[3]+ ":"+tokens[4] + ":" + tokens[5]+":"+tokens[6];
	                    fileContent.append(newLine);
	                    fileContent.append("\r\n");
	                } else {
	                    // update content as it is
	                    fileContent.append(strLine);
	                    fileContent.append("\r\n");
	                }
	            }
	        }
	        // Now fileContent will have updated content , which you can override into file
	        FileWriter fstreamWrite = new FileWriter(rcmListFile);
	        BufferedWriter out = new BufferedWriter(fstreamWrite);
	        out.write(fileContent.toString());
	        out.close();
	        //Close the input stream
	       // in.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return moneyAvialable;
	}

}
