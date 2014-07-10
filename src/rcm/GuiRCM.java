package rcm;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;





import rcm.RCM;
import rcm.RCMUser;



class GUI extends JFrame{
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 500;
	private Container contentPane;
	
	private JLabel labelItems=new JLabel("Accepted Item Values per pound ",SwingConstants.CENTER);
	private JTextArea messageTextArea=new JTextArea(5,20);
	private JButton startASessionButton;
	private JLabel labelChooseItemType=new JLabel("Choose the type of the item being inserted",SwingConstants.CENTER);
	private JComboBox itemTypesCombo;
	private JButton nextButtonAfterAddingItem;
	private JButton dropAnItemButton;
	private JComboBox rcmLocationCombo;
	JPanel controlPanel;
	JPanel itemsDetailsAreaPanel;
	JPanel startASessionPanel;
	JPanel itemTypesComboPanel;
	JPanel nextAfterAddingItemPanel;
	JPanel locationPanel;
	
	private ActionListener startASessionButtonEventHandler;
	private ActionListener itemTypesComboBoxEventHandler;
	private ActionListener nextButtonAfterAddingItemEventHandler;
	private ActionListener dropAnItemButtonEventHandler;
	private ActionListener comboBoxActionListener;
	private ItemListener comboBoxItemListener;
	
	
	RCM rcmObject= new RCM();
	RCMUser rcmUserObject= new RCMUser();
	ArrayList<String> itemsAccepted=new ArrayList<String>();
	HashMap<String, Double> pricePaidForEachItem = new HashMap<String, Double>();
	HashMap<String, String> locationAssociatedRCM = new HashMap<String, String>();
	
	public GUI(){
		contentPane = getContentPane();	
		
		startASessionButtonEventHandler = new StartASessionButtonEventHandler();
		dropAnItemButtonEventHandler= new DropAnItemButtonEventHandler();
		comboBoxActionListener = new ComboBoxActionListener();
		//comboBoxItemListener= new ComboBoxItemListener();
		/*itemTypesComboBoxEventHandler= new ItemTypesComboBoxEventHandler();
		nextButtonAfterAddingItemEventHandler=new NextButtonAfterAddingItemEventHandler();*/
		controlPanel = new JPanel();
		
		// Create the control Panel
	    createControlPanel();
	    
	    
   	    //Set the size of the frame
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        
        
         
		// Center the frame on the screen
		Toolkit toolkit = getToolkit();
		Dimension size = toolkit.getScreenSize();
		setLocation(size.width/2 - getWidth()/2,size.height/2 - getHeight()/2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	class StartASessionButtonEventHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			
			startASessionButton.setVisible(false);
			dropAnItemButton.setVisible(true);
			//remove the existing panels
			//itemsDetailsAreaPanel.setVisible(false);
			
			//startASessionPanel.setVisible(false);
			/*controlPanel.add(itemTypesComboPanel);
			controlPanel.add(nextAfterAddingItemPanel);
			
		
			
			pricePaidForEachItem=rcmObject.displayItemsAcceptedDetails();
			   Iterator<String> keySetIterator = pricePaidForEachItem.keySet().iterator();
			   itemTypesCombo.addItem("Select");
			   while(keySetIterator.hasNext()){
				   String key = keySetIterator.next();
				   itemTypesCombo.addItem(key);
			   }	
			   itemTypesCombo.addItem("Others");
			itemTypesCombo.addActionListener(itemTypesComboBoxEventHandler);*/		
			
		}
	}
	
