package bmnsouza.database.nota.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.nota.entity.ContaCorrente;

@Repository
public interface ContaCorrenteRepository extends JpaRepository<ContaCorrente, Integer> {

	@Query(nativeQuery = true)
	ContaCorrente buscarPorId(Integer id);

	@Query(nativeQuery = true)
	ContaCorrente buscarPorCpf(String cpf);

	@Query(nativeQuery = true)
	Slice<ContaCorrente> buscarTodos(Pageable pageable);

}