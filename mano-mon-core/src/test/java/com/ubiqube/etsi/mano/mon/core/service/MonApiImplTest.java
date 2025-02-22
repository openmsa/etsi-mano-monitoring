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
package com.ubiqube.etsi.mano.mon.core.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ubiqube.etsi.mano.mon.api.entities.MetricDto;
import com.ubiqube.etsi.mano.mon.core.mapper.PollingJobMapper;
import com.ubiqube.etsi.mano.mon.core.service.mapper.BatchPollingJobMapper;
import com.ubiqube.etsi.mano.mon.core.service.mapper.MonConnInformationMapper;
import com.ubiqube.etsi.mano.mon.poller.ConnectionDeclaration;
import com.ubiqube.etsi.mano.service.mon.data.MonConnInformation;
import com.ubiqube.etsi.mano.service.mon.dto.ConnInfo;
import com.ubiqube.etsi.mano.service.mon.dto.PollingJob;

@ExtendWith(MockitoExtension.class)
class MonApiImplTest {
	@Mock
	private ConnectionInformationService connRepo;
	@Mock
	private PollingJobService pollingJobRepo;
	private final PollingJobMapper pollMapper = Mappers.getMapper(PollingJobMapper.class);
	private final MonConnInformationMapper monConnInformationMapper = Mappers.getMapper(MonConnInformationMapper.class);
	private final BatchPollingJobMapper batchPollingJobMapper = Mappers.getMapper(BatchPollingJobMapper.class);
	private MonApiImpl api;

	@BeforeEach
	void setup() {
		ConnectionDeclaration cd = new TestConnectionDeclaration();
		api = new MonApiImpl(pollingJobRepo, connRepo, List.of(cd), pollMapper, monConnInformationMapper, batchPollingJobMapper);
	}

	@Test
	void testRegister() {
		final PollingJob pj = new PollingJob();
		pj.setResourceId("r");
		List<MetricDto> lst = new ArrayList<>();
		lst.add(new MetricDto());
		pj.setMetrics(lst);
		final ConnInfo connInfo = new ConnInfo();
		connInfo.setType("conn-test");
		pj.setConnection(connInfo);
		api.register(pj);
		assertTrue(true);
	}

	@Test
	void testRegisterBadType() {
		final PollingJob pj = new PollingJob();
		pj.setResourceId("r");
		List<MetricDto> lst = new ArrayList<>();
		lst.add(new MetricDto());
		pj.setMetrics(lst);
		final ConnInfo connInfo = new ConnInfo();
		connInfo.setType("bad-test");
		pj.setConnection(connInfo);
		assertThrows(IllegalArgumentException.class, () -> api.register(pj));
	}

	@Test
	void testRegisterAllready() {
		final PollingJob pj = new PollingJob();
		final ConnInfo connInfo = new ConnInfo();
		connInfo.setType("conn-test");
		pj.setConnection(connInfo);
		pj.setResourceId("r");
		List<MetricDto> lst = new ArrayList<>();
		lst.add(new MetricDto());
		pj.setMetrics(lst);
		final MonConnInformation monConn = new MonConnInformation();
		when(connRepo.findByConnId(any())).thenReturn(Optional.of(monConn));
		api.register(pj);
		assertTrue(true);
	}

	@Test
	void testDelete() {
		api.delete(null);
		assertTrue(true);
	}

	@Test
	void testList() {
		api.list();
		assertTrue(true);
	}

	@Test
	void testlistConnections() {
		api.listConnections();
		assertTrue(true);
	}

	@Test
	void testDeleteConnection() {
		api.deleteConnection(null);
		assertTrue(true);
	}
}
