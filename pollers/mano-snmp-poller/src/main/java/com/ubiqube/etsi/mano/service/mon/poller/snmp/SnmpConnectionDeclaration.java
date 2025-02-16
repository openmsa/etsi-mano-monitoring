package com.ubiqube.etsi.mano.service.mon.poller.snmp;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.mon.poller.ConnectionDeclaration;

@Service
public class SnmpConnectionDeclaration implements ConnectionDeclaration {

	@Override
	public String getType() {
		return "snmp";
	}

}
