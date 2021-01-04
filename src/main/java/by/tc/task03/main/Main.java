package by.tc.task03.main;

import by.tc.task03.dao.exception.DaoException;
import by.tc.task03.entity.Node;
import by.tc.task03.service.NodeBuilder;
import by.tc.task03.service.NodeBuilderFactory;


public class Main {

    public static final String PATH = "./src/main/resources/source.xml";

    public static void main(String[] args)  {

        NodeBuilderFactory factory = NodeBuilderFactory.getInstance();
        NodeBuilder builder = factory.getDocumentBuilder();
        Node root = null;
        try {
            root = builder.parseXML(PATH);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        PrintTree printTree = new PrintTree();
        printTree.print(root);


    }
}
