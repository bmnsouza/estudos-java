package br.gov.se.sefaz.ndgbackend.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.BilheteModel;
import br.gov.se.sefaz.ndgbackend.model.id.BilheteModelId;
import br.gov.se.sefaz.ndgbackend.repository.custom.BilheteRepositoryCustom;

@Repository
public interface BilheteRepository extends JpaRepository<BilheteModel, BilheteModelId>, BilheteRepositoryCustom {

  @Query(nativeQuery = true)
  Slice<BilheteModel> buscaPremiadosPorSorteio(Integer codSorteio, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<BilheteModel> buscaConsumidorPorSorteio(Integer codSorteio, String cpfConsumidor, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<BilheteModel> buscaEntidadePorSorteio(Integer codSorteio, String cnpjEntidade, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<BilheteModel> buscaPorSorteio(Integer codSorteio, Pageable pageable);

  @Query(nativeQuery = true)
  List<BilheteModel> buscaConsumidorSemPremioPorSorteio(Integer codSorteio);

  @Query(nativeQuery = true)
  List<BilheteModel> buscaEntidadeSemPremioPorSorteio(Integer codSorteio);

  @Query(nativeQuery = true)
  int buscaQuantidadePorDatas(String dataInicio, String dataFim);

  @Query(nativeQuery = true)
  int buscaQuantidadePorSorteio(Integer codSorteio);

  @Query(nativeQuery = true)
  int buscaQuantidadePremiadosPorSorteio(Integer codSorteio);

   @Transactional
  int cadastra(List<BilheteModel> bilheteModel);

  @Transactional
  int atualiza(List<BilheteModel> bilheteModel);

}