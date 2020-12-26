package bmnsouza.database.fazendario.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.DocumentoArrecadacao;
import bmnsouza.database.fazendario.entity.dto.documentoArrecadacao.DocumentoArrecadacaoCadastrarDTO;
import bmnsouza.database.fazendario.entity.dto.documentoArrecadacao.DocumentoArrecadacaoBuscarDAEPorDocumentoDTO;
import bmnsouza.database.fazendario.repository.custom.DocumentoArrecadacaoRepositoryCustom;

@Repository
public interface DocumentoArrecadacaoRepository extends JpaRepository<DocumentoArrecadacao, BigInteger>, DocumentoArrecadacaoRepositoryCustom {

	@Query(nativeQuery = true)
	Slice<DocumentoArrecadacao> buscarTodos(Pageable pageable);

	@Query(nativeQuery = true)
	DocumentoArrecadacao buscarPorId(BigInteger nrDAE);

	@Query(nativeQuery = true)
	Map<String, BigInteger> gerarNumeroDocumento();
	
	@Query(nativeQuery = true)
	List<DocumentoArrecadacaoBuscarDAEPorDocumentoDTO> buscarDAEPorDocumento(BigInteger nrDAE);
	
	@Transactional
	@Modifying
	int cadastrar(BigInteger nrDAE, DocumentoArrecadacaoCadastrarDTO documentoArrecadacao);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(BigInteger nrDAE, Integer cdFormaArrecadacao, Integer cdControleEmissaomensal, Integer flGeracaoValorAdicionado, Long cdRenavam, 
		Long nrDocumentoRecebimento, BigDecimal vlTotal, BigDecimal vlDescontoTotal, Integer cdLocalPagamento, Integer cdTipoDocumento, Integer nrSituacao,
		Integer cdSituacaoContrib, Integer tpContribuinte, Integer cdBancoArrecadador, Integer cdAgenciaArrecadadora, Integer cdLocalidadeEmitente,
		String cdPessoaEmissor, String cdPessoa, String cdPessoaDestinatario, String nmPessoa, String cdSistemaEmissor, String cdAutenticacao, String cdCodigoBarras,
		String dsMensagem, String flRestricaoFormaPagamento, String flCedido, LocalDate dtArrecadacao, LocalDate dtValidade, LocalDate dtSolicitacaoPagamento,
		LocalDate dtEnvioOnline, LocalDate dtEnvioConsolidado, LocalDate dtRepasse, LocalDate dtDepositoRepasse);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int remover(BigInteger nrDAE);

}
