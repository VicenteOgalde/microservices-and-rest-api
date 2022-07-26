package cl.auto.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.auto.service.entities.Auto;
import cl.auto.service.entities.repositories.IAutoRepository;


@Service
public class AutoService {

	@Autowired
	private IAutoRepository autoRepository;

	public List<Auto> findAll(){
		return autoRepository.findAll();
	}
	
	public  Auto findAutoById(int id) {
		return autoRepository.findById(id).orElse(null);
	}
	public Auto saveAuto(Auto auto) {
		Auto nuevoAuto= autoRepository.save(auto);
		return nuevoAuto;
	}
	public List<Auto> findByUsuarioId(int usuarioId){
		return autoRepository.findByUsuarioId(usuarioId);
	}
	

}
