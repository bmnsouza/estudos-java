package br.gov.se.sefaz.ndgbackend.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.ProconModel;

@Repository
public interface ProconRepository extends JpaRepository<ProconModel, Integer> {

  @Query(nativeQuery = true)
  ProconModel buscaPorId(Integer id);

  @Query(nativeQuery = true)
  ProconModel buscaPorCpf(String cpf);

  @Query(nativeQuery = true)
  Slice<ProconModel> buscaPorNome(String nome, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<ProconModel> buscaTodos(Pageable pageable);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int cadastra(String cpf, String senha, String nome, String email, String cpfResponsavel);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int atualiza(Integer id, String cpf, String nome, String dataNascimento, String logradouro, String numero, String complemento, String bairro, String cep, String municipio,
    String uf, String ddd, String telefone, String email, String frase, String fraseEmail, String situacao, String cpfResponsavel);

}