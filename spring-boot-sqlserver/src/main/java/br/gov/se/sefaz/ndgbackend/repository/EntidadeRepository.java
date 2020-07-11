package br.gov.se.sefaz.ndgbackend.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.se.sefaz.ndgbackend.model.EntidadeModel;

@Repository
public interface EntidadeRepository extends JpaRepository<EntidadeModel, Integer> {

  @Query(nativeQuery = true)
  EntidadeModel buscaPorId(Integer id);

  @Query(nativeQuery = true)
  EntidadeModel buscaPorCnpj(String cnpj);

  @Query(nativeQuery = true)
  Slice<EntidadeModel> buscaPorRazao(String razao, Pageable pageable);

  @Query(nativeQuery = true)
  Slice<EntidadeModel> buscaTodos(Pageable pageable);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int cadastra(String cnpj, String razao, String logradouro, String numero, String complemento, String bairro, String cep, String municipio, String uf,
    String ddd, String telefone, String email, String frase, String fraseEmail, String cpfRepresentanteLegal, String nomeRepresentanteLegal, String senha,
    String cpfResponsavel);

  @Transactional
  @Modifying
  @Query(nativeQuery = true)
  int atualiza(Integer id, String cnpj, String razao, String logradouro, String numero, String complemento, String bairro, String cep, String municipio,
    String uf, String ddd, String telefone, String email, String frase, String fraseEmail, String cpfRepresentanteLegal, String nomeRepresentanteLegal,
    String estado, String situacao, String cpfResponsavel, String justificativa);

}