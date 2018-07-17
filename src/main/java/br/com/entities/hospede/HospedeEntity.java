package br.com.entities.hospede;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.entities.hospedagem.HospedagemEntity;

@Entity
@Table(name="hospode")
public class HospedeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "hospede_seq")
	@SequenceGenerator(name = "hospede_seq", sequenceName = "hospede_seq", allocationSize = 1, initialValue = 1)
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private String documento;
	
	@Column
	private String telefone;
	
	@Column
	private String email;
	
	@Column
	@OneToMany(mappedBy = "hospede", cascade = CascadeType.ALL)
	private List<HospedagemEntity> hospedagens;
	
	@Column
	private boolean hospedado; 


	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public List<HospedagemEntity> getHospedagens() {
		return hospedagens;
	}

	public void setHospedagens(List<HospedagemEntity> hospedagens) {
		this.hospedagens = hospedagens;
	}

	public boolean getHospedado() {
		return hospedado;
	}

	public void setHospedado(boolean hospedado) {
		this.hospedado = hospedado;
	}
	
	
}
