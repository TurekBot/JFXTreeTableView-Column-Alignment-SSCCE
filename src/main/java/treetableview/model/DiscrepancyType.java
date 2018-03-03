package treetableview.model;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DiscrepancyType extends RecursiveTreeObject<DiscrepancyType> {
    StringProperty category;
    StringProperty name;
    StringProperty description;

    public DiscrepancyType(String category, String name, String description) {
        this.category = new SimpleStringProperty(category);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description) ;
    }

    public String getCategory() {
        return category.get();
    }

    public StringProperty categoryProperty() {
        return category;
    }

    public void setCategory(String category) {
        this.category.set(category);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getDescription() {
        return description.get();
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }
}