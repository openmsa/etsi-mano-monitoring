package com.ubiqube.etsi.mano.mon.core.service.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.ubiqube.etsi.mano.dao.mano.ai.KeystoneAuthV3;
import com.ubiqube.etsi.mano.dao.mano.ii.OpenstackV3InterfaceInfo;
import com.ubiqube.etsi.mano.service.mon.data.MonConnInformation;

import uk.co.jemos.podam.api.PodamFactoryImpl;

class MonConnInformationMapperTest {
	private MonConnInformationMapper mapper;
	private PodamFactoryImpl podam;

	@BeforeEach
	void setUp() {
		mapper = Mappers.getMapper(MonConnInformationMapper.class);
		podam = new PodamFactoryImpl();
		podam.getStrategy().setDefaultNumberOfCollectionElements(1);
	}

	@Test
	void test() {
		MonConnInformation obj = podam.manufacturePojo(MonConnInformation.class, OpenstackV3InterfaceInfo.class, KeystoneAuthV3.class);
		assertNotNull(mapper.toDto(obj));
		assertNull(mapper.toDto(null));
	}

	@Test
	void testMap() {
		MonConnInformation obj = podam.manufacturePojo(MonConnInformation.class, OpenstackV3InterfaceInfo.class, KeystoneAuthV3.class);
		assertNotNull(mapper.map(List.of(obj)));
		assertNull(mapper.map(null));
	}

}
