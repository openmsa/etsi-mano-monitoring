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
package com.ubiqube.etsi.mano.mon.core.service.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.ubiqube.etsi.mano.dao.mano.ai.KeystoneAuthV3;
import com.ubiqube.etsi.mano.dao.mano.ii.OpenstackV3InterfaceInfo;
import com.ubiqube.etsi.mano.service.mon.data.BatchPollingJob;

import uk.co.jemos.podam.api.PodamFactoryImpl;

class BatchPollingJobMapperTest {

	private BatchPollingJobMapper mapper;
	private PodamFactoryImpl podam;

	@BeforeEach
	void setUp() {
		mapper = Mappers.getMapper(BatchPollingJobMapper.class);
		podam = new PodamFactoryImpl();
		podam.getStrategy().setDefaultNumberOfCollectionElements(1);
	}

	@Test
	void test() {
		BatchPollingJob obj = podam.manufacturePojo(BatchPollingJob.class, OpenstackV3InterfaceInfo.class, KeystoneAuthV3.class);
		assertNotNull(mapper.fromEntity(obj));
		assertNull(mapper.fromEntity(null));
	}

}
