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
	void setUp() throws Exception {
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
