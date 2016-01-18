package foldertree.tree.common;

import java.nio.file.Paths;

import org.javatuples.Pair;

import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.io.Files;

public class CommonFolderTreeFactory {

//	CommonFolderTree initTree(String jsFilePath) throws Exception {
//		final String jsObjStr = new String(Files.readAllBytes(Paths.get(jsFilePath)), Charsets.UTF_8);
//		final ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
//	    final String str = (String)engine.eval("JSON.stringify("+jsObjStr+");");
//	    final List<Object> list = new ObjectMapper().readValue(str, List.class);
//	    
//	    final CommonFolderTree tree = new CommonFolderTree();
//	    list.forEach( item ->{
//	    	reverseReadNode(tree.root, item);
//	    });
//		return tree;
//	}
	
	public static final int SPACE_NUM = CommonStatic.SPACE_NUM;
	
	
	
	
	public static CommonFolderTree initTreeFromFile(String filePath) throws Exception {
		final CommonFolderTree tree = new CommonFolderTree();
		int lastMargin = -1;
		CommonFolder lastFolder = tree.root;
		for(final String line: Files.readLines(Paths.get(filePath).toFile(), Charsets.UTF_8)){
			if("".equals(line.trim())){
				continue;
			}
			if(line.length() > 0){
				final char fc = line.charAt(0);
				if(!CharMatcher.anyOf(" │├└").matches(fc)){
					continue;
				}
			}
			final Pair<Integer, CommonFolder> pair = recurReadLine(lastMargin, lastFolder, line);
			lastMargin = pair.getValue0();
			lastFolder = pair.getValue1();
		}
		return tree;
	}
	
	
//	public static void main(String... args){
//		int i= 0;
//		for(String s: Splitter.on(CharMatcher.anyOf("\r\n")).split("1\r\n\r\n2\r3\n4")){
//			System.out.println((i++) + "---" + s);
//		}
//		System.out.println("\r\n".trim().equals(""));
//	}
	
	
	public static CommonFolderTree initTreeFromString(String content) throws Exception {
		final CommonFolderTree tree = new CommonFolderTree();
		int lastMargin = -1;
		CommonFolder lastFolder = tree.root;
		for(final String line: Splitter.on(CharMatcher.anyOf("\r\n")).split(content)){
			if("".equals(line.trim())){
				continue;
			}
			
			if(line.length() > 0){
				final char fc = line.charAt(0);
				if(!CharMatcher.anyOf(" |├└").matches(fc)){
					continue;
				}
			}
			
			final Pair<Integer, CommonFolder> pair = recurReadLine(lastMargin, lastFolder, line);
			lastMargin = pair.getValue0();
			lastFolder = pair.getValue1();
		}
		
		return tree;
	}
	
	
	
	static Pair<Integer, CommonFolder> recurReadLine(final int lastMargin, final CommonFolder last, final String line){
		final int currentMargin = spaceMargin(line);
		final String name = line.substring(currentMargin*SPACE_NUM + 2, line.length()).trim();//
		
		CommonFolder parentFolder = null;
		
		if(lastMargin == currentMargin){//同级
			parentFolder = (CommonFolder)last.parent();
		}
		
		if(lastMargin < currentMargin){
			parentFolder = (CommonFolder)last;
		}
		
		
		if(lastMargin > currentMargin){
			final int diff = lastMargin - currentMargin;
			parentFolder = last;
			for(int i=diff; i>0; i--){
				parentFolder = (CommonFolder)parentFolder.parent();
			}
			parentFolder = (CommonFolder)parentFolder.parent();
		}
		
		final CommonFolder currentFolder = new CommonFolder(parentFolder, name);
		parentFolder.children().add(currentFolder);
		
		return Pair.<Integer, CommonFolder>with(currentMargin, currentFolder);
	}
	
	
	/**
	 * 缩进了多少层（空格数除以4）
	 * @param line
	 * @return
	 */
	static final int spaceMargin(String line){
		int space = 0;
		for(int i=0; i< line.length(); i++){
			final char c = line.charAt(i);
			if(c == ' ' || c == '│'){
				space++;
				continue;
			}
			break;
		}
		return space/SPACE_NUM;
	}
	
	
//	void reverseReadNode(IFolder parent, Object item){
////		if(item == null){
////			return ;//？？？？
////		}
//		if(item instanceof CharSequence || item instanceof Number){
//			parent.children().add(new JsFolder(parent, FolderType.Leaf, item.toString()));
//			return ;
//		}
//		
//		if(item instanceof Map){
//			final Map<String, Object> map = (Map<String, Object>)item;
//			if(map.size() != 1){
//				throw new IllegalArgumentException("目录层次的输入格式不对");
//			}
//			map.forEach( (k, v)->{
//				final JsFolder newParent = new JsFolder(parent, FolderType.Folder, k.toString());
//				parent.children().add(newParent);
//				reverseReadNode(newParent, v);
//				return ;
//			});
//		}
//		
//		
//		if(item instanceof List){
//			final List<Object> list = (List<Object>)item;
//			list.forEach( subItem -> {
//				subItem
//			});
//			
//			
//		}
//		
//		throw new IllegalStateException("目录层次的输入格式不对");
//	}

}
