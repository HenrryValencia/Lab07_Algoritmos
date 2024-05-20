package BaseData;

public class BSTree <E extends Comparable<E>>{
	class Node {
		protected E data;
		protected Node left,right;
		
		public Node(E data) {
			this (data,null,null);
		}
		public Node(E data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}
		public E getData() {return this.data;}
		public void setRight(Node x){this.right = x;}
		public void setLeft(Node x){this.left = x;}
	}
	
	public Node current;
	public Node parent;
	public Node aux;
	
	private Node root;
	
	public BSTree() {this.root = null;}
	
	public boolean isEmpty() {return this.root == null;}
	
	public void insert(E x) throws Exception {
		Node newNode = new Node(x);
		//caso vacio...
		if (isEmpty()) {this.root = newNode ;return ;}
		//existe el arbol...
		this.current = root;
		this.parent = null;
		this.aux = null;
		//
		while(current!=null) {
			this.parent = this.current;
			if(x.compareTo(this.current.getData())>0) {
				this.current = this.current.right;
			}else{
				this.current = this.current.left;
			}
		}
		if(x.compareTo(this.current.getData())>0) {
			this.parent.setRight(newNode);
		}else {
			this.parent.setLeft(newNode);
		}
		
	}
	
	public boolean search(E x) throws Exception {
		this.current = root;
		while(current!=null) {
			if(x.compareTo(this.current.getData())>0) {
				this.current = this.current.right;
			}else{
				this.current = this.current.left;
			}
		}
		if(this.current == null) {return false;}
		return true;
		
	}
	public void delete(E dato) {
		
		this.current = this.root;
		this.parent = null;
		boolean isLeftChild = true;
		while(this.current!= null && this.current.getData()!= null) {
			this.parent = this.current;
			if(dato.compareTo(this.current.getData())<0) {
				isLeftChild = true;
			}else {isLeftChild = false;}
			
			//El nodo no existe...
			
			if(this.current == null) {
				return;
			}
			
			// si no tiene HIJOS...
			
			if (this.current.left == null && this.current.right == null) {
	            if (this.current == this.root) {
	                this.root = null;
	            } else if (isLeftChild) {
	                this.parent.left = null;
	            } else {
	                this.parent.right = null;
	            }
	        }
			
		}
		
	}

	//EJERCICIO01
	//Método countNodes(), que retorne el número de nodos no-hojas de un BST
		
	public int countNodes(Node node) {
		if (node == null || (node.left == null && node.right == null)) {
			return 0;
		}
	
		return 1 + countNodes(node.left) + countNodes(node.right);
	}

	//Método height(), que retorne la altura de un nodo X cualquiera de un BST 
	//siempre y cuando exista el árbol

	public int height(Node node) {
		if (node == null) {
			return -1; // La altura de un árbol vacío se considera -1
		}
	
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);
	
		return Math.max(leftHeight, rightHeight) + 1;
	}
	
	//EJERCICIO02
	//Método que devuelve el total de nodos de un arbol, de forma recursiva

	public int countNodesTotal(Node node) {
		if (node == null) {
			return 0;
		}
	
		return 1 + countNodes(node.left) + countNodes(node.right);
	}
	

	@Override
	public String toString() {
		return " ";
	}

}
