
package bmnsouza.database.fazendario.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {

	@Query(nativeQuery = true)
	boolean isAcessoPermitido(String usuario);

	@Query(nativeQuery = true)
	Login buscarPorUsuario(String usuario);

	@Query(nativeQuery = true)
	boolean isBloqueado(String usuario);

	@Query(nativeQuery = true)
	boolean isObrigatoriedadePassada(String ie);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizarTentativaLoginErro(String usuario);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizarTentativaLoginSucesso(String usuario);

}