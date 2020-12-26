package bmnsouza.database.fazendario.repository;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.PercentualCalculoReceita;
import bmnsouza.database.fazendario.entity.id.PercentualCalculoReceitaId;

@Repository
public interface PercentualCalculoReceitaRepository extends JpaRepository<PercentualCalculoReceita, PercentualCalculoReceitaId> {
	
	@Query(nativeQuery = true)
	Slice<PercentualCalculoReceita> buscarTodos(Pageable pageable);
	
	@Query(nativeQuery = true)
	PercentualCalculoReceita buscarPorId(Integer cdReceita, Integer nrSequencial);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(Integer cdReceita, BigDecimal vlPercentualMultaMora, Integer qtMesLimiteMultamora, BigDecimal vlPercentualJurosMora,
		BigDecimal vlPercentAgregacao, BigDecimal vlPercentualAliquota,  BigDecimal vlUfp, LocalDate dtIniciovigencia, LocalDate dtFinalVigencia,
		Integer nrMotivoReceita, Integer tpTransmissao, BigDecimal vlUfpFinal);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(Integer cdReceita, Integer nrSequencial, BigDecimal vlPercentualMultaMora, Integer qtMesLimiteMultaMora, BigDecimal vlPercentualJurosMora,
		BigDecimal vlPercentualAgregacao, BigDecimal vlPercentualAliquota, BigDecimal vlUfp, LocalDate dtInicioVigencia, LocalDate dtFinalVigencia,
		Integer nrMotivoReceita, Integer tpTransmissao, BigDecimal vlUfpFinal);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(Integer cdReceita, Integer nrSequencial);

}