package org.openhab.binding.maxcul.internal;

public enum MaxCulWeekdays {
	
	SATURDAY(0,"Sat"),
	SUNDAY(1,"Sun"),
	MONDAY(2,"Mon"), 
	TUESDAY(3,"Tue"),
	WENDSDAY(4,"Wen"),
	THURSDAY(5,"Thu"),
	FRIDAY(6,"Fri"),
	UNKNOWN(-1, "n/a");

	private final int weekDayIndex;
	private final String shortName;

	private MaxCulWeekdays(int idx, String shortName) {
		weekDayIndex = idx;
		this.shortName = shortName;
	}

	public int getDayIndexInt() {
		return weekDayIndex;
	}
	
	public String getDayShortName() {
		return shortName;
	}

	public static MaxCulWeekdays getWeekDayFromInt(int idx) {
		for (int i = 0; i < MaxCulWeekdays.values().length; i++) {
			if (MaxCulWeekdays.values()[i].getDayIndexInt() == idx)
				return MaxCulWeekdays.values()[i];
		}
		return UNKNOWN;
	}
	
	public static MaxCulWeekdays getWeekDayFromShortName(String shortName) {
		for (int i = 0; i < MaxCulWeekdays.values().length; i++) {
			if (MaxCulWeekdays.values()[i].getDayShortName().equals(shortName))
				return MaxCulWeekdays.values()[i];
		}
		return UNKNOWN;
	}
}
