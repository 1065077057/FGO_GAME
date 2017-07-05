// 简单模式

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
import javafx.stage.Stage;
import javafx.util.Duration;

public class Easy extends Main{
	// 这里一定要用 final 因为是跟动画有关。只有用final才能固定。
	protected final Timeline tl_timeEnd = new Timeline();
	// 定义 static Stage 用于关闭开始窗口打开新的窗口
	protected static Stage returnStage = new Stage();
	protected static Stage st_chooseLeave = new Stage();
	// 三个 final static Image 对应3种不同的图片
	protected final static Image IMGA = new Image("application/images/059E.png");
	protected final static Image IMGB = new Image("application/images/060E.png");
	protected final static Image IMGC = new Image("application/images/068E.png");
	protected final static Image IMGKB = new Image("application/images/KB.png");
	// 图片数量计数器，辅助 randomChoose 方法随机分配图片，控制图片数量
	private static int IMGANUM = 0;
	private static int IMGBNUM = 0;
	private static int IMGCNUM = 0;
	// 临时控制节点，完成  “翻牌后是否匹配” 需求
	private String onePoint = null;
	private String twoPoint = null;
	private ImageView last = null;
	// 关于分数的部分，记分变量、临时储存惩罚或奖励机制的变量
	private static int score = 0;
	private int reward = 0;
	private int punish = 0;
	// 游戏结束控制，牌全部翻完的情况与时间结束的情况以及新的窗口
	private int cardEndChoose = 0;
	private int timeDead = 0;
	
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
	
