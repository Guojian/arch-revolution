package personal.jeremyxu.archrevolutition.demo5.tracing.support;

import java.util.Map;

public class SpringCloudTracingStorage {
  private static ThreadLocal<Map<String, String>> tracingCtxInfo = new ThreadLocal<>();

  public static void storeTracingCtxInfo(Map<String, String> info){
    tracingCtxInfo.set(info);
  }

  public static Map<String, String> getTracingCtxInfo(){
    return tracingCtxInfo.get();
  }
}
