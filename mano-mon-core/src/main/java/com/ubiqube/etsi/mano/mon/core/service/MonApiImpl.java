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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.mon.api.MonApi;
import com.ubiqube.etsi.mano.mon.api.entities.BatchPollingJobDto;
import com.ubiqube.etsi.mano.mon.core.mapper.PollingJobMapper;
import com.ubiqube.etsi.mano.mon.core.service.mapper.BatchPollingJobMapper;
import com.ubiqube.etsi.mano.mon.core.service.mapper.MonConnInformationMapper;
import com.ubiqube.etsi.mano.mon.poller.ConnectionDeclaration;
import com.ubiqube.etsi.mano.service.mon.data.BatchPollingJob;
import com.ubiqube.etsi.mano.service.mon.data.MonConnInformation;
import com.ubiqube.etsi.mano.service.mon.dto.MonConnInformationDto;
import com.ubiqube.etsi.mano.service.mon.dto.PollingJob;

@Service
public class MonApiImpl implements MonApi {
	private final PollingJobService pollingJobRepository;

	private final ConnectionInformationService connRepository;

	private final List<ConnectionDeclaration> connectionDeclarations;

	private final List<String> availableConnections;

	private final PollingJobMapper pollingJobMapper;

	private final MonConnInformationMapper monConnInformationMapper;

	private final BatchPollingJobMapper batchPollingJobMapper;

	public MonApiImpl(final PollingJobService pollingJobRepository, final ConnectionInformationService connRepository, final List<ConnectionDeclaration> connectionDeclarations, final PollingJobMapper pollingJobMapper,
			final MonConnInformationMapper monConnInformationMapper, final BatchPollingJobMapper batchPollingJobMapper) {
		this.pollingJobRepository = pollingJobRepository;
		this.connRepository = connRepository;
		this.connectionDeclarations = connectionDeclarations;
		this.availableConnections = connectionDeclarations.stream().map(ConnectionDeclaration::getType).toList();
		this.pollingJobMapper = pollingJobMapper;
		this.monConnInformationMapper = monConnInformationMapper;
		this.batchPollingJobMapper = batchPollingJobMapper;
	}

	@Override
	public BatchPollingJobDto register(final @NonNull PollingJob pj) {
		final BatchPollingJob polling = pollingJobMapper.fromDto(pj);
		final Optional<MonConnInformation> conn = connRepository.findByConnId(pj.getConnection().getConnId());
		if (conn.isEmpty()) {
			final MonConnInformation connInfo = polling.getConnection();
			verifyConnectionType(connInfo);
			final MonConnInformation newConn = connRepository.save(connInfo);
			polling.setConnection(newConn);
		} else {
			polling.setConnection(conn.get());
		}

		return batchPollingJobMapper.fromEntity(pollingJobRepository.save(polling));
	}

	private void verifyConnectionType(final MonConnInformation connInfo) {
		final String type = connInfo.getConnType();
		final boolean found = connectionDeclarations.stream().anyMatch(c -> c.getType().equals(type));
		if (!found) {
			throw new IllegalArgumentException("Connection type not supported: " + type + ". Available connections: " + availableConnections);
		}
	}

	@Override
	public void delete(final @NonNull UUID id) {
		pollingJobRepository.deleteById(id);
	}

	@Override
	public List<BatchPollingJobDto> list() {
		final List<BatchPollingJob> ret = new ArrayList<>();
		final Iterable<BatchPollingJob> ite = pollingJobRepository.findAll();
		ite.forEach(ret::add);
		return ret.stream().map(batchPollingJobMapper::fromEntity).toList();
	}

	@Override
	public List<MonConnInformationDto> listConnections() {
		return monConnInformationMapper.map(connRepository.findAll());
	}

	@Override
	public void deleteConnection(@NonNull final UUID id) {
		connRepository.deleteById(id);
	}
}
