package RMOS;

//import java.awt.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Map;
import java.io.*;

import javax.swing.JPanel;

public class RMOS {
/*
 * The administrator logs in with a username.
 Adds a recycling station to the group to be monitored.
 Sets (change) the list of recyclable items for the recycling machines.
This may include adding new item types, removing item types,
changing the price and so on.
 Activates the recycling station in the group to accept items.
 Displays the statistics of usage for the RCM group as described above.
 */
/*
 * A graphical interface, where each of the individual machines in the group (monitored by this workstation) can be represented. 
 * 
 -Each machine is identified by its location and an id. 
 -Each machine should store the time of the last removal of its items.
-Add a new machine to the group.
-Remove a machine from the group. (Not required for single person
teams)
- Check the operational status of each machine.
- Change/add new types of recyclable items. (Not required for single
person teams)
- Change the price of an item. (Not required for single person teams)
- Check the amount of money in a specific RCM.
- Check the current (and available) capacity (by weight or volume) of an
RCM. This indicates whether an RCM is full and has to be emptied.
- Get number of items returned by a specific machine (or all the machines
in the group) in a month.
- Show the time a specific RCM was emptied last time.
- Get the location of recycling station and id of the machine that was used
the most (in the last n days) (Not required for single person teams)
- Display the usage statistics for each RCM in the RCM group. This will include the total weight of recycled
 items by machine (per day, week ..), total value (cash or coupons) issued, using a visualization (a graph for
 */
	//List listOfItems= new List();
	ArrayList<String>stationsInGroup=new ArrayList<String>();
//	ArrayList<String>stationsNotInGroup=new ArrayList<String>();
	ArrayList<String>listOfRecylableItems=new ArrayList<String>();
	HashMap<String, Double> itemInfoInAllMachines=new HashMap<String, Double>();
	HashMap<String, Boolean>machineNameWithState=new HashMap<String, Boolean>();
		 
	RMOS()
	{
		
	}
	//login class 
	
