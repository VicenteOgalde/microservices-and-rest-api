package cl.usuario.service.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import cl.usuario.service.entities.Usuario;
import cl.usuario.service.feignclients.AutoFeignClient;
import cl.usuario.service.feignclients.MotoFeignClient;
import cl.usuario.service.models.Auto;
import cl.usuario.service.models.Moto;
import cl.usuario.service.repositories.IUsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private IUsuarioRepository usuarioRepository;
	@Autowired
	private AutoFeignClient autoFeignClient;
	@Autowired
	private MotoFeignClient motoFeignClient;

	// usando resttemplate
	public List<Auto> getAutos(int usuarioId) {
		List<Auto> autos = restTemplate.getForObject("http://localhost:8082/auto/usuario/" + usuarioId, List.class);
		return autos;
	}

	public List<Moto> getMotos(int usuarioId) {
		List<Moto> motos = restTemplate.getForObject("http://localhost:8083/moto/usuario/" + usuarioId, List.class);
		return motos;
	}

	// usando feignClient
	public Auto saveAuto(int usuarioId, Auto auto) {
		auto.setUsuarioId(usuarioId);
		return autoFeignClient.save(auto);
	}

	public Moto saveMoto(int usuarioId, Moto moto) {
		moto.setUsuarioId(usuarioId);
		return motoFeignClient.saveMoto(moto);
	}

	public Map<String, Object> getUsuarioAndVehiculos(int usuarioId) {
		Map<String, Object> results = new HashMap<String, Object>();
		Usuario usuario = usuarioRepository.findById(usuarioId).orElse(null);
		if (usuario == null) {
			results.put("mensaje", "El usuario no existe");
			return results;
		}
		results.put("usuario", usuario);
		System.out.println("estoy por pasar");
		List<Auto> autos = new ArrayList<Auto>();
		try {
			autos = autoFeignClient.getAutos(usuarioId);
		} catch (Exception e) {
			autos = null;
			
		}
		System.out.println("pase por aqui" + autos);
		if (autos == null) {
			results.put("autos", "cliente sin autos");
		} else {
			results.put("autos", autos);
		}
		List<Moto> motos = new ArrayList<Moto>();
		System.out.println("casi en motos");
		try {
			motos = motoFeignClient.getMotos(usuarioId);
		} catch (Exception e) {
			motos = null;
		}
		if (motos == null) {
			results.put("motos", "cliente sin motos");
		} else {
			results.put("motos", motos);
		}
		return results;

	}

	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}

	public Usuario findUsuarioById(int id) {
		return usuarioRepository.findById(id).orElse(null);
	}

	public Usuario saveUsuario(Usuario usuario) {
		Usuario nuevoUsuario = usuarioRepository.save(usuario);
		return nuevoUsuario;
	}
}
