package br.gov.se.sefaz.ndgbackend.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.SorteioPremioResgateModel;

@Repository
public interface SorteioPremioResgateRepository extends JpaRepository<SorteioPremioResgateModel, Integer> {

  @Query(nativeQuery = true)
  Slice<SorteioPremioResgateModel> buscaExpirados(Pageable pageable);
  
  @Query(nativeQuery = true)
  List<SorteioPremioResgateModel> buscaNaoExpirados();

}