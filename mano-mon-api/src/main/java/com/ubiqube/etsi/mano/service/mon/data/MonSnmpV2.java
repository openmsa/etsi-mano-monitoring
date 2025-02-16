package com.ubiqube.etsi.mano.service.mon.data;

import java.util.UUID;

import com.ubiqube.etsi.mano.dao.mano.InterfaceInfo;
import com.ubiqube.etsi.mano.service.mon.poller.snmp.SnmpV2AuthInfo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MonSnmpV2 extends MonConnInformation<InterfaceInfo, SnmpV2AuthInfo> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

}
