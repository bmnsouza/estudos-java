package bmnsouza.database.nota.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bmnsouza.database.nota.entity.SorteioPremio;
import bmnsouza.database.nota.repository.custom.SorteioPremioRepositoryCustom;

public class SorteioPremioRepositoryImpl implements SorteioPremioRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int cadastrar(List<SorteioPremio> sorteioPremio) {		
		StringBuilder insert = new StringBuilder("begin ");
		
		// Insert da tabela NFP_ContaCorrente..TB_SOP_SORTEIO_PREMIO (Colunas)
		insert.append("insert into NFP_ContaCorrente..TB_SOP_SORTEIO_PREMIO (sor_codSorteio, sop_codPremio, sop_valPremio, sop_flPremiado, sop_tpPremio) ");

		// Insert da tabela NFP_ContaCorrente..TB_SOP_SORTEIO_PREMIO (Itens)
		sorteioPremio.forEach(premio -> {
			insert.append("select ?, ?, ?, ?, ? union all ");
		});

		// Retira o último union all
		insert.setLength(insert.length()-11);
		insert.append("; end;");

		// Cria a instância de Query
		Query query = entityManager.createNativeQuery(insert.toString());		

		// Primeiro valor posicional do parâmetro
		int position = 0;
		
		// Parâmetros da tabela NFP_ContaCorrente..TB_SOP_SORTEIO_PREMIO
		for (SorteioPremio sorteio: sorteioPremio) {
			query.setParameter(++position, sorteio.getCodSorteio()).setParameter(++position, sorteio.getCodPremio()).setParameter(++position, sorteio.getValorPremio())
				.setParameter(++position, sorteio.getFlagPremiado()).setParameter(++position, sorteio.getTipoPremio());
		}

		// Executa os comandos com os respectivos parâmetros
		return query.executeUpdate();
	}

}