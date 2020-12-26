package bmnsouza.database.fazendario.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.GrupoReceita;

@Repository
public interface GrupoReceitaRepository extends JpaRepository<GrupoReceita, Integer>{

	@Query(nativeQuery = true)
	Slice<GrupoReceita> buscarTodos(Pageable pageable);
	
	@Query(nativeQuery = true)
	GrupoReceita buscarPorId(Integer nrGrupoReceita);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(String dsGrupoReceita);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(Integer nrGrupoReceita, String dsGrupoReceita, String flAtivo);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(Integer nrGrupoReceita);
	
}
