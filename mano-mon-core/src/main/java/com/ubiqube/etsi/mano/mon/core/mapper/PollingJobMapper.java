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

import org.jspecify.annotations.Nullable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ubiqube.etsi.mano.dao.mano.AccessInfo;
import com.ubiqube.etsi.mano.dao.mano.InterfaceInfo;
import com.ubiqube.etsi.mano.dao.mano.ai.KeystoneAuthV3;
import com.ubiqube.etsi.mano.dao.mano.ai.SnmpV3Auth;
import com.ubiqube.etsi.mano.dao.mano.ii.OpenstackV3InterfaceInfo;
import com.ubiqube.etsi.mano.service.mon.data.BatchPollingJob;
import com.ubiqube.etsi.mano.service.mon.data.MonConnInformation;
import com.ubiqube.etsi.mano.service.mon.data.MonKeystoneV3;
import com.ubiqube.etsi.mano.service.mon.data.MonSnmpV2;
import com.ubiqube.etsi.mano.service.mon.data.MonSnmpV3;
import com.ubiqube.etsi.mano.service.mon.dto.ConnInfo;
import com.ubiqube.etsi.mano.service.mon.dto.KeystoneV3;
import com.ubiqube.etsi.mano.service.mon.dto.PollingJob;
import com.ubiqube.etsi.mano.service.mon.dto.SnmpV2;
import com.ubiqube.etsi.mano.service.mon.dto.SnmpV3;
import com.ubiqube.etsi.mano.service.mon.poller.snmp.SnmpV2AuthInfo;

@Mapper
public interface PollingJobMapper {
	PollingJobMapper INSTANCE = Mappers.getMapper(PollingJobMapper.class);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "lastRun", ignore = true)
	BatchPollingJob fromDto(PollingJob pj);

	@Mapping(target = "interfaceInfo", ignore = true)
	@Mapping(target = "failureDetails", ignore = true)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "serverStatus", ignore = true)
	@Mapping(target = "version", ignore = true)
	@Mapping(source = "connId", target = "connId")
	@Mapping(source = "type", target = "connType")
	@Nullable
	default MonConnInformation fromDto(@Nullable final ConnInfo ci) {
		if (null == ci) {
			return null;
		}
		MonConnInformation conn = new MonConnInformation();
		if (ci instanceof KeystoneV3 keystone) {
			conn = new MonKeystoneV3();
			AccessInfo ai = map(keystone);
			InterfaceInfo ii = new OpenstackV3InterfaceInfo();
			ii.setEndpoint(ci.getEndpoint());
			conn.setInterfaceInfo(ii);
			conn.setAccessInfo(ai);
		} else if (ci instanceof final SnmpV2 snmp) {
			conn = new MonSnmpV2();
			InterfaceInfo ii = new InterfaceInfo();
			ii.setEndpoint(ci.getEndpoint());
			conn.setInterfaceInfo(ii);
			conn.setAccessInfo(mapSnmpv2(snmp));
		} else if (ci instanceof final SnmpV3 snmp) {
			conn = new MonSnmpV3();
			InterfaceInfo ii = new InterfaceInfo();
			ii.setEndpoint(ci.getEndpoint());
			conn.setInterfaceInfo(ii);
			conn.setAccessInfo(mapSnmpv3(snmp));
		}
		conn.setConnId(ci.getConnId());
		conn.setConnType(ci.getType());
		conn.setName(ci.getName());
		return conn;
	}

	SnmpV2AuthInfo mapSnmpv2(SnmpV2 snmp);

	SnmpV3Auth mapSnmpv3(SnmpV3 snmp);

	KeystoneAuthV3 map(KeystoneV3 keystone);

}
