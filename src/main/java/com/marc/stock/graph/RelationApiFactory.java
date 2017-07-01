package com.marc.stock.graph;

public interface RelationApiFactory {
    RelationApi buildRelationApi(String sourceType, String targetType);
}
