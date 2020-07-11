package br.gov.se.sefaz.ndgbackend.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.SorteioPremioBilheteModel;

@Repository
public interface SorteioPremioBilheteRepository extends JpaRepository<SorteioPremioBilheteModel, Integer> {

  @Query(nativeQuery = true)
  Slice<SorteioPremioBilheteModel> buscaPorSorteio(Integer codSorteio, Pageable pageable);

}