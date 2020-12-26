package bmnsouza.database.nota.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmnsouza.database.nota.entity.Transferencia;
import bmnsouza.database.nota.entity.dto.transferencia.TransferenciaResgateDTO;

@Repository
public interface TransferenciaRepository extends JpaRepository<Transferencia, Integer> {

	@Query(nativeQuery = true)
	Slice<Transferencia> buscarPorCpf(String cpf, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Transferencia> buscarPorDataRelatorio(LocalDate dataRelatorio, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Transferencia> buscarPorStatus(int status, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<TransferenciaResgateDTO> buscarResgateConferencia(Pageable pageable);

	@Query(nativeQuery = true)
	Slice<TransferenciaResgateDTO> buscarResgateRealizado(LocalDate dataRelatorio, Pageable pageable);

	@Query(nativeQuery = true)
	Slice<Map<String, String>> buscarResgateNaoRealizado(Pageable pageable);

	@Query(nativeQuery = true)
	Map<String, BigDecimal> buscarTotalCreditoPorAno(String cpf, int ano);

	@Query(nativeQuery = true)
	Slice<Transferencia> buscarTodos(Pageable pageable);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizarParaInvalida(Integer id);

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizarParaConferencia();

	@Transactional
	@Modifying
	@Query(nativeQuery = true)
	int atualizarParaResgate();

	@Query(nativeQuery = true)
	long transferirCredito(Integer id, Integer tipoConta, String dscTransferencia, String cpf, BigDecimal saldo, String banco, String agencia, String digitoAgencia,
		String conta, String digitoConta, String operacao);

}