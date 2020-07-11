package br.gov.se.sefaz.ndgbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.ConsumidorQuantidadeBilheteEntidadeModel;

@Repository
public interface ConsumidorQuantidadeBilheteEntidadeRepository extends JpaRepository<ConsumidorQuantidadeBilheteEntidadeModel, String> {

  @Query(nativeQuery = true)
  List<ConsumidorQuantidadeBilheteEntidadeModel> buscaQuantidade(String dataInicio, String dataFim);

}