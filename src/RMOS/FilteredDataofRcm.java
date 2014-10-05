package RMOS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Scanner;

public class FilteredDataofRcm extends Observable{
/*
 * defines three functions: get the station names,itemsacceptable,activestations	
 */
	   
	 String RCMDetails="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/RCMDetails.txt";
	 String RCMList="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/RCMList.txt";
	 String RecylableItems="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/RecyclableItems.txt";
	
	BufferedReader brRCMDetails = null;
	BufferedReader brRCMList = null;
	BufferedReader brRecylableItems=null;
	 
	
	//constructor
	FilteredDataofRcm()
	 {
	 }
	 
	//station in group 
	//station not in group
	
	public ArrayList<String> getStationInGroup()
	{
		File rcmListFile= new File(RCMList);
		ArrayList<String> rcmIdList = new ArrayList<String>();
		try{
			try {
				Scanner sc= new Scanner(rcmListFile);
				while(sc.hasNextLine()){
		             String line = sc.nextLine();
		             String[] details = line.split("\\|");
		             String id= details[0];
		             System.out.println(id);
		             rcmIdList.add(id);				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		finally{
			
		}
		//System.out.println(rcmIdList);
		return rcmIdList;
	}

	//items acceptable to remove from list
	ArrayList<String> getItemsAcceptable()
	{
			File rcmListFile= new File(RecylableItems);
		ArrayList<String> rcmIdList = new ArrayList<String>();
		try{
			try {
				Scanner sc= new Scanner(rcmListFile);
				while(sc.hasNextLine()){
		             String line = sc.nextLine();
		             String[] details = line.split("\\|");
		             //String id= details[0];
		             rcmIdList.add(details[0]);		
		             
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		finally{
			
		}
		//System.out.println(rcmIdList);
		setChanged();
		notifyObservers(rcmIdList);
		return rcmIdList;
		
	}
	 //change item Panel components
	 //station active   
	   //activate panel
	//inactive stations
	
	public ArrayList<String> getActiveStation()
	{  ArrayList<String> activeStations=new ArrayList<String>();
		 try{
				brRCMList = new BufferedReader(new FileReader(RCMList));

			String line=" ";
			String[] filedata=new  String[10];
			int i=0;
			while((line=brRCMList.readLine())!=null)
			{
				if(line.contains("Inactive"))
				{
					
					filedata[i]=line;
					String temp=filedata[i];
					String[] st = temp.split("\\|");
			        activeStations.add(st[0]);
			        System.out.println("InActive stations......."+activeStations.get(i));
				      
					i++;
					
				}
			}
			
			//extract the stations from it
		//	for(int i1=0;filedata[i1]!=null;i++)
	    //    {
	    //    String temp=filedata[i1];
	       // Scanner s=new Scanner(temp);
	        //String nextline = s.nextLine(); 
	     //   String[] st = temp.split("[:]+");
	     //   activeStations.add(st[0]);
	        
	    //    }
			
			}
			catch(Exception e)
			{
				
			}
			finally{
				if(brRCMList != null)
					try {
						brRCMList.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			     		
			}
			return activeStations;
			}
		 
	public ArrayList<String> getInfoOfAllRcm(String s2)
	{  ArrayList<String> Stations=new ArrayList<String>();
		 try{
				brRCMList = new BufferedReader(new FileReader(RCMList));

			String line=" ";
			String[] filedata=new  String[10];
			int i=0;
			while((line=brRCMList.readLine())!=null)
			{
				if(line.contains(s2))	
				{	filedata[i]=line;
					
					Stations.add(line);
					i++;
				}	
				
			}
			
			//extract the stations from it
		/*	for(int i1=0;filedata[i1]!=null;i++)
	        {
	        String temp=filedata[i1];
	       // Scanner s=new Scanner(temp);
	        //String nextline = s.nextLine(); 
	        String[] st = temp.split("[:]+");
	        activeStations.add(st[0]);
	        
	       System.out.print("Active stations"+activeStations.get(i));
	        }*/
			
			}
			catch(Exception e)
			{
				
			}
			finally{
				if(brRCMList != null)
					try {
						brRCMList.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			     		
			}
			return Stations;
			}
	
			
	
	
		 }

	
	
