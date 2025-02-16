package com.ubiqube.etsi.mano.service.mon.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class KeystoneV3 extends ConnInfo {

	/**
	 * The OpenStack region to use for the VIM connection.
	 */
	@NotNull
	private final String region = "RegionOne";
	/**
	 * The OpenStack project to use for the VIM connection.
	 */
	@NotNull
	private String project;

	private String projectId;
	/**
	 * The OpenStack project domain to use for the VIM connection.
	 */
	@NotNull
	private String projectDomain;
	/**
	 * The OpenStack user domain to use for the VIM connection.
	 */
	@NotNull
	private String userDomain;

	@NotNull
	private String password;
	@NotNull
	private String username;

}
