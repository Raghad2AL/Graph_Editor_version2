import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;
public class DrawingPanel extends JPanel{
	private JPanel buttons;
	private JButton addB,connect,deleteNode;
	private String d1,p1="",p2="",p5,p6;
	private String d;
	private Node n,n1,n2,n3;
	private Rectangles r,r1,r2,r3;
	private Lines l;
	private ArrayList<Node> nodes;
	private ArrayList<Node> paths=new ArrayList<Node>();
	private ArrayList<Rectangles> rectangles=new ArrayList<Rectangles>();
	private ArrayList<Lines> lines=new ArrayList<Lines>();
	private boolean es=false,eb=false,ed=false,b=false;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D)g;
		g2d.setStroke(new BasicStroke(1.5f,0,0));
		g2d.setFont(new Font("",Font.PLAIN,40));
		//g2d.setColor(new Color(15, 85, 89));
		g2d.setColor(Color.GREEN);
		if (!lines.isEmpty())
			for (Line2D i:lines){
				g2d.setColor(new Color(58,193,243));
				g2d.draw(i);
		}
		
		if (!rectangles.isEmpty())
			for (int i=0;i<rectangles.size();i++){
				g2d.setColor(Color.white);
				if((nodes.get(i).getData().equals(rectangles.get(i).getData()))){
					g2d.fill(rectangles.get(i));
					}
				else g2d.fill(rectangles.get(i));
				g2d.setColor(Color.RED);
				g2d.draw(rectangles.get(i));
				g2d.setColor(new Color(25, 220, 200));
				g2d.drawString(rectangles.get(i).getData(), rectangles.get(i).x+20, rectangles.get(i).y+55);
		
		
		}
		if(es){
		if (rectangles.isEmpty());
		else for (Rectangle i:rectangles){
			g2d.setColor(Color.GREEN);
			g2d.draw(i);
		}
		if (r==null);
		else {
			g2d.drawRect(r.x, r.y, r.width, r.height);
			g2d.setColor(new Color(25, 220, 200));
			g2d.drawString(r.getData(), r.x+20 , r.y+55);
		}
		if(eb)es=false;
	}//mouseMotion
		
	}
	public DrawingPanel(){
		//setBackground(Color.WHITE);
		setBackground(new Color(215,242,252));
		setLayout(new BorderLayout());
		nodes =new ArrayList<Node>();
		rectangles=new ArrayList<Rectangles>();
		
		buttons=new JPanel();
		addB=new JButton("Add A Node");
		addB.setFocusPainted(false);
		addB.setFont(new Font("",0,15));
		addB.setPreferredSize(new Dimension(150,50));
		addB.setForeground(new Color(255, 220, 200));
		addB.setBackground(new Color(67,179,234));
		addB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				paths.clear();
				repaint();
				while (true){
					b=false;
				d1=JOptionPane.showInputDialog("Please Enter your node number");
				if (!nodes.isEmpty()){
					for (Node i:nodes){
						if (i.getData().equals(d1)) {
							b=true;
							JOptionPane.showMessageDialog(DrawingPanel.this,"Data Already Exists");
						}
					}
				}
				if (d1==null|| !d1.matches("[0-9]+") ){
					b=true;
					JOptionPane.showMessageDialog(DrawingPanel.this,"Invalid Number");
				}
				if(!b) break;
				}//while
				n=new Node(d1);
				n1=n;
				nodes.add(n);
				es=true;
				eb=false;
			
			}
		});//adding nodes
		connect=new JButton("Connect the Nodes");
		connect.setFocusPainted(false);
		connect.setFont(new Font("",0,15));
		connect.setPreferredSize(new Dimension(200,50));
		connect.setForeground(new Color(255, 220, 200));
		connect.setBackground(new Color(67,179,234));
		connect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				paths.clear();
				repaint();
				int count=0;
				int count2=0;
				while (true){
				b=false;
				count=0;
				count2=0;
				p1=JOptionPane.showInputDialog("Your first Node (The Parent)");
				p2=JOptionPane.showInputDialog("Your second Node (The Child)");
				if (!nodes.isEmpty()){
					for (Node i:nodes){
						if (!(i.getData().equals(p1))) count++;
						if (!(i.getData().equals(p2))) count2++;
						} 
						if(count==nodes.size()||count2==nodes.size()){
							b=true;
							JOptionPane.showMessageDialog(DrawingPanel.this,"Data not found");
						}
				}
				if (p1==null||p2==null|| !p1.matches("[0-9]+") || !p2.matches("[0-9]+")){
					b=true;
					JOptionPane.showMessageDialog(DrawingPanel.this,"Invalid Number");
				}
				if(!b) break;
				}
				
				for (int i=0;i<rectangles.size();i++){
					if(p1.equals(rectangles.get(i).getData())) r1=rectangles.get(i);
					if(p2.equals(rectangles.get(i).getData())) r2=rectangles.get(i);
				}
				
				for (Node i:nodes){
					if (p1.equals(i.getData()))n2=i;
					if (p2.equals(i.getData()))n3=i;
				}
				
				n2.addChild(n3);
				l=new Lines(r1.getCenterX(),r1.getCenterY(),r2.getCenterX(),r2.getCenterY());
				l.setLinesData(r1.getData(), r2.getData());
				lines.add(l);
				
			}
		});//connecting the points
		deleteNode=new JButton("Delete Node");
		deleteNode.setFont(new Font("",0,15));
		deleteNode.setPreferredSize(new Dimension(150,50));
		deleteNode.setForeground(new Color(255, 220, 200));
		deleteNode.setBackground(new Color(67,179,234));
		deleteNode.setFocusPainted(false);
		deleteNode.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				paths.clear();
				repaint();
				int count=0;
				while (true){
					b=false;
					d=JOptionPane.showInputDialog(DrawingPanel.this,"Please enter the node you want to delete");
					if (!nodes.isEmpty()){
					for (Node i:nodes){
						if (!i.getData().equals(d)) count++;}
					
						if (count==nodes.size()){
							b=true;
							JOptionPane.showMessageDialog(DrawingPanel.this,"Data not found");
						}
					
				}
				if (d==null|| !d.matches("[0-9]+")){
					b=true;
					JOptionPane.showMessageDialog(DrawingPanel.this,"Invalid Number");
				}
				if(!b) break;
				}
				
				for (int i=0;i<lines.size();i++){
					
					if (lines.get(i).getData1().equals(d)||lines.get(i).getData2().equals(d)){
						lines.remove(lines.get(i));
						i--;
					}

				}
				for (int i=0;i<rectangles.size();i++){
					if (rectangles.get(i).getData().equals(d))rectangles.remove(rectangles.get(i));
				}
				for (int i=0;i<nodes.size();i++){
					if (nodes.get(i).getData().equals(d))nodes.remove(nodes.get(i));
				}
				repaint();
			}
		});//Deleting
		
		buttons.setLayout(new FlowLayout());
		buttons.add(addB);
		buttons.add(connect);
		buttons.add(deleteNode);
		buttons.setBackground(new Color(215,242,252));
		addMouseMotionListener(new MouseMotionAdapter(){
		@Override
		public void mouseMoved(MouseEvent e){
			r=new Rectangles(e.getX(),e.getY(),60,80);
			if(n1!=null)r.setData(""+n1.getData());
			repaint();
		}
		@Override
		public void mouseDragged(MouseEvent e){
				if(ed){
					r3.y=e.getY();
					r3.x=e.getX();
					
					if(!lines.isEmpty()){
						for (Lines i:lines){
							if (r3.getData().equals(i.getData1())){i.x1=r3.getCenterX();i.y1=r3.getCenterY();}
							if (r3.getData().equals(i.getData2())){i.x2=r3.getCenterX();i.y2=r3.getCenterY();}
						}
					}
					repaint();
			}
		}
		
	});
		addMouseListener(new MouseAdapter(){
			@Override
			public void mousePressed(MouseEvent e){
				if (es){
					r=new Rectangles(e.getX(),e.getY(),60,80);
					r.setData(n1.getData()+"");
					rectangles.add(r);
					repaint();
					eb=true;
				}
				if(!rectangles.isEmpty()){
					for (Rectangles i: rectangles){
						if (i.contains(e.getPoint())){
							ed=true;
							r3=i;
							break;
						}	
					}
				}
				
			}
			@Override
			public void mouseReleased(MouseEvent e){
				if(e.isMetaDown()) {
					paths.clear();
					repaint();
					int count=0;
					while (true){
						b=false;
						count=0;
						p5=JOptionPane.showInputDialog(DrawingPanel.this,"Old Data");
						p6=JOptionPane.showInputDialog(DrawingPanel.this,"New Data");
					if (!nodes.isEmpty()){
						for (Node i:nodes){
							if (i.getData().equals(p6)) {
								b=true;
								JOptionPane.showMessageDialog(DrawingPanel.this,"Data Already Exists");
							}
							if (!i.getData().equals(p5))count++;
						}
						if (count==nodes.size()){
							b=true;
							JOptionPane.showMessageDialog(DrawingPanel.this,"Node Not Found");
						}
					}
					if (p5==null||p6==null|| !p5.matches("[0-9]+") || !p6.matches("[0-9]+")){
						b=true;
						JOptionPane.showMessageDialog(DrawingPanel.this,"Invalid Number");
					}
					if(!b) break;
					}//while
					
					for (int i=0;i<rectangles.size();i++){
						if (rectangles.get(i).getData().equals(p5)) rectangles.get(i).setData(p6);;
					}
					for (int i=0;i<lines.size();i++){
						if (lines.get(i).getData2().equals(p5)) lines.get(i).setLinesData(lines.get(i).getData1(),p6);
						if (lines.get(i).getData1().equals(p5)) lines.get(i).setLinesData(p6,lines.get(i).getData2());
					}
					for (int i=0;i<nodes.size();i++){
						if (nodes.get(i).getData().equals(p5)) nodes.get(i).setData(p6);;
					}
					repaint();
				}
					//r3=null;
					//ed=false;
				
			}
		});
		add(buttons,BorderLayout.SOUTH);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DrawingPanel p=new DrawingPanel();
		JFrame j=new JFrame("Drawing Programme");
		 j.pack();
		  Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		 int height = screenSize.height;
		  int width = screenSize.width;
		 j.setSize(width/2, height/2);
		j.add(p);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
		j.setSize(850,700);
		j.setLocationRelativeTo(null);
	}
	

}
