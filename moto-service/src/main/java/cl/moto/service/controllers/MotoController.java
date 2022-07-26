package cl.moto.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.moto.service.entities.Moto;
import cl.moto.service.services.MotoService;

@RestController
@RequestMapping("/moto")
public class MotoController {

	@Autowired
	private MotoService motoService;
	
	@GetMapping
	public ResponseEntity<List<Moto>> findAll(){
		if(motoService.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(motoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Moto> findMotoById(@PathVariable("id")int id){
		if(motoService.findMotoById(id).equals(null)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(motoService.findMotoById(id));
	}

	@PostMapping
	public ResponseEntity<Moto> saveMoto(@RequestBody Moto moto){
		return ResponseEntity.ok(motoService.saveMoto(moto));
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Moto>> findAllMotoByIdUsuario(@PathVariable("usuarioId")int usuarioId){
		if(motoService.findByUsuarioId(usuarioId).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(motoService.findByUsuarioId(usuarioId));
	
	}
	
}
