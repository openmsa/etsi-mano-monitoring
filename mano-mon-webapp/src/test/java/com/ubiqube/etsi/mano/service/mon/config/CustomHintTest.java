package com.ubiqube.etsi.mano.service.mon.config;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.aot.hint.ReflectionHints;
import org.springframework.aot.hint.RuntimeHints;

class CustomHintTest {

	private CustomHint srv;

	@Test
	void test() {
		srv = new CustomHint();
		RuntimeHints hints = mock(RuntimeHints.class);
		ReflectionHints ref = mock(ReflectionHints.class);
		when(hints.reflection()).thenReturn(ref);
		srv.registerHints(hints, null);
		assertTrue(true);
	}

}
