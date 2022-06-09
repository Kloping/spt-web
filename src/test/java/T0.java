import io.github.kloping.MySpringTool.StarterObjectApplication;
import io.github.kloping.MySpringTool.annotations.CommentScan;
import io.github.kloping.little_web.conf.TomcatConfig;

/**
 * @author github.kloping
 */
@CommentScan(path = "web")
public class T0 {
    public static void main(String[] args) throws Throwable {
        StarterObjectApplication application = new StarterObjectApplication();
        TomcatConfig config = new TomcatConfig();
        config.setPort(80);
        application.PRE_SCAN_RUNNABLE.add(() -> {
            application.INSTANCE.getContextManager().append(config);
        });
        application.run0(T0.class);

        StarterObjectApplication application1 = new StarterObjectApplication();
        TomcatConfig config1 = new TomcatConfig();
        config1.setPort(81);
        application1.PRE_SCAN_RUNNABLE.add(() -> {
            application1.INSTANCE.getContextManager().append(config1);
        });
        application1.run0(T0.class);

    }
}
