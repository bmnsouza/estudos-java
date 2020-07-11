package br.gov.se.sefaz.ndgbackend.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.ConsumidorQuantidadeBilheteModel;

@Repository
public interface ConsumidorQuantidadeBilheteRepository extends JpaRepository<ConsumidorQuantidadeBilheteModel, String> {

  @Query(nativeQuery = true)
  Slice<ConsumidorQuantidadeBilheteModel> buscaPorSorteio(int codSorteio, Pageable pageable);

}