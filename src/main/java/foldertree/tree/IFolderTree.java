package foldertree.tree;

import foldertree.folder.IFolder;

public interface IFolderTree <T extends IFolder>{
	T root();
}
