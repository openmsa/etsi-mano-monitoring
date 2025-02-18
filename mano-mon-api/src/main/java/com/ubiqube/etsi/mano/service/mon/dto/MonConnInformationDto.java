package com.ubiqube.etsi.mano.service.mon.dto;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.AccessInfo;
import com.ubiqube.etsi.mano.dao.mano.InterfaceInfo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MonConnInformationDto<I extends InterfaceInfo, A extends AccessInfo> implements Serializable {
	private static final long serialVersionUID = 1L;
	private UUID id;

	@NotNull
	private UUID connId;

	@NotNull
	private String connType;

	private String name;

	private I interfaceInfo;

	private A accessInfo;

	private Map<String, String> extra;

	private boolean ignoreSsl;

	private StatusTypeDto serverStatus;

	private FailureDetailsDto failureDetails;

	private long version;

}
