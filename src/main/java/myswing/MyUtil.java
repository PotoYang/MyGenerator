package myswing;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/4/26 20:10
 * Modified By:
 * Description:
 */
public class MyUtil {
    public static void generate(String database, String table) {
        updateGeneratorConfigXml(database, table);
    }

    private static void updateGeneratorConfigXml(String database, String table) {
        try {
            Document document = new SAXReader().read(new File("generatorConfig.xml"));
            Element element = document.getRootElement()
                    .element("context").element("jdbcConnection");
            element.addAttribute("connectionURL", "jdbc:mysql://localhost:3306/" + database);
            element = document.getRootElement().element("context").element("table");
            element.addAttribute("tableName", table);
            element.addAttribute("domainObjectName", firstCharUpper(table));

            FileOutputStream outputStream = new FileOutputStream("generatorConfig.xml");
            OutputFormat format = OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer = new XMLWriter(outputStream, format);
            writer.write(document);
            writer.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String firstCharUpper(String table) {
        char first = table.charAt(0);
        return String.valueOf(first).toUpperCase().concat(table.substring(1, table.length()));
    }
}
