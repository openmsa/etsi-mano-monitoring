package com.ubiqube.etsi.mano.mon.core.mapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.ubiqube.etsi.mano.service.mon.data.MonConnInformation;
import com.ubiqube.etsi.mano.service.mon.dto.ConnInfo;
import com.ubiqube.etsi.mano.service.mon.dto.KeystoneV3;

import uk.co.jemos.podam.api.PodamFactoryImpl;

class ConnectionInfoMapperTest {

	private ConnectionInfoMapper mapper;
	private PodamFactoryImpl podam;

	@BeforeEach
	void setUp() {
		mapper = Mappers.getMapper(ConnectionInfoMapper.class);
		podam = new PodamFactoryImpl();
		podam.getStrategy().setDefaultNumberOfCollectionElements(1);
	}

	@Test
	void test() {
		ConnInfo dto = podam.manufacturePojo(ConnInfo.class);
		MonConnInformation res = mapper.fromDto(dto);
		assertNotNull(res);
		assertNull(mapper.fromDto((ConnInfo) null));
	}

	@Test
	void testKeystoneV3() {
		KeystoneV3 dto = podam.manufacturePojo(KeystoneV3.class);
		MonConnInformation res = mapper.fromDto(dto);
		assertNotNull(res);
		assertNull(mapper.fromDto((KeystoneV3) null));
	}
}
