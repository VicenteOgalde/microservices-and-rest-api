package cl.usuario.service.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.usuario.service.entities.Usuario;
import cl.usuario.service.models.Auto;
import cl.usuario.service.models.Moto;
import cl.usuario.service.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> usuarios = usuarioService.findAll();
		if (usuarios.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> obtenerUsuario(@PathVariable ("id")int id){
		Usuario usuario= usuarioService.findUsuarioById(id);
		if(usuario == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuario);
	}
	@PostMapping
	public ResponseEntity<Usuario> saveUsuario(@RequestBody Usuario usuario){
		Usuario usuarioNuevo= usuarioService.saveUsuario(usuario);
		return ResponseEntity.ok(usuarioNuevo);
	}
	@GetMapping("/autos/{usuarioId}")
	public ResponseEntity<List<Auto>> listarAutos(@PathVariable("usuarioId")int usuarioId){
		if(usuarioService.findUsuarioById(usuarioId)==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuarioService.getAutos(usuarioId));
		
	}
	@GetMapping("/motos/{usuarioId}")
	public ResponseEntity<List<Moto>> listarMotos(@PathVariable("usuarioId")int usuarioId){
		if(usuarioService.findUsuarioById(usuarioId)==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(usuarioService.getMotos(usuarioId));
		
	}
	@PostMapping("/auto/{usuarioId}")
	public ResponseEntity<Auto> saveAuto(@PathVariable("usuarioId")int usuarioId,
			@RequestBody Auto auto){
		return ResponseEntity.ok(usuarioService.saveAuto(usuarioId, auto));
		
	}
	@PostMapping("/moto/{usuarioId}")
	public ResponseEntity<Moto> saveMoto(@PathVariable("usuarioId")int usuarioId,
			@RequestBody Moto moto){
		return ResponseEntity.ok(usuarioService.saveMoto(usuarioId, moto));
		
	}
	
	@GetMapping("/todos/{usuarioId}")
	public ResponseEntity<Map<String, Object>> listarTodo(@PathVariable("usuarioId")int usuarioId){
		return ResponseEntity.ok(usuarioService.getUsuarioAndVehiculos(usuarioId));
	}
	

}