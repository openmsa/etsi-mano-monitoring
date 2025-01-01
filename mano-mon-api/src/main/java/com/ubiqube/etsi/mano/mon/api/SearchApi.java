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

import org.springframework.util.MultiValueMap;

import com.ubiqube.etsi.mano.service.mon.data.MonitoringDataSlim;

import org.jspecify.annotations.NonNull;

public interface SearchApi {
	@NonNull
	List<MonitoringDataSlim> findByObjectIdAndKey(@NonNull String instance, @NonNull String object);

	@NonNull
	List<MonitoringDataSlim> search(@NonNull MultiValueMap<String, String> params);

}
