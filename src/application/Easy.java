// ��ģʽ

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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Easy extends Main{
	// ���� static Stage ���ڹرտ�ʼ���ڴ��µĴ���
	public static Stage returnStage = new Stage();
	public static Stage st_chooseLeave = new Stage();
	// ���� final static Image ��Ӧ3�ֲ�ͬ��ͼƬ
	private final static Image IMGA = new Image("application/images/059E.png");
	private final static Image IMGB = new Image("application/images/060E.png");
	private final static Image IMGC = new Image("application/images/068E.png");
	private final static Image IMGKB = new Image("application/images/KB.png");
	// ͼƬ���������������� randomChoose �����������ͼƬ������ͼƬ����
	private static int IMGANUM = 0;
	private static int IMGBNUM = 0;
	private static int IMGCNUM = 0;
	// ��ʱ���ƽڵ㣬���  �����ƺ��Ƿ�ƥ�䡱 ����
	private String onePoint = null;
	private String twoPoint = null;
	private ImageView last = null;
	// ���ڷ����Ĳ��֣��Ƿֱ�������ʱ����ͷ��������Ƶı���
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
	
	// Easy.fxml ��ӳ���������
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
	public Text textGood = new Text();
	@FXML
	public Text textGreat = new Text();
	@FXML
	public Text textUnbelievable = new Text();
	@FXML
	public Text textBad = new Text();
	@FXML
	public Text textWorse = new Text();
	@FXML
	public MenuItem reStart = new MenuItem();
	@FXML
	public MenuItem chooseLeave = new MenuItem();
	@FXML
	public MenuItem set = new MenuItem();
	@FXML
	public MenuItem record = new MenuItem();
	@FXML
	public MenuItem help = new MenuItem();
	@FXML
	public MenuItem about = new MenuItem();
	@FXML
	public Text scoreShow = new Text();
	@FXML
	public Text timeMinute = new Text();
	@FXML
	public Text timeSecond = new Text();
	
	// �������ͼƬ����
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
	 * �����ͷ�������Ч���Բ�����ɡ�
	 * �����м���ͣʧ�ܣ����ֻʹ�� Timeline ���޷���ͣ�ģ������뷨��
	 * 1.����� EventHandler ��Ƕ�� EventHandler Ҳ����������֧һ����˫������
	 *   ͬһʱ���ߣ��޷��õ���ͣЧ����
	 * 2.����3��ʱ���ߣ��ֱ��ǵ�һ������ڶ�������end����һ������ʱ�������ͣЧ
	 *   ����ʵ��������ʹ�����ظ��������޷�����ʱ�Duration.millis(1) ��Ҫ��
	 *   1��
	 * ���з�����
	 * 	 ʹ�� PathTransition���������� Scene ʹ�õ��� HBox �� VBox������ֱ�Ӽ���
	 * 	 ͸���� line�����������������ǿ��еġ���������ʱ�������ͣ��֮���ԡ�
	 * ĿǰЧ��ʵ����ʹ������ʱ���ߣ�һ���ǳ������岢�������ƶ�������ʱ��ھ���
	 * 3000ms �� end ʱ���߿�ʼ���С��������岢��͸���ȹ�0��
	 * ע�⣺
	 * ���沿��������Ĵ��룬���嵽ʹ��ֻ�Ƕ� Timeline �����ã�ֻ��2�д��룬����
	 * ���ظ����������� play ������
	 * */
	EventHandler<ActionEvent> flowTextForGood = e -> {
		textGood.setOpacity(1);
		textGood.setY(textGood.getY()-1);
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
	KeyFrame kf_flowTextForGood_end = new KeyFrame(Duration.millis(3000),flowTextForGood_end);
	Timeline flowTextForGoodShow = new Timeline(kf_flowTextForGood);
	Timeline flowTextForGoodShow_end = new Timeline(kf_flowTextForGood_end);
	
	KeyFrame kf_flowTextForGreat = new KeyFrame(Duration.millis(1),flowTextForGreat);
	KeyFrame kf_flowTextForGreat_end = new KeyFrame(Duration.millis(3000),flowTextForGreat_end);
	Timeline flowTextForGreatShow = new Timeline(kf_flowTextForGreat);
	Timeline flowTextForGreatShow_end = new Timeline(kf_flowTextForGreat_end);
	
	KeyFrame kf_flowTextForUnbelievable = new KeyFrame(Duration.millis(1),flowTextForUnbelievable);
	KeyFrame kf_flowTextForUnbelievable_end = new KeyFrame(Duration.millis(3000),flowTextForUnbelievable_end);
	Timeline flowTextForUnbelievableShow = new Timeline(kf_flowTextForUnbelievable);
	Timeline flowTextForUnbelievableShow_end = new Timeline(kf_flowTextForUnbelievable_end);
	
	KeyFrame kf_flowTextForBad = new KeyFrame(Duration.millis(1),flowTextForBad);
	KeyFrame kf_flowTextForBad_end = new KeyFrame(Duration.millis(3000),flowTextForBad_end);
	Timeline flowTextForBadShow = new Timeline(kf_flowTextForBad);
	Timeline flowTextForBadShow_end = new Timeline(kf_flowTextForBad_end);
	
	KeyFrame kf_flowTextForWorse = new KeyFrame(Duration.millis(1),flowTextForWorse);
	KeyFrame kf_flowTextForWorse_end = new KeyFrame(Duration.millis(3000),flowTextForWorse_end);
	Timeline flowTextForWorseShow = new Timeline(kf_flowTextForWorse);
	Timeline flowTextForWorseShow_end = new Timeline(kf_flowTextForWorse_end);
	
	/*
	 * Easy ģʽ��12��ͼƬ��ÿ��ͼƬ��Ӧ�� MouseEvent ������
	 * ����ģʽ��
	 * 1.ֻ����������Ч
	 * 2.ֻ�е��͸����Ϊ0��ͼƬ��Ч
	 * 3.��ɺ�������  �����ƺ��Ƿ�ƥ�䡱
	 * 4.
	 */
	
	/*
	 * ���� Timeline
	 * �ڼ�������ʹ���˼��� EventHandler ������Ҫ����� KeyFrame ��
	 * ��� Timeline �Ĺ��ܡ�
	 * ֮ǰ����ʹ�� FadeTransition �ܸı�͸���ȣ�������ɲ�������������
	 * ����Ϊ��չ�������ķ���Ч�������Ƿ��ƣ�ʹ�ø���͸���ȣ��о����ã�
	 * ���е�һЩ������Ч�����ͣ�
	 * backToLeave	������ʧ
	 * cardToOpen	��Ƭ������ʾ
	 * backToOpen	������ʾ
	 * cardToLeave	��Ƭ������ʧ
	 * KeyFrame ����һ����ֻ��ǰ����� kyOf
	 * Timeline ����ͬ����ʾ A״̬ To B״̬
	 * last ����������Ϊ�˿�����һ�� ImageView�������Ʋ�ƥ���ʱ�򣩣����б���
	 * ǰ���� last ����
	 * ������װ��֡���� Timeline �б��ֳɶ���
	 * */
	@FXML
	protected void onClicked00(MouseEvent event){
		if((event.getButton().toString() == "PRIMARY")&&(img_0_0.getImage() == IMGKB)&&(img_0_0.getOpacity() == 1)){
			
			/*	�����ͷ���Ч���Դ��룬�޸ĺ�ɾ��ע�ͷ��ţ������һ��ͼƬ�������иö���
				flowTextForBadShow.setCycleCount(800);
				flowTextForBadShow_end.setCycleCount(1);
				flowTextForBadShow_end.play();
				flowTextForBadShow.play();
			*/

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
						case 3: {
									score += 60;
									flowTextForGoodShow.setCycleCount(800);
									flowTextForGoodShow_end.setCycleCount(1);
									flowTextForGoodShow_end.play();
									flowTextForGoodShow.play();
								}break;
						case 5: {
									score += 120;
									flowTextForGreatShow.setCycleCount(800);
									flowTextForGreatShow_end.setCycleCount(1);
									flowTextForGreatShow_end.play();
									flowTextForGreatShow.play();
								}break;
						case 9: {
									score += 380;
									flowTextForUnbelievableShow.setCycleCount(800);
									flowTextForUnbelievableShow_end.setCycleCount(1);
									flowTextForUnbelievableShow_end.play();
									flowTextForUnbelievableShow.play();
								}break;
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
					 * �����������ǻ�� bug �ģ���ʱ���
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
									flowTextForBadShow.setCycleCount(800);
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
									flowTextForBadShow.setCycleCount(800);
									flowTextForBadShow_end.setCycleCount(1);
									flowTextForBadShow_end.play();
									flowTextForBadShow.play();
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
					 * �����������ǻ�� bug �ģ���ʱ���
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
					 * �����������ǻ�� bug �ģ���ʱ���
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
					 * �����������ǻ�� bug �ģ���ʱ���
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
	// ���¿�ʼһ�� Easy �Ѷȵ���Ϸ
	@FXML
	protected void beganOnClicked(ActionEvent event){
		/*
		 * ÿ�����¿�ʼ��Ҫ����͸����Ϊ 100% ����
		 * ����֡�û�л�ԭ����bug
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
		 * ����ʹ�� scene builder ��ȷ���� Image ��ַ
		 * �ǲ�һ���ģ���������ʹ���˳�ʼ����
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
		
		// �������
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
		// ���ü���������ֹ��ѭ��
		IMGANUM = 0;
		IMGBNUM = 0;
		IMGCNUM = 0;
		// ���÷����Լ����Ϳ���
		score = 0;
		reward = 0;
		punish = 0;
		scoreShow.setText("0");
		/* ����ʱ��ʼ */
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
		};
		KeyFrame kf_timeEnd = new KeyFrame(Duration.millis(1000),timeEnd);
		Timeline tl_timeEnd = new Timeline(kf_timeEnd);
		tl_timeEnd.setCycleCount(60);
		tl_timeEnd.play();
	}
	
	// MenuBar ���� MenuItem ������
	@FXML
	private void onReStart(ActionEvent event) throws IOException{
		Parent reStart = FXMLLoader.load(getClass().getResource("ReStart.fxml"));
		
		Scene returnScene = new Scene(reStart,500,200);
		returnStage.setScene(returnScene);
		returnStage.show();
	}
	/*ReStart.fxml ������ӳ���������*/
	@FXML
	Button btDoReturn = new Button();
	@FXML
	Button btDontReturn = new Button();
	@FXML
	void doReturn(ActionEvent event) throws IOException{
		Parent start = FXMLLoader.load(getClass().getResource("Start.fxml"));
		
		Scene scene = new Scene(start,800,900);
		thisStage.setScene(scene);
		thisStage.show();
		returnStage.close();
	}
	@FXML
	void dontReturn(ActionEvent event){
		returnStage.close();
	}
	
	@FXML
	private void onChooseLeave(ActionEvent event) throws IOException{
		Parent start = FXMLLoader.load(getClass().getResource("ChooseLeave.fxml"));
		
		Scene scene = new Scene(start,500,600);
		st_chooseLeave.setScene(scene);
		st_chooseLeave.setTitle("Choose Leave");
		st_chooseLeave.show();
	}
	/*ReStart.fxml ������ӳ���������*/
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
		
		Scene scene = new Scene(easy,900,900);
		thisStage.setScene(scene);
		st_chooseLeave.close();
	}
	@FXML
	void chooseNormal(ActionEvent event) throws IOException{
		/* ��ɺ�ʵװ
		Parent normal = FXMLLoader.load(getClass().getResource("Normal.fxml"));
		
		Scene scene = new Scene(normal,900,900);
		thisStage.setScene(scene);
		st_chooseLeave.close();
		*/
	}
	@FXML
	void chooseHard(ActionEvent event) throws IOException{
		/* ��ɺ�ʵװ
		Parent hard = FXMLLoader.load(getClass().getResource("Hard.fxml"));
		
		Scene scene = new Scene(hard,900,900);
		thisStage.setScene(scene);
		st_chooseLeave.close();
		*/
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

		Scene scene = new Scene(exit,500,800);
		setStage.setTitle("����FGO GAME");
		setStage.setScene(scene);
		setStage.show();
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