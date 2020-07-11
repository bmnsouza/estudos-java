package br.gov.se.sefaz.ndgbackend.repository;

import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.ResgateModel;

@Repository
public interface ResgateRepository extends JpaRepository<ResgateModel, String> {

  @Query(nativeQuery = true)
  Slice<ResgateModel> buscaConferencia(Pageable pageable);

  @Query(nativeQuery = true)
  Slice<ResgateModel> buscaRealizado(String dataRelatorio, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<Map<String, String>> buscaNaoRealizado(Pageable pageable);

}