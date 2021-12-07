import bulk.box.data.BulkBoxPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application
{
	private BulkBoxPane pane = null;
	
	public static void main(String[] args) 
	{
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception 
	{
		arg0.setTitle("Bulk Box Data");
 
		pane = new BulkBoxPane();
		 
		//Set the Scene
		Scene scene = new Scene(pane);
		arg0.setWidth(860);
		arg0.setHeight(735);
		scene.getStylesheets().add("style.css"); 
		arg0.setScene(scene);
		//Open the Curtains
		arg0.show();
	}
}
