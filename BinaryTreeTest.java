import org.junit.Test;

public class BinaryTreeTest {

    @Test
    public void testInsertar() {
        BinaryTree<Association<String, String>> tree = new BinaryTree<>(new AssociationComparator());
        Association<String, String> association1 = new Association<>("house", "casa");
        Association<String, String> association2 = new Association<>("dog", "perro");
        Association<String, String> association3 = new Association<>("cat", "gato");

        
        tree.insertar(association1);
        tree.insertar(association2);
        tree.insertar(association3);

       
        assertTrue(tree.contains(association1));
        assertTrue(tree.contains(association2));
        assertTrue(tree.contains(association3));
    }

    @Test
    public void testSearch() {
        BinaryTree<Association<String, String>> tree = new BinaryTree<>(new AssociationComparator());
        Association<String, String> association1 = new Association<>("house", "casa");
        Association<String, String> association2 = new Association<>("dog", "perro");
        Association<String, String> association3 = new Association<>("cat", "gato");

        
        tree.insertar(association1);
        tree.insertar(association2);
        tree.insertar(association3);

        
        assertEquals(association1, tree.buscar(new Association<>("house", null)));
        assertEquals(association2, tree.buscar(new Association<>("dog", null)));
        assertEquals(association3, tree.buscar(new Association<>("cat", null)));
        
       
        assertNull(tree.buscar(new Association<>("bird", null)));
    }
}
