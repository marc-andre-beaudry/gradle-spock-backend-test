package com.marc.stock.graph;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainIntegrationTest {

    @Mock
    private RelationApiFactory factory;
    private ImpactTreeNode systemNode;

    @Before
    public void setup() {
        systemNode = ImpactTreeNodeFixture.buildSimpleDefaultTree();
    }

    @Test
    public void test_path() {
        List<ImpactTreePath> paths = systemNode.getPaths();
        assertEquals("We have 5 paths", 5, paths.size());
    }

    @Test
    public void test_get_data() {
        List<ImpactTreePath> paths = systemNode.getPaths();

        assertEquals("We have 5 paths", 5, paths.size());
    }



}