	//display of station info in text area
	
	
	//add a recycle station
	void addRecycleStation(String stationToBeAdded,String location,String capacity) throws IOException
	{
			
		
		boolean flag1=false;
		
		//String fileName="RCMList.txt";
		 String oldfileName="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/RCMList.txt";
		 String newfileName="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/temp.txt";
		String allData=" ";
		String tempData=" ";
		BufferedReader br = null;
      BufferedWriter bw = null;
      try {
        
		br = new BufferedReader(new FileReader(oldfileName));
		bw = new BufferedWriter(new FileWriter(newfileName));
         String line;
        String line1;
         while ((line=br.readLine()) != null) {
        	 
        	
            if (line.contains(stationToBeAdded)==true)
            {
             flag1=true;
             br.close();
             break;
             //tempData=line;
             
            	//  line = line.replace("Inactive", "Active");
            }
            else
            {
            //	tempData=line.concat("\n");
            //allData=allData.concat(tempData);
           //tempData=line.concat("\n"); 	
            	bw.write(line);
            	bw.write("\n");
            }
            
                 
            		
         }
         line1=stationToBeAdded+"|Inactive"+"|"+location+"|"+capacity+"|"+"1000|0|0|0";
         bw.write(line1);  
     	
       //  bw.write(allData);
       //  bw.write("\n");
           
     //  if(flag1==true)
     //   {
     //   	while((line1=br.readLine())!=null)
     //   	{
      //  		bw.write(line1);   
        		//tempData=line1;
        		//allData=allData.concat(tempData);
      //  	}
      //  	System.out.println(allData);
      //  }
       // else
       //     	if(flag1==false)
       //     {
            		 	 
       //     		line=stationToBeAdded+":Inactive"+":"+location+":"+capacity+":";
       //     	bw.write(allData+"\n");
       //         bw.write(line+"\n");
               
                
        // }
      } catch (Exception e) {
         return;
      }
      finally {
         try {
            if(br != null)
               br.close();
         } catch (IOException e) {
            //
         }
         try {
            if(bw != null)
               bw.close();
         } catch (IOException e) {
            //
         }
      }
      // Once everything is complete, delete old file..
      File oldFile = new File(oldfileName);
      oldFile.delete();

      // And rename tmp file's name to old file name
      File newFile = new File(newfileName);
      newFile.renameTo(oldFile);
   
		
			//machineNameWithState.put(stationToBeAdded,false);		
		
	
}
	
	
	//change the list of recyclable item
	void setListOfRecyclableItems(String recylableItem,String rate)
	{
	
boolean flag1=false;
		
		//String fileName="RCMList.txt";
		 String oldfileName="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/RecyclableItems.txt";
		 String newfileName="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/temp.txt";
		String allData="";
		String tempData="";
		BufferedReader br = null;
      BufferedWriter bw = null;
      try {
        
		br = new BufferedReader(new FileReader(oldfileName));
		bw = new BufferedWriter(new FileWriter(newfileName));
         String line;
       String line1;
         while ((line=br.readLine()) != null) {
        	 
        	
            if (line.contains(recylableItem)==true)
            {
            	//item already present
             flag1=true;
             br.close();
             break;
             //tempData=line;
             
            	//  line = line.replace("Inactive", "Active");
            }
            else
            {
            	tempData=line.concat("\n");
            allData=allData.concat(tempData);
            
            	
            }
              
            		
         }
         if(flag1==true)
         {
         	while((line1=br.readLine())!=null)
         	{
         		tempData=line1;
         		allData=allData.concat(tempData);
         	}
         	System.out.println(allData);
         }
         else  
      
            	if(flag1==false)
            {
            		 	 
            	line=recylableItem+"|"+rate;
            	bw.write(allData);
                bw.write(line+"\n");
               
                
         }
      } catch (Exception e) {
         return;
      }
      finally {
         try {
            if(br != null)
               br.close();
         } catch (IOException e) {
            //
         }
         try {
            if(bw != null)
               bw.close();
         } catch (IOException e) {
            //
         }
      }
      // Once everything is complete, delete old file..
      File oldFile = new File(oldfileName);
      oldFile.delete();

      // And rename tmp file's name to old file name
      File newFile = new File(newfileName);
      newFile.renameTo(oldFile);
   
		
			//machineNameWithState.put(stationToBeAdded,false);		
      /*	boolean flag=false;
		for(int i=0;i<itemInfoInAllMachines.size();i++)
		{
			if(itemInfoInAllMachines.get(recylableItem) != null)
			{
				//station already in group
				flag=true;
				break;
			}
		
		}
		if(flag==false)
		{
			//listOfRecylableItems.add(recylableItem);
			itemInfoInAllMachines.put(recylableItem, (double) 0);
		}
		*/
	
}
		
		
		
		
	
	
	//removing item types
	void removeItemForRecylableItemsList(String item)
	{
		
		
		//String fileName="RCMList.txt";
		 String oldfileName="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/RecyclableItems.txt";
		 String newfileName="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/temp.txt";
		//String allData=" ";
		String tempData=" ";
		BufferedReader br = null;
      BufferedWriter bw = null;
      try {
        
		br = new BufferedReader(new FileReader(oldfileName));
		bw = new BufferedWriter(new FileWriter(newfileName));
         String line;
         //String line1;
         while ((line=br.readLine()) != null) {
        	 
        	
            if (line.contains(item)==true)
            {
            	//item already present
             //br.close();
             
             //tempData=line;
             
            	//  line = line.replace("Inactive", "Active");
            }
            else
            {
            	//tempData=line.concat("\n");
           // allData=allData.concat(tempData);
            bw.write(line);
            bw.write("\n");	
            }
              
            		
         }
         
        } catch (Exception e) {
         return;
      }
      finally {
         try {
            if(br != null)
               br.close();
         } catch (IOException e) {
            //
         }
         try {
            if(bw != null)
               bw.close();
         } catch (IOException e) {
            //
         }
      }
      // Once everything is complete, delete old file..
      File oldFile = new File(oldfileName);
      oldFile.delete();

      // And rename tmp file's name to old file name
      File newFile = new File(newfileName);
      newFile.renameTo(oldFile);

		
		
		/*boolean flag=false;
		//int itemIndex = 0;
		for(int i=0;i<itemInfoInAllMachines.size();i++)
		{
			if(itemInfoInAllMachines.get(item)!=null)
			{
				//station already in group
				flag=true;
				//itemIndex=i;
				break;
			}
		
		}
		if(flag==true)
		{
			//listOfRecylableItems.remove(itemIndex);
			itemInfoInAllMachines.remove(item);
		}*/
	}
	
