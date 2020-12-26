package bmnsouza.database.fazendario.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bmnsouza.database.fazendario.entity.DocumentoArrecadacao;
import bmnsouza.database.fazendario.entity.dto.documentoArrecadacao.DocumentoArrecadacaoAtualizarDTO;
import bmnsouza.database.fazendario.entity.dto.documentoArrecadacao.DocumentoArrecadacaoBuscarDAEPorDocumentoDTO;
import bmnsouza.database.fazendario.entity.dto.documentoArrecadacao.DocumentoArrecadacaoBuscarDAEPorDocumentoDTO.DocumentoArrecadacaoDTO;
import bmnsouza.database.fazendario.entity.dto.documentoArrecadacao.DocumentoArrecadacaoBuscarDAEPorDocumentoDTO.DocumentoArrecadacaoDTO.DetalheDocumentoArrecadacaoDTO;
import bmnsouza.database.fazendario.entity.dto.documentoArrecadacao.DocumentoArrecadacaoCadastrarDTO;
import bmnsouza.database.fazendario.repository.DocumentoArrecadacaoRepository;
import bmnsouza.util.result.EntidadeResult;
import bmnsouza.util.result.ResultUtil;

@Service
public class DocumentoArrecadacaoService {

	@Autowired
	private DocumentoArrecadacaoRepository documentoArrecadacaoRepository;

	@Autowired
	private ResultUtil resultUtil;

	@Value("${spring.data.web.pageable.default-page-size}")
	private int PAGE_SIZE;

