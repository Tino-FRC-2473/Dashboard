package application;

import com.jfoenix.controls.JFXProgressBar;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class DriverController extends Group {

	private BorderPane pane = new BorderPane();
	private Rectangle2D screenBalance = Screen.getPrimary().getVisualBounds(); //for getting screen dimensions
	private double screenWidth = screenBalance.getWidth();

	private void setLayout() {

		VBox leftVB = new VBox();
		leftVB.setAlignment(Pos.TOP_CENTER);
		double screenHeight = 630;
		double camWidth = 600;
		leftVB.setPrefSize((screenWidth - camWidth) / 2, screenHeight);
//		leftVB.getChildren().add(new Rectangle(, screenHeight, Color.ALICEBLUE));
		VBox camera = new VBox();

		//LEFT VBOX THINGS
		Label title = new Label("MOTOR STATUS");
		title.setPadding(new Insets(10, 10, 10, 10));
		title.setFont(new Font("Sans Serif", 20));

		double battery = 0.64;
		Label batteryLabel = new Label(battery * 100 + "%");
		batteryLabel.setFont(new Font("Arial", 40));
		JFXProgressBar batteryBar = new JFXProgressBar();
		//hbox for battery stuff
		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.setSpacing(20);
		hbox.getChildren().addAll(batteryBar, batteryLabel);
		hbox.setPadding(new Insets(0, 10, 0, 10));
		//help
		batteryBar.setPrefWidth((screenWidth - camWidth) / 2 - 200);
		batteryBar.setPrefHeight(30);
		batteryBar.setProgress(battery);

		int volts = 100;
		Label voltage = new Label("VOLTAGE: " + volts + " v ");
		voltage.setFont(new Font("Arial", 30));
		voltage.setPadding(new Insets(30, 10, 40, 10));

		//TEMPERATURE STUFF
		StackPane tempPane = new StackPane();
		Image img = new Image("images/temperature scale.png");
		ImageView temp = new ImageView(img);
		temp.setFitWidth(200);
		temp.setFitHeight(200);

		Image img2 = new Image("images/temperature hand.png");
		ImageView hand = new ImageView(img2);
		hand.setFitWidth(150);
		hand.setFitHeight(150);

		double minTemp = 0;
		double maxTemp = 180;
		double temperature = 100;
		hand.setRotate(temperature - (minTemp + maxTemp) * 0.5); //clockwise from vertical

		Label tempLabel = new Label(temperature + "\u00B0");
		tempLabel.setFont(new Font("Sans Serif", 20));
		tempLabel.setPadding(new Insets(120, 0, 0, 0));
		tempPane.getChildren().addAll(temp, hand, tempLabel);


		leftVB.getChildren().addAll(title, hbox, voltage, tempPane);

		//RIGHT VBOX THINGS
		VBox rightVB = new VBox();
		rightVB.setPrefSize((screenWidth - camWidth) / 2, screenHeight);

		pane.setLeft(leftVB);
		pane.setRight(rightVB);

	}

	Pane getContent() {
		setLayout();
		return pane;
	}
}