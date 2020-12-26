package bmnsouza.database.nota.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Data;

@Data
@Entity
@Table(catalog = "NFP_ContaCorrente", schema = "dbo", name = "ContaCorrente")
public class ContaCorrente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "pkconta")
	private Integer id;

	@CPF
	@Column(name = "strcpfcnpjuser")
	private String cpf;

	@Column(name = "dtmtimestamp")
	private LocalDateTime dataCriacao;

}