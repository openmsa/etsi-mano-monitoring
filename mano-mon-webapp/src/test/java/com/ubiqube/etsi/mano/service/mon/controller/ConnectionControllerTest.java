package com.ubiqube.etsi.mano.service.mon.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ubiqube.etsi.mano.mon.api.MonApi;

@ExtendWith(MockitoExtension.class)
class ConnectionControllerTest {
	@Mock
	private MonApi monApi;
	private ConnectionController srv;

	@BeforeEach
	void setUp() {
		srv = new ConnectionController(monApi);
	}

	@Test
	void testDelete() {
		srv.delete(null);
		assertTrue(true);
	}

	@Test
	void testList() {
		srv.list();
		assertTrue(true);
	}
}
