package BaseData;
public class BSTree <E extends Comparable<E>> {
    class Node {
        protected E data;
        protected Node left, right;

        public Node(E data) {
            this(data, null, null);
        }

        public Node(E data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public E getData() {
            return this.data;
        }

        public void setRight(Node x) {
            this.right = x;
        }

        public void setLeft(Node x) {
            this.left = x;
        }
    }

    private Node root;

    public BSTree() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void insert(E x) throws Exception {
        Node newNode = new Node(x);
        if (isEmpty()) {
            this.root = newNode;
            return;
        }
        Node current = root;
        Node parent = null;
        while (current != null) {
            parent = current;
            if (x.compareTo(current.getData()) > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        if (x.compareTo(parent.getData()) > 0) {
            parent.setRight(newNode);
        } else {
            parent.setLeft(newNode);
        }
    }

    public boolean search(E x) throws Exception {
        Node current = root;
        while (current != null) {
            if (x.compareTo(current.getData()) == 0) {
                return true;
            } else if (x.compareTo(current.getData()) > 0) {
                current = current.right;
            } else {
                current = current.left;
            }
        }
        return false;
    }

    public void delete(E dato) {
        Node current = root;
        Node parent = null;
        boolean isLeftChild = true;

        // Buscar el nodo a eliminar
        while (current != null && !dato.equals(current.getData())) {
            parent = current;
            if (dato.compareTo(current.getData()) < 0) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
        }

        if (current == null) {
            return; // Nodo no encontrado
        }

        // Nodo sin hijos
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        }
        // Nodo con un solo hijo (derecho)
        else if (current.left == null) {
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        }
        // Nodo con un solo hijo (izquierdo)
        else if (current.right == null) {
            if (current == root) {
                root = current.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        }
        // Nodo con dos hijos
        else {
            Node successor = getSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
    }

    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.right;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if (successor != delNode.right) {
            successorParent.left = successor.right;
            successor.right = delNode.right;
        }
        return successor;
    }

    @Override
    public String toString() {
        return "BSTree";
    }
