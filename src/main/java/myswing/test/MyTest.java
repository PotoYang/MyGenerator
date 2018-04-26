package myswing.test;

import myswing.DatabaseConnect;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with Intellij IDEA.
 *
 * @author potoyang
 * Create: 2018/4/26 17:07
 * Modified By:
 * Description:
 */
public class MyTest {
    @Test
    public void testGetDatabase() {
        DatabaseConnect databaseConnect = new DatabaseConnect();
        databaseConnect.init();
        databaseConnect.getDatabase();
    }

    @Test
    public void testXmlWrite01() {
        try {
            Document document = new SAXReader().read(new File("student.xml"));
            FileOutputStream outputStream = new FileOutputStream("d:\\student.xml");
            XMLWriter writer = new XMLWriter(outputStream);
            writer.write(document);
            writer.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testXmlUpdate() {
    }

    @Test
    public void upperFirst() {
        String table = "appstore";
        char first = table.charAt(0);
        String temp = String.valueOf(first).toUpperCase().concat(table.substring(1, table.length()));
        System.out.println(temp);
    }
}
