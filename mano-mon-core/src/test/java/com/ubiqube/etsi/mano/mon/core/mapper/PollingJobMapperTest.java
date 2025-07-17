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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.ubiqube.etsi.mano.service.mon.data.BatchPollingJob;
import com.ubiqube.etsi.mano.service.mon.data.MonConnInformation;
import com.ubiqube.etsi.mano.service.mon.dto.ConnInfo;
import com.ubiqube.etsi.mano.service.mon.dto.KeystoneV3;
import com.ubiqube.etsi.mano.service.mon.dto.PollingJob;
import com.ubiqube.etsi.mano.service.mon.dto.SnmpV2;
import com.ubiqube.etsi.mano.service.mon.dto.SnmpV3;

import uk.co.jemos.podam.api.PodamFactoryImpl;

class PollingJobMapperTest {

	private PodamFactoryImpl podam;

	@BeforeEach
	void setUp() {
		podam = new PodamFactoryImpl();
		podam.getStrategy().setDefaultNumberOfCollectionElements(1);
	}

	@Test
	void test() {
		PollingJobMapper pj = Mappers.getMapper(PollingJobMapper.class);
		BatchPollingJob res = pj.fromDto(podam.manufacturePojo(PollingJob.class));
		assertNotNull(res);
		assertNull(pj.fromDto((PollingJob) null));
	}

	@Test
	void testConnInfo() {
		PollingJobMapper pj = Mappers.getMapper(PollingJobMapper.class);
		assertNull(pj.fromDto((ConnInfo) null));
		MonConnInformation res = pj.fromDto(podam.manufacturePojo(ConnInfo.class));
		assertNotNull(res);
	}

	@Test
	void testConnInfoKeystone() {
		PollingJobMapper pj = Mappers.getMapper(PollingJobMapper.class);
		assertNull(pj.fromDto((ConnInfo) null));
		ConnInfo obj = podam.manufacturePojo(KeystoneV3.class);
		MonConnInformation res = pj.fromDto(obj);
		assertNotNull(res);
	}

	@Test
	void testConnInfoSnmpV2() {
		PollingJobMapper pj = Mappers.getMapper(PollingJobMapper.class);
		assertNull(pj.fromDto((ConnInfo) null));
		ConnInfo obj = podam.manufacturePojo(SnmpV2.class);
		MonConnInformation res = pj.fromDto(obj);
		assertNotNull(res);
	}

	@Test
	void testConnInfoSnmpV3() {
		PollingJobMapper pj = Mappers.getMapper(PollingJobMapper.class);
		assertNull(pj.fromDto((ConnInfo) null));
		ConnInfo obj = podam.manufacturePojo(SnmpV3.class);
		MonConnInformation res = pj.fromDto(obj);
		assertNotNull(res);
	}
}
