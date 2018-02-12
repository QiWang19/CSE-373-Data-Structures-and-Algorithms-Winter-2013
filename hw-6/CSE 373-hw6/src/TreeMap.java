import java.util.ArrayList;


// TODO: Remove each 'todo' comment once I implement each part!

// TODO: class comment header

public class TreeMap<K extends Comparable<K>, V> extends AbstractTreeMap<K, V> {
	private TreeNode root= super.root;
	
	public TreeMap() {
		
	}
		
	// TODO: comment header
	public String toString() {
		ArrayList<TreeNode> result = this.inOrder(super.root);
		if (result == null || result.size() == 0) {
			return "{}";
		}
		StringBuilder sb = new StringBuilder();
		sb.append("{");
		for (int i = 0; i < result.size() - 1; i++) {
			TreeNode temp = result.get(i);
			String str = temp.key.toString() + "=" + temp.value.toString();
			sb.append(str);
			sb.append(", ");
		}
		TreeNode n = result.get(result.size() - 1);
		String s = n.key.toString() + "=" + n.value.toString();
		sb.append(s);
		sb.append("}");
		return sb.toString();
	}
	
	public ArrayList<TreeNode> inOrder(TreeNode head) {
		ArrayList<TreeNode> res = new ArrayList<TreeNode>();
		if (head == null) {
			return res;
		}
		ArrayList<TreeNode> left = inOrder(head.left);
		ArrayList<TreeNode> right = inOrder(head.right);
		res.addAll(left);
		res.add(head);
		res.addAll(right);
		return res;
	}
	
	
	// TODO: comment header
	protected TreeNode put(TreeNode node, K key, V value) {
		if (node == null) {
			node = new TreeNode(key, value);   // reached dead end; put new node here
			size++;
		} else if (key.compareTo(node.key) > 0) {
			node.right = put(node.right, key, value);
			int bf = getBf(node);
			if (bf > 1) {
				if (key.compareTo(node.right.key) > 0) {
					node = leftRotate(node);
				} else {
					node = rightLeftRotate(node);
				}
			}
			
		} else if (key.compareTo(node.key) < 0) {
			node.left = put(node.left, key, value);
			int bf = getBf(node);
			if (bf < -1) {
				if (key.compareTo(node.left.key) < 0) {
					node = rightRotate(node);
				} else {
					node = leftRightRotate(node);
				}
			}
		} else if (key.equals(node.key)) {
			// a duplicate; replace the value
			node.value = value;
		}

		node.height = computeHeight(node);
		return node;
		
		
	}
	
	private int getBf(TreeNode node) {
		int res = 0;
		if (node.left != null && node.right != null) {
			res = node.right.height - node.left.height;
		} else if (node.left == null && node.right == null) {
			res =  0;
		} else if (node.left == null) {
			res =  node.right.height;
		} else if (node.right == null) {
			res = 0 - node.left.height;
		}
		return res;
	}
	
	private TreeNode rightRotate(TreeNode oldParent) {
		TreeNode orphan = oldParent.left.right;
		
		TreeNode newParent = oldParent.left;
		
		newParent.right = oldParent;
		
		oldParent.left = orphan;
		
		oldParent.height = computeHeight(oldParent);
		newParent.height = computeHeight(newParent);
		
		return newParent;
	}
	
	private TreeNode leftRotate(TreeNode oldParent) {
		TreeNode orphan = oldParent.right.left;
		TreeNode newParent = oldParent.right;
		newParent.left = oldParent;
		oldParent.right = orphan;
		oldParent.height = computeHeight(oldParent);
		newParent.height = computeHeight(newParent);
		return newParent;
	}

	private TreeNode leftRightRotate(TreeNode oldParent) {
		oldParent.left = leftRotate(oldParent.left);
		oldParent = rightRotate(oldParent);
		return oldParent;
	}
	
	private TreeNode rightLeftRotate(TreeNode oldParent) {
		oldParent.right = rightRotate(oldParent.right);
		oldParent = leftRotate(oldParent);
		return oldParent;
	}
	
	private ArrayList<TreeNode> getPath( K key) {
		ArrayList<TreeNode> res = new ArrayList<TreeNode>();
		if (this.root == null) {
			return res;
		}
		TreeNode curt = this.root;
		while(curt != null) {
			if (key.compareTo(curt.key) < 0) {
				res.add(curt);
				curt = curt.left;
			} else if (key.compareTo(curt.key) > 0) {
				res.add(curt);
				curt = curt.right;
			} else {
				res.add(curt);
				break;
			}
		}
		
		return res;
	}
	// TODO: comment header
	protected TreeNode remove(TreeNode node, K key) {
		if (node == null) {
			// nothing here
		} else if (key.compareTo(node.key) < 0) {
			node.left = remove(node.left, key);
			int bf = getBf(node);
			if (bf > 1) {
				int bf1 = getBf(node.right);
				if (bf1 < 0) {
					node = rightLeftRotate(node);
				} else {
					node = leftRotate(node);
				}
			}
		} else if (key.compareTo(node.key) > 0) {
			node.right = remove(node.right, key);
			int bf = getBf(node);
			if (bf < -1) {
				int bf1 = getBf(node.left);
				if (bf1 > 0) {
					node = leftRotate(node);
				} else {
					node = rightRotate(node);
				}
			}
		} else if (key.equals(node.key)) { // (value == root.data)
			// found it! remove now.
			if (node.left == null && node.right == null) {
				// leaf
				node = null;
				size--;
			} else if (node.right == null) {
				// only left child
				node = node.left;
				size--;
			} else if (node.left == null) {
				// only right child
				node = node.right;
				size--;
			} else {
				// hard case: two children; replace me with min from my right
				K rightMinKey = getMinKey(node.right);
				V rightMinValue = get(node.right, rightMinKey);
				node.right = remove(node.right, rightMinKey);   // does a size--
				node.key = rightMinKey;
				node.value = rightMinValue;
			}
		}
		
		if (node != null) {
			node.height = computeHeight(node);
		}
		return node;
	}
}
