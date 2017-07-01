package com.marc.stock.graph;


public class Relation {

    private final Resource target;
    private final String relationName;

    public Relation(Resource targetValue, String relationName) {
        this.target = targetValue;
        this.relationName = relationName;
    }

    public Resource getTarget() {
        return target;
    }

    public String getRelationName() {
        return relationName;
    }
}
