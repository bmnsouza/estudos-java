package bmnsouza.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bmnsouza.model.EmailModel;

@Component
public class EmailUtil {

	@Autowired 
	private WebClientUtil webClientUtil;
	
	/**
	 * Consome a API de envio de e-mail do corporativo
	 * @param urlGateway
	 * @param email
	 * @return String
	 */
	public String enviarEmail(String urlGateway, EmailModel emailModel) {
		return webClientUtil.webClient(urlGateway).post()
			.uri(uriBuilder -> uriBuilder.path("/corporativo/v1/enviarEmail").build())
			.bodyValue(emailModel)
			.retrieve().bodyToMono(String.class).block();
	 }

}