package org.openhab.binding.maxcul.internal;

public enum MaxCulBoostDuration {
	MIN_0(0), MIN_5(1), MIN_10(2), 
	MIN_15(3), MIN_20(4), MIN_25(5), MIN_30(6), MIN_60(7),
	UNKNOWN(-1);	
	
	private final int durationIndex;

	private MaxCulBoostDuration(int idx) {
		durationIndex = idx;
	}

	public int getDurationIndex() {
		return durationIndex;
	}

	public static MaxCulBoostDuration getBoostDurationFromIdx(int idx) {
		for (int i = 0; i < MaxCulBoostDuration.values().length; i++) {
			if (MaxCulBoostDuration.values()[i].getDurationIndex() == idx)
				return MaxCulBoostDuration.values()[i];
		}
		return UNKNOWN;
	}
}
