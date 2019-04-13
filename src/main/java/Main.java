import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            //JAVAFX
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent t) {
                    Platform.exit();
                    System.exit(0);
                }
            });
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("main.fxml"));
            primaryStage.setTitle("Graphical Evaluation Framework");
            primaryStage.setScene(new Scene(root, 981, 626));
            primaryStage.show();
            Platform.setImplicitExit(false);

        }catch (Exception exp) {
            System.out.println("Initerror : Failed to load main.fxml or creating Chart EXP: "+exp);
        }
    }



}
