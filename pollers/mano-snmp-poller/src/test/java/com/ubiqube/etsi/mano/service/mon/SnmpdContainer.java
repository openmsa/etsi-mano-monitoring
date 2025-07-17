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
package com.ubiqube.etsi.mano.service.mon;

import java.util.Map;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.InternetProtocol;
import com.github.dockerjava.api.model.NetworkSettings;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.api.model.Ports.Binding;

public class SnmpdContainer extends GenericContainer<SnmpdContainer> {

	private Integer snmpPort;

	public SnmpdContainer(final DockerImageName dockerImageName) {
		super(dockerImageName);
	}

	@Override
	protected void containerIsStarted(final InspectContainerResponse containerInfo) {
		NetworkSettings networkSettings = containerInfo.getNetworkSettings();
		Ports ports = networkSettings.getPorts();
		Map<ExposedPort, Binding[]> map = ports.getBindings();
		Binding[] bindings = map.get(new ExposedPort(161, InternetProtocol.UDP));
		Binding binding = bindings[0];
		String udp99 = binding.getHostPortSpec();
		snmpPort = Integer.valueOf(udp99);
		super.containerIsStarted(containerInfo);
	}

	public Integer getSnmpPort() {
		return snmpPort;
	}

}
