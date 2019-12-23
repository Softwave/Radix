package sample;

// Main controller for Radix - A little program to convert numbers from one base to another
// Made for my own use while playing with C64 programming
// Copyright Jessica Leyba, 2019
// See license for details

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Controller
{
    // Controls
    @FXML
    private Button btnConvert;

    // Choice controls
    @FXML
    private ChoiceBox choiceInput;
    @FXML
    private ChoiceBox choiceBase;

    // Input controls
    @FXML
    private TextField inputInput;

    // Output fields
    @FXML
    private TextField outputDecimal;
    @FXML
    private TextField outputBinary;
    @FXML
    private TextField outputHex;
    @FXML
    private TextField outputOctal;
    @FXML
    private TextField outputBase;

    // Alert
    private Alert alert = new Alert(AlertType.WARNING);

    // Initializer, runs on startup
    public void initialize()
    {
        // Populate the input and output base choosers with 16 bases
        for (int i = 2; i < 17; i++)
        {
            choiceInput.getItems().add(Integer.toString(i));
            choiceBase.getItems().add(Integer.toString(i));
        }
        // Default to decimal
        choiceBase.getSelectionModel().select(8);
        choiceInput.getSelectionModel().select(8);
    }


    public void convertBases(ActionEvent actionEvent)
    {
        System.out.println("Button clicked");
        String inputString = inputInput.getText();
        String inputBaseString = (String)choiceInput.getValue();
        String outputBaseString = (String)choiceBase.getValue();

        int inputBaseVal = Integer.parseInt(inputBaseString);
        int outputBaseVal = Integer.parseInt(outputBaseString);
        try {
            int inputVal = Integer.parseInt(inputString, inputBaseVal);
            convertAll(inputVal, outputBaseVal);
        } catch (NumberFormatException e) {
            alert.setTitle("Invalid input");
            alert.setHeaderText("NUMBER FORMAT EXCEPTION");
            alert.setContentText("You entered an invalid value; please try again");
            alert.showAndWait();
        }
    }

    // Utility methods
    static String baseToBase(String num, int base1, int base2)
    {
        return Integer.toString(Integer.parseInt(num, base1), base2);
    }
    private void convertAll(int decValue, int outputBaseValue)
    {
        String outDecimal = Integer.toString(decValue);
        String outBinary  = baseToBase(outDecimal, 10, 2);
        String outHex     = baseToBase(outDecimal, 10, 16);
        String outOctal   = baseToBase(outDecimal, 10, 8);
        String outBase    = baseToBase(outDecimal, 10, outputBaseValue);

        // Set
        outputDecimal.setText(outDecimal);
        outputBinary.setText(outBinary);
        outputHex.setText(outHex);
        outputOctal.setText(outOctal);
        outputBase.setText(outBase);
    }

    // Copy event handlers
    public void copyDecimal(ActionEvent actionEvent)
    {
        clipCopy(outputDecimal.getText());
        System.out.println("Decimal copied");
    }
    public void copyBinary(ActionEvent actionEvent)
    {
        System.out.println("Binary copied");
        clipCopy(outputBinary.getText());
    }
    public void copyHex(ActionEvent actionEvent)
    {
        System.out.println("Hex copied");
        clipCopy(outputHex.getText());
    }
    public void copyOctal(ActionEvent actionEvent)
    {
        System.out.println("Octal copied");
        clipCopy(outputOctal.getText());
    }
    public void copyBase(ActionEvent actionEvent)
    {
        System.out.println("Copied arbitrary base");
        clipCopy(outputBase.getText());
    }

    private void clipCopy(String inString)
    {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent  content = new ClipboardContent();
        content.putString(inString);
        clipboard.setContent(content);
    }

}
