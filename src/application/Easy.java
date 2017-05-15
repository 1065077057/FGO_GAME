// 简单模式

package application;

import java.io.IOException;
import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	
	/*
	 * Easy 模式，12张图片，每张图片对应的 MouseEvent 监视器
	 * 控制模式：
	 * 1.只有鼠标左击有效
	 * 2.只有点击透明度为0的图片有效
	 * 3.完成核心需求  “翻牌后是否匹配”
	 * 4.
	 */
	@FXML
	protected void onClicked00(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_0_0.getImage() == IMGKB)){
			img_0_0.setImage(img_0_0_kb);
			FadeTransition ft = new FadeTransition(Duration.millis(900),img_0_0); 
			ft.setFromValue(0);
			ft.setToValue(1);
			ft.setCycleCount(1);
			ft.setAutoReverse(false);
			ft.play();

			if(onePoint == null){
				onePoint = img_0_0.getImage().toString();
				last = img_0_0;
			}else{
				twoPoint = img_0_0.getImage().toString();
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
				}else{
					img_0_0.setImage(IMGKB);
					last.setImage(IMGKB);
					FadeTransition ftimg = new FadeTransition(Duration.millis(900),img_0_0);
					ftimg.setFromValue(0);
					ftimg.setToValue(1);
					ftimg.setCycleCount(1);
					ftimg.setAutoReverse(false);
					ftimg.play();
					FadeTransition ftlast = new FadeTransition(Duration.millis(900),last);
					ftlast.setFromValue(0);
					ftlast.setToValue(1);
					ftlast.setCycleCount(1);
					ftlast.setAutoReverse(false);
					ftlast.play();
					last = null;
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
				}
			}
		}
	}
	@FXML
	protected void onClicked01(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_0_1.getImage() == IMGKB)){
			img_0_1.setImage(img_0_1_kb);
			FadeTransition ft = new FadeTransition(Duration.millis(900),img_0_1);
			ft.setFromValue(0);
			ft.setToValue(1);
			ft.setCycleCount(1);
			ft.setAutoReverse(false);
			ft.play();
			
			if(onePoint == null){
				onePoint = img_0_1.getImage().toString();
				last = img_0_1;
			}else{
				twoPoint = img_0_1.getImage().toString();
				if(onePoint.equals(twoPoint)){
					last = null;
					onePoint = null;
					twoPoint = null;
				}else{
					img_0_1.setImage(IMGKB);
					last.setImage(IMGKB);
					FadeTransition ftimg = new FadeTransition(Duration.millis(900),img_0_1);
					ftimg.setFromValue(0);
					ftimg.setToValue(1);
					ftimg.setCycleCount(1);
					ftimg.setAutoReverse(false);
					ftimg.play();
					FadeTransition ftlast = new FadeTransition(Duration.millis(900),last);
					ftlast.setFromValue(0);
					ftlast.setToValue(1);
					ftlast.setCycleCount(1);
					ftlast.setAutoReverse(false);
					ftlast.play();
					last = null;
					onePoint = null;
					twoPoint = null;
				}
			}
		}
	}
	@FXML
	protected void onClicked02(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_0_2.getImage() == IMGKB)){
			img_0_2.setImage(img_0_2_kb);
			FadeTransition ft = new FadeTransition(Duration.millis(900),img_0_2);
			ft.setFromValue(0);
			ft.setToValue(1);
			ft.setCycleCount(1);
			ft.setAutoReverse(false);
			ft.play();
			if(onePoint == null){
				onePoint = img_0_2.getImage().toString();
				last = img_0_2;
			}else{
				twoPoint = img_0_2.getImage().toString();
				if(onePoint.equals(twoPoint)){
					last = null;
					onePoint = null;
					twoPoint = null;
				}else{
					img_0_2.setImage(IMGKB);
					last.setImage(IMGKB);
					FadeTransition ftimg = new FadeTransition(Duration.millis(900),img_0_2);
					ftimg.setFromValue(0);
					ftimg.setToValue(1);
					ftimg.setCycleCount(1);
					ftimg.setAutoReverse(false);
					ftimg.play();
					FadeTransition ftlast = new FadeTransition(Duration.millis(900),last);
					ftlast.setFromValue(0);
					ftlast.setToValue(1);
					ftlast.setCycleCount(1);
					ftlast.setAutoReverse(false);
					ftlast.play();
					last = null;
					onePoint = null;
					twoPoint = null;
				}
			}
		}
	}
	@FXML
	protected void onClicked03(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_0_3.getImage() == IMGKB)){
			img_0_3.setImage(img_0_3_kb);
			FadeTransition ft = new FadeTransition(Duration.millis(900),img_0_3);
			ft.setFromValue(0);
			ft.setToValue(1);
			ft.setCycleCount(1);
			ft.setAutoReverse(false);
			ft.play();
			if(onePoint == null){
				onePoint = img_0_3.getImage().toString();
				last = img_0_3;
			}else{
				twoPoint = img_0_3.getImage().toString();
				if(onePoint.equals(twoPoint)){
					last = null;
					onePoint = null;
					twoPoint = null;
				}else{
					img_0_3.setImage(IMGKB);
					last.setImage(IMGKB);
					FadeTransition ftimg = new FadeTransition(Duration.millis(900),img_0_3);
					ftimg.setFromValue(0);
					ftimg.setToValue(1);
					ftimg.setCycleCount(1);
					ftimg.setAutoReverse(false);
					ftimg.play();
					FadeTransition ftlast = new FadeTransition(Duration.millis(900),last);
					ftlast.setFromValue(0);
					ftlast.setToValue(1);
					ftlast.setCycleCount(1);
					ftlast.setAutoReverse(false);
					ftlast.play();
					last = null;
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