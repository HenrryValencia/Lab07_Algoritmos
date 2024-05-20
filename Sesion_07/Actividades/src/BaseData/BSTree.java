package BaseData;
import java.util.Stack;

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
		
	@Override
	public String toString() {
		return " ";
	}
	///////////
	public int countNodes() {
	    return countNonLeafNodes(root);
	}

	private int countNonLeafNodes(Node node) {
	    if (node == null || (node.left == null && node.right == null)) {
	        return 0; // Si el nodo es null o es una hoja, no se cuenta como nodo no hoja ojo!!!
	    }
	    return 1 + countNonLeafNodes(node.left) + countNonLeafNodes(node.right);
	}
	///////////
	public int height(E x) {
	    return getNodeHeight(root, x);
	}

	private int getNodeHeight(Node node, E x) {
	    if (node == null) {
	        return -1; // Si el nodo es null, no hay altura
	    }

	    if (node.getData().equals(x)) {
	        return 0; // Altura de un nodo es 0
	    }

	    int leftHeight = getNodeHeight(node.left, x);
	    int rightHeight = getNodeHeight(node.right, x);

	    if (leftHeight == -1 && rightHeight == -1) {
	        return -1; // El nodo no está en el árbol
	    }

	    return 1 + Math.max(leftHeight, rightHeight); // Altura del nodo actual es 1 más el maximo de las alturas de sus hijos
	}
	//////////
	public int areaBST() {
        int leafNodes = countLeafNodes(root);
        int treeHeight = height(root);
        return leafNodes * treeHeight;
    }

    private int countLeafNodes(Node node) {
        if (node == null) {
            return 0;
        }
        if (node.left == null && node.right == null) {
            return 1;
        }
        return countLeafNodes(node.left) + countLeafNodes(node.right);
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }
    ///////////
    public void iterativePreOrder() {
        if (root == null) {
            return;
        }

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node current = stack.pop();
            System.out.print(current.getData() + " ");

            if (current.right != null) {
                stack.push(current.right);
            }
            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }
    ///////////
    public int totalNodes() {
        return countNodes(root);
    }

    private int countNodes(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + countNodes(node.left) + countNodes(node.right);
    }
	
}
