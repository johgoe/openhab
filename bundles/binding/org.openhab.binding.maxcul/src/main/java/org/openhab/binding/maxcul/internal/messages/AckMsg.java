/**
 * Copyright (c) 2010-2019 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.maxcul.internal.messages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Message class to handle ACK/NACK
 *
 * @author Paul Hampson (cyclingengineer)
 * @since 1.6.0
 */
public class AckMsg extends BaseMsg {

    final static private int ACK_MSG_PAYLOAD_LEN = 2;
    final static private int ACK_MSG_EXTENDED_PAYLOAD_LEN = 4;
    private boolean isNack;
    private ThermostatControlMode ctrlMode;
    private boolean dstActive;
    private boolean lanGateway; // unknown what this is for
    private boolean lockedForManualSetPoint;
    private boolean rfError;
    private boolean batteryLow;

    private boolean extendedAck = false;
    private double desiredTemperature;
    private boolean displayMeasuredTemp;

    private static final Logger logger = LoggerFactory.getLogger(AckMsg.class);

    public AckMsg(String rawMsg) {
        super(rawMsg);
        if (this.payload.length == ACK_MSG_PAYLOAD_LEN || this.payload.length == ACK_MSG_EXTENDED_PAYLOAD_LEN) {
            isNack = (this.payload[0] == 0x81);
            /* extract control mode */
            ctrlMode = ThermostatControlMode.values()[(this.payload[1] & 0x3)];
            /* extract DST status */
            dstActive = extractBitFromByte(this.payload[1], 3);
            ;
            /* extract lanGateway */
            lanGateway = extractBitFromByte(this.payload[1], 4);
            /* extract locked status */
            lockedForManualSetPoint = extractBitFromByte(this.payload[0], 5);
            /* extract rferror status */
            rfError = extractBitFromByte(this.payload[1], 6);
            /* extract battery status */
            batteryLow = extractBitFromByte(this.payload[1], 7);
            if (this.payload.length == ACK_MSG_EXTENDED_PAYLOAD_LEN) {
                this.extendedAck = true;
                /*
                 * extract whether wall therm is configured to show measured or
                 * desired temperature
                 */
                displayMeasuredTemp = (this.payload[1] == 0 ? false : true);
                /* extract desired temperature information */
                desiredTemperature = (this.payload[2] & 0x7f) / 2.0;
            }
        } else {
            logger.error("Got " + this.msgType + " message with incorrect length! ");
        }
        /* TODO should probably check if it's a 0x01 for ACK */
        this.printDebugPayload();
    }

    public AckMsg(byte msgCount, byte msgFlag, byte groupId, String srcAddr, String dstAddr, boolean isNack) {
        super(msgCount, msgFlag, MaxCulMsgType.ACK, groupId, srcAddr, dstAddr);

        byte[] payload = new byte[ACK_MSG_PAYLOAD_LEN];
        payload[0] = 0x01;
        if (isNack) {
            payload[0] |= 0x80; // make 0x81 for NACK
        }
        super.appendPayload(payload);
        this.printDebugPayload();
    }

    public boolean getIsNack() {
        return isNack;
    }

    /**
     * Print output as decoded fields
     */
    @Override
    protected void printFormattedPayload() {
        logger.debug("\tIs ACK?                  => " + (!this.isNack));
        logger.debug("\tControl Mode        => " + ctrlMode);
        logger.debug("\tDST Active          => " + dstActive);
        logger.debug("\tLAN Gateway         => " + lanGateway);
        logger.debug("\tPanel locked        => " + lockedForManualSetPoint);
        logger.debug("\tRF Error            => " + rfError);
        logger.debug("\tBattery Low         => " + batteryLow);
        if (this.extendedAck) {
            logger.debug("\tDesired Temperature => " + desiredTemperature);
            logger.debug("\tDisplay meas. temp  => " + displayMeasuredTemp);
        }
    }

    public boolean getBatteryLow() {
        return batteryLow;
    }

    public ThermostatControlMode getControlMode() {
        return ctrlMode;
    }

    public Double getDesiredTemperature() {
        return desiredTemperature;
    }

    public boolean isExtendedAck() {
        return extendedAck;
    }

}
