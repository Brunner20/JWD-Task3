package by.tc.task03.service;


import by.tc.task03.service.impl.NodeBuilderImpl;

public class NodeBuilderFactory {

    private static final NodeBuilderFactory instance = new NodeBuilderFactory();

    private final NodeBuilder nodeBuilder = new NodeBuilderImpl();


    private NodeBuilderFactory(){}


    public static NodeBuilderFactory getInstance(){
        return instance;
    }

    public NodeBuilder getDocumentBuilder(){ return nodeBuilder;}
}
