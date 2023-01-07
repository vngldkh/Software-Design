package entities;

import java.util.HashSet;

final class Vertex {
    final String name;
    private final String content;
    private final HashSet<Vertex> ancestors;
    private final HashSet<Vertex> children;

    Vertex(String name, String content) {
        this.name = name;
        this.content = content;
        children = new HashSet<>();
        ancestors = new HashSet<>();
    }

    void AddParent(Vertex parent) {
        updateAncestors(this, parent);
    }

    private void updateAncestors(Vertex current, Vertex ancestor) {
        current.ancestors.addAll(ancestor.ancestors);
        current.ancestors.add(ancestor);
        if (children.isEmpty()) {
            return;
        }
        for (var child : children) {
            updateAncestors(child, ancestor);
        }
    }

    boolean AddChildren(Vertex child) {
        children.add(child);
        return ancestors.contains(child);
    }

    HashSet<Vertex> GetChildren() {
        return children;
    }

    boolean HasAncestors() {
        return !ancestors.isEmpty();
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Vertex vertex) {
            return this.name.equals(vertex.name);
        }
        return false;
    }
}
