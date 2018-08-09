package com.mylearn.jvm.btrace;
/*import static com.sun.btrace.BTraceUtils.println;
import static com.sun.btrace.BTraceUtils.str;
import static com.sun.btrace.BTraceUtils.strcat;
import static com.sun.btrace.BTraceUtils.timeMillis;

import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.Kind;
import com.sun.btrace.annotations.Location;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.ProbeClassName;
import com.sun.btrace.annotations.ProbeMethodName;
import com.sun.btrace.annotations.TLS;*/
/**
 * Created by IntelliJ IDEA.
 * User: kuohao.ykh
 * Date: 2015/10/13
 * Time: 17:35
 * CopyRight: taobao
 * Descrption:
 */
//@BTrace
public class TraceTest {
  /*  @TLS
      private static long startTime = 0;

      @OnMethod(clazz = "HelloWorld", method = "execute")
      public static void startMethod(){
          startTime = timeMillis();
      }

      @OnMethod(clazz = "com.mylearn.jvm.btrace.HelloWorld", method = "execute", location = @Location(Kind.RETURN))
      public static void endMethod(){
          println(strcat("the class method execute time=>", str(timeMillis()-startTime)));
          println("-------------------------------------------");
      }

      @OnMethod(clazz = "com.mylearn.jvm.btrace.HelloWorld", method = "execute", location = @Location(Kind.RETURN))
      public static void traceExecute(@ProbeClassName String name,@ProbeMethodName String method,int sleepTime){
          println(strcat("the class name=>", name));
          println(strcat("the class method=>", method));
          println(strcat("the class method params=>", str(sleepTime)));

      }*/
}
