package com.marc.stock.graph;


import java.util.*;

public class ImpactTreeNode {

    private static final int MAX_DEPTH = 100;
    private final String type;

    private final ImpactTreeNode parent;
    private final List<ImpactTreeNode> impacts;

    public ImpactTreeNode(String type) {
        this(type, null);
    }

    public ImpactTreeNode(String type, ImpactTreeNode parent) {
        this.type = type;
        this.parent = parent;
        this.impacts = new ArrayList<>();
        if (this.parent != null) {
            this.parent.impacts.add(this);
        }
    }

    public String getType() {
        return type;
    }

    public List<ImpactTreeNode> getImpacts() {
        return impacts;
    }

    public List<ImpactTreePath> getPaths() {
        List<ImpactTreePath> paths = new ArrayList<>();
        int currentDepth = 0;
        Queue<Object> queue = new LinkedList<>();
        queue.add(this);
        queue.add(new ImpactTreePath(Arrays.asList(this.getType())));

        while (!queue.isEmpty()) {
            if (currentDepth > MAX_DEPTH) {
                break;
            }
            ImpactTreeNode head = (ImpactTreeNode) queue.poll();
            ImpactTreePath headPath = (ImpactTreePath) queue.poll();

            if (head.getImpacts().size() == 0) {
                paths.add(headPath);
                continue;
            }
            head.getImpacts().forEach(child -> {
                List<String> segments = new ArrayList<>();
                segments.addAll(headPath.getPathSegments());
                segments.add(child.getType());
                queue.add(child);
                queue.add(new ImpactTreePath(segments));
            });
            currentDepth++;
        }
        return paths;
    }
}
