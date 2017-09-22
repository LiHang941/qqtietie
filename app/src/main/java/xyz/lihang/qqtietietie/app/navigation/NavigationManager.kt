package xyz.lihang.qqtietietie.app.navigation

import android.content.Context

/**
 * 跳转控制
 * schema->url，force=
 * @author HanikLZ
 * @since 2016/10/27
 */
object NavigationManager {

    private val schemaMap = mutableMapOf<String, String>()
    private val protocolMap = mutableMapOf<String, NavigationHandler>()

    private fun String.toValuePair(splitChar: Char = '=') = split(splitChar).let {
        if (it.size > 1) it[0] to it[1] else if (it.isNotEmpty()) it[0] to "" else throw IllegalArgumentException("not pair")
    }

    /**
     * 注册跳转
     */
    fun registerSchema(schema: String, path: String) {
        schemaMap[schema] = path
    }

    /**
     * 注册跳转处理器
     */
    fun registerProtocolHandler(protocol: String, handler: NavigationHandler) {
        protocolMap[protocol] = handler
    }

    /**
     * 跳转到指定位置
     */
    fun navigate(context: Context, schema: String) = navigateSchema(schema, { handler, protocol, url, params -> handler.handleNavigate(context, protocol, url, params) })

    /**
     * 以Fragment的形式创建跳转连接，并返回
     */
    fun navigateFragment(schema: String) = navigateSchema(schema, NavigationHandler::createNavigateFragment)

    fun <T> navigateSchema(schema: String, action: (NavigationHandler, String, String, Map<String, String>) -> T?): T? {
        val params = mutableMapOf<String, String>()
        val url = if (schema.contains('?')) {
            val (u, p) = schema.split('?')
            p.split('&').map { it.toValuePair() }.toMap(params)
            u
        } else {
            schema
        }.run {
            schemaMap[this] ?: this
        }
        val (protocol, address) = if (url.contains("://")) {
            url.split("://")
        } else {
            listOf("", url)
        }
        return protocolMap[protocol]?.run { action.invoke(this, protocol, address, params) }
    }
}

