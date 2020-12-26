package bmnsouza.database.nota.repository;

import java.math.BigDecimal;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.nota.entity.Saldo;

@Repository
public interface SaldoRepository extends JpaRepository<Saldo, Integer> {

	@Query(nativeQuery = true)
	Saldo buscarPorId(Integer id);

	@Query(nativeQuery = true)
	Saldo buscarPorCpf(String cpf);

	@Query(nativeQuery = true)
	Slice<Saldo> buscarPorSaldoMenorQue(BigDecimal saldo, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Saldo> buscarPorSaldoIgualA(BigDecimal saldo, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Saldo> buscarPorSaldoMaiorQue(BigDecimal saldo, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Saldo> buscarTodos(Pageable pageable);

}