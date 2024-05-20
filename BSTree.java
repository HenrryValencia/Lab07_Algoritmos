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
		//Buscamos el espacio para insertar
		while(this.current!=null) {
			this.parent = this.current;
			//x que se mayor que...
			if(x.compareTo(this.current.getData())>0) {
				this.current = this.current.right;
			}else{
				this.current = this.current.left;
			}
		}
		if(newNode.getData().compareTo(this.parent.getData())>0) {
			this.parent.setRight(newNode);
		}else {
			this.parent.setLeft(newNode);
		}
		
	}
	
	public boolean search(E x) throws Exception {
		this.current = root;
		while (this.current != null) {
	        if (x.compareTo(this.current.getData()) == 0) {
	            return true;
	        } else if (x.compareTo(this.current.getData()) > 0) {
	            this.current = this.current.right;
	        } else {
	            this.current = this.current.left;
	        }
	    }
	    return false;
	}
	
	public void delete(E dato) {
		
		this.current = this.root;
	    this.parent = null;
	    boolean isLeftChild = true;

	    // Encontrar el nodo a eliminar
	    while (this.current != null && !dato.equals(this.current.getData())) {
	        this.parent = this.current;
	        if (dato.compareTo(this.current.getData()) < 0) {
	            isLeftChild = true;
	            this.current = this.current.left;
	        } else {
	            isLeftChild = false;
	            this.current = this.current.right;
	        }
	    }

	    // El nodo no se encontró
	    if (this.current == null) {
	        return;
	    }

	    // Caso 1: El nodo no tiene hijos
	    if (this.current.left == null && this.current.right == null) {
	        if (this.current == this.root) {
	            this.root = null;
	        } else if (isLeftChild) {
	            this.parent.left = null;
	        } else {
	            this.parent.right = null;
	        }
	        return;
	    }
	    // Caso 2: El nodo tiene un solo hijo (derecho)
	    else if (this.current.left == null) {
	        if (this.current == this.root) {
	            this.root = this.current.right;
	        } else if (isLeftChild) {
	            this.parent.left = this.current.right;
	        } else {
	            this.parent.right = this.current.right;
	        }
	        return;
	    }
	    // Caso 2: El nodo tiene un solo hijo (izquierdo)
	    else if (this.current.right == null) {
	        if (this.current == this.root) {
	            this.root = this.current.left;
	        } else if (isLeftChild) {
	            this.parent.left = this.current.left;
	        } else {
	            this.parent.right = this.current.left;
	        }
	        return;
	    }
	    // Caso 3: El nodo tiene dos hijos
	    else {
	        Node successor = subBusqueda(this.current);

	        if (this.current == this.root) {
	            this.root = successor;
	        } else if (isLeftChild) {
	            this.parent.left = successor;
	        } else {
	            this.parent.right = successor;
	        }

	        successor.left = this.current.left;
	    }
	}
	//EL MAYOR MAS MENOR

		public Node subBusqueda(Node c) {
			Node successorParent = c;
		    Node successor = c;
		    Node current = c.right;

		    while (current != null) {
		        successorParent = successor;
		        successor = current;
		        current = current.left;
		    }

		    if (successor != c.right) {
		        successorParent.left = successor.right;
		        successor.right = c.right;
		    }

		    return successor;
		}

    
	//EJERCICIO01
	//Ejercicio01-A
	//Método countNodes(), que retorne el número de nodos no-hojas de un BST
		
	public int countNodes(Node x) {
		if (x == null || (x.left == null && x.right == null)) {
			return 0;
		}
	
		return 1 + countNodes(x.left) + countNodes(x.right);
	}

	//Método height(), que retorne la altura de un nodo X cualquiera de un BST 
	//siempre y cuando exista el árbol
	//Ejercicio01-B

	public int height(Node x) {
		if (x == null) {
			return -1; // La altura de un árbol vacío se considera -1
		}
	
		int leftHeight = height(x.left);
		int rightHeight = height(x.right);
	
		return Math.max(leftHeight, rightHeight) + 1;
	}

	//EJERCICIO02
	public int areaBST(Node x){
		int totalNodes = countNodesTotal(x);
		int height = height(x);

		return totalNodes * height;
	}
	
	//EJERCICIO04
	//Método que devuelve el total de nodos de un arbol, de forma recursiva

	public int countNodesTotal(Node node) {
		if (node == null) {
			return 0;
		}
	
		return 1 + countNodesTotal(node.left) + countNodesTotal(node.right);
	}
		
	@Override
	public String toString() {
		return " ";
	}
}