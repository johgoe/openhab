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
package org.openhab.binding.ecobee.messages;

/**
 * The update sensor function allows the caller to update the name of an ecobee3 remote sensor.
 *
 * Each ecobee3 remote sensor "enclosure" contains two distinct sensors types temperature and occupancy. Only one of the
 * sensors is required in the request. Both of the sensors' names will be updated to ensure consistency as they are part
 * of the same remote sensor enclosure. This also reflects accurately what happens on the Thermostat itself.
 *
 * Note: This function is restricted to the ecobee3 thermostat model only.
 *
 * @see <a href="https://www.ecobee.com/home/developer/api/documentation/v1/functions/UpdateSensor.shtml">Update
 *      Sensor</a>
 * @author John Cocula
 * @since 1.7.0
 */
public final class UpdateSensorFunction extends AbstractFunction {

    /**
     * Construct an UpdateSensorFunction.
     * 
     * @param name
     *            the updated name to give the sensor. Has a max length of 32, but shorter is recommended.
     * @param deviceId
     *            the deviceId for the sensor, typically this indicates the enclosure and corresponds to the
     *            ThermostatRemoteSensor.id field. For example: <code>rs:100</code>
     * @param sensorId
     *            the identifier for the sensor within the enclosure. Corresponds to the RemoteSensorCapability.id. For
     *            example: 1
     */
    public UpdateSensorFunction(final String name, final String deviceId, final String sensorId) {
        super("updateSensor");

        if (name == null || deviceId == null || sensorId == null) {
            throw new IllegalArgumentException("name, deviceId and sensorId arguments are required.");
        }

        makeParams().put("name", name);
        makeParams().put("deviceId", deviceId);
        makeParams().put("sensorId", sensorId);
    }
}
