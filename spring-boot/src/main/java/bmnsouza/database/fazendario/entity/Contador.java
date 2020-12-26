package bmnsouza.database.fazendario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Contador implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String ie;

	private String cnpj;

	private String razaoSocial;

	@Column(name = "pes_nmpessoa")
	private String nome;

}