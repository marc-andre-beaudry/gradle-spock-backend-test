package com.marc.stock.graph;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MockDataRelationApiImpl implements RelationApi {

    private final String sourceType;
    private final String targetType;
    private final int relationCount;

    public MockDataRelationApiImpl(String sourceType, String targetType, int relationCount) {
        this.sourceType = sourceType;
        this.targetType = targetType;
        this.relationCount = relationCount;
    }

    @Override
    public List<Relation> addRelation(List<Resource> inputResources) {

        List<Resource> outputResources = IntStream.range(0, relationCount).mapToObj(x -> {
            String targetName = targetType + "#" + x;
            Resource resource = new Resource(targetType, targetName);
            return resource;
        }).collect(Collectors.toList());

        Random random = new Random();
        return inputResources.stream().map(source -> {
            int targetIndex = random.nextInt(relationCount);
            Resource target = outputResources.get(targetIndex);
            Relation relation = new Relation(target, "depends on");
            source.getRelations().add(relation);
            return relation;
        }).collect(Collectors.toList());
    }
}