package com.marc.stock.graph;

import java.util.List;

public class DefaultRelationApiImpl implements RelationApi {

    private final String sourceType;
    private final String targetType;

    public DefaultRelationApiImpl(String sourceType, String targetType) {
        this.sourceType = sourceType;
        this.targetType = targetType;
    }

    @Override
    public List<Relation> addRelation(List<Resource> inputs) {
        return null;
    }
}
