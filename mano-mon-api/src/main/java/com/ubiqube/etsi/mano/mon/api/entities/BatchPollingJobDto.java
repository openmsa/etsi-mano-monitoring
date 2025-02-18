package com.ubiqube.etsi.mano.mon.api.entities;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.NonNull;

import com.ubiqube.etsi.mano.dao.mano.AccessInfo;
import com.ubiqube.etsi.mano.dao.mano.InterfaceInfo;
import com.ubiqube.etsi.mano.service.mon.dto.MonConnInformationDto;

import lombok.Data;

@Data
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
