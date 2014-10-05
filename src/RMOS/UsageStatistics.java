package RMOS;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
//import java.util.Iterator;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javax.swing.JPanel;

import RCM.RCM;

public class UsageStatistics extends JPanel{
	
 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

/**
	 * 
	 */
	 String RCMDetails="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/RCMDetails.txt";
	// String RCMList="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/RCMList.txt";
	// String RecylableItems="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/RecyclableItems.txt";
	
	BufferedReader brRCMDetails = null;
//	BufferedReader brRCMList = null;
//	BufferedReader brRecylableItems=null;
	ArrayList<Double>weightInEachMachine=new ArrayList<Double>();
	FilteredDataForUsageStatistics f=new FilteredDataForUsageStatistics();
	FilteredDataofRcm f1=new FilteredDataofRcm();
//Weight stats for all
	
	
	public void getWeightAddedStatistics()
	{
		ArrayList<Double>totalWeightInEachMachine=new ArrayList<Double>();
		ArrayList<String>MachineNames=new ArrayList<String>();
		MachineNames=f1.getStationInGroup();
		double tempwt=0;
		double wt=0;
		for(int i=0;i<MachineNames.size();i++)
		{
		tempwt=f.getWeightAddedInMachine(MachineNames.get(i));
		totalWeightInEachMachine.add(tempwt);
		weightInEachMachine.add(tempwt);
		}
		for(int i=0;i<totalWeightInEachMachine.size();i++)
		{
			wt=wt+totalWeightInEachMachine.get(i);
		}
		
		
		//wt is the total and array has weight of each component
		
		//call the draw pie chart function
		
	}
	
