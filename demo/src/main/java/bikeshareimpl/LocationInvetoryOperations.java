
package bikeshareimpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import resources.LocationInventory;
import util.MongodbConnection;
import bikeshareinterfaces.LocationInventoryInterface;

public class LocationInvetoryOperations implements LocationInventoryInterface 
{
	private MongoTemplate mongoTemplate = MongodbConnection.getConnection();
	
	//To get bike inventory for an hour at a particular location
	public String[] getInvForAnHour(int location_id, Date date, int hour)  

	{
		Date formattedDate = dateFormatter(date);
		String [] returnNullIfNotFound = null;
		
		LocationInventory loc_inv = new LocationInventory();

	    Query query = new Query();
	    query.addCriteria(Criteria.where("location_id").is(location_id).and("date").is(formattedDate));
	    
	    loc_inv = mongoTemplate.findOne(query, LocationInventory.class,"locationinv");
	    
	    if (loc_inv == null)
	    {
	    	return returnNullIfNotFound;
	    	
	    }
	    
	    switch (hour)
    	{ 		
    	  case 0:   return loc_inv.gethour_0();  	
	    	case 1:   return loc_inv.gethour_1();  	
	    	case 2:   return loc_inv.gethour_2();  	
	    	case 3:   return loc_inv.gethour_3();  	
	    	case 4:   return loc_inv.gethour_4();  	
	    	case 5:   return loc_inv.gethour_5();  	
	    	case 6:   return loc_inv.gethour_6();  	
	    	case 7:   return loc_inv.gethour_7();  
	    	case 8:   return loc_inv.gethour_8();  	
	    	case 9:   return loc_inv.gethour_9();  	
	    	case 10:  return loc_inv.gethour_10();  
	    	case 11:  return loc_inv.gethour_11();  
	    	case 12:  return loc_inv.gethour_12(); 
	    	case 13:  return loc_inv.gethour_13();  
	    	case 14:  return loc_inv.gethour_14();  
	    	case 15:  return loc_inv.gethour_15(); 
	    	case 16:  return loc_inv.gethour_16();  
	    	case 17:  return loc_inv.gethour_17();  
	    	case 18:  return loc_inv.gethour_18(); 
	    	case 19:  return loc_inv.gethour_19();  
	    	case 20:  return loc_inv.gethour_20();  
	    	case 21:  return loc_inv.gethour_21();  
	    	case 22:  return loc_inv.gethour_22();  
	    	case 23:  return loc_inv.gethour_23();  
	    	default:  return returnNullIfNotFound;
	    	
    	}
    	
	} // Method Ends
	    
	//To get bike inventory for an hour at several locations    
	public Map<Integer, String[]> getInvForAnHourAtVariousLoc(int[] arrayOfLocations, Date date, int hour )
	    
	{
		Map<Integer, String[]> locWiseInventoryForAnHour = new HashMap<>();
		
		for (int loop = 0; loop < arrayOfLocations.length; loop++ )
		{
			int loc_id = arrayOfLocations[loop];
			locWiseInventoryForAnHour.put(loc_id, getInvForAnHour(loc_id, date, hour));
		}
		
		return locWiseInventoryForAnHour;
		
	}
	    
	//To get bike inventory at a location for several hours
	public Map<Integer, String[]> getInvForSeveralHoursAtOneLocation(int location_id, Date date, int[] arrayOfHours )
	{
		Map<Integer, String[]> hourWiseInventoryForLocation = new HashMap<>();
		
		for (int loop = 0; loop < arrayOfHours.length; loop++ )
		{
			int hour = arrayOfHours[loop];
			hourWiseInventoryForLocation.put(hour, getInvForAnHour(location_id, date, hour));
		}
		
		return hourWiseInventoryForLocation;
	}
	    
	    
	//To update location inventory for several hours
	public void updateInvForBookingHours(int location_id, Date date, int[] arrayOfHours, String bikeID)
	
