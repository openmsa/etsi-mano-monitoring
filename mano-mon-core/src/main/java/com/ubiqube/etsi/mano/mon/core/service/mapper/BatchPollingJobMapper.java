package com.ubiqube.etsi.mano.mon.core.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.ubiqube.etsi.mano.mon.api.entities.BatchPollingJobDto;
import com.ubiqube.etsi.mano.service.mon.data.BatchPollingJob;

@Component
@Mapper(componentModel = "spring")
public interface BatchPollingJobMapper {
	BatchPollingJobDto fromEntity(BatchPollingJob entity);

	List<BatchPollingJobDto> fromEntity(List<BatchPollingJob> ret);
}
