/**
 * Copyright (C) 2019-2025 Ubiqube.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
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
		zmq.getClass();
	}

}
