package com.ubiqube.etsi.mano.service.mon.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.MethodArgumentNotValidException;

class GlobalExceptionHandlerTest {

	private GlobalExceptionHandler srv;

	@BeforeEach
	void setUp() {
		srv = new GlobalExceptionHandler();
	}

	@Test
	void test() {
		MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
		srv.handleMethodArgumentNotValid(ex, null, null, null);
		assertTrue(true);
	}

	@Test
	void testHandleExceptionInternal() {
		MethodArgumentNotValidException ex = mock(MethodArgumentNotValidException.class);
		srv.handleExceptionInternal(ex, ex, null, HttpStatusCode.valueOf(456), null);
		assertTrue(true);
	}
}
