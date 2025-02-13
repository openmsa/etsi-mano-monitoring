package com.ubiqube.etsi.mano.mon.poller.zabbix.poller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ZabbixMetricQueryTest {

	private ZabbixMetricQuery zmq;

	@BeforeEach
	void setUp() {
		zmq = new ZabbixMetricQuery();
	}

	@Test
	void test() {
		String res = ZabbixMetricQuery.bytesToHex("012345ABCDEF".getBytes());
		assertEquals("303132333435414243444546", res);
		assertTrue(true);
	}

}
