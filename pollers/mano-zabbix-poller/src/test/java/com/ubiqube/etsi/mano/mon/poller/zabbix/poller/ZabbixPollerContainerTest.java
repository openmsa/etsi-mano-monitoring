package com.ubiqube.etsi.mano.mon.poller.zabbix.poller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.ubiqube.etsi.mano.dao.mano.AccessInfo;
import com.ubiqube.etsi.mano.dao.mano.InterfaceInfo;
import com.ubiqube.etsi.mano.service.mon.data.BatchPollingJob;
import com.ubiqube.etsi.mano.service.mon.data.JmsMetricHolder;
import com.ubiqube.etsi.mano.service.mon.data.Metric;
import com.ubiqube.etsi.mano.service.mon.data.MonConnInformation;
import com.ubiqube.etsi.mano.service.mon.data.MonitoringDataSlim;

@Testcontainers
class ZabbixPollerContainerTest {
	@Container
	private final GenericContainer<?> zabbixContainer = new GenericContainer<>("zabbix/zabbix-agent:centos-latest")
			.withEnv("ZBX_PASSIVESERVERS", "0.0.0.0/0")
			.withExposedPorts(10050);
	private final JmsTemplate jmsTemplate = mock(JmsTemplate.class);
	private final ConfigurableApplicationContext configurableApplicationContext = mock(ConfigurableApplicationContext.class);
	private final ConfigurableListableBeanFactory clbf = mock(ConfigurableListableBeanFactory.class);
	private ZabbixPoller zabbixPoller;

	private MonConnInformation<InterfaceInfo, AccessInfo> createConnection() {
		InterfaceInfo ii = new InterfaceInfo();
		ii.setEndpoint("localhost:" + zabbixContainer.getMappedPort(10050));
		AccessInfo ai = new AccessInfo();
		MonConnInformation<InterfaceInfo, AccessInfo> conn = new MonConnInformation<>();
		conn.setInterfaceInfo(ii);
		conn.setAccessInfo(ai);
		return conn;
	}

	@BeforeEach
	void setUp() {
		zabbixPoller = new ZabbixPoller(jmsTemplate, configurableApplicationContext);
	}

	@Test
	void test() {
		assertTrue(true);
		Integer port = zabbixContainer.getMappedPort(10050);
		assertTrue(port > 0);
	}

	@Test
	void testZabbixEmpty() {
		BatchPollingJob<InterfaceInfo, AccessInfo> job = new BatchPollingJob<InterfaceInfo, AccessInfo>();
		job.setResourceId("localhost:" + zabbixContainer.getMappedPort(10050));
		job.setId(UUID.randomUUID());
		job.setConnection(createConnection());
		job.setMetrics(List.of());
		// queue
		when(configurableApplicationContext.getBeanFactory()).thenReturn(clbf);
		when(clbf.resolveEmbeddedValue(any())).thenReturn("queue");
		zabbixPoller.onEvent(job);
		assertTrue(true);
	}

	@Test
	void testZabbix() {
		BatchPollingJob<InterfaceInfo, AccessInfo> job = new BatchPollingJob<InterfaceInfo, AccessInfo>();
		job.setResourceId("localhost:" + zabbixContainer.getMappedPort(10050));
		job.setId(UUID.randomUUID());
		job.setConnection(createConnection());
		Metric m00 = new Metric("agent.hostname", "text");
		Metric m01 = new Metric("vm.memory.size[available]", "numeric");
		Metric m02 = new Metric("bad", "text");
		job.setMetrics(List.of(m00, m01, m02));
		// queue
		when(configurableApplicationContext.getBeanFactory()).thenReturn(clbf);
		ArgumentCaptor<String> destinationCaptor = ArgumentCaptor.forClass(String.class);
		ArgumentCaptor<Object> messageCaptor = ArgumentCaptor.forClass(Object.class);
		when(clbf.resolveEmbeddedValue(any())).thenReturn("queue");
		doNothing().when(jmsTemplate).convertAndSend(destinationCaptor.capture(), messageCaptor.capture());
		zabbixPoller.onEvent(job);
		assertTrue(destinationCaptor.getValue().contains("queue"));
		Object msg = messageCaptor.getValue();
		assertNotNull(msg);
		assertTrue(msg instanceof JmsMetricHolder);
		JmsMetricHolder holder = (JmsMetricHolder) msg;
		List<MonitoringDataSlim> metrics = holder.getMetrics();
		assertNotNull(metrics);
		assertEquals(3, metrics.size());
		assertEquals("localhost:" + zabbixContainer.getMappedPort(10050), metrics.get(0).getResourceId());
		assertEquals("agent.hostname", metrics.get(0).getKey());
		assertFalse(metrics.get(0).isError());
		assertEquals("localhost:" + zabbixContainer.getMappedPort(10050), metrics.get(1).getResourceId());
		assertEquals("vm.memory.size[available]", metrics.get(1).getKey());
		assertFalse(metrics.get(1).isError());
		assertEquals("localhost:" + zabbixContainer.getMappedPort(10050), metrics.get(2).getResourceId());
		assertEquals("bad", metrics.get(2).getKey());
		assertTrue(metrics.get(2).isError());

	}
}
