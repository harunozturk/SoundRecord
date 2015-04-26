

import java.io.File;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
	
	String path = new File(".").getAbsolutePath();
		
	@FXML
	public TextField isim;
	
	@FXML
	public TextField kelime;
	
	@FXML
	private Button kaydet;
	
	private JavaSoundRecorder recorder = new JavaSoundRecorder();	

	@FXML
	private void sesBitir() {
		kaydet.setText("Kaydet");
		recorder.finish();
	}
	
	@FXML
	private void sesAl() throws InterruptedException {
		
		kaydet.setText("Kaydediliyor");
		String isim1 = isim.getText();
		String kelime1 = kelime.getText();
		
		recorder.setPath((path + isim1 + "_" + kelime1 + ".wav").toString());
		
		Thread stopper1 = new Thread(new Runnable() {
            public void run() {

            	recorder.start();
            	
            }

        });
        stopper1.start();

		
	}

}
