package com.bysj.auto.config;
/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092900623961";

	// 商户私钥，您的PKCS8格式RSA2私钥
	public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCZ7qIwE9qRdVEjDSjF40tCgH0cokgoc0f2UPgrrCpSjnjvjqswGQtNW2LlOIrcGAYUkO4pipBvRv78ucNPLkFbdUkhmDUbMFnbmkNzmM3hqZV0PCiUgpblytrLlVLRVBVfq8tL3J8oz3SmSUV1z9K15m03/Hza6uo1PoFj36q13U0+c9x82BA5k8kbaB3gqauOdPychkyEsH6T1fS8x8d5Xz+atyHL9+oCVFSeFdk1J35SWFs11ou0UmJ2LpL3U+k5jQGxh5UGJg1szT/G+M89sC1sbGo+4ux4n8GorRHs5JyYyaJXRSS7msXDjQ41cZDvYYB4mfkAG+RK8alxqMzfAgMBAAECggEAUvy4K7tIZosdBwydIrJ7bIrg6IME6+nargzQ/pp+5qswv9XmrLOTyEywcGufUTESdhv8iiDN5XAdIBr2FMYXLjt5BceNOe9DQoVUQAntVO+cR2H3brlIqPJIKuGCGKCANWfk3i0/ET1m4g82h2FXdAGaeEiC93MWeUYNc86ttgWUKvQiOVv8yHNyOPqxySEcRR0jHPww4hQALIcmCaOQqO++zUNjguNB5KJvkoWrSE5+pXNnzIzOZRKlY5CVhA61ZlCo+xl66+gYxxVtBR6JzalHwP9erm53LWx8USUlikIMHhEhKP27hte7Cb/CiW5xt/p9RGTPN1rUIHqD85UHoQKBgQDpv0LTnNls/bmz5wGqGmHm7lZZP55j0puWDHzPMh4jt2nhbCbMXBl6tHBBgFa/6yVrOADrDRE6Nyo5QHALOEVyY7prkrpeA50bR14zVXulhwbw8Hc43Fu4So7vB1brPp3ET5RZmTlKuk0qDNEV8sjN6th05IFqJWKB1MvNEVC+9QKBgQColiwZYk/NI5Aopg/VVHmi5yyE27VK3vf/KPVBW9+sxHobv1BsHRNZxXCx5yKNflZN91hbQ24W5vakOTU0Y4ds6njVY+gJq/FzabMvYJ/cyxI6hqEs/tmWsPsaO8WvKZHzF/y+1F7hxKYTzjCIwdN83PkcR8VLf55C0WTOn6JQAwKBgBP+nlLHqfmewpHXAtdwJkvAu3Yq8PuM+m4dZ1e568YWXGJCBBebETrj0G5p8gG8tZi0U6/HkF+wdFYtYugyyg385edpx7NfD8NTmYU+nLMOF9PoXeppPxdGmW9s60x4MLqoLAJ2DuaLhSpBqmj8OZ0Tp6xoF4Hi7Mg6Uaya/JM5AoGAYym6FNBMKlkR0ackRxJiXdzmxdtKPxj5/N+Fq4U0AzaqsKmOz5kmqbZGPI69N0cp0sB7+2qlVdlTGHQCBKfKn9knbDVQ5md6Ja311Rdb0Q6t0Tkhkwrwx/+gI+UmuYqVK81fQIxAp0H5gkrVJ4wDLz33VZgtx+PwHlN2nUcuvSsCgYAwjzIUXbe5TwTZYA20zaYc4/q/O0UwZLXjpcmEsWYHwqyI/HVPmcvwB/LxjneOSSIYeX/MCS81XjOvyceYKsv+TmEkedHECRXM5pvka1cchnczJBXhDI7AtBCYBtu2f3VPq0Gj+wsRgwUZ7DzoEkHcf3l4kODZ+Xt9PdvWbELspQ==";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
	public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjBXkHjha8u0KvVNln41ujEYvj//13FmHjdmvlBenCWNnx2XH8cRt4Wpjy5V6qXpmtptlwYGTNQeWGFyu7MTS8B7k8BLfYBhIrZBgS5hUStw58uVYxmZy/I67Q5rZ2mUQ2ReQv8dFQs0U/5asZ39RZy3Wh3fp+Ev3gYr87ek3etk/ai6ufHzYNcZgHU1J2z/MINlyFXp5pKn+fhU1thehozbvPslTFuxjzpla9XgDuP20PLLQYhSbUaMFI04lu0Sp7OYYGwRJpfpJ21bgcVVJChap3v6NCijormxU57uFUPQPZwqge5vU+Ylk8yc/NLh/fAfQnnCay2xhNM16w1S2vQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

//    /**
//     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
//     * @param sWord 要写入日志里的文本内容
//     */
//    public static void logResult(String sWord) {
//        FileWriter writer = null;
//        try {
//            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
//            writer.write(sWord);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (writer != null) {
//                try {
//                    writer.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
}

