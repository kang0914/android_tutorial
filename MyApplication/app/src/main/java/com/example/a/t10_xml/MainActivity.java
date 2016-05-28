package com.example.a.t10_xml;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    class MyDomParserTask extends AsyncTask<String, Void, Document>{

        private String getElementText(Element data, String tag){
            NodeList hourList = data.getElementsByTagName(tag);
            Element hour = (Element) hourList.item(0);

            NodeList hourTextList = hour.getChildNodes();
            return hourTextList.item(0).getNodeValue();
        }

        @Override
        protected void onPostExecute(Document document) {
            super.onPostExecute(document);
            String res = "";
            NodeList nodeList = document.getElementsByTagName("data");
            for(int i = 0; i <nodeList.getLength(); i++ ){
                Element data = (Element) nodeList.item(i);
                res += "hour : " + getElementText(data, "hour");
                res += "day : " + getElementText(data, "day");
                res += "temp : " + getElementText(data, "temp");
                res += "wfKor : " + getElementText(data, "wfKor");
                res += "\n";
            }

            textView.setText(res);
        }

        @Override
        protected Document doInBackground(String... params) {
            URL url = null;
            Document doc = null;

            try {
                url = new URL(params[0]);
                DocumentBuilderFactory dbf = new DocumentBuilderFactory.newInstance();
                DocumentBuilder db = dbf.newDocumentBuilder();
                doc = db.parse(url.openStream());
            } catch (Exception e) {
                e.printStackTrace();
            }

            return doc;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        MyDomParserTask task = new MyDomParserTask();
        task.execute("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1162058500");
    }
}
