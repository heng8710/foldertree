package foldertree.tree.common;

import foldertree.tree.IFolderTree;

public class CommonFolderTree implements IFolderTree<CommonFolder>{

	final CommonFolder root = new CommonFolder(null, /*FolderType.Folder,*/ "root");
	
	CommonFolderTree() {
		super();
	}

	
	
	
	@Override
	public CommonFolder root() {
		return root;
	}



	@Override
	public String toString() {
		final StringBuilder r = new StringBuilder();
		printLine(r, -1, root);
		return r.toString();
	}
	
	void printLine(final StringBuilder r, final int lastMargin, final CommonFolder parent/*已经打印过了不要再打*/){
		for(CommonFolder folder: parent.children()){
			r.append(CommonStatic.spaceMargin(lastMargin + 1) ).append(CommonStatic.START_TAG).append(folder.name()).append("\r\n");
			if(folder.children().size() > 0){
				printLine(r, lastMargin+1, folder);
			}
		}
	}

	
	
}
