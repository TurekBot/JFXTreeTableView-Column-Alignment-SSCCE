package treetableview;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scenicview.ScenicView;

import java.io.IOException;

public class Demo extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("/fxml/DiscrepancyTypesPane.fxml"));

            root.getStylesheets().add(getClass().getResource("/css/styles.css").toExternalForm());
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            ScenicView.show(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
