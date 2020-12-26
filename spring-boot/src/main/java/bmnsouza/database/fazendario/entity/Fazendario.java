package bmnsouza.database.fazendario.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Fazendario implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "username")
	private String cpf;

	@Column(name = "name")
	private String nome;

}