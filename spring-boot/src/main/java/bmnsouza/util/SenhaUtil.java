package bmnsouza.util;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import bmnsouza.exception.ServiceException;

public class SenhaUtil {

	/**
	 * Criptografa a senha
	 * <p>
	 * <b>OBS:</b> Método transcrito do código implementado em C# com o objetivo de manter o padrão das senhas no Java (SQL Server)
	 * @param senha Senha a ser criptografada
	 * @return Senha criptografada
	 * @throws ServiceException
	 */
	public static String criptografar(String senha) throws ServiceException {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] digest = md5.digest("F@z&Nd41".getBytes("UTF-8"));
			byte[] keyBytes = Arrays.copyOf(digest, 24);

			for (int j = 0, k = 16; j < 8;) {
				keyBytes[k++] = keyBytes[j++];
			}

			SecretKey secretKey = new SecretKeySpec(keyBytes, 0, 24, "TripleDES");
			IvParameterSpec iv = new IvParameterSpec(new byte[8]);
			Cipher cipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

			byte[] cipherText = cipher.doFinal(senha.getBytes("UTF-8"), 0, senha.length());
			senha = Base64.getEncoder().encodeToString(cipherText);

			return senha;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServiceException("Erro ao tentar criptografar senha");
		}
	}

	/**
	 * Criptografa o usuário com a senha
	 * <p>
	 * <b>OBS:</b> Método transcrito do código implementado em C# com o objetivo de manter o padrão das senhas no Java (Oracle)
	 * @param usuario Usuário a ser criptografado com a senha
	 * @param senha Senha a ser criptografada com o usuario
	 * @return Senha criptografada composta do usuario e senha
	 * @throws ServiceException
	 */
	public static String criptografar(String usuario, String senha) throws ServiceException {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			senha = new StringBuilder(senha.toUpperCase()).append(usuario.toUpperCase()).toString();
			senha = Base64.getEncoder().encodeToString(md5.digest(senha.getBytes("UTF-8")));

			return senha;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ServiceException("Erro ao tentar criptografar usuário e senha");
		}
	}	

	/**
	 * Valida a atualização da senha
	 * @param senhaBancoDados senha armazenada no banco de dados
	 * @param senhaAntiga senha que será comparada com a armazenada no banco de dados
	 * @param senhaNova senha que será atualizada no banco de dados
	 * @param confirmaSenhaNova senha que será comparada com a senha nova
	 * @return Senha nova criptografada
	 * @throws ServiceException
	 */
	public static String validarAtualizacaoSenha(String senhaBancoDados, String senhaAntiga, String senhaNova, String confirmaSenhaNova) throws ServiceException {
		// Criptografa as senhas para realizar as verificações
		senhaAntiga = criptografar(senhaAntiga);
		senhaNova = criptografar(senhaNova);
		confirmaSenhaNova = criptografar(confirmaSenhaNova);

		// Verifica se a senha antiga é diferente da senha armazenada na base de dados
		if (!senhaAntiga.equals(senhaBancoDados)) {
			throw new ServiceException("senhaAntiga", "Incorreta");
		}

		// Verifica se senha nova é igual à senha antiga
		if (senhaNova.equals(senhaAntiga)) {
			throw new ServiceException("senhaNova", "Igual à senha antiga");
		}

		// Verifica se confirma senha nova é diferente de senha nova
		if (!confirmaSenhaNova.equals(senhaNova)) {
			throw new ServiceException("confirmaSenhaNova", "Diferente de senha nova");
		}

		return senhaNova;
	}

}
