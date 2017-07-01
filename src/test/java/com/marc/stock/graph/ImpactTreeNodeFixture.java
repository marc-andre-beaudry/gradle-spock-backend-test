package com.marc.stock.graph;

public class ImpactTreeNodeFixture {

    public static ImpactTreeNode buildSimpleDefaultTree() {
        ImpactTreeNode systemNode1 = new ImpactTreeNode("System");

        ImpactTreeNode databaseNode1 = new ImpactTreeNode("Database", systemNode1);
        ImpactTreeNode physicalServerNode1 = new ImpactTreeNode("PhysicalServer", systemNode1);
        ImpactTreeNode physicalServerNode2 = new ImpactTreeNode("PhysicalServer", databaseNode1);
        ImpactTreeNode virtualServerNode1 = new ImpactTreeNode("VirtualServer", systemNode1);
        ImpactTreeNode physicalServerNode3 = new ImpactTreeNode("PhysicalServer", virtualServerNode1);

        ImpactTreeNode webService1 = new ImpactTreeNode("WebService", systemNode1);
        ImpactTreeNode webService2 = new ImpactTreeNode("WebService", webService1);
        ImpactTreeNode webInstance1 = new ImpactTreeNode("WebInstance", webService2);
        ImpactTreeNode physicalServer4 = new ImpactTreeNode("PhysicalServer", webInstance1);
        ImpactTreeNode virtualServerNode2 = new ImpactTreeNode("VirtualServer", webInstance1);
        ImpactTreeNode physicalServer5 = new ImpactTreeNode("PhysicalServer", virtualServerNode2);
        return systemNode1;
    }
}
