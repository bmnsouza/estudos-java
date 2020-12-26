package bmnsouza.database.fazendario.repository;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.Receita;

@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Integer>{

	@Query(nativeQuery = true)
	Slice<Receita> buscarTodos(Pageable pageable);

	@Query(nativeQuery = true)
	Receita buscarPorId(int nrGrupoReceita);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(int cdReceita, String dsReceita, int nrGrupoReceita, int cdOrigemReceita, int cdOrgao, LocalDate dtInicioVigencia, LocalDate dtFimVigencia,
		int cdReceitaPai, int tpReceita);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(int cdReceita, String dsReceita, int nrGrupoReceita, int cdOrigemReceita, int cdOrgao, LocalDate dtInicioVigencia, LocalDate dtFimVigencia,
		int cdReceitaPai, int tpReceita);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(int cdReceita);

}