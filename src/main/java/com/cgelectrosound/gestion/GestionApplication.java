package com.cgelectrosound.gestion;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GestionApplication extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) throws Exception {
		var context = SpringApplication.run(GestionApplication.class);
		var fxml = new FXMLLoader(getClass().getResource("/Home.fxml"));
		var scene = new Scene(fxml.load());
		stage.setScene(scene);
		stage.show();
	}
}