	class DropAnItemButtonEventHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			//JOptionPane.showMessageDialog(null, "Item Dropped Successfully");
			boolean status=rcmUserObject.dropItem();
			if(status){
				JOptionPane.showMessageDialog(null, "Item Dropped Successfully");
			}else
			{
				JOptionPane.showMessageDialog(null, "Item Not Dropped");
			}
			
		}
	}
	
	class ComboBoxActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			String itemName = rcmLocationCombo.getSelectedItem().toString();
		 	if(itemName!="Select"){
		 		rcmObject.setLocation(itemName);
		 		JOptionPane.showMessageDialog(null, rcmObject.getLocation());
		 		//locationAssociatedRCM= rcmObject.getLocationAssociatedRCM();
				 Iterator<String> keySetIterator = locationAssociatedRCM.keySet().iterator();
				   while(keySetIterator.hasNext()){
					   String key = keySetIterator.next();
					   if(locationAssociatedRCM.get(key).equals(rcmObject.getLocation())){
						   rcmObject.setMachineId(key);
						   JOptionPane.showMessageDialog(null, rcmObject.getMachineId());
						   startASessionButton.setEnabled(true);
						   }
					   }
		 		
		 	}
		 	else{
		 		startASessionButton.setEnabled(false);
		 	}
		 	
			 
		 }
	}
	
	/*class ComboBoxItemListener implements ItemListener
	{
		 public void itemStateChanged(ItemEvent event) {
			 startASessionButton.setEnabled(false);
		 }
	}*/
	
	/*class ItemTypesComboBoxEventHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			
			String itemName = itemTypesCombo.getSelectedItem().toString();
			if(itemName=="Select"){
				JOptionPane.showMessageDialog(null, "Choose an item type");		 		
		 	}
			else if (itemName=="Others"){
				JOptionPane.showMessageDialog(null, "Item cannot be accepted");
				// GUI needs to be handled here ********************
			}
			else{
				nextButtonAfterAddingItem.setEnabled(true);
				JOptionPane.showMessageDialog(null, "Please drop the item. Once done click on Next");
				
			}
			
			
		}
	}
	
	class NextButtonAfterAddingItemEventHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			rcmUserObject.dropItem(itemTypesCombo.getSelectedItem().toString());
			JOptionPane.showMessageDialog(null, "Item Accepted");
			
		}
	}*/
	
	
	
	public JPanel createTextAreaPanel()
	{
	   
	   JPanel panel = new JPanel();
	   //Call the method to display the items accepted and associated price
	   
	   pricePaidForEachItem=rcmObject.displayItemsAcceptedDetails();
	   Iterator<String> keySetIterator = pricePaidForEachItem.keySet().iterator();
	   while(keySetIterator.hasNext()){
		   String key = keySetIterator.next();
		   messageTextArea.append( key + " : $" + pricePaidForEachItem.get(key)+"\n");
	   }	
	   panel.add(labelItems, BorderLayout.NORTH);
	   panel.add(messageTextArea);
	   
	   panel.setBorder(new TitledBorder(new EtchedBorder(), "Item Details"));
	   return panel;
	}
	
	public JPanel startASessionPanel(){
		
		startASessionButton = new JButton("Start a Session");
		startASessionButton.setEnabled(false);
		dropAnItemButton= new JButton("Drop the Item");
		dropAnItemButton.setVisible(false);
		startASessionButton.addActionListener(startASessionButtonEventHandler);
		dropAnItemButton.addActionListener(dropAnItemButtonEventHandler);
		JPanel panel = new JPanel();
		panel.add(startASessionButton);
		panel.add(dropAnItemButton);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Start A Session"));
		return panel;
		
	}
	
	/*public JPanel itemTypesComboBoxPanel(){
		itemTypesCombo= new JComboBox<>();
		JPanel panel= new JPanel();
		panel.add(labelChooseItemType);
		panel.add(itemTypesCombo);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Item Types Combo Box"));
		return panel;
	}
	
	public JPanel nextButtonAfterAddingItemPanel(){
		nextButtonAfterAddingItem= new JButton("Next");
		nextButtonAfterAddingItem.setEnabled(false);
		nextButtonAfterAddingItem.addActionListener(nextButtonAfterAddingItemEventHandler);
		JPanel panel= new JPanel();
		panel.add(nextButtonAfterAddingItem);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Next"));
		return panel;
	}*/
	
	public JPanel createLocationComboBox(){
		rcmLocationCombo= new JComboBox<>();
		rcmLocationCombo.addItem("Select");
		//ArrayList<String>rcmLocations= new ArrayList<String>();
		locationAssociatedRCM= rcmObject.getLocationAssociatedRCM();
		 Iterator<String> keySetIterator = locationAssociatedRCM.keySet().iterator();
		   while(keySetIterator.hasNext()){
			   String key = keySetIterator.next();
			   rcmLocationCombo.addItem(locationAssociatedRCM.get(key));
		   }
		JPanel panel = new JPanel();
		panel.add(rcmLocationCombo);
		rcmLocationCombo.addActionListener(comboBoxActionListener);
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Choose Location"));
		return panel;
	}
	
	
	/**
	Creates the control panel which holds other component panels
	*/
	public void createControlPanel()
	{
		itemsDetailsAreaPanel= createTextAreaPanel();
		locationPanel = createLocationComboBox();
		startASessionPanel= startASessionPanel();
		/*itemTypesComboPanel= itemTypesComboBoxPanel();
		nextAfterAddingItemPanel= nextButtonAfterAddingItemPanel();*/
		
		controlPanel.setLayout(new GridLayout(0,1));
		controlPanel.add(itemsDetailsAreaPanel);
		controlPanel.add(locationPanel);
		controlPanel.add(startASessionPanel);
		
		
		contentPane.add(controlPanel, BorderLayout.SOUTH);
	}
	
	
	
	
}

public class GuiRCM {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		JFrame frame = new GUI();
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setTitle("Recycle Machine");
	      frame.setVisible(true);      
	}

}
