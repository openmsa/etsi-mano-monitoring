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
package com.ubiqube.etsi.mano.mon.api;

import java.util.List;
import java.util.UUID;

import org.jspecify.annotations.NonNull;

import com.ubiqube.etsi.mano.mon.api.entities.BatchPollingJobDto;
import com.ubiqube.etsi.mano.service.mon.dto.MonConnInformationDto;
import com.ubiqube.etsi.mano.service.mon.dto.PollingJob;

public interface MonApi {

	BatchPollingJobDto register(@NonNull PollingJob pj);

	void delete(@NonNull UUID id);

	List<BatchPollingJobDto> list();

	List<MonConnInformationDto> listConnections();

	void deleteConnection(@NonNull UUID id);
}