	//change the rate
	void changePrice(String item,double price)
	{
boolean flag1=false;
		
		//String fileName="RCMList.txt";
		 String oldfileName="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/Data/RecyclableItems.txt";
		 String newfileName="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/Data/temp.txt";
		String allData=" ";
		String tempData=" ";
		BufferedReader br = null;
      BufferedWriter bw = null;
      try {
        
		br = new BufferedReader(new FileReader(oldfileName));
		bw = new BufferedWriter(new FileWriter(newfileName));
         String line;
       String line1;
         while ((line=br.readLine()) != null) {
        	 
        	
            if (line.contains(item)==true)
            {
            	//item already present
             flag1=true;
             //line1=br.readLine();
             
             break;
             //tempData=line;
             
            	//  line = line.replace("Inactive", "Active");
            }
            else
            {
            	tempData=line.concat("\n");
            allData=allData.concat(tempData);
            
            	
            }
              
            		
         }
         if(flag1==true)
         {
         	while((line1=br.readLine())!=null)
         	{
         		tempData=line1;
         		allData=allData.concat(tempData);
         	}
         	System.out.println(allData);
         }
         else  
      
            	if(flag1==false)
            {
            		 	 
            	//line=item+":"+rate+":";
            	bw.write(allData+"\n");
                bw.write(line+"\n");
               
                
         }
      } catch (Exception e) {
         return;
      }
      finally {
         try {
            if(br != null)
               br.close();
         } catch (IOException e) {
            //
         }
         try {
            if(bw != null)
               bw.close();
         } catch (IOException e) {
            //
         }
      }
      // Once everything is complete, delete old file..
      File oldFile = new File(oldfileName);
      oldFile.delete();

      // And rename tmp file's name to old file name
      File newFile = new File(newfileName);
      newFile.renameTo(oldFile);

		
		
		
		
		
	}
	//Activates the recycling station in the group to accept items.
	//The status includes whether the machine is in operational status (operational or down), current weight of recycled items in the machine,
	//amount of money in the machine, time last emptied.
	void activateMachine(String machineName)
	{
		
		
		//String fileName="RCMList.txt";
		 String oldfileName="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/RCMList.txt";
		 String newfileName="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/temp.txt";
		String allData="";
		String tempData="";
		BufferedReader br = null;
      BufferedWriter bw = null;
      try {
        
		br = new BufferedReader(new FileReader(oldfileName));
		bw = new BufferedWriter(new FileWriter(newfileName));
         String line;
        String line1 = " ";
         while ((line=br.readLine()) != null) {
        	 
        	
            if (line.contains(machineName)==true&&line.contains("Inactive"))
            {
             line1 = line.replace("Inactive", "Active");
             
                 }
            else
            {
            	tempData=line.concat("\n");
            allData=allData.concat(tempData);
         }
              
            		
         }
         
         bw.write(allData);	 
         bw.write(line1);
    } catch (Exception e) {
         return;
      }
      finally {
         try {
            if(br != null)
               br.close();
         } catch (IOException e) {
            //
         }
         try {
            if(bw != null)
               bw.close();
         } catch (IOException e) {
            //
         }
      }
      // Once everything is complete, delete old file..
      File oldFile = new File(oldfileName);
      oldFile.delete();

      // And rename tmp file's name to old file name
      File newFile = new File(newfileName);
      newFile.renameTo(oldFile);
   
		
			//machineNameWithState.put(stationToBeAdded,false);		
		
	
}

