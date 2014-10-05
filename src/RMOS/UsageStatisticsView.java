package RMOS;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

//import t1.BarChart;
//import t1.GraphsDemo;

public class UsageStatisticsView extends JFrame{

	

		   /**
	 * 
	 */
	
	
	
	
	
	private static final long serialVersionUID = 1L;
	public UsageStatisticsView(String stats){
		super ("Bar Chart");
		
		setPreferredSize(new Dimension(800,800));
		setLocationRelativeTo(null);
		

		BarChart chart = new BarChart();
	//String str="Color.red";
		FilteredDataForUsageStatistics d=new FilteredDataForUsageStatistics();
		// the data values are hardcoded
		//chart.addBar(Color.red, 100);
		//chart.addBar(Color.green, 8);
		//chart.addBar(Color.blue, 54);
		//chart.addBar(Color.black, 23);
		
		FilteredDataofRcm f=new FilteredDataofRcm();
		if(stats=="Weight added")
		{
			ArrayList<String> machineList=new ArrayList<String>();
		
		machineList=f.getStationInGroup();
		
		for(int i=0;i<machineList.size();i++)
		{
			int wt=(int) d.getWeightAddedInMachine(machineList.get(i));
			chart.addBar(i, wt);
			//System.out.println(chart.bars.get(1));
					//chart.addBar(Color.red, d.getWeightAddedInMachine(machineList.get(i)));
		}
		}
		else
		/*	if(stats=="Cash/Coupon Dispatched")
			{
				ArrayList<String> machineList=new ArrayList<String>();
				
				machineList=f.getStationInGroup();
				
				for(int i=0;i<machineList.size();i++)
				{
					double wt= d.getTotalCashInMachine(machineList.get(i));
					System.out.println(machineList.get(i));
					int temp=(int)wt;
					chart.addBar(i, temp);
					}
			}*/
		
		getContentPane().setBackground(Color.CYAN  );
		getContentPane().add(chart);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	public static void main(String[] args)
	{
		String s="Weight added";
		new UsageStatisticsView(s);	
		

	}	
}
class BarChart extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private
	Map<Integer, Integer> bars =
            new LinkedHashMap<Integer, Integer>();

	public BarChart()
	{
		setPreferredSize(new Dimension(50,50));
		
	}
	/**
	 * Add new bar to chart
	 * @param color color to display bar
	 * @param d size of bar
	 */
	public void addBar(int color, int d)
	{
		bars.put(color,d);
		
		// cannot call paintComponent() or paint() directly
		repaint();
	}

	
	@Override
	protected void paintComponent(Graphics g)
	{
		// determine longest bar

		int max = Integer.MIN_VALUE;
		for (Integer value : bars.values())
		{
			max = Math.max(max, value);
		}

		// paint bars

		int width = (getWidth() / bars.size()) - 100;
		int x = 1;
		for (int color : bars.keySet())
		{
			int value = bars.get(color);
			int height = (int)
                            ((getHeight()-5) * ((double)value / max))-300;
			//g.setColor(color);
			g.fillRect(x, getHeight() - height, width, height);
			g.setColor(Color.black);
			g.drawRect(x, getHeight() - height, width, height);
			x += (width + 2);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(bars.size() * 10 + 2, 50);
	}
	
}

