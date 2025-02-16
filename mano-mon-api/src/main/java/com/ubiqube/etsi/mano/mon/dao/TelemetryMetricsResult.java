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
package com.ubiqube.etsi.mano.mon.dao;

import java.time.OffsetDateTime;

import org.jspecify.annotations.NonNull;

import com.ubiqube.etsi.mano.service.mon.data.MonitoringDataSlim;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author Olivier Vignaud {@literal <ovi@ubiqube.com>}
 *
 */
@Setter
@Getter
@ToString
public class TelemetryMetricsResult implements MonitoringDataSlim {
	@NonNull
	private String masterJobId;

	private String resourceId;

	private String key;

	private Double value;

	private String text;

	private boolean error;

	@Temporal(TemporalType.TIMESTAMP)
	private OffsetDateTime time;

	public TelemetryMetricsResult() {
		// Nothing.
	}

	public TelemetryMetricsResult(@NonNull final String masterJobId, final String resourceId, final String key, final Double value, final String txt, final OffsetDateTime timestamp, final boolean error) {
		this.masterJobId = masterJobId;
		this.resourceId = resourceId;
		this.key = key;
		this.value = value;
		this.time = timestamp;
		this.error = error;
		this.text = txt;
	}

}
