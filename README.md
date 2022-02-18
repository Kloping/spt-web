# an extension of SpringTool

This is an extension of SpringTool

Suitable for small Web server framework

example

```java

@WebRestController
public class RestController0 {
    @GetMethod("/get")
    public String m0(@RequestParm("name") String name) {
        return "Hello! " + name;
    }

    @GetMethod("/get0")
    public M0 m1(@RequestParm("name") String name) {
        return new M0(name, 10);
    }

    @GetMethod("favicon.ico")
    public void favicon(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://q1.qlogo.cn/g?b=qq&nk=3474006766&s=640");
    }
}
```

<details> 
<summary>M0</summary> 

```java

/**
 * @author github.kloping
 */
public class M0 {
    private String name;
    private Integer year;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public M0(String name, Integer year) {
        this.name = name;
        this.year = year;
    }
}
```

</details>

The prerequisite is to leave the class annotated by @WebrestController in
@CommentScan(path)  path 