	public ArrayList<String> getInCapacity(){
		String RCMList="/Users/apurva/Documents/oops/Assmts/EcoRecycleSystem/src/RCMList.txt";
		File rcmListFile= new File(RCMList);
		ArrayList<String> rcmWtList = new ArrayList<String>();
		try{
			try {
				Scanner sc= new Scanner(rcmListFile);
				while(sc.hasNextLine()){
		             String line = sc.nextLine();
		             String[] details = line.split("\\|");
		             String wt= details[4];
		             System.out.println(wt);
		             rcmWtList.add(wt);				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		finally{
			
		}
		//System.out.println(rcmIdList);
		return rcmWtList;
		
	}
	
	public ArrayList<Double>getTotalWeightAddedinEachMachine()
	{
		return weightInEachMachine;
	}
	public void getTotalCashForGroup()
	{
		ArrayList<Double>totalCashInEachMachine=new ArrayList<Double>();
		ArrayList<String>MachineNames=new ArrayList<String>();
		MachineNames=f1.getStationInGroup();
		double tempcash=0;
		double cash=0;
		for(int i=0;i<MachineNames.size();i++)
		{
		//tempcash=f.getTotalCashInMachine(MachineNames.get(i));
		totalCashInEachMachine.add(tempcash);
		}
		for(int i=0;i<totalCashInEachMachine.size();i++)
		{
			cash=cash+totalCashInEachMachine.get(i);
		}
		
		//call the draw pie chart function
		
	}
	
	public void getNumberTimesMachineEmptied()
	{
		ArrayList<Double>totalCountForEmptying=new ArrayList<Double>();
		ArrayList<String>MachineNames=new ArrayList<String>();
		MachineNames=f1.getStationInGroup();
		double tempcount=0;
		double totalCount=0;
		for(int i=0;i<MachineNames.size();i++)
		{
		tempcount=f.lastEmptiedCount(MachineNames.get(i), 2);  //before two
		totalCountForEmptying.add(tempcount);
		}
		for(int i=0;i<totalCountForEmptying.size();i++)
		{
			totalCount=totalCount+totalCountForEmptying.get(i);
		}
		
		//call the draw pie chart function
		
		
	}
	
	
	
	

/*	private static final long serialVersionUID = 1L;
private ArrayList<String> typeofItems=new ArrayList<String>();
	Map<String, Color> colors=new HashMap<String, Color>();
	
	public void addrcm(String rcm )
	   {
	      // do not add null rcms
	      if ( rcm == null )
	         throw new NullPointerException();

	      // add rcm to rcms Vector
	      typeofItems.add(rcm );

	      // add Color to Hashtable for drawing rcm's wedge
	      colors.put( rcm, getRandomColor() );

	      // register as Observer to receive rcm updates
	     // rcm.addObserver( this );

	      // update display with new rcm information
	      repaint();
	   }
	 public void removeAccount( String account )
	   {
	      // stop receiving updates from given Account
	     // account.deleteObserver( this );

	      // remove Account from accounts Vector
	      typeofItems.remove( account );

	      // remove Account's Color from Hashtable
	      colors.remove( account );

	      // update display to remove Account information
	      repaint();
	   }
	 
	   public void paintComponent( Graphics g )
	   {
	      // ensure proper painting sequence
	      super.paintComponent( g );

	      // draw pie chart
	      drawPieChart( g );

	      // draw legend to describe pie chart wedges
	      drawLegend( g );
	   }
	   private void drawPieChart( Graphics g )
	   {
	      // get combined Account balance
	//      double totalBalance = getTotalBalance();

	      // create temporary variables for pie chart calculations
	      double percentage = 0.0;
	      int startAngle = 0;
	      int arcAngle = 0;
	      
	//    Iterator<RCM> accountIterator = typeofItems.iterator();
	//      RCM account = null;
	      typeofItems.add("Plastic");
	      typeofItems.add("Wood");
	     // typeofItems.add("Plastic");
	      
	      // draw pie wedge for each Account
//	     while ( accountIterator.hasNext() ) {

	         // get next Account from Iterator
//	        account = accountIterator.next();

	         // draw wedges only for included Accounts
	//         if ( !includeAccountInChart( account ) )
	//            continue;

	         // get percentage of total balance held in Account
//	         percentage = account.getBalance() / totalBalance;
	      percentage=40;
	         // calculate arc angle for percentage
	         arcAngle = ( int ) Math.round( percentage * 360 );

	         // set drawing Color for Account pie wedge
	         g.setColor( colors.get( typeofItems ) );
	         
	         // draw Account pie wedge
	         g.fillArc( 5, 5, 100, 100, startAngle, arcAngle );

	         // calculate startAngle for next pie wedge
	         startAngle += arcAngle;
	     
	//     }
	   
	   } // end method drawPieChart

	   // draw pie chart legend on given Graphics context
	   private void drawLegend( Graphics g )
	   {
//	      Iterator<RCM> accountIterator = typeofItems.iterator();
	      RCM account = null;

	      // create Font for Account name
	      Font font = new Font( "SansSerif", Font.BOLD, 12 );
	      g.setFont( font );

	      // get FontMetrics for calculating offsets and
	      // positioning descriptions
	      FontMetrics metrics = getFontMetrics( font );
	      int ascent = metrics.getMaxAscent();
	      int offsetY = ascent + 2;

	      // draw description for each Account
	      for ( int i = 1;i<5;i++){ 
	    		  //accountIterator.hasNext(); i++ ) {

	         // get next Account from Iterator
//	         account = accountIterator.next();

	         // draw Account color swatch at next offset
	         g.setColor( colors.get( account ) );
	         g.fillRect( 125, offsetY * i, ascent, ascent );

	         // draw Account name next to color swatch
	        // g.setColor( Color.black );
	         g.drawString("stub", 140, offsetY*i+ascent);
	        // g.drawString( account.getName(), 140,
	        //    offsetY * i + ascent );
	      }
	   } // end method drawLegend

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		repaint();
	}
	  private Color getRandomColor()
	   {
	      // calculate random red, green and blue values
	      int red = ( int ) ( Math.random() * 256 );
	      int green = ( int ) ( Math.random() * 256 );
	      int blue = ( int ) ( Math.random() * 256 );

	      // return newly created Color
	      return new Color( red, green, blue );
	   }

	  

	   // get AccountBarGraphView's preferred size
	   public Dimension getPreferredSize()
	   {
	      return new Dimension( 210, 110 );
	   }

	   // get AccountBarGraphView's preferred size
	   public Dimension getMinimumSize()
	   {
	      return getPreferredSize();
	   }

	   // get AccountBarGraphView's preferred size
	   public Dimension getMaximumSize()
	   {
	      return getPreferredSize();
	   }
	
	   //stub
	   
	   public double getbalance(int i) {
		double s=0;
		   if(i==1)
		{
			s=50;
		}else
			if(i==2)
			{
				s=80;
			}
			else
				if(i==3)
				{
					s=80;
				}
				
		return s;
	}
*/
}
