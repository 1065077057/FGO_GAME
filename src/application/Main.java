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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
	@FXML
	ImageView startSetting = new ImageView();
	@FXML
	ImageView startAbout = new ImageView();
	
	// 定义 static Stage 用于关闭开始窗口打开新的窗口
	public static Stage thisStage = new Stage();
	public static Stage exitStage = new Stage();
	public static Stage setStage = new Stage();
	public static Stage aboutStage = new Stage();
	// 继承给 Easy 等使用的
	public static Stage failStage = new Stage();
	public static Stage winStage = new Stage();
	// 继承使用的背景音乐
	public static File M1 = new File("src\\application\\music\\M1.mp3");
	public static File M2 = new File("src\\application\\music\\M2.mp3");
	public static Media mediaBGMM1 = new Media(M1.toURI().toASCIIString());
	public static MediaPlayer playerBGMM1 = new MediaPlayer(mediaBGMM1);
	public static Media mediaBGMM2 = new Media(M2.toURI().toASCIIString());
	public static MediaPlayer playerBGMM2 = new MediaPlayer(mediaBGMM2);
	
	public void start(Stage stage) throws IOException{
		Parent start = FXMLLoader.load(getClass().getResource("Start.fxml"));

		/*测试阶段 暂时不自动播放*/
		playerBGMM1.setAutoPlay(true);
			
		Scene scene = new Scene(start,569,450);
		thisStage.setTitle("FGO Game");
		thisStage.setScene(scene);
		// 固定窗口大小
		thisStage.setResizable(false);
		thisStage.show();
	}
	
	// Start.fxml 中控制三个难度的 Button 的监视器
	@FXML
	private void onStartEasy(ActionEvent event) throws IOException{
		Parent easy = FXMLLoader.load(getClass().getResource("Easy.fxml"));
		Parent help = FXMLLoader.load(getClass().getResource("Help.fxml"));
		
		Scene scene = new Scene(easy,950,900);
		thisStage.setScene(scene);
		Scene helpScene = new Scene(help,766,600);
		Stage helpStage = new Stage();
		helpStage.setTitle("FGO Game Help");
		helpStage.setScene(helpScene);
		helpStage.show();
	}
	@FXML
	private void onStartNormal(ActionEvent event) throws IOException{
		Parent normal = FXMLLoader.load(getClass().getResource("Normal.fxml"));
		Parent help = FXMLLoader.load(getClass().getResource("Help.fxml"));

		Scene scene = new Scene(normal,950,930);
		thisStage.setScene(scene);
		Scene helpScene = new Scene(help,766,600);
		Stage helpStage = new Stage();
		helpStage.setTitle("FGO Game Help");
		helpStage.setScene(helpScene);
		helpStage.show();
	}
	@FXML
	private void onStartHard(ActionEvent event) throws IOException{
		Parent hard = FXMLLoader.load(getClass().getResource("Hard.fxml"));
		Parent help = FXMLLoader.load(getClass().getResource("Help.fxml"));

		Scene scene = new Scene(hard,950,970);
		thisStage.setScene(scene);
		Scene helpScene = new Scene(help,766,600);
		Stage helpStage = new Stage();
		helpStage.setTitle("FGO Game Help");
		helpStage.setScene(helpScene);
		helpStage.show();
	}
	
	// Start.fxml 中与帮助 Button 监听器
	@FXML
	private void onStartHelp(MouseEvent event) throws IOException{
		Parent help = FXMLLoader.load(getClass().getResource("Help.fxml"));
		
		Scene scene = new Scene(help,766,600);
		Stage stage = new Stage();
		stage.setTitle("FGO Game Help");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	/*Exit.fxml 映射的数据域与一些监听器*/
	@FXML
	private void onStartExit(MouseEvent event) throws IOException{
		Parent exit = FXMLLoader.load(getClass().getResource("Exit.fxml"));
		
		Scene scene = new Scene(exit,480,160);
		exitStage.setTitle("退出FGO GAME");
		exitStage.setScene(scene);
		exitStage.setResizable(false);
		exitStage.show();
	}
	@FXML
	Button btDoExit = new Button();
	@FXML
	Button btDontExit = new Button();
	@FXML
	void doExit(ActionEvent event){
		thisStage.close();
		exitStage.close();
		winStage.close();
		failStage.close();
	}
	@FXML
	void dontExit(ActionEvent event){
		exitStage.close();
	}
	
	@FXML
	private void onStartAbout(MouseEvent event) throws IOException{
		Parent about = FXMLLoader.load(getClass().getResource("About.fxml"));
		Scene scene = new Scene(about,400,380);
		Stage stage = new Stage();
		stage.setTitle("关于我们");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}

	/*Setting.fxml 映射的数据域与一些监听器*/
	@FXML
	private void onStartSetting(MouseEvent event) throws IOException{
		Parent exit = FXMLLoader.load(getClass().getResource("Setting.fxml"));

		bgmM1.setToggleGroup(group);
		bgmM2.setToggleGroup(group);

		Scene scene = new Scene(exit,360,500);
		setStage.setTitle("设置FGO GAME");
		setStage.setScene(scene);
		setStage.setResizable(false);
		setStage.show();
	}
	@FXML
	RadioButton bgmM1 = new RadioButton();
	@FXML
	RadioButton bgmM2 = new RadioButton();
	@FXML
	ToggleGroup group = new ToggleGroup();
	@FXML
	public void changeM1(ActionEvent event){
		playerBGMM2.stop();
		playerBGMM1.play();
	}
	@FXML
	public void changeM2(ActionEvent event){
		playerBGMM1.stop();
		playerBGMM2.play();
	}
	
	public static void main(String[] args){
		Application.launch(args);
	}
}