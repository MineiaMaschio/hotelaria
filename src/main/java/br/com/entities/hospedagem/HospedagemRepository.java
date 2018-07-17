package br.com.entities.hospedagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HospedagemRepository extends JpaRepository<HospedagemEntity, Long>{
	
		
		
}
