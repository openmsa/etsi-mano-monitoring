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
package com.ubiqube.etsi.mano.service.mon.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;

/**
 *
 * @author Olivier Vignaud
 *
 */
class MonitoringIdTest {

	@Test
	void test() {
		final MonitoringId srv = new MonitoringId();
		srv.setKey(null);
		srv.setTime(null);
		srv.getKey();
		srv.getTime();
		srv.hashCode();
		srv.toString();
		srv.equals(srv);
		srv.equals(null);
		srv.equals("");
		final MonitoringId srv2 = new MonitoringId();
		srv.equals(srv2);
		srv2.setKey("");
		srv.equals(srv2);
		srv2.setKey(null);
		srv2.setTime(OffsetDateTime.MAX);
		srv.equals(srv2);
		assertTrue(true);
	}

}
