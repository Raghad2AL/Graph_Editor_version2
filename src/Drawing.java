import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.*;
import java.util.ArrayList;

class Rectangles extends Rectangle {
	private String data="";
	public Rectangles(int x,int y,int width,int height){
		super(x,y,width,height);
	}
	public void setData(String d){
		data=d;
	}
	public String getData(){
		return data;
	}
	
}