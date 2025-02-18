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
package com.ubiqube.etsi.mano.mon.api.entities;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.NonNull;

import com.ubiqube.etsi.mano.dao.mano.AccessInfo;
import com.ubiqube.etsi.mano.dao.mano.InterfaceInfo;
import com.ubiqube.etsi.mano.service.mon.dto.MonConnInformationDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BatchPollingJobDto<I extends InterfaceInfo, A extends AccessInfo> {
	/**
	 * Internal ID.
	 */
	private UUID id;

	/***
	 * Resource Id to poll.
	 */
	@NonNull
	private String resourceId;

	/**
	 * Metrics to poll.
	 */
	@NonNull
	private List<MetricDto> metrics;

	/**
	 * Connection ID. Linked to where/how to poll question.
	 */
	private MonConnInformationDto<I, A> connection;

	/**
	 * Interval between polling ticks.
	 */
	private long interval;

	/**
	 * Last time the metric have been polled (SUCCESS / FAILURE )
	 */
	private ZonedDateTime lastRun;

	@Override
	public String toString() {
		return "BatchPollingJob [id=" + id + ", resourceId=" + resourceId + ", connectionId=" + connection + ", interval=" + interval + ", lastRun=" + lastRun + "]";
	}

}
