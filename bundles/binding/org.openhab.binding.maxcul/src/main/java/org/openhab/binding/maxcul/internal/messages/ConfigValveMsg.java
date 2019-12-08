/**
 * Copyright (c) 2010-2015, openHAB.org and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.openhab.binding.maxcul.internal.messages;


import org.openhab.binding.maxcul.internal.MaxCulBoostDuration;
import org.openhab.binding.maxcul.internal.MaxCulWeekdays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConfigValveMsg extends BaseMsg {

	final static private int CONFIG_VALVE_PAYLOAD_LEN = 4; /* in bytes */

	private static final Logger logger = LoggerFactory
			.getLogger(ConfigValveMsg.class);

	private MaxCulBoostDuration boostDuration;
	private int boostValveposition; //0-100
	private int decalcificationHour; //0-23
	private int maxValveSetting; //0-100
	private int valveOffset; //0-100
	private MaxCulWeekdays decalcificationDay;


	public ConfigValveMsg(String rawMsg) {
		super(rawMsg);
		logger.debug(this.msgType + " Payload Len -> " + this.payload.length);
		if (this.payload.length == CONFIG_VALVE_PAYLOAD_LEN) {			
			this.boostDuration = MaxCulBoostDuration.getBoostDurationFromIdx(payload[0]>>5);
			byte bTemp=payload[0];
			bTemp &= 0x1F;			
			this.boostValveposition = (int)bTemp*5;
			this.decalcificationDay = MaxCulWeekdays.getWeekDayFromInt(payload[1] >>5);
			byte dTemp=payload[1];
			dTemp &= 0x1F;			
			this.decalcificationHour = (int)dTemp;
			this.maxValveSetting = (int)(payload[2]  * 100 / 255f);
			this.valveOffset = (int)(payload[3] * 100 / 255f);
		} else {
			logger.error("Got " + this.msgType
					+ " message with incorrect length!");
		}
	}
	
	private byte[] buildPayload() {
		byte[] payload = new byte[CONFIG_VALVE_PAYLOAD_LEN];
		payload[1] = (byte) (boostDuration.getDurationIndex()<<5|(int)(boostValveposition/5f));
		payload[0] = (byte) (decalcificationDay.getDayIndexInt()<<5|decalcificationHour);
		payload[2] = (byte) (maxValveSetting * 255/100f);
		payload[3] = (byte) (valveOffset * 255/100f);
		return payload;
	}

	/**
	 * Construct with default values
	 */
	public ConfigValveMsg(byte msgCount, byte msgFlag, byte groupId,
			String srcAddr, String dstAddr) {
		super(msgCount, msgFlag, MaxCulMsgType.CONFIG_VALVE, groupId,
				srcAddr, dstAddr);
		super.appendPayload(buildPayload());
	}

	public ConfigValveMsg(byte msgCount, byte msgFlag, byte groupId,
			String srcAddr, String dstAddr, MaxCulBoostDuration boostDuration, int boostValveposition, MaxCulWeekdays decalcificationDay,int decalcificationHour, int maxValveSetting, int valveOffset ) {
		super(msgCount, msgFlag, MaxCulMsgType.CONFIG_VALVE, groupId,
				srcAddr, dstAddr);

		this.boostDuration = boostDuration;
		this.boostValveposition = boostValveposition;
		this.decalcificationDay = decalcificationDay;
		this.decalcificationHour = decalcificationHour;
		this.maxValveSetting = maxValveSetting;
		this.valveOffset = valveOffset;
		super.appendPayload(buildPayload());
	}
	
	public MaxCulBoostDuration getBoostDuration() {
		return boostDuration;
	}

	public double getBoostValveposition() {
		return boostValveposition;
	}

	public int getDecalcification() {
		return decalcificationHour;
	}
	
	public MaxCulWeekdays getDecalcificationDay() {
		return decalcificationDay;
	}

	public double getMaxValveSetting() {
		return maxValveSetting;
	}

	public double getValveOffset() {
		return valveOffset;
	}


	
}