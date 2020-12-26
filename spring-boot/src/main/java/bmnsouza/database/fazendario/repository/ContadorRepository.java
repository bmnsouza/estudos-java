package bmnsouza.database.fazendario.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.fazendario.entity.Contador;

@Repository
public interface ContadorRepository extends JpaRepository<Contador, String> {

	@Query(nativeQuery = true)
	Slice<Contador> buscarContribuintes(String uf, String codigo, Pageable pageable);

}