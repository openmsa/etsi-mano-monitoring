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

import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ubiqube.etsi.mano.service.mon.data.MonConnInformation;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type", defaultImpl = MonConnInformation.class, visible = true)
@JsonSubTypes({
		@JsonSubTypes.Type(value = KeystoneV3.class, name = "openstack-v3"),
		@JsonSubTypes.Type(value = SnmpV2.class, name = "snmp"),
		@JsonSubTypes.Type(value = SnmpV3.class, name = "snmp3"),
		@JsonSubTypes.Type(value = SnmpV2.class, name = "zabbix-v1")
})
public class ConnInfo {
	@NotNull
	private UUID connId;
	@NotNull
	private String type;

	private String name;

	private String endpoint;

	@JsonProperty("connection-timeout")
	private int connectionTimeout = 5_000;

	/**
	 * Read timeout in millisecondes.
	 */
	@JsonProperty("read-timeout")
	private int readTimeout = 5_000;

	/**
	 * Retry on failure.
	 */
	private int retry = 5;

	private Map<String, String> extra;

	private boolean ignoreSsl;

}
