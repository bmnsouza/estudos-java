package bmnsouza.database.fazendario.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.TipoDocumentoSituacao;
import bmnsouza.database.fazendario.entity.id.TipoDocumentoSituacaoId;

@Repository
public interface TipoDocumentoSituacaoRepository extends JpaRepository<TipoDocumentoSituacao, TipoDocumentoSituacaoId> {
	
	@Query(nativeQuery = true)	
	Slice<TipoDocumentoSituacao>buscarTodos(Pageable pageable);
	
	@Query(nativeQuery = true)
	TipoDocumentoSituacao buscarPorId(Integer cdTipoDocumento, Integer nrSituacao);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(Integer cdTipoDocumento, Integer nrSituacao);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(Integer cdTipoDocumento, Integer nrSituacao);
	
}