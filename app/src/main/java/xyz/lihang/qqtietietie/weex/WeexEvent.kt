package xyz.lihang.qqtietietie.weex

/**
 * @author HanikLZ
 * @since 2016/12/28
 */
enum class WeexEvent(val eventName: String) {

    SHOW("show"),
    HIDE("hide"),
    PUSH("push");

    fun fire(instance: WeexInstance, params: Map<String, Any> = emptyMap()) {
        instance.fireGlobalEventCallback(eventName, params)
    }

}

