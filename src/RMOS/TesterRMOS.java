package RMOS;

import javax.swing.JFrame;

public class TesterRMOS {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 new LogInGui();
		  /* GuiRMOS application = new GuiRMOS();
		
		   application.setDefaultCloseOperation(
		         JFrame.EXIT_ON_CLOSE );*/
		
	/*UsageStatisticsView manager = new UsageStatisticsView();
	      manager.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	      manager.setVisible( true );*/
		FilteredDataofRcm f=new FilteredDataofRcm();
//		f.getStationInGroup();
	System.out.println(f.getStationInGroup());
//	System.out.println(f.getStationsNameNotInGroup());
	System.out.println(f.getItemsAcceptable());
	System.out.println(f.getActiveStation());
	//System.out.println(f.g);
	
		   }


}
