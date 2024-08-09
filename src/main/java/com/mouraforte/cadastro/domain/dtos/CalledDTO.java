package com.mouraforte.cadastro.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mouraforte.cadastro.domain.Called;

import jakarta.validation.constraints.NotNull;

public class CalledDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate openDate = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate closedDate;
	@NotNull(message = "O campo PRIORIDADE é obrigatorio")
	private Integer priority;
	@NotNull(message = "O campo STATUS é obrigatorio")
	private Integer status;
	@NotNull(message = "O campo OBSERVACÕES é obrigatorio")
	private String comments;
	@NotNull(message = "O campo TITULO é obrigatorio")
	private String name;
	@NotNull(message = "O campo TECNICO é obrigatorio")
	private Long technician;
	@NotNull(message = "O campo CLIENTE e obrigatorio")
	private Long client;
	private String nameTechnician;
	private String nameClient;

	public CalledDTO() {
		super();
	}

	public CalledDTO(Called called) {
		super();
		this.id = called.getId();
		this.openDate = called.getOpenDate();
		this.closedDate = called.getClosedDate();
		this.priority = called.getPriority().getPriorityId();
		this.status = called.getStatus().getStatusId();
		this.comments = called.getComments();
		this.name = called.getName();
		this.technician = called.getTechnician().getId();
		this.client = called.getClient().getId();
		this.nameTechnician = called.getTechnician().getName();
		this.nameClient = called.getClient().getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDate getOpenDate() {
		return openDate;
	}

	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}

	public LocalDate getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(LocalDate closedDate) {
		this.closedDate = closedDate;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getTechnician() {
		return technician;
	}

	public void setTechnician(Long technician) {
		this.technician = technician;
	}

	public Long getClient() {
		return client;
	}

	public void setClient(Long client) {
		this.client = client;
	}

	public String getNameTechnician() {
		return nameTechnician;
	}

	public void setNameTechnician(String nameTechnician) {
		this.nameTechnician = nameTechnician;
	}

	public String getNameClient() {
		return nameClient;
	}

	public void setNameClient(String nameClient) {
		this.nameClient = nameClient;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
