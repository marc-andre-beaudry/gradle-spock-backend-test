package com.marc.stock.graph;

import java.util.List;

public class ImpactTreePath {

    private final List<String> pathSegments;

    public ImpactTreePath(List<String> pathSegments) {
        this.pathSegments = pathSegments;
    }

    public List<String> getPathSegments() {
        return pathSegments;
    }

    @Override
    public String toString() {
        return String.join("->", pathSegments);
    }
}