package com.marc.stock.graph;

import java.util.List;
import java.util.stream.Collectors;

public class ImpactTreePathResolver {

    private final RelationApiFactory relationApiFactory;

    public ImpactTreePathResolver(RelationApiFactory relationApiFactory) {
        this.relationApiFactory = relationApiFactory;
    }

    public List<Resource> getData(ImpactTreePath path, List<Resource> startingResources) {
        boolean anyWithRelations = startingResources.stream().anyMatch(x -> x.getRelations().size() > 0);
        if (anyWithRelations) {
            throw new RuntimeException("Invalid starting resources");
        }

        List<String> segments = path.getPathSegments();
        List<Resource> headResources = startingResources;
        if (segments.size() < 1) {
            return headResources;
        }
        for (int i = 0; i < segments.size() - 1; i++) {
            String sourceType = segments.get(i);
            String targetType = segments.get(i + 1);
            RelationApi relationApi = relationApiFactory.buildRelationApi(sourceType, targetType);
            List<Relation> relations = relationApi.addRelation(headResources);
            headResources = relations
                    .stream()
                    .map(x -> x.getTarget())
                    .distinct()
                    .collect(Collectors.toList());
        }
        return startingResources;
    }
}
