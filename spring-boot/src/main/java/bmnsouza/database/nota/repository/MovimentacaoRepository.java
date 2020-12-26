package bmnsouza.database.nota.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.nota.entity.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Integer> {

	@Query(nativeQuery = true)
	Slice<Movimentacao> buscarPorContaCorrente(Integer idContaCorrente, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Movimentacao> buscarPorCpf(String cpf, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Movimentacao> buscarPorSorteio(Integer codSorteio, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Movimentacao> buscarTodos(Pageable pageable);

}