	// 随机分配图片方法
	private static Image randomChoose(){
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
	
	// 写入记录方法。每次游戏结束，都更新记录。游戏结束时，读取记录并且比较
	private static void write_record() throws IOException{
		// 加载记录
		int[] record_score = new int[3];
		FileInputStream input = new FileInputStream("src/application/record/record_easy.bat");
		for(int i = 0;i < 3;i++){
			record_score[i] = input.read();
		}
		input.close();
		// 分数比较筛选算法
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
		// 写入记录
		FileOutputStream output = new FileOutputStream("src/application/record/record_easy.bat");
		for(int i = 0;i < 3;i++){
			output.write(record_score[i]);
			System.out.println(record_score[i]);
		} 
		output.close();
	}
	// 游戏胜利显示方法 以及游戏胜利里面映射的数据域与监听器
	protected void gameWin() throws IOException{
		Parent start = FXMLLoader.load(getClass().getResource("GameOver_Win.fxml"));
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
	/* 
	 * 为了保证时间的变化是动态的，使用中间变量，在时间停止的时候记录
	 * 这样可以保证时间变化，并且可以获得最终值（否则无法获得最终停止的时间）
	 */
	private static int staticTime;
	@FXML
	private void beganCount(ActionEvent event) throws IOException{
		// 加载记录
		int[] record_score = new int[3];
		FileInputStream input = new FileInputStream("src/application/record/record_easy.bat");
		for(int i = 0;i < 3;i++){
			record_score[i] = input.read();
		}
		input.close();
		
		count.setOpacity(0);
		finalScore.setText(""+(score+staticTime));
		finalTime.setText(""+(60-staticTime));
		if(Integer.parseInt(finalScore.getText()) > record_score[2]){
			newRecord.setOpacity(1);
		}
		write_record();
	}
	
	/*
	 * 奖励惩罚文字特效测试部分完成。
	 * 尝试中间暂停失败，如果只使用 Timeline 是无法暂停的，尝试想法：
	 * 1.如果在 EventHandler 中嵌套 EventHandler 也是像制作分支一样，双方不在
	 *   同一时间线，无法得到暂停效果。
	 * 2.设置3条时间线，分别是第一部分与第二部分与end。第一二部分时差产生暂停效
	 *   果。实际上由于使用了重复，根本无法设置时差。Duration.millis(1) 都要是
	 *   1。
	 * 可行方法：
	 * 	 使用 PathTransition。但是由于 Scene 使用的是 HBox 与 VBox，不能直接加上
	 * 	 透明的 line。但是理论上这样是可行的。可以利用时差产生暂停。之后尝试。
	 * 目前效果实现是使用两条时间线，一条是出现字体并且向上移动。利用时差，在经过
	 * 3000ms 后 end 时间线开始运行。重置字体并且透明度归0。
	 * 注意：
	 * 下面部分是整体的代码，具体到使用只是对 Timeline 的设置，只有2行代码，调用
	 * 了重复次数方法与 play 方法。
	 * 
	 * 关于特效中命名问题，由于最初版本中是Text呈现的特效，所以为了方便，在改成ImageView
	 * 后仍然使用原名字
	 * */
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
		// 胜利！
		/*
		 * 测试，实际上 cardEndChoose 的值应该是当前难度的总卡片数
		 * */
		if(cardEndChoose == 12){
			// 同时初始化 cardEndChoose
			cardEndChoose = 0;
			tl_timeEnd.stop();
			// 获得最终时间数值
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
		/*
		 * 留下这句代码是会出 bug 的，暂时标记
		 * last = null;
		 */
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

			/*  测试特效使用，直接点击图片 0,0
				flowTextForGoodShow.setCycleCount(310);
				flowTextForGoodShow_end.setCycleCount(1);
				flowTextForGoodShow_end.play();
				flowTextForGoodShow.play();
			*/

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
	// 重新开始一局 Easy 难度的游戏
	@FXML
	private void beganOnClicked(ActionEvent event){
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
		last = null;
		onePoint = null;
		twoPoint = null;
		score = 0;
		reward = 0;
		punish = 0;
		scoreShow.setText("0");
		/* 
		 * 倒计时开始 
		 * 倒计时这一段算是使用了小技巧，根据官方文档的论述，解决了多重时间线的问题（属于
		 * 内存泄露）以后要注意这一方面。官方给出：fx中的动画是不能被垃圾回收的，所以要自
		 * 己控制。利用 final 等，最好将 timeline 等放在数据域中，保证不被内存泄露。
		 * */
		// 重置时间与时间结束控制
		timeDead = 0;
		cardEndChoose = 0;
		timeMinute.setText("1");
		timeSecond.setText("00");
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
			// time 的值测试使用，实际上应该是 0
			if(time == 0 && timeChange == 0){
				timeDead = 1;
				/*
				 * 还是需要单独完成，不能完全复用，方案：
				 * 尽量将fail或者win窗口当做跳板。参考成功与失败部分：
				 * 成功：返回标题，跳到了确认窗口，这样只用对 failStage 使用 close 方法
				 * 失败：在来一次，直接调用 重新开始 监听器，由于本质上调用的并不是 Easy.fxml
				 * 		 中的映射，所以无法找到，抛出异常。
				 * 综上，要么当做跳板，要么单独制作一部分。跳板制作一个确认窗口也是有好
				 * 处的。
				 * 不能直接使用 Main 的继承。因为 Easy 继承 Main 跟fail窗口无关
				 * */
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
	/*fail 窗口单独需要的监听器与数据域映射*/
	@FXML
	Button btOnceAgain = new Button();
	@FXML
	private void onceAgain(ActionEvent event) throws IOException{
		thisStage.setScene(thisStage.getScene());
		thisStage.setResizable(false);
		failStage.close();
		winStage.close();
	}
	
	// MenuBar 各个 MenuItem 监听器
	/*ReStart.fxml 数据域映射与监听器*/
	@FXML
	private void onReStart(ActionEvent event) throws IOException{
		Parent reStart = FXMLLoader.load(getClass().getResource("ReStart.fxml"));
		
		Scene returnScene = new Scene(reStart,480,140);
		returnStage.setScene(returnScene);
		returnStage.setTitle("返回标题");
		returnStage.setResizable(false);
		returnStage.show();
	}
	@FXML
	Button btDoReturn = new Button();
	@FXML
	Button btDontReturn = new Button();
	@FXML
	void doReturn(ActionEvent event) throws IOException{
		Parent start = FXMLLoader.load(getClass().getResource("Start.fxml"));
		
		Scene scene = new Scene(start,569,450);
		thisStage.setScene(scene);
		thisStage.show();
		thisStage.setResizable(false);
		returnStage.close();
		// 添加游戏结束的时候弹出窗口的操作 
		failStage.close();
		winStage.close();
	}
	@FXML
	void dontReturn(ActionEvent event){
		returnStage.close();
		// 添加游戏结束的时候弹出窗口的操作 
		failStage.close();
		winStage.close();
	}
	
	@FXML
	private void onChooseLeave(ActionEvent event) throws IOException{
		Parent start = FXMLLoader.load(getClass().getResource("ChooseLeave.fxml"));
		
		Scene scene = new Scene(start,450,460);
		st_chooseLeave.setScene(scene);
		st_chooseLeave.setTitle("Choose Leave");
		st_chooseLeave.setResizable(false);
		st_chooseLeave.show();
	}
	/*ReStart.fxml 数据域映射与监听器*/
	@FXML
	Button btChooseEasy = new Button();
	@FXML
	Button btChooseNormal = new Button();
	@FXML
	Button btChooseHard = new Button();
	@FXML
	Button btDontChoose = new Button();
	@FXML
	void chooseEasy(ActionEvent event) throws IOException{
		Parent easy = FXMLLoader.load(getClass().getResource("Easy.fxml"));
		
		Scene scene = new Scene(easy,950,900);
		thisStage.setScene(scene);
		st_chooseLeave.close();
	}
	@FXML
	void chooseNormal(ActionEvent event) throws IOException{
		Parent normal = FXMLLoader.load(getClass().getResource("Normal.fxml"));
		
		Scene scene = new Scene(normal,950,930);
		thisStage.setScene(scene);
		st_chooseLeave.close();
	}
	@FXML
	void chooseHard(ActionEvent event) throws IOException{
		Parent hard = FXMLLoader.load(getClass().getResource("Hard.fxml"));
		
		Scene scene = new Scene(hard,950,970);
		thisStage.setScene(scene);
		st_chooseLeave.close();
	}
	@FXML
	void dontChoose(ActionEvent event){
		st_chooseLeave.close();
	}
	
	@FXML
	private void onSet(ActionEvent event) throws IOException{
		Parent exit = FXMLLoader.load(getClass().getResource("Setting.fxml"));
		
		bgmM1.setToggleGroup(group);
		bgmM2.setToggleGroup(group);

		Scene scene = new Scene(exit,360,500);
		setStage.setTitle("设置FGO GAME");
		setStage.setScene(scene);
		setStage.setResizable(false);
		setStage.show();
	}
	
	/**/
	@FXML
	private void onRecord(ActionEvent event) throws IOException{
		Parent record = FXMLLoader.load(getClass().getResource("Record.fxml"));
		
		Scene scene = new Scene(record,490,550);
		Stage stage = new Stage();
		stage.setTitle("FGO Game Record");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	@FXML
	private void onHelp(ActionEvent event) throws IOException{
		Parent help = FXMLLoader.load(getClass().getResource("Help.fxml"));
		
		Scene scene = new Scene(help,766,600);
		Stage stage = new Stage();
		stage.setTitle("FGO Game Help");
		stage.setScene(scene);
		stage.show();
	}
	@FXML
	private void onAbout(ActionEvent event) throws IOException{
		Parent about = FXMLLoader.load(getClass().getResource("About.fxml"));
		Scene scene = new Scene(about,400,380);
		Stage stage = new Stage();
		stage.setTitle("关于我们");
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	/*胜利、失败窗口点击退出的操作*/
	@FXML
	public Button exit = new Button();
	@FXML
	private void onExit(ActionEvent event) throws IOException{
		Parent exit = FXMLLoader.load(getClass().getResource("Exit.fxml"));
		
		Scene scene = new Scene(exit,500,200);
		exitStage.setTitle("退出FGO GAME");
		exitStage.setScene(scene);
		exitStage.show();
	}
}