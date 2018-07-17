package br.com.entities.hospedagem;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.entities.hospede.HospedeEntity;
import edu.umd.cs.findbugs.annotations.NonNull;

@Entity
@Table(name="hospedagem")
public class HospedagemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "hospedagem_seq")
	@SequenceGenerator(name = "hospedagem_seq", sequenceName = "hospedagem_seq", allocationSize = 1, initialValue = 1)
	private Long id;
	
	@ManyToOne
	@NotNull
	private HospedeEntity hospede;
	
	@Column
	@NotNull
	private LocalDateTime dataEntrada;
	
	@Column
	private LocalDateTime dataSaida;
	
	@Column
	@NotNull
	private boolean adicionaVeiculo;

	@Column
	private BigDecimal valor;
	

	//Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public HospedeEntity getHospede() {
		return hospede;
	}

	public void setHospede(HospedeEntity hospede) {
		this.hospede = hospede;
	}

	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDateTime getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}

	public boolean getAdicionaVeiculo() {
		return adicionaVeiculo;
	}

	public void setAdicionaVeiculo(boolean adicionaVeiculo) {
		this.adicionaVeiculo = adicionaVeiculo;
	}
	
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
}
