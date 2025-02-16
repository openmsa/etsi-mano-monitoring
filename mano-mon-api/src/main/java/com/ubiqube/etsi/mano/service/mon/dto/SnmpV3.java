package com.ubiqube.etsi.mano.service.mon.dto;

import lombok.Data;

@Data
public class SnmpV3 extends ConnInfo {
	private String securityName;
	private String privacyPassphrase;
	private String authenticationProtocol;
	private String authenticationPassphrase;
	private String privacyProtocol;
}
