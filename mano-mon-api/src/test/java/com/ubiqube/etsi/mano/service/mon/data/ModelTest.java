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
package com.ubiqube.etsi.mano.service.mon.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ubiqube.etsi.mano.mon.dao.AllHostMetrics;
import com.ubiqube.etsi.mano.service.mon.dto.ConnInfo;
import com.ubiqube.etsi.mano.service.mon.dto.PollingJob;

import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.EqualsVerifierReport;
import nl.jqno.equalsverifier.Warning;

/**
 *
 * @author Olivier Vignaud
 *
 */
class ModelTest {

	private static final Logger LOG = LoggerFactory.getLogger(ModelTest.class);

	@ParameterizedTest
	@MethodSource("providerClass")
	void testName(final Class<?> param) throws Exception {
		realHandle(param.getName());
		assertTrue(true);
	}

	private static Stream<Arguments> providerClass() {
		return Stream.of(
				Arguments.of(AuthentificationInformations.class),
				Arguments.of(AuthParamBasic.class),
				Arguments.of(AuthParamOauth2.class),
				Arguments.of(AuthType.class),
				Arguments.of(BatchPollingJob.class),
				Arguments.of(FailureDetails.class),
				Arguments.of(JmsMetricHolder.class),
				Arguments.of(Metric.class),
				Arguments.of(MetricFunction.class),
				Arguments.of(MonConnInformation.class),
				Arguments.of(OAuth2GrantType.class),
				Arguments.of(ConnInfo.class),
				Arguments.of(PollingJob.class),
				Arguments.of(AllHostMetrics.class),
				Arguments.of(StatusType.class));
	}

	public static void realHandle(final String x) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, IntrospectionException {
		if (x.startsWith("com.ubiqube.etsi.mano.utils") || x.endsWith("Builder")) {
			return;
		}
		final Class<?> clazz = Class.forName(x);
		if (clazz.isInterface()
				|| clazz.isEnum()
				|| Modifier.isAbstract(clazz.getModifiers())
				|| Exception.class.isAssignableFrom(clazz)) {
			return;
		}
		final Object obj = clazz.getConstructor().newInstance();
		final BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		final PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
		testObject(obj, props);
	}

	private static void testObject(final Object obj, final PropertyDescriptor[] props) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException, IllegalArgumentException {
		for (final PropertyDescriptor propertyDescriptor : props) {
			final Method mr = propertyDescriptor.getReadMethod();
			if (null != mr) {
				mr.invoke(obj);
			}
			final Method mw = propertyDescriptor.getWriteMethod();
			if ((null != mw) && (null != mr)) {
				final Class<?> ret = mr.getReturnType();
				if (Modifier.isAbstract(ret.getModifiers())) {
					// continue
				}
				mw.invoke(obj, createType(ret));
			}
		}
		obj.hashCode();
		obj.toString();
		obj.equals(null);
		obj.equals(props);
		obj.equals(obj);
		final EqualsVerifierReport rep = EqualsVerifier
				.simple()
				.forClass(obj.getClass())
				.suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT, Warning.SURROGATE_KEY)
				.report();
		System.out.println("" + rep.getMessage());
	}

	private static Object createType(final Class<?> ret) throws NoSuchMethodException, SecurityException, IllegalArgumentException {
		if ("boolean".equals(ret.getCanonicalName()) || ret.isAssignableFrom(Boolean.class)) {
			return Boolean.TRUE;
		}
		if ("int".equals(ret.getCanonicalName()) || ret.isAssignableFrom(Boolean.class)) {
			return 123;
		}
		if ("long".equals(ret.getCanonicalName()) || ret.isAssignableFrom(Boolean.class)) {
			return 123L;
		}
		if ("double".equals(ret.getCanonicalName()) || ret.isAssignableFrom(Boolean.class)) {
			return 123d;
		}
		if (ret.isAssignableFrom(Set.class)) {
			return Set.of();
		}
		if (ret.isAssignableFrom(List.class)) {
			return List.of();
		}
		if (ret.isAssignableFrom(Map.class)) {
			return Map.of();
		}
		if (ret.isAssignableFrom(UUID.class)) {
			return UUID.randomUUID();
		}
		if (ret.isAssignableFrom(LocalTime.class)) {
			return LocalTime.now();
		}
		if (ret.isAssignableFrom(LocalDateTime.class)) {
			return LocalDateTime.now();
		}
		if (ret.isAssignableFrom(OffsetDateTime.class)) {
			return OffsetDateTime.now();
		}
		if (ret.isAssignableFrom(ZonedDateTime.class)) {
			return ZonedDateTime.now();
		}
		if (ret.isAssignableFrom(Float.class)) {
			return Float.valueOf(123);
		}
		if (ret.isAssignableFrom(Long.class)) {
			return Long.valueOf(123);
		}
		if (ret.isAssignableFrom(Integer.class)) {
			return Integer.valueOf(123);
		}
		if (ret.isAssignableFrom(Double.class)) {
			return Double.valueOf(123D);
		}
		if (ret.isAssignableFrom(BigDecimal.class)) {
			return BigDecimal.ONE;
		}
		if (ret.isAssignableFrom(URI.class)) {
			return URI.create("http://localhost/");
		}
		if (ret.isAssignableFrom(URL.class)) {
			try {
				return URI.create("http://localhost/").toURL();
			} catch (final MalformedURLException e) {
				throw new RuntimeException(e);
			}
		}
		return createComplex(ret);
	}

	private static Object createComplex(final Class<?> ret) throws NoSuchMethodException, SecurityException, IllegalArgumentException {
		if (ret.isEnum()) {
			final Object[] cst = ret.getEnumConstants();
			return cst[0];
		}
		try {
			final Constructor<?> ctor = ret.getConstructor();
			return ctor.newInstance();
		} catch (final InstantiationException | IllegalAccessException | InvocationTargetException e) {
			LOG.trace("", e);
			return null;
		}
	}

}
