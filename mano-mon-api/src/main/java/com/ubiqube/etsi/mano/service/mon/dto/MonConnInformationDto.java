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
import java.util.Map;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.AccessInfo;
import com.ubiqube.etsi.mano.dao.mano.InterfaceInfo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MonConnInformationDto<I extends InterfaceInfo, A extends AccessInfo> implements Serializable {
	private static final long serialVersionUID = 1L;
	private UUID id;

	@NotNull
	private UUID connId;

	@NotNull
	private String connType;

	private String name;

	private I interfaceInfo;

	private A accessInfo;

	private Map<String, String> extra;

	private boolean ignoreSsl;

	private StatusTypeDto serverStatus;

	private FailureDetailsDto failureDetails;

	private long version;

}
