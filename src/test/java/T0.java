import io.github.kloping.little_web.conf.TomcatConfig;
import io.github.kloping.MySpringTool.StarterApplication;
import io.github.kloping.MySpringTool.annotations.CommentScan;

/**
 * @author github.kloping
 */
@CommentScan(path = "web")
public class T0 {
    public static void main(String[] args) throws Throwable {
        StarterApplication.PRE_SCAN_RUNNABLE.add(()->{
            TomcatConfig config = new TomcatConfig();
            StarterApplication.Setting.INSTANCE.getContextManager().append(config);
        });
        StarterApplication.run(T0.class);
    }
}
