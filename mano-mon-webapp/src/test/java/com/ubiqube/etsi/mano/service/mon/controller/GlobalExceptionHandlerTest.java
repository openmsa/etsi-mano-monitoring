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
