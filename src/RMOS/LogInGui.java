package RMOS;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import LogIn.LogIn;

public class LogInGui extends JFrame implements ActionListener{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */
	 private JLabel userName;
	   private JLabel password;
	   private JTextField userNameTextField;
	   private JTextField passwordTextField;
	   private JPasswordField passwordField;
	   
	   private JButton loginButton;
	   public static boolean logIn=false;
	   JPanel loginPanel= new JPanel();
	  JFrame j;
	   Container containerLogin;
	 public LogInGui()
	 {
		/* loginPanel=new JPanel();
		 loginPanel.setLayout(new BoxLayout(loginPanel,BoxLayout.Y_AXIS));
		 
		 LogInButton=new JButton("Login");
		 LogInButton.addActionListener(this);
		 userText=new JTextField();
		 passwordText=new JTextField();
		 
		 userLabel=new JLabel("User Name");
		 passwordLabel=new JLabel("Password");
		 loginPanel.add(userLabel);
		 loginPanel.add(userText);
		 loginPanel.add(passwordLabel);
		 loginPanel.add(passwordText);
		 loginPanel.add(LogInButton);*/
		  //j=new JFrame();
		 containerLogin = getContentPane();
		 containerLogin.setBackground(Color.MAGENTA);
		 //containerLogin= new Container();
		 containerLogin.setLayout(new GridLayout(0,1));
	      containerLogin.setVisible(true);
	      
	      userName= new JLabel("Enter UserName");
		  userNameTextField= new JTextField("",10);
		  password= new JLabel("Enter Password");
		  //passwordTextField= new JTextField("",10);
		  passwordField= new JPasswordField(10);
		  
		  loginButton= new JButton("LogIn");
		 loginPanel.add(userName);
		  loginPanel.add(userNameTextField);
		  loginPanel.add(password);
		  loginPanel.add(passwordField);
		  loginPanel.add(loginButton);
		  loginPanel.setBackground(Color.PINK);
		 // loginPanel.setAlignmentX(RIGHT_ALIGNMENT);
		  loginButton.addActionListener(loginFunction);
		  containerLogin.add(loginPanel);
		  //j.add(containerLogin);
		  setSize(300,300);
	      setLocationRelativeTo(null);
	      setVisible( true );
		 
	 }
	 
	 ActionListener loginFunction =new ActionListener() {
   	  @SuppressWarnings("deprecation")
	@Override
			public void actionPerformed(ActionEvent arg0) {
   		  LogIn instanceOfLogin= new LogIn();
   		  if(instanceOfLogin.loginRmos(userNameTextField.getText(), passwordField.getText())){
   			  {
   				  JOptionPane.showMessageDialog( null,
   			  
				            "Login Successful "  );
   			 
   			  GuiRMOS g=new GuiRMOS();
   			  containerLogin.setVisible(false);
   			  
   			  g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   		  }
   			  //loginPanel.setVisible(false);
   			 // addStationPanel.setVisible(true);
   			  logIn=true;
   		  }
   		  else{
   		  JOptionPane.showMessageDialog( null,
			            "Login UnSuccessful "  );
   		  }
   	  }
     };

	/*@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		boolean f;
		f=l.loginRmos(userText.getText(), passwordText.getText());
		
		if(f==true)
		{
			//login successful
		}
		else
		{
			if(f==false)
			{
				//login unsuccessful
			}
		}
		
	}*/
	
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		   //LogInGui application =
			//	   new LogInGui();
		    //  application.setDefaultCloseOperation(
		    //     JFrame.EXIT_ON_CLOSE );
		
	
		//FilteredDataofRcm f=new FilteredDataofRcm();
	
	
		   }*/

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
