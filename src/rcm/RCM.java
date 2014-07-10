package rcm;

import data.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class RCM {
	private String machineId;
	private String location;
	static ArrayList<String>itemsAccepted= new ArrayList<String>();	
	static HashMap<String, Double> pricePaidForEachItem = new HashMap<String, Double>();
	static HashMap<String, String> locationAssociatedRCM = new HashMap<String, String>();
	final double totalCapacity;
	private double inCapacity=0;
	private Date machineEmptied;
	private double moneyAvailable=1000;
	//private String recyclableListFilename = "..\\..\\..\\Data\\RecyclableItems.txt";
	private String recyclableListFilename = "C:\\Users\\kavya.rc\\Desktop\\Data\\RecyclableItems.txt";
	private String rcmListFileName = "C:\\Users\\kavya.rc\\Desktop\\Data\\RCMList.txt";
	//choose weight in metric units
	
	public RCM(){
		totalCapacity=1000;
		machineEmptied= new Date();
	}
	
	public void setLocation(String locationName){
		location=locationName;
	}
	public String getLocation(){
		return location;
	}
	public void setMachineId(String id){
		machineId=id;
	}
	public String getMachineId(){
		return machineId;
	}
	
	
	@SuppressWarnings("finally")
	public HashMap displayItemsAcceptedDetails(){
		
		try{
			File recyclableFile = new File(recyclableListFilename);		
			if(recyclableFile.exists()){
				Scanner inFile= new Scanner(recyclableFile);
				while(inFile.hasNextLine()){
					//itemsAccepted= new String[(int)recyclableFile.length()];
					String line = inFile.nextLine();
					String[] fileData = line.split("[:]+");
					pricePaidForEachItem.put(fileData[0], Double.parseDouble(fileData[1]));
					itemsAccepted.add(fileData[0]);
				}
			//System.out.println(" Recyclable File  found");
			inFile.close();	
		}
		else{
			//System.out.println("Recyclable File not found");
		}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println(e.printStackTrace());
		}
		finally{
			return pricePaidForEachItem;	
		}
	}
	
	@SuppressWarnings("finally")
	public boolean checkIfItemAccepted(String itemType){
		//Check if the accepted item type is acceptable
		boolean result=false;
		try{
		for(int i=0;i<itemsAccepted.size();i++){
			if(itemsAccepted.get(i).equalsIgnoreCase(itemType)){
				result= true;
				break;
			}
			else{
				result= false;
			}
		}
		}
		finally{
			return result;
		}
	}
	
public Double getMoneyAvailable(String rcmID){
	Double moneyAvialable=0.0;
	//Access RCM List file and get the money available
	File rcmListFile= new File(rcmListFileName);
	try {
		Scanner sc= new Scanner(rcmListFile);
		while(sc.hasNextLine()){
             String line = sc.nextLine();
             String[] details = line.split(":");
             if(details[0].equals(rcmID)){
            	 moneyAvialable=Double.parseDouble(details[3]);
            	 break;
             }
         }
		
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return moneyAvialable;
}

public void setMoneyAvailable(String rcmID, Double money){
	Double moneyAvialable=0.0;
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
            //System.out.println(strLine);
            String tokens[] = strLine.split(":");
            //String tokens1[] = strLine.split("|");
            //System.out.println(tokens.length);
            if (tokens.length > 0) {
                // Here tokens[0] will have value of ID
                if (tokens[0].equals(rcmID)) {
                    tokens[3] = money.toString();
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

public HashMap getLocationAssociatedRCM(){
		
	//Access RCM List file and get the money available
	File rcmListFile= new File(rcmListFileName);
	
	try {
		Scanner sc= new Scanner(rcmListFile);
		while(sc.hasNextLine()){
             String line = sc.nextLine();
             String[] details = line.split(":");
             locationAssociatedRCM.put(details[0],details[2]);         	 
             }
         }
		
	catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return locationAssociatedRCM;
}
	
	/*public Double getRCMTotalCapacity(String machineId){
		try{
			File rcmListFile = new File(rcmListFileName);
			if(rcmListFile.exists()){
				Scanner inFile= new Scanner(rcmListFile);
				//while(inFile.)
				System.out.println(" RCM List File  found");
			}
			else{
				System.out.println(" RCM List File not found");
			}
			
		}
		finally{
			
		}
	}*/
	
	
}


