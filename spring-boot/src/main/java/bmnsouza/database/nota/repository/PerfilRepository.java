package bmnsouza.database.nota.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.nota.entity.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {

	@Query(nativeQuery = true)
	Perfil buscarPorId(Integer id);

	@Query(nativeQuery = true)
	List<Perfil> buscarTodos();

}