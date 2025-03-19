package br.edu.catolica.data_extrator;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DataExtratorApplication {
	public static void main(String[] args) {
		SpringApplication.run(DataExtratorApplication.class, args);
	}

	@EventListener(WebServerInitializedEvent.class)
	public void onApplicationEvent(WebServerInitializedEvent event) {
		int port = event.getWebServer().getPort();
		String url = "http://localhost:" + port;

		if (Desktop.isDesktopSupported()) {
			new Thread(() -> {
				try {
					Thread.sleep(2000); // Aguarda 2 segundos para garantir que o servidor subiu
					Desktop.getDesktop().browse(URI.create(url));
				} catch (IOException | InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
}
