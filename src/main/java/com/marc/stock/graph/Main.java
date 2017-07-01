package com.marc.stock.graph;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

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
        //physicalServerNode2.getChildren().add(systemNode1);

        ImpactTreePathResolver resolver = new ImpactTreePathResolver(new MockDataRelationApiFactory());
        System.out.println("---- Printing Tree ----");

        systemNode1.getPaths().forEach(path -> {
            System.out.println("-- Metadata path: " + path);
            List<Resource> rootResources = resolver.getData(path, systems());
            rootResources.forEach(rootResource -> {
                rootResource.getPaths().forEach(resourcePath -> {
                    System.out.println(resourcePath);
                });
            });
        });
    }

    private static List<Resource> systems() {
        Resource resource1 = new Resource("System", "System#1");
        Resource resource2 = new Resource("System", "System#2");
        Resource resource3 = new Resource("System", "System#3");
        return Arrays.asList(resource1, resource2, resource3);
    }

    private static void test() {

        ImpactTreeNode internalWebsite1 = new ImpactTreeNode("InternalWebsite");
        ImpactTreeNode publicWebsite1 = new ImpactTreeNode("InternalWebsite");
        ImpactTreeNode webServerVM1 = new ImpactTreeNode("WebServerVM");
        ImpactTreeNode server1 = new ImpactTreeNode("Server");
        ImpactTreeNode san1 = new ImpactTreeNode("SAN");
        ImpactTreeNode erp1 = new ImpactTreeNode("ERP");
        ImpactTreeNode crm1 = new ImpactTreeNode("CRM");
        ImpactTreeNode datawarehouse = new ImpactTreeNode("DataWarehouse");
        ImpactTreeNode databaseVM = new ImpactTreeNode("DatabaseVM");
    }
}
