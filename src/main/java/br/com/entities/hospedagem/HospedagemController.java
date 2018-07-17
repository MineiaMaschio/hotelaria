package br.com.entities.hospedagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javassist.NotFoundException;

@RestController
public class HospedagemController {

	@Autowired
	private HospedagemService hospedagemService;

	@RequestMapping(method = RequestMethod.POST, value = "/hospedagem/checkIn/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HospedagemEntity> realizarCheckIn(@RequestBody HospedagemEntity hospedagem, @PathVariable Long id) throws Exception {
		HospedagemEntity hospedagemCadastrada = hospedagemService.realizarCheckIn(hospedagem, id);
		;
		return new ResponseEntity<HospedagemEntity>(hospedagemCadastrada, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/hospedagem/checkOut/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HospedagemEntity> realizarCheckOut(@PathVariable Long id) throws NotFoundException {
		HospedagemEntity hospedagemCadastrada = hospedagemService.realizarCheckout(id);
		return new ResponseEntity<HospedagemEntity>(hospedagemCadastrada, HttpStatus.CREATED);
	}

}
