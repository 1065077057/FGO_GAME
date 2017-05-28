package application;

import java.io.File;
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application{
	// Start.fxml 映射的数据域
	@FXML
	Button startEasy = new Button();
	@FXML
	Button startNormal = new Button();
	@FXML
	Button startHard = new Button();
	@FXML
	ImageView startHelp = new ImageView();
	@FXML
	ImageView startExit = new ImageView();
	
	// 定义 static Stage 用于关闭开始窗口打开新的窗口
	public static Stage thisStage = new Stage();
	
	public void start(Stage stage) throws IOException{
		Parent start = FXMLLoader.load(getClass().getResource("Start.fxml"));
		
		File musicFile_M1 = new File("src\\application\\music\\M1.mp3");
		Media musicMedia_M1 = new Media(musicFile_M1.toURI().toASCIIString());
		MediaPlayer M1 = new MediaPlayer(musicMedia_M1);
		/*测试阶段 暂时不自动播放
		M1.setAutoPlay(true);
		*/
		
		Scene scene = new Scene(start,569,900);
		thisStage.setTitle("FGO Game");
		thisStage.setScene(scene);
		thisStage.show();
	}
	
	// Start.fxml 中控制三个难度的 Button 的监视器
	@FXML
	private void onStartEasy(ActionEvent event) throws IOException{
		Parent easy = FXMLLoader.load(getClass().getResource("Easy.fxml"));
		Parent help = FXMLLoader.load(getClass().getResource("Help.fxml"));
		
		Scene scene = new Scene(easy,800,900);
		thisStage.setScene(scene);
		Scene helpScene = new Scene(help,250,250);
		Stage helpStage = new Stage();
		helpStage.setScene(helpScene);
		helpStage.show();
	}
	@FXML
	private void onStartNormal(ActionEvent event) throws IOException{
		
	}
	@FXML
	private void onStartHard(ActionEvent event) throws IOException{
		
	}
	
	// Start.fxml 中左右退出与帮助 Button 监视器
	@FXML
	private void onStartHelp(MouseEvent event) throws IOException{
		Parent help = FXMLLoader.load(getClass().getResource("Help.fxml"));
		
		Scene scene = new Scene(help,300,300);
		Stage stage = new Stage();
		stage.setTitle("FGO Game Help");
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	private void onStartExit(MouseEvent event){
		thisStage.close();
	}
	
	public static void main(String[] args){
		Application.launch(args);
	}
}