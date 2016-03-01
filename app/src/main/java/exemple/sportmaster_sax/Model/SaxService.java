package exemple.sportmaster_sax.Model;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class SaxService {

    public SaxService() {
        // TODO Auto-generated constructor stub
    }

    public static List<HashMap<String, String>> readXML(InputStream inputStream,String nodeName)
    {
        try {
            //实例化SAX工厂类
            SAXParserFactory factory= SAXParserFactory.newInstance();
            //实例化SAX解析器。
            SAXParser sParser=factory.newSAXParser();
            //实例化DefaultHandler，设置需要解析的节点
            MyHandler myHandler=new MyHandler(nodeName);
            // 开始解析
            sParser.parse(inputStream, myHandler);
            // 解析完成之后，关闭流
            inputStream.close();
            //返回解析结果。
            return myHandler.getList();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

}