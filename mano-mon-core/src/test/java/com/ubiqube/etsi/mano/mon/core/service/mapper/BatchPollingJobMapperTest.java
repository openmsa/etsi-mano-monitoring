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
