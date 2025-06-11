package com.ubiqube.etsi.mano.service.mon;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Ports;
import com.ubiqube.etsi.mano.dao.mano.InterfaceInfo;
import com.ubiqube.etsi.mano.dao.mano.ai.SnmpV3Auth;
import com.ubiqube.etsi.mano.service.mon.data.BatchPollingJob;
import com.ubiqube.etsi.mano.service.mon.data.JmsMetricHolder;
import com.ubiqube.etsi.mano.service.mon.data.Metric;
import com.ubiqube.etsi.mano.service.mon.data.MonSnmpV2;
import com.ubiqube.etsi.mano.service.mon.data.MonSnmpV3;
import com.ubiqube.etsi.mano.service.mon.data.MonitoringDataSlim;
import com.ubiqube.etsi.mano.service.mon.poller.snmp.Snmp3Poller;
import com.ubiqube.etsi.mano.service.mon.poller.snmp.SnmpPoller;
import com.ubiqube.etsi.mano.service.mon.poller.snmp.SnmpV2AuthInfo;

@Testcontainers
class ContainerTest {
	@Container
	public SnmpdContainer snmpd = new SnmpdContainer(DockerImageName.parse("testainers/snmpd-container:latest"))
			.waitingFor(Wait.forLogMessage("NET-SNMP version .*", 1))
			.withEnv("SNMP_V3_AUTH_PWD", "ubiqubepv3")
			.withEnv("SNMP_V3_USER", "ubiqube")
			.withEnv("SNMP_V3_PRIV_PWD", "ubiqubepr3")
			.withCreateContainerCmdModifier(cmd -> {
				List<ExposedPort> exposedPorts = new ArrayList<>();
				exposedPorts.add(ExposedPort.udp(161));
				cmd.withExposedPorts(exposedPorts);
				Ports ports = cmd.getHostConfig().getPortBindings();
				ports.bind(ExposedPort.udp(161), Ports.Binding.empty());
				cmd.getHostConfig().withPortBindings(ports);
			});
	private Integer port;
	private final JmsTemplate jmsTemplate = mock(JmsTemplate.class);
	private final ConfigurableApplicationContext configurableApplicationContext = mock(ConfigurableApplicationContext.class);
	private SnmpPoller snmpPoller;
	private Snmp3Poller snmpPoller3;

	@BeforeEach
	void setUp() {
		port = snmpd.getSnmpPort();
		snmpPoller = new SnmpPoller(jmsTemplate, configurableApplicationContext);
		snmpPoller3 = new Snmp3Poller(jmsTemplate, configurableApplicationContext);
	}

	private MonSnmpV2 createConnectionv2() {
		InterfaceInfo ii = new InterfaceInfo();
		ii.setEndpoint("udp:" + snmpd.getHost() + "/" + port);
		SnmpV2AuthInfo ai = new SnmpV2AuthInfo();
		ai.setCommunity("public");
		MonSnmpV2 conn = new MonSnmpV2();
		conn.setInterfaceInfo(ii);
		conn.setAccessInfo(ai);
		return conn;
	}

	private MonSnmpV3 createConnectionv3() {
		InterfaceInfo ii = new InterfaceInfo();
		ii.setEndpoint("udp:" + snmpd.getHost() + "/" + port);
		SnmpV3Auth ai = new SnmpV3Auth();
		ai.setAuthenticationPassphrase("ubiqubepv3");
		ai.setAuthenticationProtocol("SHA");
		ai.setPrivacyPassphrase("ubiqubepr3");
		ai.setPrivacyProtocol("AES128");
		ai.setSecurityName("ubiqube");

		MonSnmpV3 conn = new MonSnmpV3();
		conn.setInterfaceInfo(ii);
		conn.setAccessInfo(ai);
		return conn;
	}

