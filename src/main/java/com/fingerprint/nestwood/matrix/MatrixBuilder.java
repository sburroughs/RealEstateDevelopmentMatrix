package com.fingerprint.nestwood.matrix;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class MatrixBuilder {

    public DevelopmentMatrix buildMatrix() throws MatrixBuildException {

        MatrixHeader stageHeaders = null;
        try {
            String headerNodeXpath = "headers/header[@type='stage']/node";
            stageHeaders = getHeader(headerNodeXpath);
        } catch (Exception e) {
            throw new MatrixBuildException(e);
        }


        MatrixHeader taskHeader = null;
        try {
            String taskNodeXpath = "headers/header[@type='task']/node";
            taskHeader = getHeader(taskNodeXpath);
        } catch (Exception e) {
            throw new MatrixBuildException(e);
        }


        MatrixContent matrixContent = null;
        try {
            matrixContent = getMatrixContent(stageHeaders.getHeaders().size(), taskHeader.getHeaders().size());
        } catch (Exception e) {
            throw new MatrixBuildException(e);
        }


        DevelopmentMatrix matrix = new DevelopmentMatrix(stageHeaders, taskHeader);
        matrix.setMatrix(matrixContent);
        return matrix;
    }

    private MatrixHeader getHeader(String nodesXpath) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {

        Document document = resourceToDocument("headers.xml");

        XPathFactory xPathfactory = XPathFactory.newInstance();
        XPath xpath = xPathfactory.newXPath();
        XPathExpression expr = xpath.compile(nodesXpath);

        List<MatrixNode> headerNodes = new ArrayList<>();

        NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
        int numberOfNodes = nodes.getLength();

        for (int i = 0; i < numberOfNodes; i++) {
            Element nodeElement = (Element) nodes.item(i);
            String title = nodeElement.getElementsByTagName("title").item(0).getTextContent();
            String content = nodeElement.getElementsByTagName("content").item(0).getTextContent();

            MatrixNode matrixNode = new MatrixNode();
            matrixNode.setTitle(title);
            matrixNode.setContent(content);

            headerNodes.add(matrixNode);
        }


        MatrixHeader header = new MatrixHeader();
        header.setHeaders(headerNodes);

        return header;
    }

    private MatrixContent getMatrixContent(int stageSize, int taskSize) throws IOException, ParserConfigurationException, SAXException {

        MatrixContent matrixContent = new MatrixContent(stageSize, taskSize);

        Document document = resourceToDocument("content.xml");

        Element root = document.getDocumentElement();
        NodeList stageNodes = root.getElementsByTagName("stage");
        for (int i = 0; i < stageNodes.getLength(); i++) {
            Element currentStage = (Element) stageNodes.item(i);
            NodeList taskNodes = currentStage.getElementsByTagName("task");
            for (int j = 0; j < taskNodes.getLength(); j++) {

                Node task = taskNodes.item(j);
                String content = task.getTextContent();

                MatrixNode node = new MatrixNode();
                node.setContent(content);

                matrixContent.addContent(i, j, node);
            }
        }


        return matrixContent;
    }

    public Document resourceToDocument(String fileName) throws IOException, ParserConfigurationException, SAXException {

        String path = "/content/" + fileName;

        InputStream resource = null;
        try {
            resource = this.getClass().getResourceAsStream(path);
            String xml = IOUtils.toString(resource, "UTF-8");

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new ByteArrayInputStream(xml.getBytes("utf-8"))));

            return doc;
        } finally {
            if (resource != null) {
                resource.close();
            }
        }
    }


}
