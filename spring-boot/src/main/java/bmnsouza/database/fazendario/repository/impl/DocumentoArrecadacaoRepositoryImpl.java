package bmnsouza.database.fazendario.repository.impl;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bmnsouza.database.fazendario.entity.dto.documentoArrecadacao.DocumentoArrecadacaoCadastrarDTO;
import bmnsouza.database.fazendario.entity.dto.documentoArrecadacao.DocumentoArrecadacaoCadastrarDTO.DetalheDocumentoArrecadacaoCadastrarDTO;
import bmnsouza.database.fazendario.repository.custom.DocumentoArrecadacaoRepositoryCustom;

public class DocumentoArrecadacaoRepositoryImpl implements DocumentoArrecadacaoRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int cadastrar(BigInteger nrDAE, DocumentoArrecadacaoCadastrarDTO documentoArrecadacaoCadastrarDTO) {
		StringBuilder insert = new StringBuilder("begin ");
		
		// Insert da tabela tb_dba_documento_base
		insert.append("insert into tb_dba_documento_base (dba_nrdae, dba_cdsituacaoconsistencia, sid_nrsituacao, tdo_cdtipodocumento) values (?, 1, ?, ?);");

		// Insert da tabela tb_dae_documento_arrecadacao
		insert.append("insert into tb_dae_documento_arrecadacao (dba_nrdae, dae_dtarrecadacao, bco_cdbancoarrecadador, agc_cdagenciaarrecadadora, dae_dtemissao, ")
			.append("dae_dtvalidade, pes_cdpessoa, dae_vltotal, dae_dtsolicitacaopagamento, dae_cdformaarrecadacao, dae_flrestricaoformapagamento, dae_dsmensagem, ")
			.append("dae_dtenvioonline, dae_nrdocumentorecebimento, dae_nmpessoa, dae_dtrepasse, dae_dtoperacao, dae_cdpessoaemissor, dae_dtgeracao, ")
			.append("dae_cdcodigobarras, dae_cdautenticacao, dae_cdlocalpagamento, dae_cdcontroleemissaomensal, dae_dtenvioconsolidado, tdo_cdtipodocumento, ")
			.append("sid_nrsituacao, dae_vldescontototal, vei_cdrenavam, dae_dtdepositorepasse, tsi_cdsituacaocontrib, tpc_tpcontribuinte, dae_flgeracaovaloradicionado, ")
			.append("pes_cdpessoadestinatario, loc_cdlocalidadeemitente, dae_cdsistemaemissor, dae_flcedido) values (?, to_date(?, 'dd/mm/rrrr'), to_number(?), ")
			.append("to_number(?), sysdate, to_date(?, 'dd/mm/rrrr'), ?, ?, to_date(?, 'dd/mm/rrrr'), to_number(?), ?, ?, to_date(?, 'dd/mm/rrrr'), to_number(?), ?, ")
			.append("to_date(?, 'dd/mm/rrrr'), sysdate, ?, sysdate, ?, ?, to_number(?), null, to_date(?, 'dd/mm/rrrr hh24:mi:ss'), ?, ?, to_number(?), to_number(?), ")
			.append("to_date(?, 'dd/mm/rrrr'), to_number(?), to_number(?), -1, ?, to_number(?), ?, ?);");
			
		// Insert da tabela tb_ded_detalhe_dae (Colunas)
		insert.append("insert into tb_ded_detalhe_dae (dba_nrdae, ded_nritem, ded_nranoreferencia, ded_nrmesreferencia, ded_dtvencimentoreceita, ded_vlprincipalreceita, ")
		.append("ded_vltotalreceita, ded_vldesconto, ded_cddocumentoorigem, rec_cdreceita, tid_nrtipodesconto, obr_nrobjetoreferencia, ded_nrparcelaapuracao, ")
		.append("tdg_cdtipodocumentoorigem, mre_nrmotivoreceita, prs_cdproduto, ded_qttaxa, prs_tpproduto, ded_cdmotivoitd, ded_tpproducao, ded_nritemprocesso, ")
		.append("loc_cdlocalidade, top_nrtipooperacao, ded_vlatualizmonetaria, ded_vlmultamora, ded_vljuros, ret_nrregimetributacaoicms, frc_nrformarecolhimentoicms, ")
		.append("cae_cdcategoriaatvecon, sga_cdsubgrupo, atm_cdatvmacro, cna_cdcnae, cnf_cdcnaef, aee_cdatvdeconestadual, ded_tppagamentoprocesso, ")
		.append("ded_dtdesembaracoaduaneiro, org_cdorgao, ded_nrchaveacesso, pes_cdpessoadestinatario) ");
		
