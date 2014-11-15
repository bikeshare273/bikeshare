package bikeshareinterfaces;

import java.text.ParseException;
import java.util.*;

public interface LocationInventoryInterface {
	
	public String[] getInvForAnHour(int location_id, Date date, int hour ) throws ParseException;
	
	public Map<Integer, String[]> getInvForAnHourAtVariousLoc(int[] arrayOfLocation_id, Date date, int hour );
	
	public Map<Integer, String[]> getInvForSeveralHoursAtOneLocation(int location_id, Date date, int[] arrayOfHours );
			
	public void updateInvForBookingHours(int location_id, Date date, int[] arrayOfHours, String bikeID );
	
}
