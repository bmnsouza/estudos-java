package bmnsouza.database.fazendario.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.DetalheDocumentoArrecadacao;
import bmnsouza.database.fazendario.entity.id.DetalheDocumentoArrecadacaoId;

@Repository
public interface DetalheDocumentoArrecadacaoRepository extends JpaRepository<DetalheDocumentoArrecadacao, DetalheDocumentoArrecadacaoId>{
	
	@Query(nativeQuery = true)
	Slice<DetalheDocumentoArrecadacao> buscarTodos(Pageable pageable);

	@Query(nativeQuery = true)
	DetalheDocumentoArrecadacao buscarPorId(BigInteger nrDAE, Integer nrItem);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(BigInteger nrDAE, Integer nrItem, Integer nrAnoReferencia, Integer nrMesReferencia, LocalDate dtVencimentoReceita,
			Integer nrParcelaApuracao, Integer qtTaxa, Integer tpProducao, Integer cdMotivoITD,
			BigDecimal vlPrincipalReceita, BigDecimal vlDesconto, BigDecimal vlAtualizMonetaria, BigDecimal vlMultaMora,
			BigDecimal vlJuros, BigDecimal vlTotalReceita, Integer nrObjetoReferencia, Integer nrRegimeTributacaoIcms,
			Integer cdReceita, Integer nrTipoDesconto, Integer nrMotivoReceita, Integer cdTipoDocumentoOrigem,
			Integer cdLocalidade, Integer cdProduto, Integer tpProduto, Integer nrFormaRecolhimentoIcms,
			Integer cdCategoriaAtvEcon, Integer cdSubGrupo, Integer cdAtvMacro, Integer cdCnae, Integer cdCnaef,
			Integer cdAtvdEconEstadual, Integer nrTipoOperacao, Integer nrItemProcesso, String tpPagamentoProcesso,
			LocalDate dtDesembaracoAduaneiro, Integer cdOrgao, String nrChaveAcesso, String cdPessoaDestinatario,
			BigInteger cdDocumentoOrigem);
	
}
