package br.gov.se.sefaz.ndgbackend.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.BloqueioResgateModel;

@Repository
public interface BloqueioResgateRepository extends JpaRepository<BloqueioResgateModel, String> {

  @Query(nativeQuery = true)
  BloqueioResgateModel buscaPorData(String dataBloqueio);

  @Query(nativeQuery = true)
  Slice<BloqueioResgateModel> buscaTodos(Pageable pageable);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int cadastra(String dataBloqueio, String mensagemBloqueio);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int atualiza(String dataBloqueio, String mensagemBloqueio);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int exclui(String dataBloqueio);

}