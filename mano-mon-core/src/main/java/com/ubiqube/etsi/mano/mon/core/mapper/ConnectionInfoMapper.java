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
package com.ubiqube.etsi.mano.mon.core.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import com.ubiqube.etsi.mano.dao.mano.ai.KeystoneAuthV3;
import com.ubiqube.etsi.mano.dao.mano.ii.OpenstackV3InterfaceInfo;
import com.ubiqube.etsi.mano.service.mon.data.MonConnInformation;
import com.ubiqube.etsi.mano.service.mon.dto.ConnInfo;
import com.ubiqube.etsi.mano.service.mon.dto.KeystoneV3;

@Component
@Mapper(componentModel = "spring")
public interface ConnectionInfoMapper {

	@Mapping(target = "accessInfo", ignore = true)
	@Mapping(target = "interfaceInfo", ignore = true)
	@Mapping(target = "failureDetails", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "serverStatus", ignore = true)
	@Mapping(target = "version", ignore = true)
	@Mapping(source = "connId", target = "connId")
	@Mapping(source = "type", target = "connType")
	MonConnInformation fromDto(ConnInfo ci);

	@Mapping(target = "accessInfo.region", source = "region")
	@Mapping(target = "accessInfo.project", source = "project")
	@Mapping(target = "accessInfo.projectDomain", source = "projectDomain")
	@Mapping(target = "accessInfo.userDomain", source = "userDomain")
	@Mapping(target = "accessInfo.projectId", source = "projectId")
	@Mapping(target = "accessInfo.username", source = "username")
	@Mapping(target = "accessInfo.password", source = "password")
	@Mapping(target = "connType", ignore = true)
	@Mapping(target = "failureDetails", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "interfaceInfo", ignore = true)
	@Mapping(target = "serverStatus", ignore = true)
	@Mapping(target = "version", ignore = true)
	MonConnInformation<OpenstackV3InterfaceInfo, KeystoneAuthV3> fromDto(KeystoneV3 ci);
}
