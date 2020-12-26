package bmnsouza.database.fazendario.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import bmnsouza.database.fazendario.entity.IcmsContribuinteNaoInscrito;
import bmnsouza.database.fazendario.entity.id.IcmsContribuinteNaoInscritoId;

public interface IcmsContribuinteNaoInscritoRepository extends JpaRepository<IcmsContribuinteNaoInscrito, IcmsContribuinteNaoInscritoId> {

	@Query(nativeQuery = true)
	Slice<IcmsContribuinteNaoInscrito> buscarTodos(Pageable pageable);
	
	@Query(nativeQuery = true)
	IcmsContribuinteNaoInscrito buscarPorId(Integer nrObjetoReferencia, Integer nrFormaRecolhimentoIcms);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(Integer nrObjetoReferencia, Integer nrFormaRecolhimentoIcms);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(Integer nrObjetoReferencia, Integer nrFormaRecolhimentoIcms);
	
}