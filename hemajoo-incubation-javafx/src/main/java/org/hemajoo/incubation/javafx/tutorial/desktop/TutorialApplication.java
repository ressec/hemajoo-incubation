/*
 * Copyright(c) 2018 - Hemajoo Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Hemajoo Incubation Software (HIS) project
 * which is licensed under the Apache license version 2 and use is subject to
 * license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package org.hemajoo.incubation.javafx.tutorial.desktop;

import java.io.IOException;

import org.hemajoo.incubation.javafx.tutorial.desktop.model.Tutorial;
import org.hemajoo.incubation.javafx.tutorial.desktop.view.TutorialContentController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * The main Tutorial application.
 * <hr>
 * @author  Resse Christophe - Hemajoo Corp.
 * @version 1.0.0
 */
public class TutorialApplication extends Application
{
	/**
	 * Primary stage.
	 */
	private Stage primaryStage;

	/**
	 * The root layout (view).
	 */
	private BorderPane rootLayout;

	/**
	 * The data model managed by the application.
	 */
	private ObservableList<Tutorial> model = FXCollections.observableArrayList();

	/**
	 * Instantiates a new tutorial application.
	 */
	@SuppressWarnings("nls")
	public TutorialApplication()
	{
		// Some sample data until we are able to persist the data model.
		model.add(new Tutorial("Tutorial #1", "Description for tutorial #1 comes here!"));
		model.add(new Tutorial("Tutorial #2", "Description for tutorial #2 comes here!"));
		model.add(new Tutorial("Tutorial #3", "Description for tutorial #3 comes here!"));
		model.add(new Tutorial("Tutorial #4", "Description for tutorial #4 comes here!"));
	}

	@SuppressWarnings("nls")
	@Override
	public void start(Stage primaryStage)
	{
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle(" - Tutorial Launcher Application");
		this.primaryStage.getIcons().add(new Image("/image/ipod_32x32.png"));
		showRootLayout();
		showTutorialOverview();
	}

	/**
	 * Display the root layout.
	 */
	@SuppressWarnings("nls")
	public void showRootLayout()
	{
		try
		{
			// Load root layout from FXML file.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TutorialApplication.class.getResource("view/RootLayout.fxml"));
			rootLayout = loader.load();

			// Show the scene containing the root layout.
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Display the person overview inside the root layout.
	 */
	@SuppressWarnings("nls")
	public void showTutorialOverview()
	{
		try
		{
			// Load the tutorial content view.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TutorialApplication.class.getResource("view/TutorialContent.fxml"));
			AnchorPane personOverview = loader.load();

			// Set person overview into the center of root layout.
			rootLayout.setCenter(personOverview);

			// Give the controller access to the application.
			TutorialContentController controller = loader.getController();
			controller.setMainApplication(this);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Returns the primary stage.
	 * <hr>
	 * @return Primary stage.
	 */
	public Stage getPrimaryStage()
	{
		return primaryStage;
	}

	/**
	 * Returns the model (data).
	 * <hr>
	 * @return List of {@link Tutorial}.
	 */
	public ObservableList<Tutorial> getModel()
	{
		return model;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	@SuppressWarnings("nls")
	public static void main(String[] args)
	{
		try
		{
			java.net.URL iconURL = TutorialApplication.class.getResource("/image/zorpia_512x512.png");
			java.awt.Image image = new javax.swing.ImageIcon(iconURL).getImage();
			com.apple.eawt.Application.getApplication().setDockIconImage(image);
		}
		catch (Exception e)
		{
			// Won't work on Windows or Linux.
		}

		launch(args);
	}
}
