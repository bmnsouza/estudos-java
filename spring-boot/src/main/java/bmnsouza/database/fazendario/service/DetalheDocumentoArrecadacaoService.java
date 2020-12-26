package bmnsouza.database.fazendario.service;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.DetalheDocumentoArrecadacao;
import bmnsouza.database.fazendario.repository.DetalheDocumentoArrecadacaoRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class DetalheDocumentoArrecadacaoService {

	@Autowired
	private DetalheDocumentoArrecadacaoRepository detalheDocumentoArrecadacaoRepository;

	@Autowired
	private ResultUtil resultUtil;

	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<DetalheDocumentoArrecadacao> dados = detalheDocumentoArrecadacaoRepository
				.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorId(BigInteger nrDAE, Integer nrItem) {
		DetalheDocumentoArrecadacao dados = detalheDocumentoArrecadacaoRepository.buscarPorId(nrDAE, nrItem);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> atualizar(DetalheDocumentoArrecadacao detalheDocumentoArrecadacao) {
		detalheDocumentoArrecadacaoRepository.atualizar(detalheDocumentoArrecadacao.getNrDAE(),
				detalheDocumentoArrecadacao.getNrItem(), detalheDocumentoArrecadacao.getNrAnoReferencia(),
				detalheDocumentoArrecadacao.getNrMesReferencia(), detalheDocumentoArrecadacao.getDtVencimentoReceita(),
				detalheDocumentoArrecadacao.getNrParcelaApuracao(), detalheDocumentoArrecadacao.getQtTaxa(),
				detalheDocumentoArrecadacao.getTpProducao(), detalheDocumentoArrecadacao.getCdMotivoITD(),
				detalheDocumentoArrecadacao.getVlPrincipalReceita(), detalheDocumentoArrecadacao.getVlDesconto(),
				detalheDocumentoArrecadacao.getVlAtualizMonetaria(), detalheDocumentoArrecadacao.getVlMultaMora(),
				detalheDocumentoArrecadacao.getVlJuros(), detalheDocumentoArrecadacao.getVlTotalReceita(),
				detalheDocumentoArrecadacao.getNrObjetoReferencia(),
				detalheDocumentoArrecadacao.getNrRegimeTributacaoIcms(), detalheDocumentoArrecadacao.getCdReceita(),
				detalheDocumentoArrecadacao.getNrTipoDesconto(), detalheDocumentoArrecadacao.getNrMotivoReceita(),
				detalheDocumentoArrecadacao.getCdTipoDocumentoOrigem(), detalheDocumentoArrecadacao.getCdLocalidade(),
				detalheDocumentoArrecadacao.getCdProduto(), detalheDocumentoArrecadacao.getTpProduto(),
				detalheDocumentoArrecadacao.getNrFormaRecolhimentoIcms(),
				detalheDocumentoArrecadacao.getCdCategoriaAtvEcon(), detalheDocumentoArrecadacao.getCdSubGrupo(),
				detalheDocumentoArrecadacao.getCdAtvMacro(), detalheDocumentoArrecadacao.getCdCnae(),
				detalheDocumentoArrecadacao.getCdCnaef(), detalheDocumentoArrecadacao.getCdAtvdEconEstadual(),
				detalheDocumentoArrecadacao.getNrTipoOperacao(), detalheDocumentoArrecadacao.getNrItemProcesso(),
				detalheDocumentoArrecadacao.getTpPagamentoProcesso(),
				detalheDocumentoArrecadacao.getDtDesembaracoAduaneiro(), detalheDocumentoArrecadacao.getCdOrgao(),
				detalheDocumentoArrecadacao.getNrChaveAcesso(), detalheDocumentoArrecadacao.getCdPessoaDestinatario(),
				detalheDocumentoArrecadacao.getCdDocumentoOrigem());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

}
