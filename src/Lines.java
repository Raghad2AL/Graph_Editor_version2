import java.awt.geom.*;
public class Lines extends Line2D.Double{
String datas,datab;
	Lines(double x1,double y1,double x2,double y2){
		super(x1,y1,x2,y2);
	} 
	public void setLinesData(String data1,String data2){
		datas=data1;
		datab=data2;
	}
	public String getData1(){
		return datas;
	}
	public String getData2(){
		return datab;
	}

}
