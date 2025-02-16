package com.ubiqube.etsi.mano.mon.core.service;

import com.ubiqube.etsi.mano.mon.poller.ConnectionDeclaration;

public class TestConnectionDeclaration implements ConnectionDeclaration {

	@Override
	public String getType() {
		return "conn-test";
	}

}
