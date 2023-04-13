/**
 *     Copyright (C) 2019-2023 Ubiqube.
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.ubiqube.etsi.mano.service.mon.repository;

import java.sql.Timestamp;

import jakarta.annotation.Nullable;

/**
 *
 * @author Olivier Vignaud
 *
 */
public interface MonitoringDataProjection {
	Timestamp getTime();

	String getMasterJobId();

	String getKey();

	@Nullable
	Double getValue();

	@Nullable
	String getText();

}
