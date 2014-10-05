package RMOS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class FilteredDataForUsageStatistics {
	 String RCMDetails="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/RCMDetails.txt";
	 String RCMList="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/RCMList.txt";
	 String RecylableItems="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/RecyclableItems.txt";
	 String ClearDetails="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/ClearDetails.txt";
	  	
	BufferedReader brRCMDetails = null;
	BufferedReader brRCMList = null;
	BufferedReader brRecylableItems=null;
	ArrayList<String>emptiedMachine=new ArrayList<String>();

	public double getWeightAddedInMachine(String machineId)
	{
		double totalWeight=0;
		try {
			brRCMList = new BufferedReader(new FileReader(RCMDetails));
			String line=" ";
			String[] filedata=new  String[30];
			int i=0;
			ArrayList<String> data=new ArrayList<String>();
			try {
				while((line=brRCMList.readLine())!=null)
				{
					if(line.contains(machineId))
					{
						
						filedata[i]=line;
						String temp=filedata[i];
						String[] st = temp.split("\\|");
				        data.add(st[3]); //weight  .......to string
				        System.out.println(data.get(i));
						i++;
						
					}
						
					
				}
				
				for(int j=0;j<data.size();j++)
				{
					totalWeight=totalWeight+Double.parseDouble(data.get(j)); //toString
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalWeight;
	}
	
	
	/*public double getTotalCashInMachine(String machineId)
	{
		double totalCash=0;
		try {
			brRCMList = new BufferedReader(new FileReader(RCMList));
			String line=" ";
			String[] filedata=new  String[10];
			int i=0;
			ArrayList<String> data=new ArrayList<String>();
			try {
				while((line=brRCMList.readLine())!=null)
				{
					if(line.contains(machineId))
					{
						
						filedata[i]=line;
						String temp=filedata[i];
						String[] st = temp.split("\\|");
				        data.add(st[4]); //weight     
						i++;
						
					}
						
					
				}
				
				for(int j=0;j<data.size();i++)
				{
					totalCash=totalCash+Integer.parseInt(data.get(j));
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return totalCash;
		
		
	}*/

	
	
	/*public double getTotalCashInMachine(String machineId)
	{
		double totalCash=0;
		String temp1=" ";
		try {
			brRCMList = new BufferedReader(new FileReader(RCMList));
			String line=" ";
			String[] filedata=new  String[30];
			int i=0;
			//ArrayList<String> data=new ArrayList<String>();
			try {
				while((line=brRCMList.readLine())!=null)
				{
					if(line.contains(machineId))
					{
						
						filedata[i]=line;
						String temp=filedata[i];
						String[] st = temp.split("[|]+");
				        //data.add(st[7]); //cash     
				        //System.out.println(data.get(i));
						temp1=st[6];
						System.out.println(temp1);
						totalCash=Double.parseDouble(temp1);
						break;
						//System.out.println(temp1);
						
					}
						
					
				}
				
				//for(int j=0;j<data.size();i++)
				//{
				//	totalCash=totalCash+Integer.parseInt(data.get(j));
			//	}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(totalCash);
		return totalCash;
		
		
	}*/

	public double lastEmptiedCount(String machineId,double duration)
	{
		//double totalCash=0;
		double counter = 0;
		try {
			brRCMList = new BufferedReader(new FileReader(RCMList));
			String line=" ";
			String[] filedata=new  String[10];
			int i=0;
			
			String temp1;
			double temp;
			ArrayList<String> data=new ArrayList<String>();
			try {
				while((line=brRCMList.readLine())!=null)
				{
					if(line.contains(machineId))
					{
						
						filedata[i]=line;
						temp1=filedata[i];
						String[] st = temp1.split("\\|");
				        data.add(st[5]); //time     
						i++;
						
					}
						
					
				}
				
				for(int j=0;j<data.size();i++)
				{
					temp=Double.parseDouble(data.get(j));
					if(temp<duration)
					{
						counter++;
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return counter;
		
		
	}
	
	
	public int getDate(String machineId, int time){
		//double money=getTotalAmountToBePaid();
		int counter=0;
		//String clearDetailsFileName="C:\\Users\\kavya.rc\\Desktop\\Data\\ClearDetails.txt";
		//Access RCM List file 
				File clearListFile= new File(ClearDetails);
				try {
					//int i=0;
					Scanner sc= new Scanner(clearListFile);
					while(sc.hasNextLine()){
			             String line = sc.nextLine();
			             String[] details = line.split("\\|");
			             emptiedMachine.add(details[0]);
			             if(details[0].equals(machineId)){
			            	 String date = details[1];
			            	 DateFormat df = new SimpleDateFormat("E MMM dd kk:mm:ss z yyyy");
			            	 
			            	 try {
								Date result =   df.parse(date);
								Date date1= new Date();
								
								//in milliseconds
								long diff = date1.getTime() - result.getTime();
					 
								long diffSeconds = diff / 1000 % 60;
								long diffMinutes = diff / (60 * 1000) % 60;
								long diffHours = diff / (60 * 60 * 1000) % 24;
								long diffDays = diff / (24 * 60 * 60 * 1000);
					 
								if(((diffHours*60)+diffMinutes)<=time){
									counter++;
								}
								System.out.print(diffDays + " days, ");
								System.out.print(diffHours + " hours, ");
								System.out.print(diffMinutes + " minutes, ");
								System.out.print(diffSeconds + " seconds."+"\n");
								
								//System.out.println(result);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
			            	 
			             }
			             //i++;
			         }
					
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return counter;
	}
	
	ArrayList<String>getEmptiedMachine(){
		return emptiedMachine;
		
	}
	
	public double getCashDispatched(String rcmID){
		double moneyAvailable=0.0;
		double  moneyDispatched=0.0;
		//Access RCM List file 
		File rcmListFile= new File(RCMList);
		try {
			Scanner sc= new Scanner(rcmListFile);
			while(sc.hasNextLine()){
	             String line = sc.nextLine();
	             String[] details = line.split("\\|");
	             if(details[0].equals(rcmID)){
	            	 moneyAvailable=Double.parseDouble(details[3]);
	            	 moneyDispatched= 1000-moneyAvailable;
	            	 moneyDispatched=Math.round(moneyDispatched*100.0)/100.0;
	            	 break;
	             }
	         }
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return moneyDispatched;
	}
}
