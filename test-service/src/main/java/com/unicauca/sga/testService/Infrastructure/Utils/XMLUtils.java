package com.unicauca.sga.testService.Infrastructure.Utils;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class XMLUtils {
    private XMLUtils() {}

    // Helper para extraer el texto de un tag hijo
    public static String getTextContent(Element parent, String tagName) {
        NodeList nodes = parent.getElementsByTagName(tagName);
        if (nodes.getLength() == 0) return "";

        String raw = nodes.item(0).getTextContent();

        // Limpiar etiquetas HTML que Moodle puede incluir en el texto
        return raw.replaceAll("<[^>]+>", "").trim();
    }

    // Evita los caracteres problemáticos en serialización
    public static String escapeXml(String text) {
        if (text == null) return "";
        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&apos;");
    }
}
