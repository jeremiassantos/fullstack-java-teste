package br.com.souninja.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "rasao_social")
	private String rasaoSocial;

	private String cnpj;

	private String email;

	@ManyToOne
	@JoinColumn(name = "cliente_regime_tributario_id")
	private ClienteRegimeTributario clienteRegimeTributario;

	@JsonBackReference
	@OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<ClienteAnexoTipo> clienteAnexoTipos;

	public Cliente() {
	}

	public Cliente(Integer id, String cnpj, String email, ClienteRegimeTributario clienteRegimeTributatrio) {
		super();
		this.id = id;
		this.cnpj = cnpj;
		this.email = email;

		this.clienteRegimeTributario = clienteRegimeTributatrio;
	}

	public Cliente(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCnpj() {
		return this.cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ClienteRegimeTributario getClienteRegimeTributario() {
		return clienteRegimeTributario;
	}

	public void setClienteRegimeTributario(ClienteRegimeTributario clienteRegimeTributario) {
		this.clienteRegimeTributario = clienteRegimeTributario;
	}

	public String getRasaoSocial() {
		return rasaoSocial;
	}

	public void setRasaoSocial(String rasaoSocial) {
		this.rasaoSocial = rasaoSocial;
	}

	public List<ClienteAnexoTipo> getClienteAnexoTipos() {
		return clienteAnexoTipos;
	}

	public void setClienteAnexoTipos(List<ClienteAnexoTipo> clienteAnexoTipos) {
		this.clienteAnexoTipos = clienteAnexoTipos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Cliente other = (Cliente) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", cnpj=" + cnpj + ", email=" + email + "]";
	}

	public Boolean hasInvalid() {
		if (this.cnpj == null || this.email == null || this.rasaoSocial == null || clienteRegimeTributario == null) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}
}