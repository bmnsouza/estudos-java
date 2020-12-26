package bmnsouza.database.nota.repository;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.nota.entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	@Query(nativeQuery = true)
	Usuario buscarPorCpf(String cpf);

	@Query(nativeQuery = true)
	Slice<Usuario> buscarPorNome(String nome, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Usuario> buscarPorPerfil(int codPerfil, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Usuario> buscarPorStatus(int status, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Usuario> buscarTodos(Pageable pageable);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int cadastrar(String cpf, String nome, int codPerfil, String cpfResponsavel);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizar(String cpf, String nome, int codPerfil, int status, String cpfResponsavel);

}