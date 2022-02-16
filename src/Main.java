import bulk.box.data.BulkBoxPane;
import bulk.box.data.Delete;
import bulk.box.data.Edit;
import bulk.box.data.Insert;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	private BulkBoxPane pane = null;
	private Edit edit = null; 
	private Delete del = null; 
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		arg0.setTitle("Bulk Box Data");

		pane = new BulkBoxPane();
		//insert = new Insert(); 
		//edit = new Edit(); 
		//del = new Delete(); 

		//Set the Scene
		Scene scene = new Scene(pane);
		scene.getStylesheets().add("style.css"); 
		arg0.setResizable(false);
		arg0.setWidth(1140);
		arg0.setHeight(500);
		arg0.setScene(scene);
		//Open the Curtains
		arg0.show();
	}
}