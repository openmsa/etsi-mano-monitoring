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

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class KeystoneV3 extends ConnInfo {

	/**
	 * The OpenStack region to use for the VIM connection.
	 */
	@NotNull
	private String region = "RegionOne";
	/**
	 * The OpenStack project to use for the VIM connection.
	 */
	@NotNull
	private String project;

	private String projectId;
	/**
	 * The OpenStack project domain to use for the VIM connection.
	 */
	@NotNull
	private String projectDomain;
	/**
	 * The OpenStack user domain to use for the VIM connection.
	 */
	@NotNull
	private String userDomain;

	@NotNull
	private String password;
	@NotNull
	private String username;

}
