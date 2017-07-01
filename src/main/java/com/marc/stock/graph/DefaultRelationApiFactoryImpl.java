package com.marc.stock.graph;

public class DefaultRelationApiFactoryImpl implements RelationApiFactory {

    @Override
    public RelationApi buildRelationApi(String sourceType, String targetType) {
        return new DefaultRelationApiImpl(sourceType, targetType);
    }
}
