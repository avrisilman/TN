package un.app1.appCommon.common;

import org.greenrobot.eventbus.EventBus;

public class EBus {

    public static void post(Object o) {
        EventBus.getDefault().post(o);
    }

    public static void register(Object o) {
        if (!EventBus.getDefault().isRegistered(o))
            EventBus.getDefault().register(o);
    }

    public static void unregister(Object o) {
        EventBus.getDefault().unregister(o);
    }
}