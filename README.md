[同步BLOG请点击](https://blog.csdn.net/hshuaijun55/article/details/103170404)

# 1、背景
**本日志打印工具类支持超长文本信息打印，支持点击日志直接定位到对应代码行。** 我有借鉴com.orhanobut:logger这位兄弟的代码。废话不多说，直接上效果图。
![一般文本信息打印](https://img-blog.csdnimg.cn/20191120200914324.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hzaHVhaWp1bjU1,size_16,color_FFFFFF,t_70)
![网络请求信息打印](https://img-blog.csdnimg.cn/20191120200938514.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hzaHVhaWp1bjU1,size_16,color_FFFFFF,t_70)
# 2、类源码
```java
public class HLogger {
    public static boolean DEBUG = true;

    private static final int CHUNK_SIZE = 100;
    private static final char TOP_LEFT_CORNER = '┌';
    private static final char BOTTOM_LEFT_CORNER = '└';
    private static final char MIDDLE_CORNER = '├';
    private static final char HORIZONTAL_LINE = '│';
    private static final String DOUBLE_DIVIDER = "────────────────────────────────────────────────────────";
    private static final String SINGLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final String TOP_BORDER = TOP_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String BOTTOM_BORDER = BOTTOM_LEFT_CORNER + DOUBLE_DIVIDER + DOUBLE_DIVIDER;
    private static final String MIDDLE_BORDER = MIDDLE_CORNER + SINGLE_DIVIDER + SINGLE_DIVIDER;

    private static String className;
    private static String methodName;
    private static int lineNumber;

    private static String createLog(String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(methodName);
        buffer.append("(").append(className).append(":").append(lineNumber).append(")");
        buffer.append(log);
        return buffer.toString();
    }
    private static String createLog() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("(").append(className).append(":").append(lineNumber).append(")");
        buffer.append(methodName);
        return buffer.toString();
    }

    private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    //超长文本打印
    public static void longError(String message) {
        if (!DEBUG) return;
        if (!TextUtils.isEmpty(message)) {
            getMethodNames(new Throwable().getStackTrace());
            Log.e(className, TOP_BORDER);
            Log.i(className, HORIZONTAL_LINE + " " + createLog());
            Log.i(className, MIDDLE_BORDER + " ");
            byte[] bytes = message.getBytes();
            int length = bytes.length;
            if (length <= CHUNK_SIZE) {
                String[] lines = message.split(System.getProperty("line.separator"));
                for (String line : lines) {
                    Log.e(className, HORIZONTAL_LINE + " " + line);
                }
            } else {
                for (int i = 0; i < length; i += CHUNK_SIZE) {
                    int count = Math.min(length - i, CHUNK_SIZE);
                    String[] lines = new String(bytes, i, count).split(System.getProperty("line.separator"));
                    for (String line : lines) {
                        Log.e(className, HORIZONTAL_LINE + " " + line);
                    }
                }
            }
            Log.e(className, BOTTOM_BORDER);
        }
    }
    //超长文本打印
    public static void longInfo(String message) {
        if (!DEBUG) return;
        if (!TextUtils.isEmpty(message)) {
            getMethodNames(new Throwable().getStackTrace());
            Log.i(className, TOP_BORDER);
            Log.i(className, HORIZONTAL_LINE + " " + createLog());
            Log.i(className, MIDDLE_BORDER + " ");
            byte[] bytes = message.getBytes();
            int length = bytes.length;
            if (length <= CHUNK_SIZE) {
                String[] lines = message.split(System.getProperty("line.separator"));
                for (String line : lines) {
                    Log.i(className, HORIZONTAL_LINE + " " + line);
                }
            } else {
                for (int i = 0; i < length; i += CHUNK_SIZE) {
                    int count = Math.min(length - i, CHUNK_SIZE);
                    String[] lines = new String(bytes, i, count).split(System.getProperty("line.separator"));
                    for (String line : lines) {
                        Log.i(className, HORIZONTAL_LINE + " " + line);
                    }
                }
            }
            Log.i(className, BOTTOM_BORDER);
        }
    }

    public static void d(String msg) {
        if (DEBUG) {
            if (!TextUtils.isEmpty(msg)) {
                getMethodNames(new Throwable().getStackTrace());
                Log.d(className, createLog(msg));
            }
        }
    }

    public static void v(String msg) {
        if (DEBUG) {
            if (!TextUtils.isEmpty(msg)) {
                getMethodNames(new Throwable().getStackTrace());
                Log.d(className, createLog(msg));
            }
        }
    }

    public static void e(String msg) {
        if (!TextUtils.isEmpty(msg)) {
            getMethodNames(new Throwable().getStackTrace());
            Log.d(className, createLog(msg));
        }
    }

    public static void i(String msg) {
        if (DEBUG) {
            if (!TextUtils.isEmpty(msg)) {
                getMethodNames(new Throwable().getStackTrace());
                Log.d(className, createLog(msg));
            }
        }
    }

    public static void d(String tag, String msg) {
        if (DEBUG) {
            if (!TextUtils.isEmpty(msg)) {
//                Log.d(tag, msg);
                getMethodNames(new Throwable().getStackTrace());
                Log.d(className, createLog(msg));
            }
        }
    }

    public static void v(String tag, String msg) {
        if (DEBUG) {
            if (!TextUtils.isEmpty(msg)) {
                Log.d(tag, msg);
            }
        }
    }

    public static void e(String tag, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (DEBUG) {
            if (!TextUtils.isEmpty(msg)) {
                Log.d(tag, msg);
            }
        }
    }
}
```
# 3、代码解析
## 3.1、调用方法
```java
/**
     * 短文本打印调用方法
     * @param view
     */
    public void shortText(View view) {
        HLogger.d("这是一条测试信息。这是一条测试信息");
    }

    /**
     * 长文本打印调用方法
     * @param view
     */
    public void longText(View view) {
        HLogger.longInfo("545345343132121102045402.102135435412.121234564651313210210214541321shksjadhsdhaskjhdaskfhcask" +
                "dhaskdhakuhdoiauwdhaksncxkajsjhdkajshdaksjshdaksdhaskdhaskjdhaskjhdaksjhdaskjhdkahsfgbdajsfcnkjdanc," +
                "dsnbcsdhjkfgvsdkjhfhgaksudhaouisdqodhaksdnbcaskjcbnkadsfhdksafhgiudshgfsdkjfnakcbnkajsdhaksufghakfhdasjkfsakjdhfudhaoui" +
                "sdqodhaksdnbcaskjcbnkadsfhdksafhgiudshgfsdkjfnakcbnkajsdhaksufghakfhdasjkfsakjdhf" +
                "udhaouisdqodhaksdnbcaskjcbnkadsfhdksafhgiudshgfsdkjfnakcbnkajsdhaksufghakfhdasjkfsakjdhf" +
                "udhaouisdqodhaksdnbcaskjcbnkadsfhdksafhgiudshgfsdkjfnakcbnkajsdhaksufghakfhdasjkfsakjdhf" +
                "udhaouisdqodhaksdnbcaskjcbnkadsfhdksafhgiudshgfsdkjfnakcbnkajsdhaksufghakfhdasjkfsakjdhf");

    }
```
调用非常简单，HLogger类支持的方法如下，可以传入自己的TAG，也可以不传入，代码自动获取打印日志的类的类名作为TAG。
![HLogger支持的方法](https://img-blog.csdnimg.cn/20191120201818407.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2hzaHVhaWp1bjU1,size_16,color_FFFFFF,t_70)

3. 网络请求时，将请求信息及返回信息打印出来。会使用到长文本信息打印。以下是网络请求拦截器中，我打印的请求信息。如效果图中所示。
```java
	//打印请求信息，正式项目中可以注释掉
    RequestBody requestBody = request.body();
    String body = null;
    if (requestBody != null) {
        Buffer buffer = new Buffer();
        requestBody.writeTo(buffer);

        body = buffer.readString(Charset.forName("UTF-8"));
    }
    HLogUtil.longInfo(String.format("发送请求\nmethod：%s\nurl：%s\nheaders: %sbody：%s",
        request.method(), request.url(), request.headers(), body));

```
以下是返回信息
```java
	String result = responseBody.string();
    HLogUtil.longInfo(result);
```

## 3.2、解析
1. 通过Throwable的StackTrace，拿到当前类的类名、行号等信息。所以我们先通过这些信息封装我们的log信息。
```java
	private static String className;
    private static String methodName;
    private static int lineNumber;

	private static void getMethodNames(StackTraceElement[] sElements) {
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }
    
    private static String createLog(String log) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(methodName);
        buffer.append("(").append(className).append(":").append(lineNumber).append(")");
        buffer.append(log);
        return buffer.toString();
    }
```
调用打印。
```java
Log.d(className, createLog(msg));
```
2. 长文本信息打印，其实就是在短文本基础上进行了长文本内容的分割打印，及展示方式的封装。
```java
//超长文本打印
    public static void longInfo(String message) {
        if (!DEBUG) return;
        if (!TextUtils.isEmpty(message)) {
            getMethodNames(new Throwable().getStackTrace());
            //先打印横线
            Log.i(className, TOP_BORDER);
            //再打印类名和行号及调用方法名
            Log.i(className, HORIZONTAL_LINE + " " + createLog());
            //再打印中间横线
            Log.i(className, MIDDLE_BORDER + " ");
            byte[] bytes = message.getBytes();
            int length = bytes.length;
            //判断文本内容长度
            if (length <= CHUNK_SIZE) {
                //短文本，再判断文本内容是否有换行符
                String[] lines = message.split(System.getProperty("line.separator"));
                for (String line : lines) {
                    Log.i(className, HORIZONTAL_LINE + " " + line);
                }
            } else {
                //长文本循环打印，每一行中判断是否有换行符
                for (int i = 0; i < length; i += CHUNK_SIZE) {
                    int count = Math.min(length - i, CHUNK_SIZE);
                    String[] lines = new String(bytes, i, count).split(System.getProperty("line.separator"));
                    for (String line : lines) {
                        Log.i(className, HORIZONTAL_LINE + " " + line);
                    }
                }
            }
            Log.i(className, BOTTOM_BORDER);
        }
    }
```
## 3.3、注意
1. 长文本打印方法中，文本切割长度我是设置的100字节长度，这个可以自由调整。就是这个属性：
```java
private static final int CHUNK_SIZE = 100;
```
2. 长文本打印方法 ，如果内容中有换行符，也会自动换行。

[同步BLOG请点击](https://blog.csdn.net/hshuaijun55/article/details/103170404)
