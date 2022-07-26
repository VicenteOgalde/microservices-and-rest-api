package cl.moto.service.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.moto.service.entities.Moto;
import cl.moto.service.repositories.IMotoRepository;

@Service
public class MotoService {

	@Autowired
	private IMotoRepository motoRepository;
	
	public List<Moto> findAll(){
		return motoRepository.findAll();
	}
	
	public  Moto findMotoById(int id) {
		return motoRepository.findById(id).orElse(null);
	}
	public Moto saveMoto(Moto moto) {
		Moto nuevoMoto= motoRepository.save(moto);
		return nuevoMoto;
	}
	public List<Moto> findByUsuarioId(int usuarioId){
		return motoRepository.findByUsuarioId(usuarioId);
	}
	
	
}