	{
		     
	    Date formattedDate = dateFormatter(date);
		
		LocationInventory loc_inv = new LocationInventory();

	    Query query = new Query();
	    query.addCriteria(Criteria.where("location_id").is(location_id).and("date").is(formattedDate));

	    loc_inv = mongoTemplate.findOne(query, LocationInventory.class,"locationinv");
		
	    LocationInventory updated_loc_inv = new LocationInventory();
	    
	    updated_loc_inv = updateInventory(loc_inv, arrayOfHours, bikeID) ;
	    		
	    mongoTemplate.remove(query, LocationInventory.class, "locationinv");
	    mongoTemplate.save(updated_loc_inv, "locationinv");		
	    
	}
	    
	
	public Date dateFormatter ( Date date)
	{
			SimpleDateFormat sdf = null;
			Date formattedDate = null;
			
			try{
				sdf = new SimpleDateFormat("yyyy-MM-dd");      
				formattedDate = sdf.parse(sdf.format(date));
				}
		    catch (ParseException e)
				{
		    	System.out.println("Could not parse" + date.toString());
				} 
		return formattedDate;
	}

	//Method to select inventories to update
	public LocationInventory updateInventory(LocationInventory loc_inv, int[] arrayOfHours, String bikeID)
	    
	{
		String tempInv[] = null;
	    
		for (int loop = 0; loop < arrayOfHours.length; loop++ )
		
		switch (arrayOfHours[loop])
     	{ 		
     	   	case 0:   
     	   			tempInv = null;
     	   			tempInv = loc_inv.gethour_0();
     	   			for(int i = 0; i<tempInv.length; i++)
     	   			{tempInv[i].replace(bikeID, "X");}
     	   			loc_inv.sethour_0(tempInv);
     	   	break;
 	    	
     	    case 1:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_1();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_1(tempInv);
	   		break;
 	   		
     	    case 2:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_2();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_2(tempInv);
	   		break;
     		   		
     	    case 3:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_3();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_3(tempInv);
	   	    break;
	   	    
     	    case 4:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_4();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_4(tempInv);
	   	    break;
	   	    
     	    case 5:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_5();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_5(tempInv);
	   	    break;
	   	    
     	    case 6:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_6();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_6(tempInv);
	   	    break;
	   	    
     	    case 7:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_7();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_7(tempInv);
	   	    break;
	   	    
     	    case 8:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_8();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_8(tempInv);
	   	    break;
	   	    
     	    case 9:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_9();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_9(tempInv);
	   	    break;
	   	    
     	    case 10:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_10();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_10(tempInv);
	   	    break;
	   	    
     	    case 11:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_11();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_11(tempInv);
	   	    break;
	   	    
     	    case 12:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_12();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_12(tempInv);
	   	    break;
	   	    
     	    case 13:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_13();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_13(tempInv);
	   	    break;
	   	    
     	    case 14:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_14();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_14(tempInv);
	   	    break;
	   	    
     	    case 15:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_15();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_15(tempInv);
	   	    break;
	   	    
     	    case 16:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_16();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_16(tempInv);
	   	    break;
	   	    
     	    case 17:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_17();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_17(tempInv);
	   	    break;
	   	    
     	    case 18:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_18();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_18(tempInv);
	   	    break;
	   	    
     	    case 19:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_19();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_19(tempInv);
	   	    break;
	   	    
     	    case 20:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_20();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_20(tempInv);
	   	    break;
	   	    
     	    case 21:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_21();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_21(tempInv);
	   	    break;
	   	    
     	    case 22:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_22();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_22(tempInv);
	   	    break;
     	 
     	    case 23:   
	   			tempInv = null;
	   			tempInv = loc_inv.gethour_23();
	   			for(int i = 0; i<tempInv.length; i++)
	   			{tempInv[i].replace(bikeID, "X");}
	   			loc_inv.sethour_23(tempInv);
	   	    break;
	   	}
	
		return loc_inv;
		
	}
	

}
