package foldertree.tree.common;

public class CommonStatic {
	
	public static final int SPACE_NUM = 3;
	public static final String START_TAG = "├─";
	
	public final static String spaceMargin(int spaceMargin){
		final StringBuilder sb = new StringBuilder();
		for(int i=0; i< spaceMargin; i++){
			sb.append("│  ");
		}
		return sb.toString();
	}
}
