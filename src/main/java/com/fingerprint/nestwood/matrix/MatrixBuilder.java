package com.fingerprint.nestwood.matrix;

import org.apache.commons.io.IOUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UnknownFormatConversionException;


public class MatrixBuilder {

    private static final String TITLE_ELEMENT = "Title";
    private static final String CONTENT_ELEMENT = "Content;";

    private static final int MAX_STAGES = 2;
    private static final int MAX_TASKS = 2;

    public DevelopmentMatrix buildMatrix(String stagePathRoot) throws IOException, URISyntaxException {

        List<MatrixStage> stages = new ArrayList<>();

        List<String> stagePaths = getStagePaths(stagePathRoot);
        for (String stagePath : stagePaths) {
            MatrixStage currentStage = buildStage(stagePath);
            stages.add(currentStage);
        }

        DevelopmentMatrix matrix = new DevelopmentMatrix();
        matrix.setStages(stages);

        return matrix;
    }

    public MatrixStage buildStage(String stagePath) throws IOException, URISyntaxException {

        List<MatrixTask> tasks = new ArrayList<>();

        List<String> taskPaths = getTaskPaths(stagePath);
        for (String taskPath : taskPaths) {
            MatrixTask currentTask = buildTask(taskPath);
            tasks.add(currentTask);
        }

        MatrixStage stage = new MatrixStage();
        stage.setTitle(null);
        stage.setTasks(tasks);

        return stage;

    }


    public MatrixTask buildTask(String resourcePath) throws IOException {

        MatrixTask task = new MatrixTask();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            //result should be the path of a given stage. Folder should contain stage_content.xml and a list of task xml files
            String xmlContent = IOUtils.toString(classLoader.getResourceAsStream(resourcePath));

            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlContent);

            Element root = doc.getDocumentElement();
            if (root.getTagName().equals("Task")) {
                throw new UnknownFormatConversionException("Unexpected Root Element");
            }

            String title = getInnerContent(root, TITLE_ELEMENT);
            task.setTitle(title);
            String content = getInnerContent(root, CONTENT_ELEMENT);
            task.setContent(content);

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


        return task;
    }

    private String getInnerContent(Element rootElement, String elementName) {

        NodeList titles = rootElement.getElementsByTagName(elementName);
        if (titles.getLength() != 1) {
            throw new UnknownFormatConversionException("Incorrect Element Detected: Expecting single:" + elementName);
        }

        return titles.item(0).getTextContent();

    }


    private List<String> getStagePaths(String stagesPath) {

        List<String> paths = new ArrayList<>();

        for (int i = 1; i <= MAX_STAGES; i++) {
            String path = stagesPath + "/stage" + i;
            paths.add(path);
        }

        return paths;
    }

    private List<String> getTaskPaths(String stagePath) {

        List<String> paths = new ArrayList<>();

        for (int i = 1; i <= MAX_STAGES; i++) {
            String path = stagePath + "/task" + i + ".xml";
            paths.add(path);
        }

        return paths;
    }

}
