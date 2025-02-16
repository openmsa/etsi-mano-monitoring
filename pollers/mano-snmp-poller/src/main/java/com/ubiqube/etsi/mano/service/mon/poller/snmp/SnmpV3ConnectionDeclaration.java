package com.ubiqube.etsi.mano.service.mon.poller.snmp;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.mon.poller.ConnectionDeclaration;

@Service
public class SnmpV3ConnectionDeclaration implements ConnectionDeclaration {

	@Override
	public String getType() {
		return "snmp3";
	}

}
