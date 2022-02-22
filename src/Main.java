import bulk.box.data.BulkBoxPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	
	private BulkBoxPane pane = null;
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setTitle("Bulk Box Data");

		pane = new BulkBoxPane();

		//Set the Scene
		Scene scene = new Scene(pane);
		scene.getStylesheets().add("style.css"); 
		arg0.getIcons().add(new Image(("icon.png")));
		arg0.setResizable(false);
		arg0.setWidth(1110);
		arg0.setHeight(530);
		arg0.setScene(scene);
		//Open the Curtains
		arg0.show();
	}
}