		// Insert da tabela tb_ded_detalhe_dae (Itens)
		documentoArrecadacaoCadastrarDTO.getDetalheDocumentoArrecadacao().forEach(detalheDocumento -> {
			insert.append("select ?, ?, ?, ?, to_date(?, 'dd/mm/rrrr'), ?, ?, to_number(?), to_number(?), ?, to_number(?), ?, to_number(?), to_number(?), to_number(?), ")
				.append("to_number(?), to_number(?), to_number(?), to_number(?), to_number(?), to_number(?), to_number(?), to_number(?), to_number(?), to_number(?), ")
				.append("to_number(?), to_number(?), to_number(?), to_number(?), to_number(?), to_number(?), to_number(?), to_number(?), to_number(?), ?, ")
				.append("to_date(?, 'dd/mm/rrrr'), to_number(?), ?, ? from dual union all ");
		});
		
		// Retira o último union all
		insert.setLength(insert.length()-11);
		insert.append("; end;");
		
		// Cria a instância de Query
		Query query = entityManager.createNativeQuery(insert.toString());
		
		// Parâmetros da tabela tb_dba_documento_base
		query.setParameter(1, nrDAE).setParameter(2, documentoArrecadacaoCadastrarDTO.getNrSituacao()).setParameter(3, documentoArrecadacaoCadastrarDTO.getCdTipoDocumento());
		
		// Parâmetros da tabela tb_dae_documento_arrecadacao
		query.setParameter(4, nrDAE).setParameter(5, documentoArrecadacaoCadastrarDTO.getDtArrecadacao())
				.setParameter(6, documentoArrecadacaoCadastrarDTO.getCdBancoArrecadador()).setParameter(7, documentoArrecadacaoCadastrarDTO.getCdAgenciaArrecadadora())
				.setParameter(8, documentoArrecadacaoCadastrarDTO.getDtValidade()).setParameter(9, documentoArrecadacaoCadastrarDTO.getCdPessoa())
				.setParameter(10, documentoArrecadacaoCadastrarDTO.getVlTotal()).setParameter(11, documentoArrecadacaoCadastrarDTO.getDtSolicitacaoPagamento())
				.setParameter(12, documentoArrecadacaoCadastrarDTO.getCdFormaArrecadacao()).setParameter(13, documentoArrecadacaoCadastrarDTO.getFlRestricaoFormaPagamento())
				.setParameter(14, documentoArrecadacaoCadastrarDTO.getDsMensagem()).setParameter(15, documentoArrecadacaoCadastrarDTO.getDtEnvioOnLine())
				.setParameter(16, documentoArrecadacaoCadastrarDTO.getNrDocumentoRecebimento()).setParameter(17, documentoArrecadacaoCadastrarDTO.getNmPessoa())
				.setParameter(18, documentoArrecadacaoCadastrarDTO.getDtRepasse()).setParameter(19, documentoArrecadacaoCadastrarDTO.getCdPessoaEmissor())
				.setParameter(20, documentoArrecadacaoCadastrarDTO.getCdCodigoBarras()).setParameter(21, documentoArrecadacaoCadastrarDTO.getCdAutenticacao())
				.setParameter(22, documentoArrecadacaoCadastrarDTO.getCdLocalPagamento()).setParameter(23, documentoArrecadacaoCadastrarDTO.getDtEnvioConsolidado())
				.setParameter(24, documentoArrecadacaoCadastrarDTO.getCdTipoDocumento()).setParameter(25, documentoArrecadacaoCadastrarDTO.getNrSituacao())
				.setParameter(26, documentoArrecadacaoCadastrarDTO.getVlDescontoTotal()).setParameter(27, documentoArrecadacaoCadastrarDTO.getCdRenavam())
				.setParameter(28, documentoArrecadacaoCadastrarDTO.getDtDepositoRepasse()).setParameter(29, documentoArrecadacaoCadastrarDTO.getCdSituacaoContrib())
				.setParameter(30, documentoArrecadacaoCadastrarDTO.getTpContribuinte()).setParameter(31, documentoArrecadacaoCadastrarDTO.getCdPessoaDestinatario())
				.setParameter(32, documentoArrecadacaoCadastrarDTO.getCdLocalidadeEmitente()).setParameter(33, documentoArrecadacaoCadastrarDTO.getCdSistemaEmissor())
				.setParameter(34, documentoArrecadacaoCadastrarDTO.getFlCedido());
		
