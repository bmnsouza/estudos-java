package bmnsouza.database.nota.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.nota.entity.Entidade;

@Repository
public interface EntidadeRepository extends JpaRepository<Entidade, Integer> {

	@Query(nativeQuery = true)
	Entidade buscarPorId(Integer id);

	@Query(nativeQuery = true)
	Entidade buscarPorCnpj(String cnpj);

	@Query(nativeQuery = true)
	Slice<Entidade> buscarPorRazao(String razao, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Entidade> buscarTodos(Pageable pageable);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(String cnpj, String razao, String logradouro, String numero, String complemento, String bairro, String cep, String municipio, String uf,
		String ddd, String telefone, String email, String frase, String fraseEmail, String cpfRepresentanteLegal, String nomeRepresentanteLegal, String senha,
		String cpfResponsavel);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(Integer id, String cnpj, String razao, String logradouro, String numero, String complemento, String bairro, String cep, String municipio,
		String uf, String ddd, String telefone, String email, String frase, String fraseEmail, String cpfRepresentanteLegal, String nomeRepresentanteLegal,
		String estado, String situacao, String cpfResponsavel, String justificativa);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizarSenha(Integer id, String senha, String fraseEmail, String cpfResponsavel);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizarTentativaLoginSucesso(String cnpj);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizarTentativaLoginErro(String cnpj);

}