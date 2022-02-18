package io.github.kloping.little_web;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author github.kloping
 */
public class MimeMapping {
    private static final Map<String, String> FINAL = new HashMap<>();

    public static String get(String name) {
        if (FINAL.containsKey(name)) {
            return FINAL.get(name);
        } else {
            return "null";
        }
    }

    public static void forEach(BiConsumer<String, String> consumer) {
        FINAL.forEach(consumer);
    }

    static {
        FINAL.put("abs", "audio/x-mpeg");
        FINAL.put("ai", "application/postscript");
        FINAL.put("aif", "audio/x-aiff");
        FINAL.put("aifc", "audio/x-aiff");
        FINAL.put("aiff", "audio/x-aiff");
        FINAL.put("aim", "application/x-aim");
        FINAL.put("art", "image/x-jg");
        FINAL.put("asf", "video/x-ms-asf");
        FINAL.put("asx", "video/x-ms-asf");
        FINAL.put("au", "audio/basic");
        FINAL.put("avi", "video/x-msvideo");
        FINAL.put("avx", "video/x-rad-screenplay");
        FINAL.put("bcpio", "application/x-bcpio");
        FINAL.put("bin", "application/octet-stream");
        FINAL.put("bmp", "image/bmp");
        FINAL.put("body", "text/html");
        FINAL.put("cdf", "application/x-cdf");
        FINAL.put("cer", "application/pkix-cert");
        FINAL.put("class", "application/java");
        FINAL.put("cpio", "application/x-cpio");
        FINAL.put("csh", "application/x-csh");
        FINAL.put("css", "text/css");
        FINAL.put("dib", "image/bmp");
        FINAL.put("doc", "application/msword");
        FINAL.put("dtd", "application/xml-dtd");
        FINAL.put("dv", "video/x-dv");
        FINAL.put("dvi", "application/x-dvi");
        FINAL.put("eot", "application/vnd.ms-fontobject");
        FINAL.put("eps", "application/postscript");
        FINAL.put("etx", "text/x-setext");
        FINAL.put("exe", "application/octet-stream");
        FINAL.put("gif", "image/gif");
        FINAL.put("gtar", "application/x-gtar");
        FINAL.put("gz", "application/x-gzip");
        FINAL.put("hdf", "application/x-hdf");
        FINAL.put("hqx", "application/mac-binhex40");
        FINAL.put("htc", "text/x-component");
        FINAL.put("htm", "text/html");
        FINAL.put("html", "text/html");
        FINAL.put("ief", "image/ief");
        FINAL.put("jad", "text/vnd.sun.j2me.app-descriptor");
        FINAL.put("jar", "application/java-archive");
        FINAL.put("java", "text/x-java-source");
        FINAL.put("jnlp", "application/x-java-jnlp-file");
        FINAL.put("jpe", "image/jpeg");
        FINAL.put("jpeg", "image/jpeg");
        FINAL.put("jpg", "image/jpeg");
        FINAL.put("js", "application/javascript");
        FINAL.put("jsf", "text/plain");
        FINAL.put("json", "application/json");
        FINAL.put("jspf", "text/plain");
        FINAL.put("kar", "audio/midi");
        FINAL.put("latex", "application/x-latex");
        FINAL.put("m3u", "audio/x-mpegurl");
        FINAL.put("mac", "image/x-macpaint");
        FINAL.put("man", "text/troff");
        FINAL.put("mathml", "application/mathml+xml");
        FINAL.put("me", "text/troff");
        FINAL.put("mid", "audio/midi");
        FINAL.put("midi", "audio/midi");
        FINAL.put("mif", "application/x-mif");
        FINAL.put("mov", "video/quicktime");
        FINAL.put("movie", "video/x-sgi-movie");
        FINAL.put("mp1", "audio/mpeg");
        FINAL.put("mp2", "audio/mpeg");
        FINAL.put("mp3", "audio/mpeg");
        FINAL.put("mp4", "video/mp4");
        FINAL.put("mpa", "audio/mpeg");
        FINAL.put("mpe", "video/mpeg");
        FINAL.put("mpeg", "video/mpeg");
        FINAL.put("mpega", "audio/x-mpeg");
        FINAL.put("mpg", "video/mpeg");
        FINAL.put("mpv2", "video/mpeg2");
        FINAL.put("ms", "application/x-wais-source");
        FINAL.put("nc", "application/x-netcdf");
        FINAL.put("oda", "application/oda");
        FINAL.put("odb", "application/vnd.oasis.opendocument.database");
        FINAL.put("odc", "application/vnd.oasis.opendocument.chart");
        FINAL.put("odf", "application/vnd.oasis.opendocument.formula");
        FINAL.put("odg", "application/vnd.oasis.opendocument.graphics");
        FINAL.put("odi", "application/vnd.oasis.opendocument.image");
        FINAL.put("odm", "application/vnd.oasis.opendocument.text-master");
        FINAL.put("odp", "application/vnd.oasis.opendocument.presentation");
        FINAL.put("ods", "application/vnd.oasis.opendocument.spreadsheet");
        FINAL.put("odt", "application/vnd.oasis.opendocument.text");
        FINAL.put("otg", "application/vnd.oasis.opendocument.graphics-template");
        FINAL.put("oth", "application/vnd.oasis.opendocument.text-web");
        FINAL.put("otp", "application/vnd.oasis.opendocument.presentation-template");
        FINAL.put("ots", "application/vnd.oasis.opendocument.spreadsheet-template ");
        FINAL.put("ott", "application/vnd.oasis.opendocument.text-template");
        FINAL.put("ogx", "application/ogg");
        FINAL.put("ogv", "video/ogg");
        FINAL.put("oga", "audio/ogg");
        FINAL.put("ogg", "audio/ogg");
        FINAL.put("otf", "application/x-font-opentype");
        FINAL.put("spx", "audio/ogg");
        FINAL.put("flac", "audio/flac");
        FINAL.put("anx", "application/annodex");
        FINAL.put("axa", "audio/annodex");
        FINAL.put("axv", "video/annodex");
        FINAL.put("xspf", "application/xspf+xml");
        FINAL.put("pbm", "image/x-portable-bitmap");
        FINAL.put("pct", "image/pict");
        FINAL.put("pdf", "application/pdf");
        FINAL.put("pgm", "image/x-portable-graymap");
        FINAL.put("pic", "image/pict");
        FINAL.put("pict", "image/pict");
        FINAL.put("pls", "audio/x-scpls");
        FINAL.put("png", "image/png");
        FINAL.put("pnm", "image/x-portable-anymap");
        FINAL.put("pnt", "image/x-macpaint");
        FINAL.put("ppm", "image/x-portable-pixmap");
        FINAL.put("ppt", "application/vnd.ms-powerpoint");
        FINAL.put("pps", "application/vnd.ms-powerpoint");
        FINAL.put("ps", "application/postscript");
        FINAL.put("psd", "image/vnd.adobe.photoshop");
        FINAL.put("qt", "video/quicktime");
        FINAL.put("qti", "image/x-quicktime");
        FINAL.put("qtif", "image/x-quicktime");
        FINAL.put("ras", "image/x-cmu-raster");
        FINAL.put("rdf", "application/rdf+xml");
        FINAL.put("rgb", "image/x-rgb");
        FINAL.put("rm", "application/vnd.rn-realmedia");
        FINAL.put("roff", "text/troff");
        FINAL.put("rtf", "application/rtf");
        FINAL.put("rtx", "text/richtext");
        FINAL.put("sfnt", "application/font-sfnt");
        FINAL.put("sh", "application/x-sh");
        FINAL.put("shar", "application/x-shar");
        FINAL.put("sit", "application/x-stuffit");
        FINAL.put("snd", "audio/basic");
        FINAL.put("src", "application/x-wais-source");
        FINAL.put("sv4cpio", "application/x-sv4cpio");
        FINAL.put("sv4crc", "application/x-sv4crc");
        FINAL.put("svg", "image/svg+xml");
        FINAL.put("svgz", "image/svg+xml");
        FINAL.put("swf", "application/x-shockwave-flash");
        FINAL.put("t", "text/troff");
        FINAL.put("tar", "application/x-tar");
        FINAL.put("tcl", "application/x-tcl");
        FINAL.put("tex", "application/x-tex");
        FINAL.put("texi", "application/x-texinfo");
        FINAL.put("texinfo", "application/x-texinfo");
        FINAL.put("tif", "image/tiff");
        FINAL.put("tiff", "image/tiff");
        FINAL.put("tr", "text/troff");
        FINAL.put("tsv", "text/tab-separated-values");
        FINAL.put("ttf", "application/x-font-ttf");
        FINAL.put("txt", "text/plain");
        FINAL.put("ulw", "audio/basic");
        FINAL.put("ustar", "application/x-ustar");
        FINAL.put("vxml", "application/voicexml+xml");
        FINAL.put("xbm", "image/x-xbitmap");
        FINAL.put("xht", "application/xhtml+xml");
        FINAL.put("xhtml", "application/xhtml+xml");
        FINAL.put("xls", "application/vnd.ms-excel");
        FINAL.put("xml", "application/xml");
        FINAL.put("xpm", "image/x-xpixmap");
        FINAL.put("xsl", "application/xml");
        FINAL.put("xslt", "application/xslt+xml");
        FINAL.put("xul", "application/vnd.mozilla.xul+xml");
        FINAL.put("xwd", "image/x-xwindowdump");
        FINAL.put("vsd", "application/vnd.visio");
        FINAL.put("wav", "audio/x-wav");
        FINAL.put("wbmp", "image/vnd.wap.wbmp");
        FINAL.put("wml", "text/vnd.wap.wml");
        FINAL.put("wmlc", "application/vnd.wap.wmlc");
        FINAL.put("wmls", "text/vnd.wap.wmlsc");
        FINAL.put("wmlscriptc", "application/vnd.wap.wmlscriptc");
        FINAL.put("wmv", "video/x-ms-wmv");
        FINAL.put("woff", "application/font-woff");
        FINAL.put("woff2", "application/font-woff2");
        FINAL.put("wrl", "model/vrml");
        FINAL.put("wspolicy", "application/wspolicy+xml");
        FINAL.put("z", "application/x-compress");
        FINAL.put("zip", "application/zip");
    }

}
