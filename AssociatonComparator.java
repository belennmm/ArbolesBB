import java.util.Comparator;

/**
 * Representa el comparador para asociar.
 */
class AssociationComparator implements Comparator<Association<String, String>> {
    @Override
    public int compare(Association<String, String> a1, Association<String, String> a2) {
        return a1.getKey().compareTo(a2.getKey());
    }
}