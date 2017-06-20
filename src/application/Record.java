package application;

import java.io.FileInputStream;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class Record {
	@FXML
	public ImageView fx_img1 = new ImageView();
	@FXML
	public ImageView fx_img2 = new ImageView();
	@FXML
	public ImageView fx_img3 = new ImageView();
	@FXML
	public Text fx_score1 = new Text();
	@FXML
	public Text fx_score2 = new Text();
	@FXML
	public Text fx_score3 = new Text();
	@FXML
	public Text hover_test = new Text();
	@FXML
	public Button record_easy = new Button();
	@FXML
	public Button record_normal = new Button();
	@FXML
	public Button record_hard = new Button();
	
	@FXML
	public void onEasy(ActionEvent event) throws IOException{
		hover_test.setOpacity(0);
		record_easy.setOpacity(0);
		record_normal.setOpacity(0);
		record_hard.setOpacity(0);
		fx_img1.setOpacity(1);
		fx_img2.setOpacity(1);
		fx_img3.setOpacity(1);
		fx_score1.setOpacity(1);
		fx_score2.setOpacity(1);
		fx_score3.setOpacity(1);
		
		// 读取文件
		int[] record_score = new int[3];
		FileInputStream input = new FileInputStream("src/application/record/record_easy.bat");
		for(int i = 0;i < 3;i++){
			record_score[i] = input.read();
		}
		input.close();
		
		if(record_score[0] == -1){
			fx_score1.setText("0");
		}else{
			fx_score1.setText(""+record_score[0]);
		}
		if(record_score[1] == -1){
			fx_score2.setText("0");
		}else{
			fx_score2.setText(""+record_score[1]);
		}
		if(record_score[2] == -1){
			fx_score3.setText("0");
		}else{
			fx_score3.setText(""+record_score[2]);
		}
	}
	@FXML
	public void onNormal(ActionEvent event) throws IOException{
		hover_test.setOpacity(0);
		record_easy.setOpacity(0);
		record_normal.setOpacity(0);
		record_hard.setOpacity(0);
		fx_img1.setOpacity(1);
		fx_img2.setOpacity(1);
		fx_img3.setOpacity(1);
		fx_score1.setOpacity(1);
		fx_score2.setOpacity(1);
		fx_score3.setOpacity(1);
		
		// 读取文件
		int[] record_score = new int[3];
		FileInputStream input = new FileInputStream("src/application/record/record_normal.bat");
		for(int i = 0;i < 3;i++){
			record_score[i] = input.read();
		}
		input.close();
		
		if(record_score[0] == -1){
			fx_score1.setText("0");
		}else{
			fx_score1.setText(""+record_score[0]);
		}
		if(record_score[1] == -1){
			fx_score2.setText("0");
		}else{
			fx_score2.setText(""+record_score[1]);
		}
		if(record_score[2] == -1){
			fx_score3.setText("0");
		}else{
			fx_score3.setText(""+record_score[2]);
		}
	}
	@FXML
	public void onHard(ActionEvent event) throws IOException{
		hover_test.setOpacity(0);
		record_easy.setOpacity(0);
		record_normal.setOpacity(0);
		record_hard.setOpacity(0);
		fx_img1.setOpacity(1);
		fx_img2.setOpacity(1);
		fx_img3.setOpacity(1);
		fx_score1.setOpacity(1);
		fx_score2.setOpacity(1);
		fx_score3.setOpacity(1);
		
		// 读取文件
		int[] record_score = new int[3];
		FileInputStream input = new FileInputStream("src/application/record/record_hard.bat");
		for(int i = 0;i < 3;i++){
			record_score[i] = input.read();
		}
		input.close();
		
		if(record_score[0] == -1){
			fx_score1.setText("0");
		}else{
			fx_score1.setText(""+record_score[0]);
		}
		if(record_score[1] == -1){
			fx_score2.setText("0");
		}else{
			fx_score2.setText(""+record_score[1]);
		}
		if(record_score[2] == -1){
			fx_score3.setText("0");
		}else{
			fx_score3.setText(""+record_score[2]);
		}
	}
}