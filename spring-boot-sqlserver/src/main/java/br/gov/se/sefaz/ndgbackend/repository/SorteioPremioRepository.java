package br.gov.se.sefaz.ndgbackend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.SorteioPremioModel;
import br.gov.se.sefaz.ndgbackend.model.id.SorteioPremioModelId;
import br.gov.se.sefaz.ndgbackend.repository.custom.SorteioPremioRepositoryCustom;

@Repository
public interface SorteioPremioRepository extends JpaRepository<SorteioPremioModel, SorteioPremioModelId>, SorteioPremioRepositoryCustom {

  @Query(nativeQuery = true)
  Slice<SorteioPremioModel> buscaPorSorteio(Integer codSorteio, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<SorteioPremioModel> buscaTodos(Pageable pageable);

  @Query(nativeQuery = true)
  List<SorteioPremioModel> buscaPorSorteioTipoPremio(Integer codSorteio, char tpPremio);

  @Transactional
  int cadastra(List<SorteioPremioModel> sorteioPremioModel);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int transfere(Integer codSorteio);

  @Query(nativeQuery = true)
  int buscaQuantidadePremiosPorSorteio(Integer codSorteio);

}