package com.marc.stock.graph;

public class MockDataRelationApiFactory implements RelationApiFactory {

    @Override
    public RelationApi buildRelationApi(String sourceType, String targetType) {
        return new MockDataRelationApiImpl(sourceType, targetType, 3);
    }
}
