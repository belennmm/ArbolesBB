import java.util.Comparator;

/**
 * Representa al Árbol binario del programa.
 */
class BinaryTree<E> {
    // ignorar la mayúscula al comparar 
    private Comparator<E> comparator;

    private class Node{
        private E data;
        private Node left;
        private Node right;

        public Node(E data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    private Node root;

    /**
     * Constructor del Árbol binario.
     * @param comparator
     */
    public BinaryTree(Comparator<E> comparator) {
        this.comparator = comparator;
        root = null;
    }

    // insertar un dato en el árbol
    public void insertar(E data) {
        root = insertar(root, data);
    }

    private Node insertar(Node node, E data) {
        if (node == null){
            return new Node(data);
        }

        // comprar las cadenar que hay
        if (comparator.compare(data, node.data) < 0) {
            node.left = insertar(node.left, data);
        } 
        else if (comparator.compare(data, node.data) > 0) {
            node.right = insertar(node.right, data);
        }

        return node;
    }

    // recorrer el inOrder
    public void inOrder() {
        inOrder(root);
    }

    /**
     * Representa el inOrder del programa.
     * @param node
     */
    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(((Association<String, String>) node.data).getKey() + ", " + ((Association<String, String>) node.data).getValue() + ", "); // Imprimir la clave y el valor de la asociación
            inOrder(node.right);
        }
    }

    // buscar una asociación en el árbol es decir la palabra relacionada
    public E buscar(E data) {
        return buscar(root, data);
    }

    private E buscar(Node node, E data) {
        if(node == null){
            return null;
        }

        //comprar sin contar las mayúsculas

        if (comparator.compare(data, node.data) < 0 ){
            return buscar(node.left, data);
        } 
        else if (comparator.compare(data, node.data) > 0 ){
            return buscar(node.right, data);
        } 
        else {
            return node.data;
        }
    }
}