package com.marc.stock.graph;

import java.util.*;

public class Resource {

    private static final int MAX_DEPTH = 100;
    private final String type;
    private final String name;
    private final List<Relation> relations = new ArrayList<>();

    public Resource(String type, String name) {
        this.type = type;
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public List<Relation> getRelations() {
        return relations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        if (type != null ? !type.equals(resource.type) : resource.type != null) return false;
        return name != null ? name.equals(resource.name) : resource.name == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public List<String> getPaths() {
        List<String> paths = new ArrayList<>();
        int currentDepth = 0;
        Queue<Object> queue = new LinkedList<>();
        queue.add(this);
        queue.add(format(this.getType(), this.getName()));

        while (!queue.isEmpty()) {
            if (currentDepth > MAX_DEPTH) {
                break;
            }
            Resource head = (Resource) queue.poll();
            String headPath = (String) queue.poll();

            if (head.getRelations().size() == 0) {
                paths.add(headPath);
                continue;
            }
            head.getRelations().forEach(relation -> {
                String target = format(relation.getTarget().getType(), relation.getTarget().getName());
                String str = headPath + "-" + relation.getRelationName() + "-" + target;
                queue.add(relation.getTarget());
                queue.add(str);
            });
            currentDepth++;
        }
        return paths;
    }

    private String format(String type, String name) {
        //return "[" + type + "](" + name + ")";
        return "(" + name + ")";
    }
}
