package br.gov.se.sefaz.ndgbackend.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.gov.se.sefaz.ndgbackend.model.SorteioPremioModel;
import br.gov.se.sefaz.ndgbackend.repository.custom.SorteioPremioRepositoryCustom;

public class SorteioPremioRepositoryImpl implements SorteioPremioRepositoryCustom {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public int cadastra(List<SorteioPremioModel> sorteioPremioModel) {
    StringBuilder insert = new StringBuilder("insert into NFP_ContaCorrente..TB_SOP_SORTEIO_PREMIO select * from (values ");

    sorteioPremioModel.forEach(premio -> {
      insert.append('(').append(premio.getCodSorteio()).append(',').append(premio.getCodPremio()).append(',').append(premio.getValorPremio()).append(',')
        .append(premio.getFlagPremiado()).append(",'").append(premio.getTipoPremio()).append("'),");
    });

    insert.setLength(insert.length()-1);
    insert.append(") s (codsorteio, codpremio, valorpremio, flagpremiado, tipopremio)");

    return entityManager.createNativeQuery(insert.toString()).executeUpdate();
  }

}