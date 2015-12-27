package org.openhab.binding.maxcul.internal;

import java.util.ArrayList;
import java.util.List;

public class MaxCulWeekProfile {

	private List<MaxCulWeekProfilePart> weekProfileParts = new ArrayList<MaxCulWeekProfilePart>();
			
	public void addWeekProfilePart(MaxCulWeekProfilePart weekProfilePart){
		weekProfileParts.add(weekProfilePart);
	}
			
			
    public List<MaxCulWeekProfilePart> getWeekProfileParts(){
		return weekProfileParts;
	}		
}
