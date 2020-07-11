package br.gov.se.sefaz.ndgbackend.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.EntidadeTotalBilheteModel;

@Repository
public interface EntidadeTotalBilheteRepository extends JpaRepository<EntidadeTotalBilheteModel, String> {

  @Query(nativeQuery = true)
  Slice<EntidadeTotalBilheteModel> buscaPercentualBilhete(int codSorteio, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<EntidadeTotalBilheteModel> buscaPercentualIndicacao(int codSorteio, Pageable pageable);

}