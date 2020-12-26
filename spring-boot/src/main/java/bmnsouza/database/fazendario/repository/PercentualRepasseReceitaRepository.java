package bmnsouza.database.fazendario.repository;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.PercentualRepasseReceita;
import bmnsouza.database.fazendario.entity.id.PercentualRepassereceitaId;

@Repository
public interface PercentualRepasseReceitaRepository extends JpaRepository<PercentualRepasseReceita, PercentualRepassereceitaId> {
	
	@Query(nativeQuery = true)
	Slice<PercentualRepasseReceita> buscarTodos(Pageable pageable);
	
	@Query(nativeQuery = true)
	PercentualRepasseReceita buscarPorId(Integer cdReceita, Integer cdEntidade, Integer tpProducao);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(Integer cdReceita, Integer cdEntidade, Integer tpProducao, BigDecimal vlPercentual);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(Integer cdReceita, Integer cdEntidade, Integer tpProducao, BigDecimal vlPercentual);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(Integer cdReceita, Integer cdEntidade, Integer tpProducao);

}