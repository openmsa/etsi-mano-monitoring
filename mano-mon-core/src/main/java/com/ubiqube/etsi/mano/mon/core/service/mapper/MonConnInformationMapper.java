package com.ubiqube.etsi.mano.mon.core.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.ubiqube.etsi.mano.service.mon.data.MonConnInformation;
import com.ubiqube.etsi.mano.service.mon.dto.MonConnInformationDto;

@Component
@Mapper(componentModel = "spring")
public interface MonConnInformationMapper {

	MonConnInformationDto toDto(MonConnInformation monConnInformation);

	List<MonConnInformationDto> map(List<MonConnInformation> all);

}
