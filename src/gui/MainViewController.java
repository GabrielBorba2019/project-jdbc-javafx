package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import gui.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class MainViewController implements Initializable {
	
	@FXML
	private MenuItem menuItemSeller;
	
	@FXML
	private MenuItem menuItemDepartment;
	
	@FXML
	private MenuItem menuItemAbout;
	
	@FXML
	public void onMenuItemSellerAction() {
		System.out.println("onMenuItemSellerAction");
	}
	
	@FXML
	public void onMenuItemDepartmentAction() {
		loadView("/gui/DepartmentList.fxml");
	}
	
	@FXML
	public void onMenuItemAboutAction() {
		loadView("/gui/About.fxml");
	
	}
	
	
	
	@Override
	public void initialize(URL url, ResourceBundle rb) {
	}

	//Funcão que abre outra View dentro da janela principal
	private  synchronized void loadView(String absoluteName) {
		try {
			FXMLLoader loarder = new FXMLLoader(getClass().getResource(absoluteName));
			VBox newVbox = loarder.load();
			
			//Carregando a Tela principal uma referencia para o VBOX da janela principal
			Scene mainScene = Main.getMainScene();
			VBox mainVBox =(VBox) ((ScrollPane) mainScene.getRoot()).getContent();
			
			//Referencia para o Menubar da janela VBOX
			Node mainMenu = mainVBox.getChildren().get(0); //Armazena o mainMenu em um node
			mainVBox.getChildren().clear();//Limpa todos os filhos doVBOx
			mainVBox.getChildren().add(mainMenu);//Adicona o mainMenu
			mainVBox.getChildren().addAll(newVbox.getChildren());//Add contents newVbox
		
		}
		
		catch (IOException e) {
			Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), AlertType.ERROR);
		}
		
	}
	
}
