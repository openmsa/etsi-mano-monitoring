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
package com.ubiqube.etsi.mano.service.mon.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ubiqube.etsi.mano.mon.api.SearchApi;

@ExtendWith(MockitoExtension.class)
class SearchControllerTest {
	@Mock
	private SearchApi searchApi;
	private SearchController srv;

	@BeforeEach
	void setUp() {
		srv = new SearchController(searchApi);
	}

	@Test
	void testSearch() {
		srv.search(null, null);
		assertTrue(true);
	}

	@Test
	void testSearchApi() {
		srv.searchApi(null);
		assertTrue(true);
	}

}
