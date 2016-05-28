package com.example.a.t10_xml;

import android.os.AsyncTask;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by a on 2016-05-28.
 */
public class MyDomParserTask extends AsyncTask<String, Void, Document> {

    TextView textView;

    public MyDomParserTask(TextView textView) {
        this.textView = textView;
    }

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
            res += ",day : " + getElementText(data, "day");
            res += ",temp : " + getElementText(data, "temp");
            res += ",wfKor : " + getElementText(data, "wfKor");
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
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            doc = db.parse(url.openStream());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return doc;
    }
}
