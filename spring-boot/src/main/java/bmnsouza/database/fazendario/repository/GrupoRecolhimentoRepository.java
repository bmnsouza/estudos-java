package bmnsouza.database.fazendario.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.GrupoRecolhimento;

@Repository
public interface GrupoRecolhimentoRepository extends JpaRepository<GrupoRecolhimento, Integer> {
	
	@Query(nativeQuery = true)
	Slice<GrupoRecolhimento> buscarTodos(Pageable pageable);
	
	@Query(nativeQuery = true)
	GrupoRecolhimento buscarPorId(Integer nrGrupoRecolhimento);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(String dsGrupoRecolhimento);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(Integer nrGrupoRecolhimento, String dsGrupoRecolhimento, String flAtivo);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(Integer nrGrupoRecolhimento);
	
}
