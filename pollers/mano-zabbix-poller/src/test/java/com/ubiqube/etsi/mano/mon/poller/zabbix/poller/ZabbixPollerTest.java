package com.ubiqube.etsi.mano.mon.poller.zabbix.poller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import com.ubiqube.etsi.mano.dao.mano.AccessInfo;
import com.ubiqube.etsi.mano.dao.mano.InterfaceInfo;
import com.ubiqube.etsi.mano.service.mon.data.BatchPollingJob;
import com.ubiqube.etsi.mano.service.mon.data.Metric;

@ExtendWith(MockitoExtension.class)
class ZabbixPollerTest {
	private ZabbixPoller zabbixPoller;
	@Mock
	private JmsTemplate jmsTemplate;
	@Mock
	private ConfigurableApplicationContext confApp;
	@Mock
	private ConfigurableListableBeanFactory clbf;

	@BeforeEach
	void setUp() {
		zabbixPoller = new ZabbixPoller(jmsTemplate, confApp);
	}

	@Test
	void test() {
		BatchPollingJob<InterfaceInfo, AccessInfo> batchPollingJob = new BatchPollingJob<InterfaceInfo, AccessInfo>();
		batchPollingJob.setResourceId("zabbix-host:10050");
		batchPollingJob.setId(UUID.randomUUID());
		Metric m00 = new Metric("name", "type");
		batchPollingJob.setMetrics(List.of(m00));
		when(confApp.getBeanFactory()).thenReturn(clbf);
		when(clbf.resolveEmbeddedValue(any())).thenReturn("");
		zabbixPoller.onEvent(batchPollingJob);
		assertTrue(true);
	}

	@Test
	void testNoPort() {
		BatchPollingJob<InterfaceInfo, AccessInfo> batchPollingJob = new BatchPollingJob<InterfaceInfo, AccessInfo>();
		batchPollingJob.setResourceId("zabbix-host");
		batchPollingJob.setId(UUID.randomUUID());
		Metric m00 = new Metric("name", "type");
		batchPollingJob.setMetrics(List.of(m00));
		when(confApp.getBeanFactory()).thenReturn(clbf);
		when(clbf.resolveEmbeddedValue(any())).thenReturn("");
		zabbixPoller.onEvent(batchPollingJob);
		assertTrue(true);
	}
}
