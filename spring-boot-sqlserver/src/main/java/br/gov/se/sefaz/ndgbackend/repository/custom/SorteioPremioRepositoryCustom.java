package br.gov.se.sefaz.ndgbackend.repository.custom;

import java.util.List;

import br.gov.se.sefaz.ndgbackend.model.SorteioPremioModel;

public interface SorteioPremioRepositoryCustom {

  public int cadastra(List<SorteioPremioModel> sorteioPremioModel);

}