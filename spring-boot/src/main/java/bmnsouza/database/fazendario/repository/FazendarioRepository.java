package bmnsouza.database.fazendario.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.Fazendario;

@Repository
public interface FazendarioRepository extends JpaRepository<Fazendario, String> {

	@Query(nativeQuery = true)
	Fazendario buscarPorCpf(String cpf);

	@Query(nativeQuery = true)
	Slice<Fazendario> buscarPorNome(String nome, Pageable pageable);

}