		// Último valor posicional do parâmetro
		int position = 34;
		
		// Parâmetros da tabela tb_ded_detalhe_dae
		for (DetalheDocumentoArrecadacaoCadastrarDTO detalhe: documentoArrecadacaoCadastrarDTO.getDetalheDocumentoArrecadacao()) {
			query.setParameter(++position, nrDAE).setParameter(++position, detalhe.getNrItem()).setParameter(++position, detalhe.getNrAnoReferencia())
				.setParameter(++position, detalhe.getNrMesReferencia()).setParameter(++position, detalhe.getDtVencimentoReceita())
				.setParameter(++position, detalhe.getVlPrincipalReceita()).setParameter(++position, detalhe.getVlTotalReceita())
				.setParameter(++position, detalhe.getVlDesconto()).setParameter(++position, detalhe.getCdDocumentoOrigem()).setParameter(++position, detalhe.getCdReceita())
				.setParameter(++position, detalhe.getNrTipoDesconto()).setParameter(++position, detalhe.getNrObjetoReferencia())
				.setParameter(++position, detalhe.getNrParcelaApuracao()).setParameter(++position, detalhe.getCdTipoDocumentoOrigem())
				.setParameter(++position, detalhe.getNrMotivoReceita()).setParameter(++position, detalhe.getCdProduto()).setParameter(++position, detalhe.getQtTaxa())
				.setParameter(++position, detalhe.getTpProduto()).setParameter(++position, detalhe.getCdMotivoItd()).setParameter(++position, detalhe.getTpProducao())
				.setParameter(++position, detalhe.getNrItemProcesso()).setParameter(++position, detalhe.getCdLocalidade())
				.setParameter(++position, detalhe.getNrTipoOperacao()).setParameter(++position, detalhe.getVlAtualizMonetaria())
				.setParameter(++position, detalhe.getVlMultaMora()).setParameter(++position, detalhe.getVlJuros())
				.setParameter(++position, detalhe.getNrRegimeTributacaoIcms()).setParameter(++position, detalhe.getNrFormaRecolhimentoIcms())
				.setParameter(++position, detalhe.getCdCategoriaAtvEcon()).setParameter(++position, detalhe.getCdSubgrupo()).setParameter(++position, detalhe.getCdAtvMacro())
				.setParameter(++position, detalhe.getCdCnae()).setParameter(++position, detalhe.getCdCnaef()).setParameter(++position, detalhe.getCdAtvdEconEstadual())
				.setParameter(++position, detalhe.getTpPagamentoProcesso()).setParameter(++position, detalhe.getDtDesembaracoAduaneiro())
				.setParameter(++position, detalhe.getCdOrgao()).setParameter(++position, detalhe.getNrChaveAcesso())
				.setParameter(++position, detalhe.getCdPessoaDestinatario());
		}
				
		// Executa os comandos com os respectivos parâmetros
		return query.executeUpdate();
	}

}