	void removeRecycleStation(String stationToBeAdded,String location,String capacity) throws IOException
	{
			
		
		boolean flag1=false;
		
		//String fileName="RCMList.txt";
		 String oldfileName="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/RCMList.txt";
		 String newfileName="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/temp.txt";
		String allData=" ";
		String tempData=" ";
		BufferedReader br = null;
      BufferedWriter bw = null;
      try {
        
		br = new BufferedReader(new FileReader(oldfileName));
		bw = new BufferedWriter(new FileWriter(newfileName));
         String line;
        String line1;
         while ((line=br.readLine()) != null) {
        	 
        	
            if (line.contains(stationToBeAdded)==true)
            {
             flag1=true;
             br.close();
             break;
             //tempData=line;
             
            	//  line = line.replace("Inactive", "Active");
            }
            else
            {
            	//tempData=line.concat("\n");
            //allData=allData.concat(tempData);
           //tempData=line.concat("\n"); 	
           bw.write(line);
           bw.write("\n");
           
            }
            
               
            		
         }
         line1=stationToBeAdded+"|Inactive"+"|"+location+"|"+capacity+"|"+"1000|0|0|0";
         bw.write(line1);  
           
     //  if(flag1==true)
     //   {
     //   	while((line1=br.readLine())!=null)
     //   	{
      //  		bw.write(line1);   
        		//tempData=line1;
        		//allData=allData.concat(tempData);
      //  	}
      //  	System.out.println(allData);
      //  }
       // else
       //     	if(flag1==false)
       //     {
            		 	 
       //     		line=stationToBeAdded+":Inactive"+":"+location+":"+capacity+":";
       //     	bw.write(allData+"\n");
       //         bw.write(line+"\n");
               
                
        // }
      } catch (Exception e) {
         return;
      }
      finally {
         try {
            if(br != null)
               br.close();
         } catch (IOException e) {
            //
         }
         try {
            if(bw != null)
               bw.close();
         } catch (IOException e) {
            //
         }
      }
      // Once everything is complete, delete old file..
      File oldFile = new File(oldfileName);
      oldFile.delete();

      // And rename tmp file's name to old file name
      File newFile = new File(newfileName);
      newFile.renameTo(oldFile);
   
		
			//machineNameWithState.put(stationToBeAdded,false);		
		
	
}

		
		/*boolean flag=false;
		for(int i=0;i<machineNameWithState.size();i++)
		{
			if(machineNameWithState.containsKey(machineName));
			{
				if(machineNameWithState.containsValue(false))
				{
				flag=true;
				}
			}
		}
		if(flag==true)
		{
			machineNameWithState.put(machineName, true);
		}*/
//	}
	
	
	/*
	 * Check the amount of money in a specific RCM.
- Check the current (and available) capacity (by weight or volume) of an
RCM. This indicates whether an RCM is full and has to be emptied.
- Get number of items returned by a specific machine (or all the machines
in the group) in a month.
- Show the time a specific RCM was emptied last time.
- Get the location of recycling station and id of the machine that was used
the most (in the last n days) (Not required for single person teams)
	 */
	
	
	
	
	
	//Displays the statistics of usage for the RCM group 
	/*
	 * This may include the no. of times the machine was emptied in a specific duration (in no. of hours), 
	 * weight of items collected in a specific duration,
	 *  no. of items collected by type (aluminum, glass and so on).
	 */
	void displayStatistics()
	{
//		- Get number of items returned by a specific machine (or all the machines
//				in the group) in a month.
		/* Display the usage statistics for each RCM in the RCM group. This will include the total weight of recycled
 items by machine (per day, week ..), total value (cash or coupons) issued, using a visualization (a graph for
		 * example); show the number of times a particular RCM was emptied in a
specific duration.
		 */

	}
	
}
