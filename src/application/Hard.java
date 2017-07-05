package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class Hard extends Easy{
	protected final Timeline tl_timeEnd = new Timeline();

	protected final static Image IMGA = new Image("application/images/059E.png");
	protected final static Image IMGB = new Image("application/images/060E.png");
	protected final static Image IMGC = new Image("application/images/068E.png");
	protected final static Image IMGD = new Image("application/images/014E.png");
	protected final static Image IMGE = new Image("application/images/091E.png");
	protected final static Image IMGKB = new Image("application/images/KB.png");

	private static int IMGANUM = 0;
	private static int IMGBNUM = 0;
	private static int IMGCNUM = 0;
	private static int IMGDNUM = 0;
	private static int IMGENUM = 0;

	private String onePoint = null;
	private String twoPoint = null;
	private ImageView last = null;

	private static int score = 0;
	private int reward = 0;
	private int punish = 0;

	private int cardEndChoose = 0;
	private int timeDead = 0;
	
	private Image img_0_0_kb;
	private Image img_0_1_kb;
	private Image img_0_2_kb;
	private Image img_0_3_kb;
	private Image img_0_4_kb;
	private Image img_0_5_kb;
	private Image img_1_0_kb;
	private Image img_1_1_kb;
	private Image img_1_2_kb;
	private Image img_1_3_kb;
	private Image img_1_4_kb;
	private Image img_1_5_kb;
	private Image img_2_0_kb;
	private Image img_2_1_kb;
	private Image img_2_2_kb;
	private Image img_2_3_kb;
	private Image img_2_4_kb;
	private Image img_2_5_kb;
	private Image img_3_0_kb;
	private Image img_3_1_kb;
	private Image img_3_2_kb;
	private Image img_3_3_kb;
	private Image img_3_4_kb;
	private Image img_3_5_kb;
	private Image img_4_0_kb;
	private Image img_4_1_kb;
	private Image img_4_2_kb;
	private Image img_4_3_kb;
	private Image img_4_4_kb;
	private Image img_4_5_kb;
	
	@FXML
	private Button began;
	@FXML
	private ImageView img_0_0;
	@FXML
	private ImageView img_0_1;
	@FXML
	private ImageView img_0_2;
	@FXML
	private ImageView img_0_3;
	@FXML
	private ImageView img_0_4;
	@FXML
	private ImageView img_0_5;
	@FXML
	private ImageView img_1_0;
	@FXML
	private ImageView img_1_1;
	@FXML
	private ImageView img_1_2;
	@FXML
	private ImageView img_1_3;
	@FXML
	private ImageView img_1_4;
	@FXML
	private ImageView img_1_5;
	@FXML
	private ImageView img_2_0;
	@FXML
	private ImageView img_2_1;
	@FXML
	private ImageView img_2_2;
	@FXML
	private ImageView img_2_3;
	@FXML
	private ImageView img_2_4;
	@FXML
	private ImageView img_2_5;
	@FXML
	private ImageView img_3_0;
	@FXML
	private ImageView img_3_1;
	@FXML
	private ImageView img_3_2;
	@FXML
	private ImageView img_3_3;
	@FXML
	private ImageView img_3_4;
	@FXML
	private ImageView img_3_5;
	@FXML
	private ImageView img_4_0;
	@FXML
	private ImageView img_4_1;
	@FXML
	private ImageView img_4_2;
	@FXML
	private ImageView img_4_3;
	@FXML
	private ImageView img_4_4;
	@FXML
	private ImageView img_4_5;
	@FXML
	protected ImageView textGood = new ImageView();
	@FXML
	protected ImageView textGreat = new ImageView();
	@FXML
	protected ImageView textUnbelievable = new ImageView();
	@FXML
	protected ImageView textBad = new ImageView();
	@FXML
	protected ImageView textWorse = new ImageView();
	@FXML
	protected MenuItem reStart = new MenuItem();
	@FXML
	protected MenuItem chooseLeave = new MenuItem();
	@FXML
	protected MenuItem set = new MenuItem();
	@FXML
	protected MenuItem record = new MenuItem();
	@FXML
	protected MenuItem help = new MenuItem();
	@FXML
	protected MenuItem about = new MenuItem();
	@FXML
	protected Text scoreShow = new Text();
	@FXML
	protected Text timeMinute = new Text();
	@FXML
	protected Text timeSecond = new Text();
	
	private static Image randomChoose(){
		Random random = new Random();
		while(true){
			int choose = random.nextInt(5);
			switch(choose){
				case 0: if(IMGANUM < 6){
							IMGANUM++;
							return IMGA;
						};break;
				case 1: if(IMGBNUM < 6){
							IMGBNUM++;
							return IMGB;
						};break;
				case 2: if(IMGCNUM < 6){
							IMGCNUM++;
							return IMGC;
						};break;
				case 3: if(IMGDNUM < 6){
							IMGDNUM++;
							return IMGD;
						};break;
				case 4: if(IMGENUM < 6){
							IMGENUM++;
							return IMGE;
						};break;
			}
		}
	}
	
	private static void write_record() throws IOException{
		int[] record_score = new int[3];
		FileInputStream input = new FileInputStream("src/application/record/record_hard.bat");
		for(int i = 0;i < 3;i++){
			record_score[i] = input.read();
		}
		input.close();
		
		int score_middle = score+staticTime;
		int key = -1;
		for(int i = 0;i < 3;i++){
			if(score_middle >= record_score[i]){
				key = i;
				break;
			}
		}
		if(key != -1){
			int t1;
			int t2 = score_middle;
			for(;key < 3;key++){
				t1 = record_score[key];
				record_score[key] = t2;
				t2 = t1;
			}
		}

		FileOutputStream output = new FileOutputStream("src/application/record/record_hard.bat");
		for(int i = 0;i < 3;i++){
			output.write(record_score[i]);
		} 
		output.close();
	}

	protected void gameWin() throws IOException{
		Parent start = FXMLLoader.load(getClass().getResource("GameOver_Win_Hard.fxml"));
		Scene scene = new Scene(start,700,560);
		winStage.setTitle("WIN!");
		winStage.setScene(scene);
		winStage.setResizable(false);
		winStage.show();
	}
	@FXML
	private Text finalScore = new Text();
	@FXML
	private Text finalTime = new Text();
	@FXML
	private Button count = new Button();
	@FXML
	private ImageView newRecord = new ImageView();

	private static int staticTime;
	
	@FXML
	private void beganCount(ActionEvent event) throws IOException{
		int[] record_score = new int[3];
		FileInputStream input = new FileInputStream("src/application/record/record_hard.bat");
		for(int i = 0;i < 3;i++){
			record_score[i] = input.read();
		}
		input.close();
		
		count.setOpacity(0);
		finalScore.setText(""+(score+staticTime));
		finalTime.setText(""+(110-staticTime));
		if(Integer.parseInt(finalScore.getText()) > record_score[2]){
			newRecord.setOpacity(1);
		}
		write_record();
	}
	
	EventHandler<ActionEvent> flowTextForGood = e -> {
		textGood.setOpacity(1);
		textGood.setY(textGood.getY()-2);
	};
	EventHandler<ActionEvent> flowTextForGood_end = e -> {
		textGood.setOpacity(0);
		textGood.setY(0);
	};
	
	EventHandler<ActionEvent> flowTextForGreat = e -> {
		textGreat.setOpacity(1);
		textGreat.setY(textGreat.getY()-2);
	};
	EventHandler<ActionEvent> flowTextForGreat_end = e -> {
		textGreat.setOpacity(0);
		textGreat.setY(0);
	};
	
	EventHandler<ActionEvent> flowTextForUnbelievable = e -> {
		textUnbelievable.setOpacity(1);
		textUnbelievable.setY(textUnbelievable.getY()-2);
	};
	EventHandler<ActionEvent> flowTextForUnbelievable_end = e -> {
		textUnbelievable.setOpacity(0);
		textUnbelievable.setY(0);
	};
	
	EventHandler<ActionEvent> flowTextForBad = e -> {
		textBad.setOpacity(1);
		textBad.setY(textBad.getY()-2);
	};
	EventHandler<ActionEvent> flowTextForBad_end = e -> {
		textBad.setOpacity(0);
		textBad.setY(0);
	};
	
	EventHandler<ActionEvent> flowTextForWorse = e -> {
		textWorse.setOpacity(1);
		textWorse.setY(textWorse.getY()-2);
	};
	EventHandler<ActionEvent> flowTextForWorse_end = e -> {
		textWorse.setOpacity(0);
		textWorse.setY(0);
	};
	
	KeyFrame kf_flowTextForGood = new KeyFrame(Duration.millis(1),flowTextForGood);
	KeyFrame kf_flowTextForGood_end = new KeyFrame(Duration.millis(1200),flowTextForGood_end);
	Timeline flowTextForGoodShow = new Timeline(kf_flowTextForGood);
	Timeline flowTextForGoodShow_end = new Timeline(kf_flowTextForGood_end);
	
	KeyFrame kf_flowTextForGreat = new KeyFrame(Duration.millis(1),flowTextForGreat);
	KeyFrame kf_flowTextForGreat_end = new KeyFrame(Duration.millis(1200),flowTextForGreat_end);
	Timeline flowTextForGreatShow = new Timeline(kf_flowTextForGreat);
	Timeline flowTextForGreatShow_end = new Timeline(kf_flowTextForGreat_end);
	
	KeyFrame kf_flowTextForUnbelievable = new KeyFrame(Duration.millis(1),flowTextForUnbelievable);
	KeyFrame kf_flowTextForUnbelievable_end = new KeyFrame(Duration.millis(1200),flowTextForUnbelievable_end);
	Timeline flowTextForUnbelievableShow = new Timeline(kf_flowTextForUnbelievable);
	Timeline flowTextForUnbelievableShow_end = new Timeline(kf_flowTextForUnbelievable_end);
	
	KeyFrame kf_flowTextForBad = new KeyFrame(Duration.millis(1),flowTextForBad);
	KeyFrame kf_flowTextForBad_end = new KeyFrame(Duration.millis(1200),flowTextForBad_end);
	Timeline flowTextForBadShow = new Timeline(kf_flowTextForBad);
	Timeline flowTextForBadShow_end = new Timeline(kf_flowTextForBad_end);
	
	KeyFrame kf_flowTextForWorse = new KeyFrame(Duration.millis(1),flowTextForWorse);
	KeyFrame kf_flowTextForWorse_end = new KeyFrame(Duration.millis(1200),flowTextForWorse_end);
	Timeline flowTextForWorseShow = new Timeline(kf_flowTextForWorse);
	Timeline flowTextForWorseShow_end = new Timeline(kf_flowTextForWorse_end);

	public void noname1(ImageView img){
		FadeTransition ft = new FadeTransition(Duration.millis(450),img); 
		ft.setFromValue(1);
		ft.setToValue(0);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.play();
	}
	public void noname2(ImageView img){
		FadeTransition ft = new FadeTransition(Duration.millis(450),img); 
		ft.setFromValue(0);
		ft.setToValue(1);
		ft.setCycleCount(1);
		ft.setAutoReverse(false);
		ft.play();
	}
	public void noname3() throws IOException{
		last = null;
		onePoint = null;
		twoPoint = null;
		score += 40;
		reward++;
		punish = 0;
		cardEndChoose += 2;

		if(cardEndChoose == 30){
			cardEndChoose = 0;
			tl_timeEnd.stop();
			staticTime = Integer.parseInt(timeSecond.getText());
			gameWin();
		}
		switch(reward){
			case 3: {
						score += 60;
						flowTextForGoodShow.setCycleCount(310);
						flowTextForGoodShow_end.setCycleCount(1);
						flowTextForGoodShow_end.play();
						flowTextForGoodShow.play();
					}break;
			case 5: {
						score += 120;
						flowTextForGreatShow.setCycleCount(310);
						flowTextForGreatShow_end.setCycleCount(1);
						flowTextForGreatShow_end.play();
						flowTextForGreatShow.play();
					}break;
			case 9: {
						score += 380;
						flowTextForUnbelievableShow.setCycleCount(310);
						flowTextForUnbelievableShow_end.setCycleCount(1);
						flowTextForUnbelievableShow_end.play();
						flowTextForUnbelievableShow.play();
					}break;
		}
		if(reward == 9){
			reward = 0;
		}
		scoreShow.setText(""+score);
	}
	public void noname4(){
		onePoint = null;
		twoPoint = null;
		punish++;
		reward = 0;
		switch(punish){
			case 3: {
						if(score >= 50){
							score -= 50;
						}else{
							score = 0;
						}
						flowTextForBadShow.setCycleCount(310);
						flowTextForBadShow_end.setCycleCount(1);
						flowTextForBadShow_end.play();
						flowTextForBadShow.play();
				}break;
			case 5: {
						if(score >= 50){
							score -= 120;
						}else{
							score = 0;
						}
						flowTextForWorseShow.setCycleCount(310);
						flowTextForWorseShow_end.setCycleCount(1);
						flowTextForWorseShow_end.play();
						flowTextForWorseShow.play();
					}break;
		}
		if(punish == 5){
			punish = 0;
		}
		scoreShow.setText(""+score);
	}
	
	@FXML
	private void onClicked00(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_0_0.getImage() == IMGKB)&&(img_0_0.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_0_0);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_0_0.setImage(IMGKB);
				noname2(img_0_0);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_0_0.setImage(img_0_0_kb);
				noname2(img_0_0);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_0_0);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_0_0_kb.toString();
				last = img_0_0;
			}else{
				twoPoint = img_0_0_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked01(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_0_1.getImage() == IMGKB)&&(img_0_1.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_0_1);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_0_1.setImage(IMGKB);
				noname2(img_0_1);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_0_1.setImage(img_0_1_kb);
				noname2(img_0_1);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_0_1);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_0_1_kb.toString();
				last = img_0_1;
			}else{
				twoPoint = img_0_1_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked02(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_0_2.getImage() == IMGKB)&&(img_0_2.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_0_2);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_0_2.setImage(IMGKB);
				noname2(img_0_2);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_0_2.setImage(img_0_2_kb);
				noname2(img_0_2);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_0_2);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_0_2_kb.toString();
				last = img_0_2;
			}else{
				twoPoint = img_0_2_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked03(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_0_3.getImage() == IMGKB)&&(img_0_3.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_0_3);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_0_3.setImage(IMGKB);
				noname2(img_0_3);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_0_3.setImage(img_0_3_kb);
				noname2(img_0_3);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_0_3);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_0_3_kb.toString();
				last = img_0_3;
			}else{
				twoPoint = img_0_3_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked04(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_0_4.getImage() == IMGKB)&&(img_0_4.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_0_4);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_0_4.setImage(IMGKB);
				noname2(img_0_4);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_0_4.setImage(img_0_4_kb);
				noname2(img_0_4);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_0_4);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_0_4_kb.toString();
				last = img_0_4;
			}else{
				twoPoint = img_0_4_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked05(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_0_5.getImage() == IMGKB)&&(img_0_5.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_0_5);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_0_5.setImage(IMGKB);
				noname2(img_0_5);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_0_5.setImage(img_0_5_kb);
				noname2(img_0_5);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_0_5);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_0_5_kb.toString();
				last = img_0_5;
			}else{
				twoPoint = img_0_5_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked10(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_1_0.getImage() == IMGKB)&&(img_1_0.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_1_0);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_1_0.setImage(IMGKB);
				noname2(img_1_0);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_1_0.setImage(img_1_0_kb);
				noname2(img_1_0);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_1_0);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_1_0_kb.toString();
				last = img_1_0;
			}else{
				twoPoint = img_1_0_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked11(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_1_1.getImage() == IMGKB)&&(img_1_1.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_1_1);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_1_1.setImage(IMGKB);
				noname2(img_1_1);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_1_1.setImage(img_1_1_kb);
				noname2(img_1_1);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_1_1);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_1_1_kb.toString();
				last = img_1_1;
			}else{
				twoPoint = img_1_1_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked12(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_1_2.getImage() == IMGKB)&&(img_1_2.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_1_2);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_1_2.setImage(IMGKB);
				noname2(img_1_2);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_1_2.setImage(img_1_2_kb);
				noname2(img_1_2);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_1_2);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_1_2_kb.toString();
				last = img_1_2;
			}else{
				twoPoint = img_1_2_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked13(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_1_3.getImage() == IMGKB)&&(img_1_3.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_1_3);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_1_3.setImage(IMGKB);
				noname2(img_1_3);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_1_3.setImage(img_1_3_kb);
				noname2(img_1_3);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_1_3);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_1_3_kb.toString();
				last = img_1_3;
			}else{
				twoPoint = img_1_3_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked14(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_1_4.getImage() == IMGKB)&&(img_1_4.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_1_4);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_1_4.setImage(IMGKB);
				noname2(img_1_4);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_1_4.setImage(img_1_4_kb);
				noname2(img_1_4);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_1_4);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_1_4_kb.toString();
				last = img_1_4;
			}else{
				twoPoint = img_1_4_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked15(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_1_5.getImage() == IMGKB)&&(img_1_5.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_1_5);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_1_5.setImage(IMGKB);
				noname2(img_1_5);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_1_5.setImage(img_1_5_kb);
				noname2(img_1_5);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_1_5);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_1_5_kb.toString();
				last = img_1_5;
			}else{
				twoPoint = img_1_5_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked20(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_2_0.getImage() == IMGKB)&&(img_2_0.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_2_0);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_2_0.setImage(IMGKB);
				noname2(img_2_0);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_2_0.setImage(img_2_0_kb);
				noname2(img_2_0);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_2_0);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_2_0_kb.toString();
				last = img_2_0;
			}else{
				twoPoint = img_2_0_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked21(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_2_1.getImage() == IMGKB)&&(img_2_1.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_2_1);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_2_1.setImage(IMGKB);
				noname2(img_2_1);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_2_1.setImage(img_2_1_kb);
				noname2(img_2_1);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_2_1);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_2_1_kb.toString();
				last = img_2_1;
			}else{
				twoPoint = img_2_1_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked22(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_2_2.getImage() == IMGKB)&&(img_2_2.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_2_2);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_2_2.setImage(IMGKB);
				noname2(img_2_2);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_2_2.setImage(img_2_2_kb);
				noname2(img_2_2);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_2_2);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_2_2_kb.toString();
				last = img_2_2;
			}else{
				twoPoint = img_2_2_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked23(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_2_3.getImage() == IMGKB)&&(img_2_3.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_2_3);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_2_3.setImage(IMGKB);
				noname2(img_2_3);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_2_3.setImage(img_2_3_kb);
				noname2(img_2_3);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_2_3);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_2_3_kb.toString();
				last = img_2_3;
			}else{
				twoPoint = img_2_3_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked24(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_2_4.getImage() == IMGKB)&&(img_2_4.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_2_4);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_2_4.setImage(IMGKB);
				noname2(img_2_4);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_2_4.setImage(img_2_4_kb);
				noname2(img_2_4);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_2_4);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_2_4_kb.toString();
				last = img_2_4;
			}else{
				twoPoint = img_2_4_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked25(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_2_5.getImage() == IMGKB)&&(img_2_5.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_2_5);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_2_5.setImage(IMGKB);
				noname2(img_2_5);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_2_5.setImage(img_2_5_kb);
				noname2(img_2_5);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_2_5);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_2_5_kb.toString();
				last = img_2_5;
			}else{
				twoPoint = img_2_5_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked30(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_3_0.getImage() == IMGKB)&&(img_3_0.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_3_0);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_3_0.setImage(IMGKB);
				noname2(img_3_0);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_3_0.setImage(img_3_0_kb);
				noname2(img_3_0);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_3_0);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_3_0_kb.toString();
				last = img_3_0;
			}else{
				twoPoint = img_3_0_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked31(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_3_1.getImage() == IMGKB)&&(img_3_1.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_3_1);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_3_1.setImage(IMGKB);
				noname2(img_3_1);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_3_1.setImage(img_3_1_kb);
				noname2(img_3_1);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_3_1);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_3_1_kb.toString();
				last = img_3_1;
			}else{
				twoPoint = img_3_1_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked32(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_3_2.getImage() == IMGKB)&&(img_3_2.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_3_2);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_3_2.setImage(IMGKB);
				noname2(img_3_2);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_3_2.setImage(img_3_2_kb);
				noname2(img_3_2);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_3_2);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_3_2_kb.toString();
				last = img_3_2;
			}else{
				twoPoint = img_3_2_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked33(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_3_3.getImage() == IMGKB)&&(img_3_3.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_3_3);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_3_3.setImage(IMGKB);
				noname2(img_3_3);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_3_3.setImage(img_3_3_kb);
				noname2(img_3_3);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_3_3);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_3_3_kb.toString();
				last = img_3_3;
			}else{
				twoPoint = img_3_3_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked34(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_3_4.getImage() == IMGKB)&&(img_3_4.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_3_4);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_3_4.setImage(IMGKB);
				noname2(img_3_4);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_3_4.setImage(img_3_4_kb);
				noname2(img_3_4);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_3_4);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_3_4_kb.toString();
				last = img_3_4;
			}else{
				twoPoint = img_3_4_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked35(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_3_5.getImage() == IMGKB)&&(img_3_5.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_3_5);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_3_5.setImage(IMGKB);
				noname2(img_3_5);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_3_5.setImage(img_3_5_kb);
				noname2(img_3_5);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_3_5);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_3_5_kb.toString();
				last = img_3_5;
			}else{
				twoPoint = img_3_5_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked40(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_4_0.getImage() == IMGKB)&&(img_4_0.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_4_0);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_4_0.setImage(IMGKB);
				noname2(img_4_0);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_4_0.setImage(img_4_0_kb);
				noname2(img_4_0);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_4_0);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_4_0_kb.toString();
				last = img_4_0;
			}else{
				twoPoint = img_4_0_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked41(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_4_1.getImage() == IMGKB)&&(img_4_1.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_4_1);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_4_1.setImage(IMGKB);
				noname2(img_4_1);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_4_1.setImage(img_4_1_kb);
				noname2(img_4_1);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_4_1);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_4_1_kb.toString();
				last = img_4_1;
			}else{
				twoPoint = img_4_1_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked42(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_4_2.getImage() == IMGKB)&&(img_4_2.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_4_2);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_4_2.setImage(IMGKB);
				noname2(img_4_2);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_4_2.setImage(img_4_2_kb);
				noname2(img_4_2);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_4_2);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_4_2_kb.toString();
				last = img_4_2;
			}else{
				twoPoint = img_4_2_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked43(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_4_3.getImage() == IMGKB)&&(img_4_3.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_4_3);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_4_3.setImage(IMGKB);
				noname2(img_4_3);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_4_3.setImage(img_4_3_kb);
				noname2(img_4_3);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_4_3);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_4_3_kb.toString();
				last = img_4_3;
			}else{
				twoPoint = img_4_3_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked44(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_4_4.getImage() == IMGKB)&&(img_4_4.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_4_4);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_4_4.setImage(IMGKB);
				noname2(img_4_4);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_4_4.setImage(img_4_4_kb);
				noname2(img_4_4);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_4_4);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_4_4_kb.toString();
				last = img_4_4;
			}else{
				twoPoint = img_4_4_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}
	@FXML
	private void onClicked45(MouseEvent event) throws IOException{
		if((event.getButton().toString() == "PRIMARY")&&(img_3_5.getImage() == IMGKB)&&(img_4_5.getOpacity() == 1)&&(timeDead == 0)){
			EventHandler<ActionEvent> backToLeave = e -> {
				noname1(img_4_5);
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_4_5.setImage(IMGKB);
				noname2(img_4_5);
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_4_5.setImage(img_4_5_kb);
				noname2(img_4_5);
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				noname1(img_4_5);
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1200),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_4_5_kb.toString();
				last = img_4_5;
			}else{
				twoPoint = img_4_5_kb.toString();
				if(onePoint.equals(twoPoint)){
					noname3();
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						noname1(last);
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						noname2(last);
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1200),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1200),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					noname4();
				}
			}
		}
	}

	@FXML
	private void beganOnClicked(ActionEvent event){
		img_0_0.setOpacity(1);
		img_0_1.setOpacity(1);
		img_0_2.setOpacity(1);
		img_0_3.setOpacity(1);
		img_0_4.setOpacity(1);
		img_0_5.setOpacity(1);
		img_1_0.setOpacity(1);
		img_1_1.setOpacity(1);
		img_1_2.setOpacity(1);
		img_1_3.setOpacity(1);
		img_1_4.setOpacity(1);
		img_1_5.setOpacity(1);
		img_2_0.setOpacity(1);
		img_2_1.setOpacity(1);
		img_2_2.setOpacity(1);
		img_2_3.setOpacity(1);
		img_2_4.setOpacity(1);
		img_2_5.setOpacity(1);
		img_3_0.setOpacity(1);
		img_3_1.setOpacity(1);
		img_3_2.setOpacity(1);
		img_3_3.setOpacity(1);
		img_3_4.setOpacity(1);
		img_3_5.setOpacity(1);
		img_4_0.setOpacity(1);
		img_4_1.setOpacity(1);
		img_4_2.setOpacity(1);
		img_4_3.setOpacity(1);
		img_4_4.setOpacity(1);
		img_4_5.setOpacity(1);

		img_0_0.setImage(IMGKB);
		img_0_1.setImage(IMGKB);
		img_0_2.setImage(IMGKB);
		img_0_3.setImage(IMGKB);
		img_0_4.setImage(IMGKB);
		img_0_5.setImage(IMGKB);
		img_1_0.setImage(IMGKB);
		img_1_1.setImage(IMGKB);
		img_1_2.setImage(IMGKB);
		img_1_3.setImage(IMGKB);
		img_1_4.setImage(IMGKB);
		img_1_5.setImage(IMGKB);
		img_2_0.setImage(IMGKB);
		img_2_1.setImage(IMGKB);
		img_2_2.setImage(IMGKB);
		img_2_3.setImage(IMGKB);
		img_2_4.setImage(IMGKB);
		img_2_5.setImage(IMGKB);
		img_3_0.setImage(IMGKB);
		img_3_1.setImage(IMGKB);
		img_3_2.setImage(IMGKB);
		img_3_3.setImage(IMGKB);
		img_3_4.setImage(IMGKB);
		img_3_5.setImage(IMGKB);
		img_4_0.setImage(IMGKB);
		img_4_1.setImage(IMGKB);
		img_4_2.setImage(IMGKB);
		img_4_3.setImage(IMGKB);
		img_4_4.setImage(IMGKB);
		img_4_5.setImage(IMGKB);
		
		img_0_0_kb = randomChoose();
		img_0_1_kb = randomChoose();
		img_0_2_kb = randomChoose();
		img_0_3_kb = randomChoose();
		img_0_4_kb = randomChoose();
		img_0_5_kb = randomChoose();
		img_1_0_kb = randomChoose();
		img_1_1_kb = randomChoose();
		img_1_2_kb = randomChoose();
		img_1_3_kb = randomChoose();
		img_1_4_kb = randomChoose();
		img_1_5_kb = randomChoose();
		img_2_0_kb = randomChoose();
		img_2_1_kb = randomChoose();
		img_2_2_kb = randomChoose();
		img_2_3_kb = randomChoose();
		img_2_4_kb = randomChoose();
		img_2_5_kb = randomChoose();
		img_3_0_kb = randomChoose();
		img_3_1_kb = randomChoose();
		img_3_2_kb = randomChoose();
		img_3_3_kb = randomChoose();
		img_3_4_kb = randomChoose();
		img_3_5_kb = randomChoose();
		img_4_0_kb = randomChoose();
		img_4_1_kb = randomChoose();
		img_4_2_kb = randomChoose();
		img_4_3_kb = randomChoose();
		img_4_4_kb = randomChoose();
		img_4_5_kb = randomChoose();

		IMGANUM = 0;
		IMGBNUM = 0;
		IMGCNUM = 0;
		IMGDNUM = 0;
		IMGENUM = 0;

		score = 0;
		reward = 0;
		punish = 0;
		scoreShow.setText("0");

		last = null;
		onePoint = null;
		twoPoint = null;
		timeDead = 0;
		cardEndChoose = 0;
		timeMinute.setText("1");
		timeSecond.setText("50");
		EventHandler<ActionEvent> timeEnd = e -> {
			int time = Integer.parseInt(timeSecond.getText());
			int timeChange = Integer.parseInt(timeMinute.getText());
			if(time == 0 && timeChange == 1){
				timeMinute.setText("0");
				timeSecond.setText("59");
			}else{
				time--;
				if(time >= 10){
					timeSecond.setText(""+time);
				}else{
					timeSecond.setText("0"+time);
				}
			}

			if(time == 0 && timeChange == 0){
				timeDead = 1;
				Parent start;
				try{
					start = FXMLLoader.load(getClass().getResource("GameOver_Fail.fxml"));
					Scene scene = new Scene(start,600,360);
					failStage.setTitle("Fail!");
					failStage.setScene(scene);
					failStage.setResizable(false);
					failStage.show();
				}catch(IOException e1){
					// null
				}
			}
		};
		KeyFrame kf_timeEnd = new KeyFrame(Duration.millis(1000),timeEnd);
		if(tl_timeEnd.getKeyFrames().isEmpty()){
			tl_timeEnd.getKeyFrames().add(kf_timeEnd);
			tl_timeEnd.setCycleCount(60);
		}
		tl_timeEnd.play();
		failStage.close();
	}
}