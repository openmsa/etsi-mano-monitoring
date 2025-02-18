package com.ubiqube.etsi.mano.service.mon.dto;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

@Data
public class FailureDetailsDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(FailureDetailsDto.class);

	private URI type;

	private String title;

	private String detail;

	private String instance;

	public FailureDetailsDto() {
		// Nothing.
	}

	public FailureDetailsDto(final long _status, final String _detail) {
		try {
			instance = URI.create("http//" + InetAddress.getLocalHost().getCanonicalHostName()).toString();
		} catch (final UnknownHostException e) {
			LOG.warn("", e);
		}
		long status = _status;
		detail = _detail;
	}

}
