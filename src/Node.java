import java.util.ArrayList;

class Node {
private String data;
private int distance;
private Node parent;

private ArrayList<Node> children;

public Node (String da){
	data=da;

	parent=null;
	children=new ArrayList<Node>();
}
public void setData(String da){
	data=da;
}

public String getData(){
	return data;
}
public void addChild(Node c){
	children.add(c);
}
public Node getParent(){
	return parent;
}
public void setParent(Node p){
	parent=p;
}
public void setDistance(int d){
	distance=d;
}

public ArrayList<Node> getChildren(){	
	return children;
}

public int getDistance(){
	return distance;
}


}
