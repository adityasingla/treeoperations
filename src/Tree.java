/*
 * This is a tree class that implements some of the basic operations used with trees
 */

/*
 * Operations
 * 1) AddNode
 * 2) deleteFromTree
 * 3) inOrderTraversal
 * 4) sizeOfTree
 * 5) depthOfTree
 * 6) mirrorOfTree
 * 7) findMaxNodeinTree
 * 8) printRootToTreePath(all paths from root to the leaf of the tree)
 * 9) findCommonAncestorofTwoNodes
 * 10)findLevel
 * 11) checkBinarySubtree(check if one tree is subtree of other)
 */

public class Tree {
	Node root;
	public Tree(){
		root = null;
	}
	
	class Node{
		int value;
		Node left;
		Node right;
		public Node(int val){
			this.value = val;
			this.left = null;
			this.right = null;
		}
	}
	
	/*
	 * Function: add a value to the tree
	 */
	void add(int i){
		Node n = new Node(i);
		addToTree(n, this.root);		
	}

	private void addToTree(Node n, Node root){
		if(root == null){
			this.root = n;
		}
		else{
			
			if(n.value < root.value){
				if(root.left == null){
					root.left = n;
				}
				else{
					addToTree(n,root.left);
				}
			}
			else{
				if(root.right == null){
					root.right = n;
				}
				else{
					addToTree(n,root.right);
				}
			}
		}
	}
	
	/*
	 * Function : Print the in-order traversal of a tree
	 */
	void inOrderTraversal(Node n){
		
		if(n == null){
			return;
		}
		else{
			inOrderTraversal(n.left);
			System.out.print(String.valueOf(n.value) + " ");
			inOrderTraversal(n.right);
		}
	}
	
	/*
	 * Function : find the total number of nodes in a tree
	 */
	int sizeOfTree(Node n){
		if(n == null){
			return 0;
		}
		else{
			return sizeOfTree(n.left) + 1 + sizeOfTree(n.right); 
		}
	}
	
	/*
	 * Function : find the depth of a node in tree
	 */
	int depthOfTree(Node n){
		if(n == null){
			return 0;
		}
		else{
			return max(depthOfTree(n.left), depthOfTree(n.right)) + 1; 
		}
	}
	
	int max(int x, int y){
		if(x> y){
			return x;
		}
		else{
			return y;
		}
	}
	
	/*
	 * Function : convert tree to its mirror
	 */
	void mirrorOfTree(Node n){
		if(n == null){
			return;
		}
		else{
			mirrorOfTree(n.left);
			mirrorOfTree(n.right);
			swapNodes(n);
		}
	}
	
	void swapNodes(Node n){
		Node temp = n.left;
		n.left = n.right;
		n.right = temp;
	}
	
	/*
	 * Function : Delete from a tree
	 */
	void deleteFromTree(int val, Node root,Node prevNode){
		if(root == null){
			return;
		}
		else{
			if(root.value == val){
				if(prevNode != null){
					if(val < prevNode.value){		
						if(root.left == null){
							prevNode.left = root.left;
						}
						else if(root.right == null){
							prevNode.left = root.left;
						}
						else{
							
							Node temp = findMaxTree(root.left);
							int tempValue = temp.value;
							deleteFromTree(temp.value,root.left,root);
							root.value = tempValue;
						}
					}
					else{
						if(root.left == null){
							prevNode.right = root.right;
						}
						else if(root.right == null){
							prevNode.right = root.left;
						}
						else{
							
							Node temp = findMaxTree(root.left);
							int tempValue = temp.value;
							deleteFromTree(temp.value,root.left,root);
							root.value = tempValue;
						}
					}
				}
				else
				{
					Node temp = findMaxTree(root.left);
					int tempValue = temp.value;
					deleteFromTree(temp.value,root.left,root);
					root.value = tempValue;
				}
			}
			else{
				if(val < root.value && root.left != null){
					deleteFromTree(val,root.left,root);
				}
				else{
					if(root.right != null){
						deleteFromTree(val,root.right,root);
					}
				}
			}
		}
	}
	
	Node findMaxTree(Node n){
		if(n.right == null){
			return n;
		}
		else{
			return findMaxTree(n.right);
		}
	}
	
	/*
	 * Function :  print all paths from root to leaf node
	 */
	void printRootToLeafPath(Node root, String path){
		if(root == null){
			return;
		}
		if(root.left == null && root.right == null){
			path += String.valueOf(root.value);
			System.out.println(path);
		}
		else{
			path += String.valueOf(root.value) + "->";
			printRootToLeafPath(root.left,path);
			printRootToLeafPath(root.right,path);
		}
	}
	
	/*
	 * Function : find common ancestor for any 2 nodes in a tree
	 */
	int findCommonAncestor(Node n, int x, int y){
		if(x <= n.value && y >= n.value){
			return n.value;
		}
		else if(x >= n.value && y <= n.value){
			return n.value;
		}
		else if(x >= n.value){
			return findCommonAncestor(n.right,x,y);
		}
		else{
			return findCommonAncestor(n.left,x,y);
		}
	}
	
	/*
	 * Function : find level of a particular node
	 */
	int findLevel(Node n,int val){
		if(n == null){
			return 0;
		}
		else if(n.value == val){
			return 0;
		}
		else{
			if(val < n.value){
				return findLevel(n.left, val) + 1;
			}
			else{
				return findLevel(n.right, val) + 1;
			}
		}
	}
	
	int findParent(Node n, Node parent ,int val){
		if(n == null){
			return 0;
		}
		else if(n.value == val)
		{
			return parent.value;
		}
		else
		{
			if(val < n.value){
				return findParent(n.left,n,val);
			}
			else{
				return findParent(n.right,n,val);
			}
		}
	}
	
	/*
	 * Function : check if one tree is subtree of other
	 */
	void checkBinarySubTree(Node root1, Node root2){
		Node root1_in_root2 = findNodeInTree(root1.value,root2);
		
		boolean result = compareBinaryTrees(root1,root1_in_root2);
		System.out.println(String.valueOf(result));
	}
	
	boolean compareBinaryTrees(Node root1, Node root2){
		if(root1 == null){
			return true;
		}
		if(root2 == null){
			return false;
		}
		
		return ((root1.value == root2.value) && (compareBinaryTrees(root1.left,root2.left)) && (compareBinaryTrees(root1.right,root2.right))); 
	}
	
	Node findNodeInTree(int val, Node root){
		if(root.value == val){
			return root;
		}
		else{
			if(val < root.value){
				return findNodeInTree(val,root.left);
			}
			else{
				return findNodeInTree(val,root.right);
			}
		}
	}
	public static void main(String args[]){
		Tree tree = new Tree();
		tree.add(20);
		tree.add(8);
		tree.add(9);
		tree.add(4);
		tree.add(12);
		tree.add(10);
		tree.add(14);
		
		Tree tree2 = new Tree();
		tree2.add(8);
		tree2.add(9);
		tree.checkBinarySubTree(tree2.root, tree.root);
	}
}
