package com.cgelectrosound.gestion;

import com.cgelectrosound.gestion.utils.NavigationManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GestionApplication extends Application {

	private static ConfigurableApplicationContext springContext;

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void init(){
		springContext = SpringApplication.run(GestionApplication.class);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/Home.fxml"));

		loader.setControllerFactory(springContext::getBean);

		Parent root = loader.load();
		Scene scene = new Scene(root);

		NavigationManager.getInstance().setMainScene(scene);
		NavigationManager.getInstance().setInitialView(root);

		stage.setScene(scene);
		stage.show();
	}

	@Override
	public void stop(){
		springContext.close();
	}
}