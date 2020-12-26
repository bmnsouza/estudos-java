package bmnsouza.database.fazendario.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.MotivoAlteracaoDocumento;

@Repository
public interface MotivoAlteracaoDocumentoRepository extends JpaRepository<MotivoAlteracaoDocumento, Integer> {
	
	@Query(nativeQuery = true)
	Slice<MotivoAlteracaoDocumento> buscarTodos(Pageable pageable);
	
	@Query(nativeQuery = true)
	MotivoAlteracaoDocumento buscarPorId(Integer cdMotivo);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(String dsMotivo, Integer nrSituacao, String flManual);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(Integer cdMotivo, String dsMotivo, String flAtivo, Integer nrSituacao, String flManual);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(Integer cdMotivo);
	
}
