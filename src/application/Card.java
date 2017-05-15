/*
 * ·ÏÆú
 * ÔÝÊ±±£Áô
 * */

package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card {
	public boolean isCardBack = true;
	public ImageView thisImageView;
	public Image randomCard;
	
	Card(){
		// È±Ê¡
	}
	Card(ImageView imageView){
		thisImageView = imageView;
	}
	
	public void setOpacity(double value){
		thisImageView.setOpacity(value);
	}
	public void setImage(Image image){
		thisImageView.setImage(image);
	}
}