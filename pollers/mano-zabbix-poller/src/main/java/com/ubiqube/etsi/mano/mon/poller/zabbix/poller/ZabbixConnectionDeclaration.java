package com.ubiqube.etsi.mano.mon.poller.zabbix.poller;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.mon.poller.ConnectionDeclaration;

@Service
public class ZabbixConnectionDeclaration implements ConnectionDeclaration {

	@Override
	public String getType() {
		return "zabbix-v1";
	}

}
