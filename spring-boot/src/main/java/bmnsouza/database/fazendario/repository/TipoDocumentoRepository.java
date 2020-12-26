package bmnsouza.database.fazendario.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.TipoDocumento;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer> {

	@Query(nativeQuery = true)
	Slice<TipoDocumento> buscarTodos(Pageable pageable);
	
	@Query(nativeQuery = true)
	TipoDocumento buscarPorId(Integer cdTipoDocumento);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(Integer cdTipoDocumento, String dsTipoDocumento);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(Integer cdTipoDocumento, String dsTipoDocumento, String flAtivo);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(Integer cdTipoDocumento);
	
}