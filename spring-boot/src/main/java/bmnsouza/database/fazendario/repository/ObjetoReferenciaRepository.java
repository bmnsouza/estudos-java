package bmnsouza.database.fazendario.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.ObjetoReferencia;

@Repository
public interface ObjetoReferenciaRepository extends JpaRepository<ObjetoReferencia, Integer> {

	@Query(nativeQuery = true)	
	Slice<ObjetoReferencia> buscarTodos(Pageable pageable);
	
	@Query(nativeQuery = true)
	ObjetoReferencia buscarPorId(Integer nrObjetoReferencia);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(LocalDate dtInicioVigencia, LocalDate dtFimVigencia, String dsObjetoReferencia);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(Integer nrObjetoReferencia);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(Integer nrObjetoReferencia, LocalDate dtInicioVigencia, LocalDate dtFimVigencia, String dsObjetoReferencia);
	
}
