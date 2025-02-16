package com.ubiqube.etsi.mano.service.mon.poller.gnocchi;

import org.springframework.stereotype.Service;

import com.ubiqube.etsi.mano.mon.poller.ConnectionDeclaration;

@Service
public class GnocchiConnectionDeclaration implements ConnectionDeclaration {

	@Override
	public String getType() {
		return "openstack-v3";
	}

}