	public ResponseEntity<EntidadeResult> buscarTodos(int pagina) {
		Slice<DocumentoArrecadacao> dados = documentoArrecadacaoRepository
				.buscarTodos(PageRequest.of(pagina, PAGE_SIZE));
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> buscarPorId(BigInteger nrDAE) {
		DocumentoArrecadacao dados = documentoArrecadacaoRepository.buscarPorId(nrDAE);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}
	
	public ResponseEntity<EntidadeResult> buscarDAEPorDocumento(BigInteger nrDAE) {
		// Dados do documento
		List<DocumentoArrecadacaoBuscarDAEPorDocumentoDTO> documento = documentoArrecadacaoRepository.buscarDAEPorDocumento(nrDAE);		
		DocumentoArrecadacaoDTO dados = new DocumentoArrecadacaoDTO(
			documento.get(0).getNrDAE(), documento.get(0).getDsSituacao(), documento.get(0).getDsTipoDocumento(),
			documento.get(0).getDtEmissao(), documento.get(0).getDtValidade() != null ? documento.get(0).getDtValidade().toLocalDate() : null,
			documento.get(0).getDtArrecadacao() != null ? documento.get(0).getDtArrecadacao().toLocalDate() : null, documento.get(0).getDtEnvioonline(),
			documento.get(0).getDtEnvioConsolidado(), documento.get(0).getDtRepasse() != null ? documento.get(0).getDtRepasse().toLocalDate() : null,
			documento.get(0).getCdAutenticacao(), documento.get(0).getDsBanco(), documento.get(0).getDsAgencia(), documento.get(0).getCdPessoa(),
			documento.get(0).getNmPessoa(), documento.get(0).getCdPessoaEmissor(), documento.get(0).getCdPessoaDestinatario(),
			documento.get(0).getVlDescontoTotal(), documento.get(0).getVlTotal(), null
		);

		// Itens do documento
		List<DetalheDocumentoArrecadacaoDTO> listaDetalhe = new ArrayList<DetalheDocumentoArrecadacaoDTO>();
		for (int i = 0; i < documento.size(); i++) {
			DetalheDocumentoArrecadacaoDTO detalhe = new DocumentoArrecadacaoDTO().new DetalheDocumentoArrecadacaoDTO(
				documento.get(i).getNrItem(), documento.get(i).getMesAnoReferencia(), documento.get(i).getDtVencimentoReceita().toLocalDate(), 
				documento.get(i).getDsReceita(), documento.get(i).getDsFormaRecolhimentoIcms(), documento.get(i).getCdDocumentoOrigem(),
				documento.get(i).getVlPrincipalReceita(), documento.get(i).getVlDesconto(), documento.get(i).getVlAtualizMonetaria(),
				documento.get(i).getVlMultaMora(), documento.get(i).getVlJuros(), documento.get(i).getVlTotalReceita()
			);
			listaDetalhe.add(detalhe);
		}
		
		// Adiciona os itens ao documento
		dados.setDetalheDocumentoArrecadacao(listaDetalhe);
		
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> cadastrar(DocumentoArrecadacaoCadastrarDTO documentoArrecadacao) {
		BigInteger nrDAE = gerarNumeroDocumento();
		documentoArrecadacaoRepository.cadastrar(nrDAE, documentoArrecadacao);
		
		Map<String, BigInteger> dados = new HashMap<>();
		dados.put("nrDAE", nrDAE);
				
		return resultUtil.resultSucesso(HttpStatus.CREATED, ResultUtil.MENSAGEM_SUCESSO, dados);
	}

	public ResponseEntity<EntidadeResult> atualizar(DocumentoArrecadacaoAtualizarDTO documentoArrecadacao) {
		documentoArrecadacaoRepository.atualizar(documentoArrecadacao.getNrDAE(), documentoArrecadacao.getCdFormaArrecadacao(), 
			documentoArrecadacao.getCdControleEmissaoMensal(), documentoArrecadacao.getFlGeracaoValorAdicionado(), documentoArrecadacao.getCdRenavam(),
			documentoArrecadacao.getNrDocumentoRecebimento(), documentoArrecadacao.getVlTotal(), documentoArrecadacao.getVlDescontoTotal(),
			documentoArrecadacao.getCdLocalPagamento(), documentoArrecadacao.getCdTipoDocumento(), documentoArrecadacao.getNrSituacao(),
			documentoArrecadacao.getCdSituacaoContrib(), documentoArrecadacao.getTpContribuinte(), documentoArrecadacao.getCdBancoArrecadador(),
			documentoArrecadacao.getCdAgenciaArrecadadora(), documentoArrecadacao.getCdLocalidadeEmitente(), documentoArrecadacao.getCdPessoaEmissor(),
			documentoArrecadacao.getCdPessoa(), documentoArrecadacao.getCdPessoaDestinatario(), documentoArrecadacao.getNmPessoa(),
			documentoArrecadacao.getCdSistemaEmissor(), documentoArrecadacao.getCdAutenticacao(), documentoArrecadacao.getCdCodigoBarras(),
			documentoArrecadacao.getDsMensagem(), documentoArrecadacao.getFlRestricaoFormaPagamento(),documentoArrecadacao.getFlCedido(),
			documentoArrecadacao.getDtArrecadacao(), documentoArrecadacao.getDtValidade(), documentoArrecadacao.getDtSolicitacaoPagamento(),
			documentoArrecadacao.getDtEnvioOnLine(), documentoArrecadacao.getDtEnvioConsolidado(), documentoArrecadacao.getDtRepasse(),
			documentoArrecadacao.getDtDepositoRepasse());
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}

	public ResponseEntity<EntidadeResult> remover(BigInteger nrDAE) {
		documentoArrecadacaoRepository.remover(nrDAE);
		return resultUtil.resultSucesso(HttpStatus.OK, ResultUtil.MENSAGEM_SUCESSO);
	}
	
	/**
	 * Retorna o número do Documento de Arrecadação Estadual
	 * @return BigInteger
	 */
	private synchronized BigInteger gerarNumeroDocumento() {
		Map<String, BigInteger> numeroDocumento = documentoArrecadacaoRepository.gerarNumeroDocumento();
		String nrUltimoDAE = String.valueOf(numeroDocumento.get("nroUltimoDAE"));
		BigInteger nrDAE = new BigInteger(new StringBuilder(nrUltimoDAE).append(calcularDigitoDAE(nrUltimoDAE)).toString());
		return nrDAE;
	}
	
	/**
	 * Retorna o cálculo dos dígitos do Documento de Arrecadação Estadual
	 * @param nrUltimoDAE
	 * @return String
	 */
	private String calcularDigitoDAE(String nrUltimoDAE) {
		int a1 = Integer.parseInt(nrUltimoDAE.substring(0, 1));
		int a2 = Integer.parseInt(nrUltimoDAE.substring(1, 2));
		int a3 = Integer.parseInt(nrUltimoDAE.substring(2, 3));
		int a4 = Integer.parseInt(nrUltimoDAE.substring(3, 4));
		int m1 = Integer.parseInt(nrUltimoDAE.substring(4, 5));
		int m2 = Integer.parseInt(nrUltimoDAE.substring(5, 6));
		int s1 = Integer.parseInt(nrUltimoDAE.substring(6, 7));
		int s2 = Integer.parseInt(nrUltimoDAE.substring(7, 8));
		int s3 = Integer.parseInt(nrUltimoDAE.substring(8, 9));
		int s4 = Integer.parseInt(nrUltimoDAE.substring(9, 10));
		int s5 = Integer.parseInt(nrUltimoDAE.substring(10, 11));
		int s6 = Integer.parseInt(nrUltimoDAE.substring(11));

		// Cálculo do primeiro dígito
		int primeiro = ((13 * a1) + (12 * a2) + (11 * a3) + (10 * a4) + (9 * m1) + (8 * m2) + (7 * s1) + (6 * s2) + (5 * s3) + (4 * s4) + (3 * s5) + (2 * s6));
		int resto1 = (primeiro - ((primeiro / 11) * 11));
		int digito1 = (resto1 == 0 || resto1 == 1) ? 0 : (11 - resto1); 

		// Cálculo do segundo dígito
		int segundo = ((14 * a1) + (13 * a2) + (12 * a3) + (11 * a4) + (10 * m1) + (9 * m2) + (8 * s1) + (7 * s2) + (6 * s3) + (5 * s4) + (4 * s5) + (3 * s6) + (2 * digito1));
		int resto2 = (segundo - ((segundo / 11) * 11));
		int digito2 = (resto2 == 0 || resto2 == 1) ? 0 : (11 - resto2);  

		return new StringBuilder(String.valueOf(digito1)).append(String.valueOf(digito2)).toString();
	}

}
