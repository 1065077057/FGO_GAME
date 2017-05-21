// 简单模式

package application;

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
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Easy extends Main{
	// 三个 final static Image 对应3种不同的图片
	private final static Image IMGA = new Image("application/images/059E.png");
	private final static Image IMGB = new Image("application/images/060E.png");
	private final static Image IMGC = new Image("application/images/068E.png");
	private final static Image IMGKB = new Image("application/images/KB.png");
	// 图片数量计数器，辅助 randomChoose 方法随机分配图片，控制图片数量
	private static int IMGANUM = 0;
	private static int IMGBNUM = 0;
	private static int IMGCNUM = 0;
	// 临时控制节点，完成  “翻牌后是否匹配” 需求
	private String onePoint = null;
	private String twoPoint = null;
	private ImageView last = null;
	// 关于分数的部分，记分变量、临时储存惩罚或奖励机制的变量
	private int score = 0;
	private int reward = 0;
	private int punish = 0;
	
	private Image img_0_0_kb;
	private Image img_0_1_kb;
	private Image img_0_2_kb;
	private Image img_0_3_kb;
	private Image img_1_0_kb;
	private Image img_1_1_kb;
	private Image img_1_2_kb;
	private Image img_1_3_kb;
	private Image img_2_0_kb;
	private Image img_2_1_kb;
	private Image img_2_2_kb;
	private Image img_2_3_kb;
	
	// Easy.fxml 中映射的数据域
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
	private ImageView img_1_0;
	@FXML
	private ImageView img_1_1;
	@FXML
	private ImageView img_1_2;
	@FXML
	private ImageView img_1_3;
	@FXML
	private ImageView img_2_0;
	@FXML
	private ImageView img_2_1;
	@FXML
	private ImageView img_2_2;
	@FXML
	private ImageView img_2_3;
	@FXML
	MenuItem reStart = new MenuItem();
	@FXML
	MenuItem chooseLeave = new MenuItem();
	@FXML
	MenuItem set = new MenuItem();
	@FXML
	MenuItem record = new MenuItem();
	@FXML
	MenuItem help = new MenuItem();
	@FXML
	MenuItem about = new MenuItem();
	@FXML
	Text scoreShow = new Text();
	
	// 随机分配图片方法
	public static Image randomChoose(){
		Random random = new Random();
		while(true){
			int choose = random.nextInt(3);
			switch(choose){
				case 0: if(IMGANUM < 4){
							IMGANUM++;
							return IMGA;
						};break;
				case 1: if(IMGBNUM < 4){
							IMGBNUM++;
							return IMGB;
						};break;
				case 2: if(IMGCNUM < 4){
							IMGCNUM++;
							return IMGC;
						};break;
			}
		}
	}
	// 惩罚与奖励特效 Timeline 部分
	EventHandler<ActionEvent> flowTextForGood = e -> {
		Text text = new Text();
		Font textFont = new Font("Times New Roman",68);
		text.setText("GOOD!");
		text.setFont(textFont);
		text.setX(120);
		text.setY(1200);
		
	};
	
	/*
	 * Easy 模式，12张图片，每张图片对应的 MouseEvent 监视器
	 * 控制模式：
	 * 1.只有鼠标左击有效
	 * 2.只有点击透明度为0的图片有效
	 * 3.完成核心需求  “翻牌后是否匹配”
	 * 4.
	 */
	
	/*
	 * 关于 Timeline
	 * 在监视器中使用了几个 EventHandler 对象，主要添加在 KeyFrame 中
	 * 完成 Timeline 的功能。
	 * 之前单纯使用 FadeTransition 能改变透明度，但是完成不了连续动画，
	 * 这样为了展现完整的翻牌效果（不是翻牌，使用更改透明度，感觉更好）
	 * 其中的一些命名的效果解释：
	 * backToLeave	卡背消失
	 * cardToOpen	卡片正面显示
	 * backToOpen	卡背显示
	 * cardToLeave	卡片正面消失
	 * KeyFrame 命名一样，只是前面加上 kyOf
	 * Timeline 命名同，表示 A状态 To B状态
	 * last 的命名规则：为了控制上一个 ImageView（两张牌不匹配的时候），所有变量
	 * 前加上 last 即可
	 * 这样封装成帧后在 Timeline 中表现成动画
	 * */
	@FXML
	protected void onClicked00(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_0_0.getImage() == IMGKB)&&(img_0_0.getOpacity() == 1)){
			EventHandler<ActionEvent> backToLeave = e -> {
				FadeTransition ft = new FadeTransition(Duration.millis(450),img_0_0); 
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.setCycleCount(1);
				ft.setAutoReverse(false);
				ft.play();
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_0_0.setImage(IMGKB);
				FadeTransition ft = new FadeTransition(Duration.millis(450),img_0_0); 
				ft.setFromValue(0);
				ft.setToValue(1);
				ft.setCycleCount(1);
				ft.setAutoReverse(false);
				ft.play();
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_0_0.setImage(img_0_0_kb);
				FadeTransition ft = new FadeTransition(Duration.millis(450),img_0_0); 
				ft.setFromValue(0);
				ft.setToValue(1);
				ft.setCycleCount(1);
				ft.setAutoReverse(false);
				ft.play();
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				FadeTransition ft = new FadeTransition(Duration.millis(450),img_0_0); 
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.setCycleCount(1);
				ft.setAutoReverse(false);
				ft.play();
			};

			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1800),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();

			if(onePoint == null){
				onePoint = img_0_0_kb.toString();
				last = img_0_0;
			}else{
				twoPoint = img_0_0_kb.toString();
				if(onePoint.equals(twoPoint)){
					last = null;
					onePoint = null;
					twoPoint = null;
					score += 40;
					reward++;
					punish = 0;
					switch(reward){
						case 3: score += 60;break;
						case 5: score += 120;break;
						case 9: score += 380;break;
					}
					if(reward == 9){
						reward = 0;
					}
					scoreShow.setText(""+score);
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						FadeTransition ft = new FadeTransition(Duration.millis(450),last); 
						ft.setFromValue(1);
						ft.setToValue(0);
						ft.setCycleCount(1);
						ft.setAutoReverse(false);
						ft.play();
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						FadeTransition ft = new FadeTransition(Duration.millis(450),last); 
						ft.setFromValue(0);
						ft.setToValue(1);
						ft.setCycleCount(1);
						ft.setAutoReverse(false);
						ft.play();
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1800),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1800),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					
					/*
					 * 留下这句代码是会出 bug 的，暂时标记
					 * last = null;
					 */
					onePoint = null;
					twoPoint = null;
					punish++;
					reward = 0;
					switch(punish){
						case 3: if(score >= 50){
									score -= 50;
								}else{
									score = 0;
								}break;
						case 5: if(score >= 120){
									score -= 120;
								}else{
									score = 0;
								}break;
					}
					if(punish == 5){
						punish = 0;
					}
					scoreShow.setText(""+score);
				}
			}
		}
	}
	@FXML
	protected void onClicked01(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_0_1.getImage() == IMGKB)&&(img_0_1.getOpacity() == 1)){
			EventHandler<ActionEvent> backToLeave = e -> {
				FadeTransition ft = new FadeTransition(Duration.millis(450),img_0_1); 
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.setCycleCount(1);
				ft.setAutoReverse(false);
				ft.play();
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_0_1.setImage(IMGKB);
				FadeTransition ft = new FadeTransition(Duration.millis(450),img_0_1); 
				ft.setFromValue(0);
				ft.setToValue(1);
				ft.setCycleCount(1);
				ft.setAutoReverse(false);
				ft.play();
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_0_1.setImage(img_0_1_kb);
				FadeTransition ft = new FadeTransition(Duration.millis(450),img_0_1); 
				ft.setFromValue(0);
				ft.setToValue(1);
				ft.setCycleCount(1);
				ft.setAutoReverse(false);
				ft.play();
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				FadeTransition ft = new FadeTransition(Duration.millis(450),img_0_1); 
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.setCycleCount(1);
				ft.setAutoReverse(false);
				ft.play();
			};
			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1800),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();
			
			if(onePoint == null){
				onePoint = img_0_1_kb.toString();
				last = img_0_1;
			}else{
				twoPoint = img_0_1_kb.toString();
				if(onePoint.equals(twoPoint)){
					last = null;
					onePoint = null;
					twoPoint = null;
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						FadeTransition ft = new FadeTransition(Duration.millis(450),last); 
						ft.setFromValue(1);
						ft.setToValue(0);
						ft.setCycleCount(1);
						ft.setAutoReverse(false);
						ft.play();
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						FadeTransition ft = new FadeTransition(Duration.millis(450),last); 
						ft.setFromValue(0);
						ft.setToValue(1);
						ft.setCycleCount(1);
						ft.setAutoReverse(false);
						ft.play();
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1800),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1800),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					/*
					 * 留下这句代码是会出 bug 的，暂时标记
					 * last = null;
					 */
					onePoint = null;
					twoPoint = null;
				}
			}
		}
	}
	@FXML
	protected void onClicked02(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_0_2.getImage() == IMGKB)&&(img_0_2.getOpacity() == 1)){
			EventHandler<ActionEvent> backToLeave = e -> {
				FadeTransition ft = new FadeTransition(Duration.millis(450),img_0_2); 
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.setCycleCount(1);
				ft.setAutoReverse(false);
				ft.play();
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_0_2.setImage(IMGKB);
				FadeTransition ft = new FadeTransition(Duration.millis(450),img_0_2); 
				ft.setFromValue(0);
				ft.setToValue(1);
				ft.setCycleCount(1);
				ft.setAutoReverse(false);
				ft.play();
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_0_2.setImage(img_0_2_kb);
				FadeTransition ft = new FadeTransition(Duration.millis(450),img_0_2); 
				ft.setFromValue(0);
				ft.setToValue(1);
				ft.setCycleCount(1);
				ft.setAutoReverse(false);
				ft.play();
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				FadeTransition ft = new FadeTransition(Duration.millis(450),img_0_2); 
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.setCycleCount(1);
				ft.setAutoReverse(false);
				ft.play();
			};
			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1800),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();
			if(onePoint == null){
				onePoint = img_0_2_kb.toString();
				last = img_0_2;
			}else{
				twoPoint = img_0_2_kb.toString();
				if(onePoint.equals(twoPoint)){
					last = null;
					onePoint = null;
					twoPoint = null;
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						FadeTransition ft = new FadeTransition(Duration.millis(450),last); 
						ft.setFromValue(1);
						ft.setToValue(0);
						ft.setCycleCount(1);
						ft.setAutoReverse(false);
						ft.play();
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						FadeTransition ft = new FadeTransition(Duration.millis(450),last); 
						ft.setFromValue(0);
						ft.setToValue(1);
						ft.setCycleCount(1);
						ft.setAutoReverse(false);
						ft.play();
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1800),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1800),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					/*
					 * 留下这句代码是会出 bug 的，暂时标记
					 * last = null;
					 */
					onePoint = null;
					twoPoint = null;
				}
			}
		}
	}
	@FXML
	protected void onClicked03(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_0_3.getImage() == IMGKB)&&(img_0_3.getOpacity() == 1)){
			EventHandler<ActionEvent> backToLeave = e -> {
				FadeTransition ft = new FadeTransition(Duration.millis(450),img_0_3); 
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.setCycleCount(1);
				ft.setAutoReverse(false);
				ft.play();
			};
			EventHandler<ActionEvent> backToOpen = e -> {
				img_0_3.setImage(IMGKB);
				FadeTransition ft = new FadeTransition(Duration.millis(450),img_0_3); 
				ft.setFromValue(0);
				ft.setToValue(1);
				ft.setCycleCount(1);
				ft.setAutoReverse(false);
				ft.play();
			};
			EventHandler<ActionEvent> cardToOpen = e -> {
				img_0_3.setImage(img_0_3_kb);
				FadeTransition ft = new FadeTransition(Duration.millis(450),img_0_3); 
				ft.setFromValue(0);
				ft.setToValue(1);
				ft.setCycleCount(1);
				ft.setAutoReverse(false);
				ft.play();
			};
			EventHandler<ActionEvent> cardToLeave = e -> {
				FadeTransition ft = new FadeTransition(Duration.millis(450),img_0_3); 
				ft.setFromValue(1);
				ft.setToValue(0);
				ft.setCycleCount(1);
				ft.setAutoReverse(false);
				ft.play();
			};
			KeyFrame kfOfCardToLeave = new KeyFrame(Duration.millis(1),cardToLeave);
			KeyFrame kfOfCardToOpen = new KeyFrame(Duration.millis(450),cardToOpen);
			KeyFrame kfOfBackToLeave = new KeyFrame(Duration.millis(1),backToLeave);
			KeyFrame kfOfBackToOpen = new KeyFrame(Duration.millis(1800),backToOpen);
			Timeline backToCard = new Timeline(kfOfBackToLeave,kfOfCardToOpen);
			Timeline cardToBack = new Timeline(kfOfCardToLeave,kfOfBackToOpen);
			
			backToCard.play();
			if(onePoint == null){
				onePoint = img_0_3_kb.toString();
				last = img_0_3;
			}else{
				twoPoint = img_0_3_kb.toString();
				if(onePoint.equals(twoPoint)){
					last = null;
					onePoint = null;
					twoPoint = null;
				}else{
					EventHandler<ActionEvent> lastCardToLeave = e -> {
						FadeTransition ft = new FadeTransition(Duration.millis(450),last); 
						ft.setFromValue(1);
						ft.setToValue(0);
						ft.setCycleCount(1);
						ft.setAutoReverse(false);
						ft.play();
					};
					EventHandler<ActionEvent> lastBackToOpen = e -> {
						last.setImage(IMGKB);
						FadeTransition ft = new FadeTransition(Duration.millis(450),last); 
						ft.setFromValue(0);
						ft.setToValue(1);
						ft.setCycleCount(1);
						ft.setAutoReverse(false);
						ft.play();
					};
					KeyFrame kfOfLastBackToOpen = new KeyFrame(Duration.millis(1800),lastBackToOpen);
					KeyFrame kfOfLastCardToLeave = new KeyFrame(Duration.millis(1800),lastCardToLeave);
					Timeline lastCardToBack = new Timeline(kfOfLastCardToLeave,kfOfLastBackToOpen);
					
					cardToBack.play();
					lastCardToBack.play();
					/*
					 * 留下这句代码是会出 bug 的，暂时标记
					 * last = null;
					 */
					onePoint = null;
					twoPoint = null;
				}
			}
		}
	}
	@FXML
	protected void onClicked10(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_1_0.getOpacity() == 0)){
			img_1_0.setOpacity(1);
		}
	}
	@FXML
	protected void onClicked11(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_1_1.getOpacity() == 0)){
			img_1_1.setOpacity(1);
		}
	}
	@FXML
	protected void onClicked12(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_1_2.getOpacity() == 0)){
			img_1_2.setOpacity(1);
		}
	}
	@FXML
	protected void onClicked13(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_1_3.getOpacity() == 0)){
			img_1_3.setOpacity(1);
		}
	}
	@FXML
	protected void onClicked20(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_2_0.getOpacity() == 0)){
			img_2_0.setOpacity(1);
		}
	}
	@FXML
	protected void onClicked21(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_2_1.getOpacity() == 0)){
			img_2_1.setOpacity(1);
		}
	}
	@FXML
	protected void onClicked22(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_2_2.getOpacity() == 0)){
			img_2_2.setOpacity(1);
		}
	}
	@FXML
	protected void onClicked23(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_2_3.getOpacity() == 0)){
			img_2_3.setOpacity(1);
		}
	}
	// 重新开始一局 Easy 难度的游戏
	@FXML
	protected void beganOnClicked(ActionEvent event){
		/*
		 * 每次重新开始都要保持透明度为 100% 否则
		 * 会出现“没有还原”的bug
		 */
		img_0_0.setOpacity(1);
		img_0_1.setOpacity(1);
		img_0_2.setOpacity(1);
		img_0_3.setOpacity(1);
		img_1_0.setOpacity(1);
		img_1_1.setOpacity(1);
		img_1_2.setOpacity(1);
		img_1_3.setOpacity(1);
		img_2_0.setOpacity(1);
		img_2_1.setOpacity(1);
		img_2_2.setOpacity(1);
		img_2_3.setOpacity(1);

		/*
		 * 不能使用 scene builder 上确定的 Image 地址
		 * 是不一样的，所以这里使用了初始化。
		 */
		img_0_0.setImage(IMGKB);
		img_0_1.setImage(IMGKB);
		img_0_2.setImage(IMGKB);
		img_0_3.setImage(IMGKB);
		img_1_0.setImage(IMGKB);
		img_1_1.setImage(IMGKB);
		img_1_2.setImage(IMGKB);
		img_1_3.setImage(IMGKB);
		img_2_0.setImage(IMGKB);
		img_2_1.setImage(IMGKB);
		img_2_2.setImage(IMGKB);
		img_2_3.setImage(IMGKB);
		
		// 随机分牌
		img_0_0_kb = randomChoose();
		img_0_1_kb = randomChoose();
		img_0_2_kb = randomChoose();
		img_0_3_kb = randomChoose();
		img_1_0_kb = randomChoose();
		img_1_1_kb = randomChoose();
		img_1_2_kb = randomChoose();
		img_1_3_kb = randomChoose();
		img_2_0_kb = randomChoose();
		img_2_1_kb = randomChoose();
		img_2_2_kb = randomChoose();
		img_2_3_kb = randomChoose();
		// 重置计数器，防止死循环
		IMGANUM = 0;
		IMGBNUM = 0;
		IMGCNUM = 0;
		// 重置分数以及奖惩控制
		score = 0;
		reward = 0;
		punish = 0;
		scoreShow.setText("0");
	}
	
	// MenuBar 各个 MenuItem 监听器
	@FXML
	private void onReStart(ActionEvent event) throws IOException{
		Parent start = FXMLLoader.load(getClass().getResource("Start.fxml"));
		
		Scene scene = new Scene(start,800,900);
		thisStage.setScene(scene);
		thisStage.show();
	}
	@FXML
	private void onChooseLeave(ActionEvent event){
//		Parent start = FXMLLoader.load(getClass().getResource("ChooseLeave.fxml"));
	}
	@FXML
	private void onSet(ActionEvent event){
		
	}
	@FXML
	private void onRecord(ActionEvent event){
		
	}
	@FXML
	private void onHelp(ActionEvent event) throws IOException{
		Parent help = FXMLLoader.load(getClass().getResource("Help.fxml"));
		
		Scene scene = new Scene(help,300,300);
		Stage stage = new Stage();
		stage.setTitle("FGO Game Help");
		stage.setScene(scene);
		stage.showAndWait();
	}
	@FXML
	private void onAbout(ActionEvent event) throws IOException{
		Parent about = FXMLLoader.load(getClass().getResource("About.fxml"));
		Scene scene = new Scene(about,300,300);
		Stage stage = new Stage();
		stage.setTitle("FGO Game About");
		stage.setScene(scene);
		stage.show();
	}
}