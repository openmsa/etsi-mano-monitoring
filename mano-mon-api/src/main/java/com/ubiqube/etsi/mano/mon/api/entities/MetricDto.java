package com.ubiqube.etsi.mano.mon.api.entities;

import java.util.UUID;

import lombok.Data;

@Data
public class MetricDto {
	private UUID id;
	private String metricName;
	/**
	 * Hint for some poller.
	 */
	private String type;

	public MetricDto() {
		// Nothing.
	}

	public MetricDto(final String metricName, final String type) {
		this.metricName = metricName;
		this.type = type;
	}

}
