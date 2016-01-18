package foldertree.folder;

import java.util.LinkedHashSet;

public interface IFolder {

	String name();
	
//	FolderType type();
	
	IFolder parent();
	
	LinkedHashSet<? extends IFolder> children();
	
}
