package br.com.entities.hospede;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController(value="/")
public class HospedeController {

	@Autowired
	private HospedeService hospedeService;
	
	@RequestMapping(method = RequestMethod.POST, value= "/hospedes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HospedeEntity> cadastrarHospede(@RequestBody HospedeEntity hospede){
		HospedeEntity hospedeCadastrado = hospedeService.cadastrar(hospede);
		return new ResponseEntity<HospedeEntity>(hospedeCadastrado, HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/hospedes", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<HospedeEntity>> buscarTodosHospedes(){
		Collection<HospedeEntity> hospedesBuscados = hospedeService.buscarTodos();
		return new ResponseEntity<>(hospedesBuscados, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/hospede/{id}")
	public ResponseEntity<HospedeEntity> excluirHospede(@PathVariable Long id){
		HospedeEntity hospedeEncontrado = hospedeService.buscarPorId(id);
		if(hospedeEncontrado == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		hospedeService.deletar(hospedeEncontrado);
		return new ResponseEntity<HospedeEntity>(HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/hospede/{id}")
	public ResponseEntity<HospedeEntity> alterarHospede(@RequestBody HospedeEntity hospede){
		HospedeEntity hospedeAlterado = hospedeService.alterar(hospede);
		return new ResponseEntity<HospedeEntity>(hospedeAlterado, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/hospede/buscarHospedesAtivos")
	public ResponseEntity<Collection<HospedeEntity>> buscarHospedesAtivos(){
		Collection<HospedeEntity> listasAtivos = hospedeService.obterHospedesAtivos();
		return new ResponseEntity<>(listasAtivos, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/hospede/buscarHospedesInativos")
	public ResponseEntity<Collection<HospedeEntity>> buscarHospedesInativos(){
		Collection<HospedeEntity> listasInativos = hospedeService.obterHospedesInativos();
		return new ResponseEntity<>(listasInativos, HttpStatus.OK);
	}
	
	
}
