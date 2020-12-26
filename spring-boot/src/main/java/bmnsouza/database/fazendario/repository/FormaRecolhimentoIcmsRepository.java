package bmnsouza.database.fazendario.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.FormaRecolhimentoIcms;

@Repository
public interface FormaRecolhimentoIcmsRepository extends JpaRepository<FormaRecolhimentoIcms, Integer> {

	@Query(nativeQuery = true)
	Slice<FormaRecolhimentoIcms> buscarTodos(Pageable pageable);
	
	@Query(nativeQuery = true)
	FormaRecolhimentoIcms buscarPorId(Integer nrFormaRecolhimentoIcms);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(String dsFormaRecolhimentoIcms, LocalDate dtInicioVigencia, Integer nrGrupoRecolhimento, LocalDate dtFimVigencia);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(Integer nrFormaRecolhimentoIcms, String dsFormaRecolhimentoIcms, LocalDate dtInicioVigencia, Integer nrGrupoRecolhimento, LocalDate dtFimVigencia);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(Integer nrFormaRecolhimentoIcms);
	
}