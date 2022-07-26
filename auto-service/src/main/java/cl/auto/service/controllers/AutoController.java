package cl.auto.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.auto.service.entities.Auto;
import cl.auto.service.services.AutoService;

@RestController
@RequestMapping("/auto")
public class AutoController {
	
	@Autowired
	AutoService autoService;
	
	@GetMapping
	public ResponseEntity<List<Auto>> findAll(){
		if(autoService.findAll().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.ok(autoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Auto> findAutoById(@PathVariable("id")int id){
		if(autoService.findAutoById(id).equals(null)) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(autoService.findAutoById(id));
	}

	@PostMapping
	public ResponseEntity<Auto> saveAuto(@RequestBody Auto auto){
		return ResponseEntity.ok(autoService.saveAuto(auto));
	}
	
	@GetMapping("/usuario/{usuarioId}")
	public ResponseEntity<List<Auto>> findAllAutoByIdUsuario(@PathVariable("usuarioId")int usuarioId){
		if(autoService.findByUsuarioId(usuarioId).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(autoService.findByUsuarioId(usuarioId));
	
	}
}
