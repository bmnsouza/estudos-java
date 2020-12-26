package bmnsouza.database.fazendario.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.IcmsContribuinteInscrito;
import bmnsouza.database.fazendario.entity.id.IcmsContribuinteInscritoId;

@Repository
public interface IcmsContribuinteInscritoRepository extends JpaRepository<IcmsContribuinteInscrito, IcmsContribuinteInscritoId> {

	@Query(nativeQuery = true)
	Slice<IcmsContribuinteInscrito> buscarTodos(Pageable pageable);
	
	@Query(nativeQuery = true)
	IcmsContribuinteInscrito buscarPorId(Integer tpContribuinte, Integer nrRegimeTributacaoIcms, Integer nrFormaRecolhimentoICms);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(Integer tpContribuinte, Integer nrRegimeTributacaoIcms, Integer nrFormaRecolhimentoICms);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(Integer tpContribuinte, Integer nrRegimeTributacaoIcms, Integer nrFormaRecolhimentoICms, String flVisivelContribuinte);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(Integer tpContribuinte, Integer nrRegimeTributacaoIcms, Integer nrFormaRecolhimentoICms);
	
}