	@Test
	void testSnmpv2Empty() {
		BatchPollingJob<InterfaceInfo, SnmpV2AuthInfo> job = new BatchPollingJob<>();
		job.setConnection(createConnectionv2());
		job.setMetrics(List.of());
		// queue
		ConfigurableListableBeanFactory clbf = mock(ConfigurableListableBeanFactory.class);
		when(configurableApplicationContext.getBeanFactory()).thenReturn(clbf);
		when(clbf.resolveEmbeddedValue(any())).thenReturn("queue");
		snmpPoller.onEvent(job);
		assertTrue(true);
	}

	@Test
	void testSnmpv2WithMetrics() {
		BatchPollingJob<InterfaceInfo, SnmpV2AuthInfo> job = new BatchPollingJob<>();
		job.setId(UUID.randomUUID());
		job.setConnection(createConnectionv2());
		// OctetString
		Metric metric00 = new Metric("1.3.6.1.2.1.1.1.0", null);
		// Integer
		Metric metric01 = new Metric("1.3.6.1.2.1.2.1.0", null);
		// Counter32
		Metric metric02 = new Metric(".1.3.6.1.2.1.11.16.0", null);
		Metric metricBad = new Metric("1.1.1.1.1.1.1.1.1.1.1.1", null);
		// Gauge32
		Metric metric03 = new Metric("1.3.6.1.2.1.2.2.1.5.2", null);
		// Counter64
		Metric metric04 = new Metric(".1.3.6.1.2.1.4.31.3.1.33.2.2", null);
		job.setMetrics(List.of(metric00, metric01, metric02, metricBad, metric03, metric04));
		// queue
		ConfigurableListableBeanFactory clbf = mock(ConfigurableListableBeanFactory.class);
		when(configurableApplicationContext.getBeanFactory()).thenReturn(clbf);
		when(clbf.resolveEmbeddedValue(any())).thenReturn("queue");
		ArgumentCaptor<String> destinationCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Object> messageCaptor = ArgumentCaptor.forClass(Object.class);
		doNothing().when(jmsTemplate).convertAndSend(destinationCaptor.capture(), messageCaptor.capture());
		snmpPoller.onEvent(job);
		assertTrue(true);
		assertTrue(destinationCaptor.getValue().contains("queue"));
		Object msg = messageCaptor.getValue();
		assertNotNull(msg);
		assertTrue(msg instanceof JmsMetricHolder);
		JmsMetricHolder holder = (JmsMetricHolder) msg;
		List<MonitoringDataSlim> metrics = holder.getMetrics();
		assertNotNull(metrics);
		assertEquals(6, metrics.size());
		assertNotNull(metrics.get(0).getText());
		assertNull(metrics.get(0).getValue());
		assertEquals("1.3.6.1.2.1.1.1.0", metrics.get(0).getKey());
		assertFalse(metrics.get(0).isError());
		assertEquals("1.3.6.1.2.1.2.1.0", metrics.get(1).getKey());
		assertNotNull(metrics.get(1).getValue());
		assertFalse(metrics.get(1).isError());
		assertEquals(".1.3.6.1.2.1.11.16.0", metrics.get(2).getKey());
		assertNotNull(metrics.get(2).getValue());
		assertFalse(metrics.get(2).isError());
		assertTrue(metrics.get(3).isError());
		assertNotNull(metrics.get(4).getValue());
		assertFalse(metrics.get(4).isError());
		assertNotNull(metrics.get(5).getValue());
		assertFalse(metrics.get(5).isError());
	}

	@Test
	void testSnmpv3Empty() {
		BatchPollingJob<InterfaceInfo, SnmpV3Auth> job = new BatchPollingJob<>();
		job.setConnection(createConnectionv3());
		job.setMetrics(List.of());
		// queue
		ConfigurableListableBeanFactory clbf = mock(ConfigurableListableBeanFactory.class);
		when(configurableApplicationContext.getBeanFactory()).thenReturn(clbf);
		when(clbf.resolveEmbeddedValue(any())).thenReturn("queue");
		snmpPoller3.onEvent(job);
		assertTrue(true);
	}

}
