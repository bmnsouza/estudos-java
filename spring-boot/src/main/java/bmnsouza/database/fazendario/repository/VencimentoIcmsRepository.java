package bmnsouza.database.fazendario.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.VencimentoIcms;
import bmnsouza.database.fazendario.entity.id.VencimentoIcmsId;

@Repository
public interface VencimentoIcmsRepository extends JpaRepository<VencimentoIcms, VencimentoIcmsId> {
	
	@Query(nativeQuery = true)
	Slice<VencimentoIcms> buscarTodos(Pageable pageable);
	
	@Query(nativeQuery = true)
	VencimentoIcms buscarPorId(Integer nrSequencialVencimentoIcms, Integer nrAno);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(Integer nrAno, Integer tpContribuinte, Integer nrFormaRecolhimentoIcms, Integer nrObjetoReferencia, Integer cdCategoriaAtvEcon,
		Integer cdSubgrupo, Integer cdAtvMacro, Integer cdCnae, Integer cdCnaef, Integer nrParcelaApuracao, String flMesVencimentoSubsequente,
		Integer nrDiaVencimento, Integer nrPortaria, Integer nrAnoPortaria, Integer nrPrioridadePortaria, Integer nrMesRefInicial, Integer nrAnoRefInicial,
		Integer nrMesRefFinal, Integer nrAnoRefFinal, String cdPessoa, Integer cdProduto, Integer tpProduto, Integer nrQtdMesSubsequente);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(Integer nrSequencialVencimentoIcms, Integer nrAno, Integer tpContribuinte, Integer nrFormaRecolhimentoIcms, Integer nrObjetoReferencia, 
			Integer cdCategoriaAtvEcon, Integer cdSubgrupo, Integer cdAtvMacro, Integer cdCnae, Integer cdCnaef, Integer nrParcelaApuracao, String flMesVencimentoSubsequente,
			Integer nrDiaVencimento, Integer nrPortaria, Integer nrAnoPortaria, Integer nrPrioridadePortaria, Integer nrMesRefInicial, Integer nrAnoRefInicial,
			Integer nrMesRefFinal, Integer nrAnoRefFinal, String cdPessoa, Integer cdProduto, Integer tpProduto, Integer nrQtdMesSubsequente);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(Integer nrSequencialVencimentoIcms, Integer nrAno);

}