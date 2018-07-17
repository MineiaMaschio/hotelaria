package br.com.entities.hospedagem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.Collection;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

import br.com.entities.hospede.HospedeEntity;
import br.com.entities.hospede.HospedeService;
import javassist.NotFoundException;

@Service
public class HospedagemService {

	@Autowired
	private HospedagemRepository hospedagemRepository;

	@Autowired
	private HospedeService hospedeService;

	@Autowired
	private EntityManager em;

	public HospedagemEntity cadastrar(HospedagemEntity hospedagem) {
		return hospedagemRepository.save(hospedagem);
	}

	public HospedagemEntity buscarPorId(Long id) {
		return hospedagemRepository.getOne(id);
	}

	public Collection<HospedagemEntity> buscarTodos() {
		return hospedagemRepository.findAll();
	}

	public HospedagemEntity realizarCheckIn(HospedagemEntity hospedagem, Long id) throws Exception {
		HospedeEntity hospede = hospedeService.buscarPorId(id);
		if (hospede != null) {
			if (hospede.getHospedado() == true) {
				throw new Exception("Hospede não pode fazer checkIn pois já está hospedado");
			}
			hospede.setHospedado(true);
			hospedeService.alterar(hospede);
		}else {
			throw new Exception("É necessário informar um hospdede");
		}
		return cadastrar(hospedagem);
	}

	public HospedagemEntity realizarCheckout(Long id) throws NotFoundException {
		HospedagemEntity hospedagem = buscarPorId(id);

		if (hospedagem != null) {

			hospedagem.setDataSaida(LocalDateTime.now());
			hospedagem.setValor(calcularEstadia(hospedagem.getDataEntrada(), hospedagem.getDataSaida(),
					hospedagem.getAdicionaVeiculo()));
			HospedeEntity hospede = hospedeService.buscarPorId(id);
			hospede.setHospedado(false);
			hospedeService.alterar(hospede);
			return cadastrar(hospedagem);
		} else {
			throw new NotFoundException("Não foi encontrado a estadia do cliente ");
		}
	}

	public BigDecimal calcularEstadia(LocalDateTime tempoEntrada, LocalDateTime tempoSaida, boolean adicionaVeiculo) {

		LocalDate dataEntrada = tempoEntrada.toLocalDate();
		LocalDate dataSaida = tempoSaida.toLocalDate();
		BigDecimal valor = BigDecimal.ZERO;

		Period period = Period.between(dataEntrada, dataSaida);

		LocalDate datas = dataEntrada;

		for (int i = 0; i < period.getDays(); i++) {

			valor = valor.add(calcularDinehiroPorDia(datas, adicionaVeiculo));
			datas = datas.plusDays(1);
		}

		if (tempoSaida.toLocalTime().isAfter(LocalTime.of(16, 30))) {
			valor = valor.add(calcularDinehiroPorDia(dataSaida, adicionaVeiculo));
		}

		return valor;
	}

	public BigDecimal calcularDinehiroPorDia(LocalDate data, boolean adicionaVeiculo) {
		BigDecimal dinheiro = BigDecimal.ZERO;

		switch (data.getDayOfWeek()) {
		case MONDAY:
		case TUESDAY:
		case WEDNESDAY:
		case THURSDAY:
		case FRIDAY:
			if (adicionaVeiculo) {
				dinheiro = dinheiro.add(BigDecimal.valueOf(15));
			}
			dinheiro = dinheiro.add(BigDecimal.valueOf(120));
			break;
		case SATURDAY:
		case SUNDAY:
			if (adicionaVeiculo) {
				dinheiro = dinheiro.add(BigDecimal.valueOf(20));
			}
			dinheiro = dinheiro.add(BigDecimal.valueOf(150));
			break;
		}
		return dinheiro;
	}

	public BigDecimal buscarValorTotalHospedagem(Long id) {
		BooleanBuilder predicate = new BooleanBuilder();
		predicate.and(QHospedagemEntity.hospedagemEntity.hospede.id.eq(id));
		JPAQuery<BigDecimal> query = new JPAQuery<>(em);
		query.select(QHospedagemEntity.hospedagemEntity.valor.sum()).from(QHospedagemEntity.hospedagemEntity)
				.where(predicate);

		return query.fetchOne();
	}

	public BigDecimal buscarUltimoValor(Long id) {
		BooleanBuilder predicate = new BooleanBuilder();
		predicate.and(QHospedagemEntity.hospedagemEntity.hospede.id.eq(id));
		JPAQuery<BigDecimal> query = new JPAQuery<>(em);
		query.select(QHospedagemEntity.hospedagemEntity.valor).from(QHospedagemEntity.hospedagemEntity).where(predicate)
				.orderBy(QHospedagemEntity.hospedagemEntity.id.desc());

		return query.fetchFirst();
	}

}
