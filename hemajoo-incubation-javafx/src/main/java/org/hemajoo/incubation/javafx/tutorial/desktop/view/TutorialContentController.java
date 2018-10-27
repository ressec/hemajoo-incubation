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
package org.hemajoo.incubation.javafx.tutorial.desktop.view;

import java.io.IOException;

import org.hemajoo.incubation.javafx.tutorial.desktop.TutorialApplication;
import org.hemajoo.incubation.javafx.tutorial.desktop.model.Tutorial;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A controller for the tutorial content pane.
 * <hr>
 * @author  Resse Christophe - Hemajoo Corp.
 * @version 1.0.0
 */
public class TutorialContentController
{
	/**
	 * Table containing the registered tutorials.
	 */
	@FXML
	private TableView<Tutorial> tableTutorial;

	/**
	 * Table column for the tutorial domain.
	 */
	@FXML
	private TableColumn<Tutorial, String> columnDomain;

	/**
	 * Table column for the tutorial name.
	 */
	@FXML
	private TableColumn<Tutorial, String> columnName;

	/**
	 * The label used to display the tutorial name.
	 */
	@FXML
	private Label labelName;

	/**
	 * The label used to display the tutorial description.
	 */
	@FXML
	private Label labelDescription;

	/**
	 * The label used to display the tutorial domain.
	 */
	@FXML
	private Label labelDomain;

	/**
	 * The label used to display the tutorial index.
	 */
	@FXML
	private Label labelIndex;

	/**
	 * The label used to display the tutorial author.
	 */
	@FXML
	private Label labelAuthor;

	/**
	 * The label used to display the tutorial creation date.
	 */
	@FXML
	private Label labelCreated;

	/**
	 * The label used to display the tutorial full path name.
	 */
	@FXML
	private Label labelFullPathname;

	/**
	 * The main application.
	 */
	private TutorialApplication application;

	/**
	 * Creates a new tutorial content controller.
	 */
	public TutorialContentController()
	{
		// Empty.
	}

	/**
	 * Initializes the controller class. This method is automatically called after the FXML file has been loaded.
	 */
	@FXML
	private void initialize()
	{
		// Initialize the person table with the two columns.
		columnDomain.setCellValueFactory(cellData -> cellData.getValue().getPropertyDomain());
		columnName.setCellValueFactory(cellData -> cellData.getValue().getPropertyName());

		// Clear tutorial details.
		showTutorialDetails(null);

		// Listen for selection changes and show the person details when changed.
		tableTutorial.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showTutorialDetails(newValue));
	}

	/**
	 * Is called by the main application to give a reference back to itself.
	 * <hr>
	 * @param application Main application.
	 */
	public void setMainApplication(TutorialApplication application)
	{
		this.application = application;

		// Add observable list data to the table
		tableTutorial.setItems(application.getModel());
	}

	/**
	 * Shows the given tutorial in the details pane.
	 * <hr>
	 * @param tutorial Tutorial to show.
	 */
	@SuppressWarnings("nls")
	private void showTutorialDetails(Tutorial tutorial)
	{
		String empty = "";

		if (tutorial != null)
		{
			// Fill the labels with info from the person object.
			labelName.setText(tutorial.getName());
			labelDescription.setText(tutorial.getDescription());
			labelDomain.setText(tutorial.getDomain());
			labelAuthor.setText(tutorial.getAuthor());
			labelIndex.setText(Integer.toString(tutorial.getIndex()));
			labelFullPathname.setText(tutorial.getFullPathname());
			labelCreated.setText(tutorial.getCreated().toString());
		}
		else
		{
			labelName.setText(empty);
			labelAuthor.setText(empty);
			labelDescription.setText(empty);
			labelDomain.setText(empty);
			labelFullPathname.setText(empty);
			labelIndex.setText(empty);
			labelCreated.setText(empty);
		}
	}

	/**
	 * Shows a dialog to create and/or edit a tutorial.
	 * <hr>
	 * @param tutorial Tutorial to create and/or edit.
	 * @return {@code True} if the user has clicked the {@code OK} button, {@code false} otherwise.
	 */
	@SuppressWarnings("nls")
	public boolean showTutorialEditDialog(Tutorial tutorial)
	{
		try
		{
			// Load the FXML file and create a new stage for the popup dialog.
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(TutorialApplication.class.getResource("/fxml/TutorialEditDialog.fxml"));
			AnchorPane page = loader.load();

			// Create the dialog Stage.
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit a tutorial");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(application.getPrimaryStage());

			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set the person into the controller.
			TutorialEditDialogController controller = loader.getController();
			controller.setApplication(application);
			controller.setDialogStage(dialogStage);
			controller.setTutorial(tutorial);

			// Show the dialog and wait until the user closes it
			dialogStage.showAndWait();

			return controller.isOkClicked();
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Called when the {@code New} button has been clicked.
	 */
	@FXML
	private void handleNewTutorial()
	{
		Tutorial data = new Tutorial();

		boolean okClicked = showTutorialEditDialog(data);
		if (okClicked)
		{
			application.getModel().add(data);
		}
	}

	/**
	 * Called when the {@code Edit} button has been clicked.
	 */
	@SuppressWarnings("nls")
	@FXML
	private void handleEditTutorial()
	{
		Tutorial selected = tableTutorial.getSelectionModel().getSelectedItem();
		if (selected != null)
		{
			boolean okClicked = showTutorialEditDialog(selected);
			if (okClicked)
			{
				showTutorialDetails(selected);
			}
		}
		else
		{
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(application.getPrimaryStage());
			alert.setTitle("No entry selected.");
			alert.setHeaderText("No entry has been selected.");
			alert.setContentText("Please select an entry before editing it.");

			alert.showAndWait();
		}
	}

	/**
	 * Called when the {@code Delete} button has been clicked.
	 */
	@SuppressWarnings("nls")
	@FXML
	private void handleDeleteTutorial()
	{
		int index = tableTutorial.getSelectionModel().getSelectedIndex();
		if (index >= 0)
		{
			tableTutorial.getItems().remove(index);
		}
		else
		{
			// Nothing selected.
			Alert alert = new Alert(AlertType.WARNING);
			alert.initOwner(application.getPrimaryStage());
			alert.setTitle("No entry selected.");
			alert.setHeaderText("No entry has been selected.");
			alert.setContentText("Please select an entry before deleting it.");

			alert.showAndWait();
		}
	}
}
