package entities;

import java.util.HashSet;

/***
 * Class of graph vertex.
 */
final class Vertex {
    /***
     * Vertex name.
     */
    final String name;
    /***
     * Set of vertex's ancestors.
     */
    private final HashSet<Vertex> ancestors;
    /***
     * Set of vertex's children.
     */
    private final HashSet<Vertex> children;

    /***
     * Constructor.
     * @param name Name of the vertex.
     */
    Vertex(String name) {
        this.name = name;
        children = new HashSet<>();
        ancestors = new HashSet<>();
    }

    /***
     * Adds parent to the vertex (and all its children).
     * @param parent Parent vertex.
     */
    void AddParent(Vertex parent) {
        updateAncestors(this, parent);
    }

    /***
     * Adds parent and all missing ancestors to the vertex (and all its children).
     * @param current Vertex to update.
     * @param ancestor Its new parent.
     */
    private static void updateAncestors(Vertex current, Vertex ancestor) {
        current.ancestors.addAll(ancestor.ancestors);
        current.ancestors.add(ancestor);
        if (current.children.isEmpty()) {
            return;
        }
        for (var child : current.children) {
            updateAncestors(child, ancestor);
        }
    }

    /***
     * Adds child to the vertex.
     * @param child Child vertex.
     * @return 'true' if the child is one of the vertex's ancestors.
     */
    boolean AddChildren(Vertex child) {
        children.add(child);
        return ancestors.contains(child);
    }

    /***
     * Getter of children set.
     * @return Set of the children.
     */
    HashSet<Vertex> GetChildren() {
        return children;
    }

    /***
     * Informs whether the vertex has ancestors.
     * @return 'true' if there are some ancestors and 'false' otherwise.
     */
    boolean HasAncestors() {
        return !ancestors.isEmpty();
    }

    /***
     * Override hashCode method to make this class work correctly with HashSet and HashMap.
     * @return Hash code.
     */
    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /***
     * Override equals method to make this class work correctly with HashSet and HashMap.
     * @return 'true' if the other object is the same vertex and 'false' otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Vertex vertex) {
            return this.name.equals(vertex.name);
        }
        return false;
    }
}
