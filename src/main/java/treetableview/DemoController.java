package treetableview;

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import treetableview.model.DiscrepancyType;

import java.net.URL;
import java.util.ResourceBundle;

public class DemoController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="discrepancyTypes"
    private JFXTreeTableView<DiscrepancyType> discrepancyTypes; // Value injected by FXMLLoader

    @FXML // fx:id="categoryColumn"
    private JFXTreeTableColumn<DiscrepancyType, String> categoryColumn; // Value injected by FXMLLoader

    @FXML // fx:id="nameColumn"
    private JFXTreeTableColumn<DiscrepancyType, String> nameColumn; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionColumn"
    private JFXTreeTableColumn<DiscrepancyType, String> descriptionColumn; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert discrepancyTypes != null : "fx:id=\"discrepancyTypes\" was not injected: check your FXML file 'DiscrepancyTypesPane.fxml'.";
        assert categoryColumn != null : "fx:id=\"categoryColumn\" was not injected: check your FXML file 'DiscrepancyTypesPane.fxml'.";
        assert nameColumn != null : "fx:id=\"nameColumn\" was not injected: check your FXML file 'DiscrepancyTypesPane.fxml'.";
        assert descriptionColumn != null : "fx:id=\"descriptionColumn\" was not injected: check your FXML file 'DiscrepancyTypesPane.fxml'.";

        categoryColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<DiscrepancyType, String> param) -> {
            if (categoryColumn.validateValue(param)) return param.getValue().getValue().categoryProperty();
            else return categoryColumn.getComputedValue(param);
        });

        nameColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<DiscrepancyType, String> param) -> {
            if (nameColumn.validateValue(param)) return param.getValue().getValue().nameProperty();
            else return nameColumn.getComputedValue(param);
        });

        descriptionColumn.setCellValueFactory((TreeTableColumn.CellDataFeatures<DiscrepancyType, String> param) -> {
            if (descriptionColumn.validateValue(param)) return param.getValue().getValue().descriptionProperty();
            else return descriptionColumn.getComputedValue(param);
        });


        descriptionColumn.setCellFactory((TreeTableColumn<DiscrepancyType, String> param) -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
        descriptionColumn.setOnEditCommit((TreeTableColumn.CellEditEvent<DiscrepancyType, String> t) ->
                t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().descriptionProperty()
                .set(t.getNewValue()));

        nameColumn.setCellFactory(param -> new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder()));
        nameColumn.setOnEditCommit((TreeTableColumn.CellEditEvent<DiscrepancyType, String> t) -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue()
                    .nameProperty().set(t.getNewValue());
        });

        categoryColumn.setCellFactory(param ->
                new GenericEditableTreeTableCell<>(new TextFieldEditorBuilder())
        );
        categoryColumn.setOnEditCommit(t -> {
            t.getTreeTableView().getTreeItem(t.getTreeTablePosition().getRow()).getValue().categoryProperty().
                    set(t.getNewValue());
        });


// data
        ObservableList<DiscrepancyType> data = FXCollections.observableArrayList();
        data.add(new DiscrepancyType("COLOR DISCREPANCY", "COLOUR TOO LIGHT", ""));
        data.add(new DiscrepancyType("COLOR DISCREPANCY", "COLOUR TOO DARK", ""));
        data.add(new DiscrepancyType("COLOR DISCREPANCY", "WRONG COLOR(HUE)", ""));
        data.add(new DiscrepancyType("MEASUREMENT DISCREPANCY", "NECK", ""));
        data.add(new DiscrepancyType("MEASUREMENT DISCREPANCY", "CHEST", ""));
        data.add(new DiscrepancyType("MEASUREMENT DISCREPANCY", "SLEEVE", ""));
        data.add(new DiscrepancyType("MEASUREMENT DISCREPANCY", "LENGTH", ""));
        data.add(new DiscrepancyType("MEASUREMENT DISCREPANCY", "WIDTH", ""));
        data.add(new DiscrepancyType("MEASUREMENT DISCREPANCY", "INSEAM", ""));
        data.add(new DiscrepancyType("MEASUREMENT DISCREPANCY", "DEPTH", ""));
        data.add(new DiscrepancyType("MEASUREMENT DISCREPANCY", "DIAMETER", ""));
        data.add(new DiscrepancyType("COMPONENT DISCREPANCY", "MISSING COMPONENT(S)", ""));
        data.add(new DiscrepancyType("COMPONENT DISCREPANCY", "SUBSTITUTED COMPONENT(S)", ""));
        data.add(new DiscrepancyType("LABELING DISCREPANCY", "COUNTRY OF ORIGIN", ""));
        data.add(new DiscrepancyType("LABELING DISCREPANCY", "CARE INSTRUCTIONS", ""));
        data.add(new DiscrepancyType("LABELING DISCREPANCY", "MATERIAL COMPOSITION (CONTENTS)", ""));
        data.add(new DiscrepancyType("LABELING DISCREPANCY", "BARCODE", ""));
        data.add(new DiscrepancyType("QUALITY DISCREPANCY", "MATERIAL FLAWS", ""));
        data.add(new DiscrepancyType("QUALITY DISCREPANCY", "DAMAGE", ""));
        data.add(new DiscrepancyType("QUALITY DISCREPANCY", "OVERALL APPEARANCE", ""));


// build tree
        final TreeItem<DiscrepancyType> root = new RecursiveTreeItem<>(data, RecursiveTreeObject::getChildren);

        discrepancyTypes.setRoot(root);
        discrepancyTypes.setShowRoot(false);
        discrepancyTypes.setEditable(true);
        discrepancyTypes.group(categoryColumn);


        JFXTextField filterField = new JFXTextField();
        filterField.textProperty().addListener((o, oldVal, newVal) -> {
            discrepancyTypes.setPredicate(discrepancyType -> discrepancyType.getValue().nameProperty().get().contains(newVal)
                    || discrepancyType.getValue().descriptionProperty().get().contains(newVal)
                    || discrepancyType.getValue().categoryProperty().get().contains(newVal));
        });

        Label size = new Label();
        size.textProperty().bind(Bindings.createStringBinding(() -> discrepancyTypes.getCurrentItemsCount() + "",
                discrepancyTypes.currentItemsCountProperty()));


    }
}
