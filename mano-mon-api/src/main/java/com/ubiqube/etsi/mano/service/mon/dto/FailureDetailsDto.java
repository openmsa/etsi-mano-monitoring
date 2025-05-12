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

	private Long status;

	public FailureDetailsDto() {
		// Nothing.
	}

	public FailureDetailsDto(final Long _status, final String _detail) {
		try {
			instance = URI.create("http//" + InetAddress.getLocalHost().getCanonicalHostName()).toString();
		} catch (final UnknownHostException e) {
			LOG.warn("", e);
		}
		status = _status;
		detail = _detail;
	}

}
