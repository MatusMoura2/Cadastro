package com.mouraforte.cadastro.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mouraforte.cadastro.domain.enums.Priority;
import com.mouraforte.cadastro.domain.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name = "TB_chamado")
public class Called implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate openDate = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate closedDate;
	
	private Priority priority;
	private Status status;
	private String comments;
	
	@ManyToOne
	@JoinColumn(name = "tecnico_id")
	private Technician technician;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Client client;
	
	public Called() {
		super();
	}


	public Called(UUID id, Priority priority, Status status, String comments, Technician technician, Client client) {
		super();
		this.id = id;
		this.priority = priority;
		this.status = status;
		this.comments = comments;
		this.technician = technician;
		this.client = client;
	}


	public UUID getId() {
		return id;
	}


	public void setId(UUID id) {
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


	public Priority getPriority() {
		return priority;
	}


	public void setPriority(Priority priority) {
		this.priority = priority;
	}


	public Status getStatus() {
		return status;
	}


	public void setStatus(Status status) {
		this.status = status;
	}


	public String getComments() {
		return comments;
	}


	public void setComments(String comments) {
		this.comments = comments;
	}


	public Technician getTechnician() {
		return technician;
	}


	public void setTechnician(Technician technician) {
		this.technician = technician;
	}


	public Client getClient() {
		return client;
	}


	public void setClient(Client client) {
		this.client = client;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Called other = (Called) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
