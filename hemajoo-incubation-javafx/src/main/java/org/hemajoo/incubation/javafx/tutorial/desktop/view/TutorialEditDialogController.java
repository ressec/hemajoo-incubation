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

import org.hemajoo.incubation.javafx.tutorial.desktop.model.Tutorial;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * A controller for the create/edit dialog pane.
 * <hr>
 * @author  Resse Christophe - Hemajoo Corp.
 * @version 1.0.0
 */
public class TutorialEditDialogController
{
	/**
	 * The text field for the tutorial name.
	 */
	@FXML
	private TextField fieldName;

	/**
	 * The text field for the tutorial description.
	 */
	@FXML
	private TextField fieldDescription;

	/**
	 * The combo box field for the tutorial domain.
	 */
	@FXML
	private ComboBox<String> listDomain;

	/**
	 * The text field for the tutorial author.
	 */
	@FXML
	private TextField fieldAuthor;

	/**
	 * The text field for the tutorial index.
	 */
	@FXML
	private TextField fieldIndex;

	/**
	 * The date picker field for the tutorial creation date.
	 */
	@FXML
	private DatePicker fieldCreated;


	/**
	 * The stage for the create/edit dialog.
	 */
	private Stage dialogStage;

	/**
	 * Tutorial data model.
	 */
	private Tutorial tutorial;

	/**
	 * Pre-defined list of tutorial domains.
	 */
	@SuppressWarnings("nls")
	private ObservableList<String> domains = FXCollections.<String>observableArrayList("Akka", "jMonkey Engine", "JavaFX");

	/**
	 * The status of the {@code OK} button.
	 */
	private boolean okClicked = false;

	/**
	 * Initializes the controller class. This method is automatically called after the FXML file has been loaded.
	 */
	@FXML
	private void initialize()
	{
		listDomain.getItems().addAll(domains);
	}

	/**
	 * Sets the stage of this dialog.
	 * <hr>
	 * @param stage The stage.
	 */
	public void setDialogStage(Stage stage)
	{
		this.dialogStage = stage;
	}

	/**
	 * Sets the element to be edited in the dialog.
	 * <hr>
	 * @param element Element to be edited.
	 */
	public void setTutorial(Tutorial element)
	{
		this.tutorial = element;

		fieldName.setText(tutorial.getName());
		fieldDescription.setText(tutorial.getDescription());
		listDomain.setValue(tutorial.getDomain());
		fieldIndex.setText(Integer.toString(tutorial.getIndex()));
		fieldAuthor.setText(tutorial.getAuthor());
		fieldCreated.setValue(tutorial.getCreated());
	}

	/**
	 * Returns if the {@code OK} button has been clicked.
	 * <hr>
	 * @return {@code True} if the {@code OK} button has been clicked, {@code false} otherwise.
	 */
	public boolean isOkClicked()
	{
		return okClicked;
	}

	/**
	 * Called when the user clicks the {@code OK} button.
	 */
	@FXML
	private void handleOk()
	{
		if (isInputValid())
		{
			tutorial.setName(fieldName.getText());
			tutorial.setDescription(fieldDescription.getText());
			tutorial.setDomain(listDomain.getValue());
			tutorial.setIndex(Integer.parseInt(fieldIndex.getText()));
			tutorial.setAuthor(fieldAuthor.getText());
			tutorial.setCreated(fieldCreated.getValue());

			okClicked = true;
			dialogStage.close();
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel()
	{
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 *
	 * @return true if the input is valid
	 */
	@SuppressWarnings("nls")
	private boolean isInputValid()
	{
		String errorMessage = "";

		if (fieldName.getText() == null || fieldName.getText().length() == 0)
		{
			errorMessage += "No valid name set!\n";
		}

		if (fieldDescription.getText() == null || fieldDescription.getText().length() == 0)
		{
			errorMessage += "No valid description set!\n";
		}

		if (listDomain.getValue() == null || listDomain.getValue().length() == 0)
		{
			errorMessage += "No valid domain set!\n";
		}

		if (fieldIndex.getText() == null || fieldIndex.getText().length() == 0)
		{
			errorMessage += "No valid index set!\n";
		}
		else
		{
			try
			{
				Integer.parseInt(fieldIndex.getText());
			}
			catch (NumberFormatException e)
			{
				errorMessage += "No valid index value set (must be an integer)!\n";
			}
		}

		if (fieldAuthor.getText() == null || fieldAuthor.getText().length() == 0)
		{
			errorMessage += "No valid author set!\n";
		}

		if (fieldCreated.getValue() == null)
		{
			errorMessage += "No valid creation date set!\n";
		}

		if (errorMessage.length() == 0)
		{
			return true;
		}

		// Show the error message.
		Alert alert = new Alert(AlertType.ERROR);
		alert.initOwner(dialogStage);
		alert.setTitle("Invalid Fields");
		alert.setHeaderText("Please correct invalid fields");
		alert.setContentText(errorMessage);

		alert.showAndWait();

		return false;
	}
}
