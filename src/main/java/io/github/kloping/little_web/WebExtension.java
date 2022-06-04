package io.github.kloping.little_web;

import io.github.kloping.MySpringTool.StarterApplication;
import io.github.kloping.MySpringTool.interfaces.Extension;
import io.github.kloping.MySpringTool.interfaces.component.ContextManager;
import io.github.kloping.MySpringTool.interfaces.component.up0.ClassAttributeManager;
import io.github.kloping.io.ReadUtils;
import io.github.kloping.little_web.annotations.WebRestController;
import io.github.kloping.little_web.conf.TomcatConfig;
import io.github.kloping.little_web.impl.RequestManagerImpl0;
import io.github.kloping.little_web.page.ErrorPage;
import io.github.kloping.little_web.servlets.BaseServlet;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import static io.github.kloping.MySpringTool.h1.impl.component.HttpClientManagerImpl.SPLIT;
import static io.github.kloping.little_web.conf.TomcatConfig.CLASSPATH_KEY;


/**
 * @author github.kloping
 */
public class WebExtension implements Extension.ExtensionRunnable, ClassAttributeManager {
    @Override
    public void manager(AccessibleObject accessibleObject, ContextManager contextManager) throws InvocationTargetException, IllegalAccessException {
    }


    public static WebExtension extension = null;

    @Override
    public void manager(Class clsz, ContextManager contextManager) throws IllegalAccessException, InvocationTargetException {
        Object o = null;
        try {
            o = StarterApplication.Setting.INSTANCE.getInstanceCrater().create(clsz, contextManager);
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        contextManager.append(o);
        StarterApplication.STARTED_RUNNABLE.add(() -> {
            for (Field declaredField : clsz.getDeclaredFields()) {
                declaredField.setAccessible(true);
                try {
                    StarterApplication.Setting.INSTANCE.getFieldManager().manager(declaredField, contextManager);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        WebRestController c0 = (WebRestController) clsz.getDeclaredAnnotation(WebRestController.class);
        String s = c0.value();
        FATHER_PATH_MAPPING.put(clsz, filter(s));
        for (Method declaredMethod : clsz.getDeclaredMethods()) {
            declaredMethod.setAccessible(true);
            RequestManagerImpl0.INSTANCE.manager(declaredMethod, contextManager);
        }
        extension = this;
    }

    public Map<Class, String> FATHER_PATH_MAPPING = new HashMap<>();

    public String getFatherPath(Class cla) {
        return FATHER_PATH_MAPPING.containsKey(cla) ? FATHER_PATH_MAPPING.get(cla) : "";
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }

    public static Tomcat tomcat = null;

    @Override
    public void run() throws Throwable {
        StarterApplication.PRE_SCAN_RUNNABLE.add(() -> {
            StarterApplication.Setting.INSTANCE.getClassManager().registeredAnnotation(WebRestController.class, this);
            StarterApplication.POST_SCAN_RUNNABLE.add(() -> {
                try {
                    startServer();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            });
        });
    }

    private void startServer() throws Throwable {
        if (tomcat == null) {
            tomcat = new Tomcat();
        }
        tomcat.setBaseDir(createTempDir("base").getAbsolutePath());
        tomcat.getService().setName(TomcatConfig.DEFAULT.getName());
        tomcat.getConnector().setPort(TomcatConfig.DEFAULT.getPort());
        configContext(tomcat);
        tomcat.getServer().start();
        tomcat.getServer().await();
    }

    public static File tempDir = null;

    public void configContext(Tomcat tomcat) {
        String doBase = copyClassPathFileToTempDir(TomcatConfig.DEFAULT.getStaticPath(), tempDir = createTempDir("temp-static"));
        Context context = tomcat.addContext("", doBase);
        context.setResources(new StandardRoot(context));
        if (TomcatConfig.DEFAULT.getErrorPage() != null) {
            context.addErrorPage(new ErrorPage(TomcatConfig.DEFAULT.getErrorPage()));
        }
        tomcat.addServlet("", "servlet0", new BaseServlet());
        context.addServletMappingDecoded("/", "servlet0");
        MimeMapping.forEach((k, v) -> {
            context.addMimeMapping(k, v);
        });
    }

    public static String copyClassPathFileToTempDir(String st, File dir) {
        try {
            if (st.startsWith(CLASSPATH_KEY)) {
                st = st.substring(10);
                URL url = WebExtension.class.getClassLoader().getResource(st);
                if (url != null) {
                    if ("jar".equals(url.getProtocol())) {
                        String ofn = url.getPath();
                        String fn = ofn.substring(ofn.indexOf("/"), ofn.indexOf("!"));
                        JarFile jarFile = new JarFile(fn);
                        Enumeration<JarEntry> enumeration = jarFile.entries();
                        while (enumeration.hasMoreElements()) {
                            JarEntry entry = enumeration.nextElement();
                            if (!entry.getName().startsWith(st)) {
                                continue;
                            }
                            if (!entry.isDirectory()) {
                                InputStream is = jarFile.getInputStream(entry);
                                File file = new File(dir, entry.getName());
                                file.getParentFile().mkdirs();
                                file.getParentFile().deleteOnExit();
                                file.createNewFile();
                                file.deleteOnExit();
                                ReadUtils.copy(is, new FileOutputStream(file), true);
                            }
                        }
                    } else if ("file".equals(url.getProtocol())) {
                        return url.getPath();
                    }
                }
            } else {
                return st;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        File f = new File(dir.getAbsolutePath(), st);
        f.mkdirs();
        f.deleteOnExit();
        return f.getAbsolutePath();
    }

    private static Set<File> TEMP_FILE = new HashSet<>();

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                for (File file : TEMP_FILE) {
                    delete(file);
                }
            }
        }));
    }

    private static void deleteDir(File file) {
        for (File listFile : file.listFiles()) {
            if (listFile.isDirectory()) {
                deleteDir(listFile);
            } else {
                listFile.delete();
            }
        }
        file.delete();
    }

    private static void delete(File file) {
        try {
            if (file.isDirectory()) {
                deleteDir(file);
            } else {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static File createTempDir(String dir) {
        try {
            File file = Files.createTempDirectory(dir).toFile();
            TEMP_FILE.add(file);
            return file;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Unable to create tempDir. java.io.tmpdir is set to " + System.getProperty("java.io.tmpdir"));
        }
    }

    public static final String filter(String path) {
        if (path.isEmpty()) {
            return SPLIT;
        }
        if (path.startsWith("//")) {
            path = path.substring(1);
        }
        if (!path.startsWith(SPLIT)) {
            path = SPLIT + path;
        }
        if (path.endsWith(SPLIT)) {
            path = path.substring(0, path.length() - 1);
        }
        if (path.isEmpty()) {
            return SPLIT;
        }
        return path;
    }
}
