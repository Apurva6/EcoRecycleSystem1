import java.util.ArrayList;

import rcm.RCM;
import rcm.RCMUser;




public class Tester {
	public static void main(String[] args) {
		RCM rcmObject= new RCM();
		RCMUser rcmUserObj= new RCMUser();
		//rcmObject.getMoneyAvailable("RCM2");
		//rcmObject.setMoneyAvailable("RCM3", 900.0);
		//rcmObject.getMoneyAvailable("RCM3");
		rcmObject.displayItemsAcceptedDetails();
		rcmUserObj.dropItem();
		/*rcmUserObj.getItemTypeAdded();*/
		/*rcmUserObj.getLastItemId("RCM4");
		rcmUserObj.setLastItemId("RCM4",1);
		rcmUserObj.getLastItemId("RCM4");*/
		ArrayList<String>rcmLocations= new ArrayList<String>();
		//rcmLocations=rcmObject.getRcmLocations();
	}

}
