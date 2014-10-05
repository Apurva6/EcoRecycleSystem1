package RMOS;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.xml.crypto.Data;

import org.jfree.chart.labels.PieToolTipGenerator;
import org.jfree.data.general.PieDataset;
import org.jfree.ui.RefineryUtilities;

import RCM.RCMUser;

public class GuiRMOS extends JFrame implements ActionListener,Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * A graphical interface, where each of the individual machines in the group (monitored by this workstation) can be represented. Each machine is identified by its location and an id. Each machine should store the time of the last removal of its items.
 Add a new machine to the group.
 Remove a machine from the group. (Not required for single person
teams)
 Check the operational status of each machine.
 Change/add new types of recyclable items. (Not required for single
person teams)
 Change the price of an item. (Not required for single person teams)
 Check the amount of money in a specific RCM.
 Check the current (and available) capacity (by weight or volume) of an
RCM. This indicates whether an RCM is full and has to be emptied.
 Get number of items returned by a specific machine (or all the machines
in the group) in a month.
 Show the time a specific RCM was emptied last time.
 Get the location of recycling station and id of the machine that was used
the most (in the last n days) (Not required for single person teams)
 Display the usage statistics for each RCM in the RCM group. This will include the total weight of recycled items by machine (per day, week ..), total value (cash or coupons) issued, using a visualization (a graph for
example); show the number of times a particular RCM was emptied in a
specific duration
	 */
		   private JButton addStationButton;
		   private JButton removeStationButton;
		   private JButton addRecylableItemButton;
		   private JButton removeRecylableItemButton;
		   private JButton activateMachineButton;
		   private JButton getWeightStatsButton;
		   private JButton getTimeDurationStats;
		   private JButton getCashStatButton;
		   
		   //private JButton pictureButton;
		   private JTextField rcmName;
		   private JTextField rcmLocation;
		   private JTextField rcmCapacity;
		   private JTextField rcmNewRecylableItem;
		   //private JTextField rcmCapacity; 
		   private JLabel rcmNameLabel;
		   private JLabel rcmLocationLabel;
		   private JLabel rcmCapacityLabel;
		   private JLabel rcmNewRecylableItemLabel;
		   private JLabel rcmNewRecylableItemRateLabel;
		   private JTextField rcmNewRecylableItemRateText;
		   private JTextField stationName=new JTextField();	  
		   
		   private   JTextField status=new JTextField();
		   private  JTextField locationText=new JTextField();
		   private JTextField moneyavailable=new JTextField();
		   private JTextField incapacity=new JTextField();
		   private JLabel infonameLabel=new JLabel("Machine:");
		   private JLabel statusLabel=new JLabel("Status");
		   private JLabel locationLabel=new JLabel("Location");
		   private JLabel moneylabel=new JLabel("Money");
		   private JLabel incapacitylabel=new JLabel("Incapacity");
		   
		   String name,location,capacity;
		   
		   //panels for add,remove,list of rcm
		   //panels for add,remove,change item accepted
		   //panel to activate machine
		   JPanel addStationPanel=new JPanel();
		   //JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		   JPanel removeStationPanel=new JPanel();
		   JPanel StationsInfoPanel=new JPanel();
		   JPanel addItemPanel=new JPanel();
		   JPanel removeRecyclableMachinePanel= new JPanel();
		   JPanel removeItemPanel=new JPanel();
		   JPanel changeItemPanel=new JPanel();
		   JPanel activateMachinePanel=new JPanel();
		   JPanel statisticsPanel=new JPanel();
		   JPanel emptyMachinePanel=new JPanel();
			
		   FilteredDataofRcm fdata;
		   UsageStatisticsView Uview;
		   
		   
		   //private JComboBox stationsNotInGroupComboBox;
		   private JComboBox stationsInGroupComboBox;
		   private JComboBox stationsInfoComboBox;
		   private JTextArea StationsInfoTextBox;
		   
		   //add item panel components
		   //text and button and message component
		   
		   //remove item panel components
		   private JComboBox itemsAcceptedComboBox;
		   //button
		   
		   //change item Panel components
		   private JComboBox stationsInActiveComboBox;
		   
		   private JComboBox stationsInGroup1ComboBox;
		   private JButton emptyMachineButton;
		  
		     
		    public GuiRMOS() {
			// TODO Auto-generated constructor stub
	
		      super( "RMOS" );
		      getCashStatButton=new JButton("Cash Statistics");
		      fdata=new FilteredDataofRcm();
		      Container container = getContentPane();
		      container.setBackground(Color.PINK);
		      container.setLayout(new GridLayout(0,2));
		      String temp2="";
		      stationsInGroup1ComboBox=new JComboBox();
		      ArrayList<String> s3=new ArrayList<String>();
			  stationsInGroup1ComboBox.addItem("Select");
			  s3=fdata.getStationInGroup();
			  for(int i=0;i<s3.size();i++)
			   
			  {
				  stationsInGroup1ComboBox.addItem(s3.get(i));
			  }
			
		      stationsInGroupComboBox=new JComboBox();
		 
		      stationsInfoComboBox= new JComboBox();
		      StationsInfoTextBox=new JTextArea(5,10);
		    StationsInfoTextBox.setEditable(false);
		    fdata=new FilteredDataofRcm();
		    emptyMachineButton=new JButton("Empty Machine");
		    getTimeDurationStats=new JButton("Stattistics for Time duration");
			   
		     getWeightStatsButton=new JButton("Statistics based on Weight");
		    this.loadDataforMachineId();
		   /* ArrayList<String> temp1=new ArrayList<String>();
		      temp1= fdata.getInfoOfAllRcm(stationsInfoComboBox.getSelectedItem().toString());
		      
		      for(int i=0;i<temp1.size();i++)
		      {
		    	  
		    	  temp2=temp1.get(i)+"\n"+temp2;
		    	  
		      }
		      StationsInfoTextBox.setText(temp2);*/
	    	  StationsInfoPanel.add(stationsInfoComboBox);
		     // StationsInfoPanel.add(StationsInfoTextBox);
	    	  StationsInfoPanel.add(infonameLabel);
	    	  StationsInfoPanel.add(stationName);
		      StationsInfoPanel.add(statusLabel);
		      StationsInfoPanel.add(status);
		      StationsInfoPanel.add(moneylabel);
		      StationsInfoPanel.add(moneyavailable);
		      StationsInfoPanel.add(incapacitylabel);
		      StationsInfoPanel.add(incapacity);
		      
		      
		      
		      
		     // StationsInfoPanel.add(stationsInGroupComboBox);
		      
		      addStationPanel.setLayout(new BoxLayout(addStationPanel, BoxLayout.Y_AXIS));
		      
		      
		     
		      addStationButton = new JButton( "Add Station" );
		   //   addStationButton.setHorizontalAlignment(addStationButton.RIGHT);
		      addRecylableItemButton=new JButton("Add Recylable items");
		      removeRecylableItemButton=new JButton("Remove Recylable items");
		      activateMachineButton=new JButton("Activate machine");
		      removeStationButton=new JButton("Remove Station");
		      rcmNewRecylableItemRateLabel=new JLabel("Enter the rate");
		      rcmNewRecylableItemRateText=new  JTextField(" ",5);
		      //addStationButton.setActionCommand("Show message button");
		      //addStationButton.setToolTipText("A plain button");
		      
		    //component for add station panel
		      rcmNewRecylableItem=new JTextField(" ",5);
			  rcmNewRecylableItemLabel=new JLabel("Enter the Recylable Item you want to add");
			  
		//component to remove machine
			//  stationsInGroupComboBox=new JComboBox();
			  ArrayList<String> s2=new ArrayList<String>();
			  stationsInGroupComboBox.addItem("Select");
			  s2=fdata.getStationInGroup();
			  for(int i=0;i<s2.size();i++)
			   
			  {
				  stationsInGroupComboBox.addItem(s2.get(i));
			  }
			
			  	  
			  
		      //component for remove item
			  itemsAcceptedComboBox=new JComboBox();
			  ArrayList<String> s1=new ArrayList<String>();
			  itemsAcceptedComboBox.addItem("Select");
			  s1=fdata.getItemsAcceptable();
			  for(int i=0;i<s1.size();i++)
			   
			  {
				  itemsAcceptedComboBox.addItem(s1.get(i));
			  }
			
			  
			   //component for station info panel
			  
			   
			  
			   
			   //remove item panel components
			//  itemsAcceptedComboBox=new JComboBox();
			   //button
			   
			   //change item Panel components
			   
			   //activate panel
			  stationsInActiveComboBox=new JComboBox();
			  ArrayList<String> s11=new ArrayList<String>();
			  stationsInActiveComboBox.addItem("Select");
			  s11=fdata.getActiveStation();
			  for(int i=0;i<s11.size();i++)
			   
			  {
				  stationsInActiveComboBox.addItem(s11.get(i));
			  }
			 
		      rcmName=new JTextField(" ",5);
		      rcmLocation=new JTextField(" ",5);
		      rcmCapacity=new JTextField(" ",5);
		      rcmNameLabel=new JLabel("Name of machine");
		      rcmLocationLabel=new JLabel("Location");
		      rcmCapacityLabel=new JLabel("Capacity");
		      
		      
		      
		      ActionListener addRecylableItem=new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					RMOS instanceOfRMOS=new RMOS();
					try {
						instanceOfRMOS.setListOfRecyclableItems(rcmNewRecylableItem.getText().trim(), rcmNewRecylableItemRateText.getText().trim());
						itemsAcceptedComboBox.removeAllItems();
						ArrayList<String> s=new ArrayList<String>();
						  itemsAcceptedComboBox.addItem("Select");
						  s=fdata.getItemsAcceptable();
						  for(int i=0;i<s.size();i++)
						   
						  {
							  itemsAcceptedComboBox.addItem(s.get(i));
						  }
						
						//component for remove item
						  
						//instanceOfRMOS.setListOfRecyclableItems("Wood","20");
						JOptionPane.showMessageDialog( null,
					            "Recyclable Item Added Succesfully !" );
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				}
		    	  
		      };
		      
		      ActionListener removeStation=new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						
						// TODO Auto-generated method stub
						RCMUser r=new RCMUser();
						r.removeRecycleStation(stationsInGroupComboBox.getSelectedItem().toString());
						
						
						stationsInGroupComboBox.removeAllItems();
						 ArrayList<String> s2=new ArrayList<String>();
						  stationsInGroupComboBox.addItem("Select");
						  s2=fdata.getStationInGroup();
						  for(int i=0;i<s2.size();i++)
						   
						  {
							  stationsInGroupComboBox.addItem(s2.get(i));
						  }
						JOptionPane.showMessageDialog( null,
					            "Machine Removed Successfully !" );
					}
				};  
		      ActionListener removeRecylableItem=new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						RMOS instanceOfRMOS=new RMOS();
						try {
							//itemsAcceptedComboBox.getSelectedItem().toString();
							instanceOfRMOS.removeItemForRecylableItemsList(itemsAcceptedComboBox.getSelectedItem().toString());
//							instanceOfRMOS.removeItemForRecylableItemsList("Wood");
							JOptionPane.showMessageDialog( null,
						            "Item Type Removed Succesfully!" );
							
						} catch (Exception e2) {
							// TODO: handle exception
						}
						
					}
			    	  
			      };
			      
			      ActionListener stationInfoActionListener=new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
						//	RMOS instanceOfRMOS=new RMOS();
							ArrayList<String> s=new ArrayList<String>();
							 ArrayList<String> s1=new ArrayList<String>();
							 String[] st=new String[10];
							 String[] st1 = new String[10];
						     s=fdata.getStationInGroup();
						     s1=fdata.getInfoOfAllRcm(stationsInfoComboBox.getSelectedItem().toString());
						     String line=s1.get(0);
						     //  for(int i=0;i<s.size();i++)
						  // {
						   	
							   
							  // st[i]=s1.get(i).toString();
							  // String temp=st[i];
								st1 = line.split("\\|");
						       stationName.setText(st1[0]);
						        status.setText(st1[1]);
						      locationText.setText(st1[2]);
							  moneyavailable.setText(st1[3]);
							  incapacity.setText(st1[4]);
						      // i++;
							  // }
						       for(int i=0;i<s.size();i++)
						       {
						       	
						       stationsInfoComboBox.addItem(s.get(i));  
						       }
							
						}
				    	  
				      };
				      
				      
			      
		     
			  ActionListener activateMachine=new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					RMOS instanceOfRMOS=new RMOS();
					try {
						//instanceOfRMOS.activateMachine("RCM3");
						//System.out.println(stationsInActiveComboBox.getSelectedItem().toString());
						instanceOfRMOS.activateMachine(stationsInActiveComboBox.getSelectedItem().toString());
						JOptionPane.showMessageDialog( null,
					            "Machine Activated !" );
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
				
		    	
					
				}
			};
			 ActionListener weightStats=new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
					//	PieChart instanceOfRMOS=new PieChart("Weight Added");
						try {
							//instanceOfRMOS.activateMachine("RCM3");
							//JPanel jPanel= new PieChart("Weight Added");
							PieChart pie=new PieChart("Weigt Added");
							PieChart.createDemoPanel();
							 pie.pack();
						        RefineryUtilities.centerFrameOnScreen(pie);
						        pie.setVisible(true);
							pie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							//System.out.println(stationsInActiveComboBox.getSelectedItem().toString());
							//instanceOfRMOS.activateMachine(stationsInActiveComboBox.getSelectedItem().toString());
							//JOptionPane.showMessageDialog( null,
						    //        "Machine Activated !" );
						} catch (Exception e2) {
							// TODO: handle exception
							//e2.setStackTrace(stackTrace);
						}
						
					
			    	
						
					}
				};
				
				 ActionListener timeStats=new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
						//	PieChart instanceOfRMOS=new PieChart("Weight Added");
							try {
								//instanceOfRMOS.activateMachine("RCM3");
								//JPanel jPanel= new PieChart("Weight Added");
								PieChartTimeDuration pie=new PieChartTimeDuration("Time Added");
								PieChartTimeDuration.createDemoPanel();
								 pie.pack();
							        RefineryUtilities.centerFrameOnScreen(pie);
							        pie.setVisible(true);
								pie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
								//System.out.println(stationsInActiveComboBox.getSelectedItem().toString());
								//instanceOfRMOS.activateMachine(stationsInActiveComboBox.getSelectedItem().toString());
								//JOptionPane.showMessageDialog( null,
							    //        "Machine Activated !" );
							} catch (Exception e2) {
								// TODO: handle exception
								//e2.setStackTrace(stackTrace);
							}
							
						
				    	
							
						}
					};
				
					 ActionListener cashStats=new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
							//	PieChart instanceOfRMOS=new PieChart("Weight Added");
								try {
									//instanceOfRMOS.activateMachine("RCM3");
									//JPanel jPanel= new PieChart("Weight Added");
									PieChartCash pie=new PieChartCash("Cash Statistics");
									PieChartCash.createDemoPanel();
									 pie.pack();
								        RefineryUtilities.centerFrameOnScreen(pie);
								        pie.setVisible(true);
									pie.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
									//System.out.println(stationsInActiveComboBox.getSelectedItem().toString());
									//instanceOfRMOS.activateMachine(stationsInActiveComboBox.getSelectedItem().toString());
									//JOptionPane.showMessageDialog( null,
								    //        "Machine Activated !" );
								} catch (Exception e2) {
									// TODO: handle exception
									//e2.setStackTrace(stackTrace);
								}
								
							
					    	
								
							}
						};
					
					 ActionListener emptyMachine=new ActionListener() {
							
							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
							//	PieChart instanceOfRMOS=new PieChart("Weight Added");
								try {
									RCMUser r=new RCMUser();
									r.removeItemFromARecylableList(stationsInGroup1ComboBox.getSelectedItem().toString());
								} catch (Exception e2) {
									// TODO: handle exception
									//e2.setStackTrace(stackTrace);
								}
								
							
					    	
								
							}
						};
				
				emptyMachineButton.addActionListener(emptyMachine);		
			   // add an event handler
			  addStationButton.addActionListener( this );
			  addRecylableItemButton.addActionListener( addRecylableItem );
		      removeRecylableItemButton.addActionListener(removeRecylableItem);
			  activateMachineButton.addActionListener(activateMachine);
			  addStationPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
			  removeStationButton.addActionListener(removeStation);
			  stationsInfoComboBox.addActionListener(stationInfoActionListener);
			  getWeightStatsButton.addActionListener(weightStats);
			  getTimeDurationStats.addActionListener(timeStats);
			  StationsInfoPanel.setBorder(new TitledBorder("Stations Information"));
			  getCashStatButton.addActionListener(cashStats);
			  //StationsInfoPanel.setBackground(Color.red);
			  
			  addItemPanel.setBorder(new TitledBorder("Add Item"));
			  addItemPanel.setLayout(new BoxLayout(addItemPanel,BoxLayout.Y_AXIS));
			  addItemPanel.add(rcmNewRecylableItemLabel);
			  addItemPanel.add(rcmNewRecylableItem);
			  addItemPanel.add(rcmNewRecylableItemRateLabel);
			  addItemPanel.add(rcmNewRecylableItemRateText);
			  addItemPanel.add(addRecylableItemButton);
			  addItemPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
			 // rcmNewRecylableItem.setAlignmentX(CENTER_ALIGNMENT);
			 // rcmNewRecylableItemLabel.setAlignmentX(CENTER_ALIGNMENT);
			 // addRecylableItemButton.setAlignmentX(CENTER_ALIGNMENT);
			  //addItemPanel.add(rcmName);
			  
			   
			 //  changeItemPanel;
			   
			   changeItemPanel.setBorder(new TitledBorder("Change Item"));
			   
			 //  activateMachine;
			   activateMachinePanel.setBorder(new TitledBorder("Activate Machine/Empty Item"));
			   activateMachinePanel.add(stationsInActiveComboBox);
			   activateMachinePanel.add(activateMachineButton);
			  // activateMachinePanel.add(stationsInGroup1ComboBox);
			  // activateMachinePanel.add(emptyMachineButton);
			   
			   emptyMachinePanel.add(stationsInGroup1ComboBox);
				emptyMachinePanel.add(emptyMachineButton);
			   activateMachinePanel.add(emptyMachinePanel);
			   
			   removeItemPanel.add(itemsAcceptedComboBox);
			   removeItemPanel.setBorder(new TitledBorder("Remove item"));
			   removeItemPanel.add(removeRecylableItemButton);
				
			
			   
			   //add item panel components
			   //text and button and message component
			  addStationPanel.add(rcmNameLabel);
			  addStationPanel.add(rcmName);
			  addStationPanel.add(rcmCapacityLabel);
			  addStationPanel.add(rcmCapacity);
			  addStationPanel.add(rcmLocationLabel);
			  addStationPanel.add(rcmLocation);
			  addStationPanel.setBorder(new TitledBorder( "Add Station" ) );
			  addStationPanel.add(addStationButton);
			  
			  //removeStationPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
			  removeStationPanel.setBorder(new TitledBorder("Remove Functionalities"));
			  removeRecyclableMachinePanel.setBorder(new TitledBorder("Remove Station"));
			  removeRecyclableMachinePanel.add(stationsInGroupComboBox);
			  removeRecyclableMachinePanel.add(removeStationButton);
			  removeStationPanel.add(removeRecyclableMachinePanel);
			  removeStationPanel.add(removeItemPanel);
			  
			 
			 // Uview=new UsageStatisticsView("Weight added");
			 // statisticsPanel.add(Uview);
			  
		      container.add(addStationPanel);
		      
		      container.add(addItemPanel);
		      container.add(activateMachinePanel);
		      container.add(removeStationPanel);
		      //container.add(removeItemPanel);
		      
		      
		     statisticsPanel.add(getWeightStatsButton);
		     statisticsPanel.add(getCashStatButton);
		     statisticsPanel.add(getTimeDurationStats);
		     statisticsPanel.setBorder(new TitledBorder("View Statistics"));
		     
		      container.add(StationsInfoPanel);
		    container.add(statisticsPanel);
		      
		 //     pictureButton = new JButton( new ImageIcon("button_blue.jpg"));
		      setSize(900,900);
		      setLocationRelativeTo(null);
		      setVisible( true );
		   }

		   public void actionPerformed( ActionEvent event ){
		     RMOS instanceOfRMOS=new RMOS();
			 try {
				instanceOfRMOS.addRecycleStation(rcmName.getText().trim(),rcmLocation.getText().trim(),rcmCapacity.getText().trim());
				rcmName.setText("");
				rcmLocation.setText("");
				rcmCapacity.setText("");
				//component for remove item
				  //itemsAcceptedComboBox=new JComboBox();
				  //itemsAcceptedComboBox.removeAllItems();
				stationsInActiveComboBox.removeAllItems();
				ArrayList<String> s1=new ArrayList<String>();
				  stationsInActiveComboBox.addItem("Select");
				  s1=fdata.getActiveStation();
				  for(int i=0;i<s1.size();i++)
				   
				  {
					  stationsInActiveComboBox.addItem(s1.get(i));
				  }
				  ArrayList<String> s2=new ArrayList<String>();
				  stationsInGroupComboBox.removeAllItems();
				  stationsInGroupComboBox.addItem("Select");
				  s2=fdata.getStationInGroup();
				  for(int i=0;i<s2.size();i++)
				   
				  {
					  stationsInGroupComboBox.addItem(s2.get(i));
				  }
				  
				  
				 /* ArrayList<String> s=new ArrayList<String>();
				  itemsAcceptedComboBox.addItem("Select");
				  s=fdata.getItemsAcceptable();
				  for(int i=0;i<s.size();i++)
				   
				  {
					  itemsAcceptedComboBox.addItem(s.get(i));
				  }*/
				// instanceOfRMOS.addRecycleStation("RCM8","abc","1000");
				// instanceOfRMOS.addRecycleStation("RCM9","abc","1000");
				// instanceOfRMOS.addRecycleStation("RCM10","abc","1000");
				// instanceOfRMOS.addRecycleStation("RCM11","abc","1000");
				 
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			   
			   JOptionPane.showMessageDialog( null,
		            "Machine Added Succesfully" );
		    }

		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			FilteredDataofRcm f=(FilteredDataofRcm)arg;
			
			ArrayList<String> s=new ArrayList<String>();
			  s=f.getItemsAcceptable();
			  for(int i=0;i<s.size();i++)
			   
			  {
				  itemsAcceptedComboBox.addItem(s.get(i));
			  }

		

		}

void loadDataforMachineId()
{
	 ArrayList<String> s=new ArrayList<String>();
	 ArrayList<String> s1=new ArrayList<String>();
	 String[] st=new String[10];
	 String[] st1 = new String[10];
     s=fdata.getStationInGroup();
     s1=fdata.getInfoOfAllRcm(s.get(0).toString());
     String line=s1.get(0);
     //  for(int i=0;i<s.size();i++)
  // {
   	
	   
	  // st[i]=s1.get(i).toString();
	  // String temp=st[i];
		st1 = line.split("\\|");
       stationName.setText(st1[0]);
        status.setText(st1[1]);
      locationText.setText(st1[2]);
	  moneyavailable.setText(st1[3]);
	  incapacity.setText(st1[4]);
      // i++;
		
   	
  // }
       for(int i=0;i<s.size();i++)
       {
       	
       stationsInfoComboBox.addItem(s.get(i));  
       }
}
}
