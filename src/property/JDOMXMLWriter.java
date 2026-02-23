package property;

import org.jdom2.DocType;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by ASUP-9 on 30.08.2017.
 */
public class JDOMXMLWriter {
    public JDOMXMLWriter(String fileName, String basePath) {
        Document doc = new Document();
        doc.setDocType(new DocType("hibernate-configuration", "-//Hibernate/Hibernate Configuration DTD//EN",
                "http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd"));
        doc.setRootElement(new Element("hibernate-configuration"));
        Element elSessionFactory = new Element("session-factory");

        Element elConURL = new Element("property");
        elConURL.setAttribute("name", "connection.url");
        elConURL.addContent("jdbc:sqlite:" + basePath);
        elSessionFactory.addContent(elConURL);

        Element elConDriverClass = new Element("property");
        elConDriverClass.setAttribute("name", "connection.driver_class");
        elConDriverClass.addContent("org.sqlite.JDBC");
        elSessionFactory.addContent(elConDriverClass);

        Element elConUsername = new Element("property");
        elConUsername.setAttribute("name", "connection.username");
        elSessionFactory.addContent(elConUsername);

        Element elConPassword = new Element("property");
        elConPassword.setAttribute("name", "connection.password");
        elSessionFactory.addContent(elConPassword);

        Element elShowSQL = new Element("property");
        elShowSQL.setAttribute("name", "show_sql");
        elShowSQL.addContent("false");
        elSessionFactory.addContent(elShowSQL);

        Element elHibDialect = new Element("property");
        elHibDialect.setAttribute("name", "hibernate.dialect");
        elHibDialect.addContent("org.hibernate.dialect.HSQLDialect");
        elSessionFactory.addContent(elHibDialect);

        Element elCurrSessionContClass = new Element("property");
        elCurrSessionContClass.setAttribute("name", "current_session_context_class");
        elCurrSessionContClass.addContent("thread");
        elSessionFactory.addContent(elCurrSessionContClass);

        Element elHibConnAutoCommit = new Element("property");
        elHibConnAutoCommit.setAttribute("name", "hibernate.connection.autocommit");
        elHibConnAutoCommit.addContent("true");
        elSessionFactory.addContent(elHibConnAutoCommit);

        Element elMappingClass= new Element("mapping");
        elMappingClass.setAttribute("class", "logic.Employee");
        elSessionFactory.addContent(elMappingClass);

        doc.getRootElement().addContent(elSessionFactory);

        XMLOutputter xmlOutputter = new XMLOutputter(Format.getPrettyFormat());
        try {
            xmlOutputter.output(doc, new FileOutputStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}