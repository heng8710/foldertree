package foldertree.tree.common;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

import foldertree.folder.AbstractFolder;

public class CommonFolder extends AbstractFolder<CommonFolder>{
	
	final Map<String, Object> data = new HashMap<>();

	public CommonFolder(CommonFolder parent, /*FolderType type,*/ String name) {
		super(parent, /*type,*/ name);
	}

	public CommonFolder(CommonFolder parent, LinkedHashSet<CommonFolder> children, /*FolderType type,*/ String name) {
		super(parent, children, /*type,*/ name);
	}
	
	
	public Map<String, Object> data(){
		return data;
	}

	@Override
	public String toString() {
		return "CommonFolder [data=" + data + ", name()=" + name() + "]";
	}

}
