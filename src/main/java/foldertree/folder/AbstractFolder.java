package foldertree.folder;

import java.util.LinkedHashSet;

import com.google.common.collect.Sets;

public class AbstractFolder<T extends IFolder> implements IFolder {
	
	final T parent;
	final LinkedHashSet<T> children;
	final String name;
	

	public AbstractFolder(T parent, LinkedHashSet<T> children, /*FolderType type,*/ String name) {
		this.parent = parent;
		this.children = children;
		if(name == null){
			throw new IllegalArgumentException("folder name cannot be null");//
		}
		this.name = name;
	}

	
	
	public AbstractFolder(T parent, /*FolderType type,*/ String name) {
		this(parent, Sets.newLinkedHashSet(), /*type,*/ name);
	}



	@Override
	public T parent() {
		return parent;
	}

	@Override
	public LinkedHashSet<T> children() {
		return children;
	}



//	@Override
//	public FolderType type() {
//		return type;
//	}



	@Override
	public String name() {
		return name;
	}

}
