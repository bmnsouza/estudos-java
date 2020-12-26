package bmnsouza.database.fazendario.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.SituacaoDocumento;

@Repository
public interface SituacaoDocumentoRepository extends JpaRepository<SituacaoDocumento, Integer> {

	@Query(nativeQuery = true)
	Slice<SituacaoDocumento> buscarTodos(Pageable pageable);

	@Query(nativeQuery = true)
	SituacaoDocumento buscarPorId(Integer nrSituacao);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(String dsSituacao);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(Integer nrSituacao, String dsSituacao, String flAtivo);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(Integer nrSituacao);

}