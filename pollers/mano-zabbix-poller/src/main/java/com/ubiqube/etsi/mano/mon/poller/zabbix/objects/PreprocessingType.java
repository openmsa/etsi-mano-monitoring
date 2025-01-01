/**
 * Copyright (C) 2019-2025 Ubiqube.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.mon.poller.zabbix.objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.ubiqube.etsi.mano.mon.MonGenericException;

public enum PreprocessingType {

	JSONPATH("JSONPATH"),
	DISCARD_UNCHANGED_HEARTBEAT("DISCARD_UNCHANGED_HEARTBEAT"),
	BOOL_TO_DECIMAL("BOOL_TO_DECIMAL"),
	CHANGE_PER_SECOND("CHANGE_PER_SECOND"),
	MULTIPLIER("MULTIPLIER");

	private final String value;

	PreprocessingType(final String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static PreprocessingType fromValue(final String text) {
		for (final PreprocessingType b : PreprocessingType.values()) {
			if (b.value.equals(text)) {
				return b;
			}
		}
		throw new MonGenericException("" + text);
	}

}
