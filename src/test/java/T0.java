import io.github.kloping.little_web.conf.TomcatConfig;
import io.github.kloping.MySpringTool.StarterApplication;
import io.github.kloping.MySpringTool.annotations.CommentScan;

/**
 * @author github.kloping
 */
@CommentScan(path = "web")
public class T0 {
    public static void main(String[] args) throws Throwable {
        TomcatConfig.getDEFAULT();
        StarterApplication.run(T0.class);
    }
}
