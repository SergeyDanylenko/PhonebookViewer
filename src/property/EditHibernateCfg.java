package property;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;

/**
 * Created by ASUP-9 on 29.08.2017.
 */
public class EditHibernateCfg {
    String filepath;
    String basePath;

    public EditHibernateCfg(String filepath, String basePath) {
        this.filepath = filepath;
        this.basePath = basePath;
        try {
            File fXmlFile = new File(filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
//            рекомендація з документації
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("session-factory");
            for (int i = 0; i < nList.getLength(); i++) {
                Node node = nList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    eElement.getElementsByTagName("property").item(0).getFirstChild().setTextContent(basePath);
                }
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filepath));
            transformer.transform(source, result);
//            FileOutputStream out = new FileOutputStream(filepath);
//            Writer writer = new OutputStreamWriter(out, "windows-1251");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveToXML(String xml) {
        Document dom;
        Element e = null;

        // instance of a DocumentBuilderFactory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // use factory to get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();
            // create instance of DOM
            dom = db.newDocument();

            // create the root element
            Element rootEle = dom.createElement("hibernate-configuration");

            // create data elements and place them under root
            e = dom.createElement("role1");
            e.appendChild(dom.createTextNode("role1"));
            rootEle.appendChild(e);

            e = dom.createElement("role2");
            e.appendChild(dom.createTextNode("role2"));
            rootEle.appendChild(e);

            e = dom.createElement("role3");
            e.appendChild(dom.createTextNode("role3"));
            rootEle.appendChild(e);

            e = dom.createElement("role4");
            e.appendChild(dom.createTextNode("role4"));
            rootEle.appendChild(e);

            dom.appendChild(rootEle);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty(OutputKeys.STANDALONE, "yes");
                tr.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,
                        "\n\t\t-//Hibernate/Hibernate Configuration DTD//EN\"\n" +
                        "\t\t\"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                // send DOM to file
                tr.transform(new DOMSource(dom),
                        new StreamResult(new FileOutputStream(xml)));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }

    public static void main(String[] args) {
        saveToXML("1.xml");
    }
}
