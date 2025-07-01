package com.server.Utils;

import com.server.model.slideTextModel;
import com.server.model.slideDataModel;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ser.std.ByteArraySerializer;

import java.io.*;
import java.util.*;
// import java.util.
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class pptUploader {

    public static List<slideDataModel> pptInfo(MultipartFile file) throws IOException {
    List<slideDataModel> slideDataList = new ArrayList<>();
    Map<String, byte[]> slideXmlMap = new TreeMap<>();

    try (ZipInputStream zipStream = new ZipInputStream(file.getInputStream())) {
        ZipEntry entry;
        while ((entry = zipStream.getNextEntry()) != null) {
            if (entry.getName().startsWith("ppt/slides/slide") && entry.getName().endsWith(".xml")) {
                ByteArrayOutputStream buffer = new ByteArrayOutputStream();
                byte[] temp = new byte[1024];
                int bytesRead;
                while ((bytesRead = zipStream.read(temp)) != -1) {
                    buffer.write(temp, 0, bytesRead);
                }
                slideXmlMap.put(entry.getName(), buffer.toByteArray());
            }
        }
    }

    for (Map.Entry<String, byte[]> slide : slideXmlMap.entrySet()) {
        String slideName = slide.getKey();
        byte[] content = slide.getValue();

        List<slideTextModel> textBoxes = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new ByteArrayInputStream(content));

            NodeList shapeList = doc.getElementsByTagNameNS("*", "sp");

            for (int i = 0; i < shapeList.getLength(); i++) {
                Element shape = (Element) shapeList.item(i);
                String x = "?", y = "?", cx = "?", cy = "?";

                Element spPr = getFirst(shape, "p:spPr");
                if (spPr != null) {
                    Element xfrm = getFirst(spPr, "a:xfrm");
                    if (xfrm != null) {
                        Element off = getFirst(xfrm, "a:off");
                        Element ext = getFirst(xfrm, "a:ext");
                        if (off != null) {
                            x = off.getAttribute("x");
                            y = off.getAttribute("y");
                        }
                        if (ext != null) {
                            cx = ext.getAttribute("cx");
                            cy = ext.getAttribute("cy");
                        }
                    }
                }

                NodeList runs = shape.getElementsByTagNameNS("*", "r");
                for (int j = 0; j < runs.getLength(); j++) {
                    Element run = (Element) runs.item(j);
                    String text = getText(run, "a:t").trim();

                    Element rPr = getFirst(run, "a:rPr");
                    String fontSize = "inherited", fontColor = "inherited";
                    if (rPr != null) {
                        if (rPr.hasAttribute("sz")) fontSize = rPr.getAttribute("sz");

                        Element solidFill = getFirst(rPr, "a:solidFill");
                        if (solidFill != null) {
                            Element srgb = getFirst(solidFill, "a:srgbClr");
                            if (srgb != null) fontColor = srgb.getAttribute("val");
                        }
                    }

                    if (!text.isEmpty()) {
                        textBoxes.add(new slideTextModel(text, fontSize, fontColor, x, y, cx, cy));
                    }
                }
            }

            slideDataList.add(new slideDataModel(slideName, textBoxes));

        } catch (Exception e) {
            System.out.println("Error parsing " + slideName + ": " + e.getMessage());
        }
    }

    return slideDataList;
}

private static Element getFirst(Element parent, String tag) {
    NodeList list = parent.getElementsByTagNameNS("*", tag.replace("p:", "").replace("a:", ""));
    return list.getLength() > 0 ? (Element) list.item(0) : null;
}

private static String getText(Element parent, String tag) {
    NodeList list = parent.getElementsByTagNameNS("*", tag.replace("a:", ""));
    return list.getLength() > 0 ? list.item(0).getTextContent() : "";
}

}