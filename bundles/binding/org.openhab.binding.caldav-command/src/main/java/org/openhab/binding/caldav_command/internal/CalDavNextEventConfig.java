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
package org.openhab.binding.caldav_command.internal;

import org.openhab.core.binding.BindingConfig;

/**
 * Configuration class for items.
 *
 * @author Robert Delbrück
 * @since 1.8.0
 */
public class CalDavNextEventConfig implements BindingConfig {
    /**
     * configuration is for this item
     */
    private String itemName;

    /**
     * configuration is for the item which is referenced inside the calendar
     */
    private String itemNameToListenTo;
    private CalDavType type;

    public CalDavNextEventConfig() {
        super();
    }

    public CalDavNextEventConfig(String itemName, String itemNameToListenTo, CalDavType type) {
        super();
        this.itemName = itemName;
        this.itemNameToListenTo = itemNameToListenTo;
        this.type = type;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemNameToListenTo() {
        return itemNameToListenTo;
    }

    public void setItemNameToListenTo(String itemNameToListenTo) {
        this.itemNameToListenTo = itemNameToListenTo;
    }

    public CalDavType getType() {
        return type;
    }

    public void setType(CalDavType type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((itemName == null) ? 0 : itemName.hashCode());
        result = prime * result + ((itemNameToListenTo == null) ? 0 : itemNameToListenTo.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        CalDavNextEventConfig other = (CalDavNextEventConfig) obj;
        if (itemName == null) {
            if (other.itemName != null) {
                return false;
            }
        } else if (!itemName.equals(other.itemName)) {
            return false;
        }
        if (itemNameToListenTo == null) {
            if (other.itemNameToListenTo != null) {
                return false;
            }
        } else if (!itemNameToListenTo.equals(other.itemNameToListenTo)) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CalDavNextEventConfig [itemName=" + itemName + ", itemNameToListenTo=" + itemNameToListenTo + ", type="
                + type + "]";
    }
}
