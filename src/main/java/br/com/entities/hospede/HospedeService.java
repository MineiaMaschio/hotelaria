package br.com.entities.hospede;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;

@Service
public class HospedeService {

	@Autowired
	private HospedeRepository hospedeRepository;
	
	@Autowired
	private EntityManager em;

	public HospedeEntity cadastrar(HospedeEntity hospede){
		return hospedeRepository.save(hospede);
	}
	
	public void deletar(HospedeEntity hospede) {
		hospedeRepository.delete(hospede);
	}
	
	public HospedeEntity buscarPorId(Long id){
		Optional<HospedeEntity> hospede = hospedeRepository.findById(id);
		return hospede.orElse(null);
	}
	
	public Collection<HospedeEntity> buscarTodos(){
		return hospedeRepository.findAll();
	}
	
	public HospedeEntity alterar(HospedeEntity hospede) {
		return hospedeRepository.save(hospede);
	}
	
	public Collection<HospedeEntity> obterHospedesInativos(){
		BooleanBuilder predicate = new BooleanBuilder();
		predicate.and(QHospedeEntity.hospedeEntity.hospedado.eq(false));
		JPAQuery<HospedeEntity> query = new JPAQuery<>(em);
		query.from(QHospedeEntity.hospedeEntity)
			 .where(predicate);
		
		List<HospedeEntity> fetch = query.fetch();
		return fetch;
	}
	
	public Collection<HospedeEntity> obterHospedesAtivos(){
		BooleanBuilder predicate = new BooleanBuilder();
		predicate.and(QHospedeEntity.hospedeEntity.hospedado.eq(true));
		JPAQuery<HospedeEntity> query = new JPAQuery<>(em);
		query.from(QHospedeEntity.hospedeEntity)
			 .where(predicate);
		
		List<HospedeEntity> fetch = query.fetch();
		return fetch;
	}
	

	
	
	
}
