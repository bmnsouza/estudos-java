package bmnsouza;

import org.springframework.boot.Banner.Mode;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class SpringBootApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder().bannerMode(Mode.OFF).sources(SpringBootApplication.class).run(args);
	}

}