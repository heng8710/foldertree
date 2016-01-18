package foldertree.tree.js.test;

import foldertree.tree.common.CommonFolderTree;
import foldertree.tree.common.CommonFolderTreeFactory;

public class Test {

	public static void main(String[] args) throws Exception {
		CommonFolderTree  tree = CommonFolderTreeFactory.initTreeFromFile("d:/ssh/to1.txt");
		System.out.println(tree);
		
	}

}
