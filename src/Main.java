import bulk.box.data.BulkBoxPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
		arg0.getIcons();
		arg0.setResizable(false);
		arg0.setWidth(1150);
		arg0.setHeight(590);
		arg0.setScene(scene);
		//Open the Curtains
		arg0.show();
	}
}

//https://stackoverflow.com/questions/35640634/suming-a-specific-tableview-column-row-in-javafx
// https://stackoverflow.com/questions/49918079/javafx-textfield-text-validation
// https://stackoverflow.com/questions/13784333/platform-runlater-and-task-in-javafx
// https://community.oracle.com/tech/developers/discussion/3519136/dynamically-refresh-content-of-the-javafx-gridpane
// https://stackoverflow.com/questions/22772379/updating-ui-from-different-threads-in-javafx
// javafx thread update ui
// https://stackoverflow.com/questions/22772379/updating-ui-from-different-threads-in-javafx
// https://stackoverflow.com/questions/14674274/multithreading-in-javafx-hangs-the-ui?noredirect=1&lq=1
// https://stackoverflow.com/questions/25171039/what-is-the-best-way-to-manage-multithreading-in-javafx-8
