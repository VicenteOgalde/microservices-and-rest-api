package cl.auto.service.entities.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.auto.service.entities.Auto;
@Repository
public interface IAutoRepository  extends JpaRepository<Auto, Integer>{

	List<Auto> findByUsuarioId(int usuarioId);
}
