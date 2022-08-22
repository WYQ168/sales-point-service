package com.sales.app.controller;


import com.alibaba.fastjson.JSON;
import com.sales.app.utils.unionpay.demo.DemoBase;
import lombok.extern.slf4j.Slf4j;
import com.sales.app.utils.unionpay.sdk.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.log4j.Logger;

/**
 * 银联代扣支付逻辑：
 * APP原生页面填写用户姓名、手机号、身份证号码、银行卡号，点击确定调用APP后台接口获取银联签约跳转页面（后台调用银联侧接口生成银联签约跳转页面），跳转到银联签约（业务开通）页面（此页面是银联侧的页面），获取短信验证码确认开通；
 * 若开通成功，银联后台通知商户（银联页面自动返回至商户页面），商户保存银联返回的银行卡号末4位数字与token的对应关系。
 */
@Slf4j
@RequestMapping("/unionpay")
@Controller
public class UnionpayController {

    private final static Logger logger = Logger.getLogger(UnionpayController.class);

    private static String merId = "填写商户id";

    private static String trId = "99988877766 ";//生产环境由业务分配，测试环境可以使用99988877766

    /*static {
        SDKConfig.getConfig().loadPropertiesFromSrc();
    }*/

    /**
     * 银联侧开通
     * 商户APP内输入姓名、手机号、身份证号码、银行卡号（输入信息的页面是app自己的），输入完成调用APP后台服务，
     * 后台服务调用银联侧开通接口，获取到银联返回的自动跳转的Html表单给app，app去跳转（此时跳转后的页面是银联的）
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/tokenOpenCard")
    public void tokenOpenCard(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html; charset=" + DemoBase.encoding);
        String certifId = req.getParameter("idcard");//用户身份证号码
        String customerNm = req.getParameter("username");//用户姓名
        String phoneNo = req.getParameter("mobile");//用户手机号
        String accNo = req.getParameter("bankNo");//用户银行卡号

        Map<String, String> contentData = new HashMap<String, String>();

        /***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
        contentData.put("version", DemoBase.version);                  //版本号
        contentData.put("encoding", DemoBase.encoding);            //字符集编码 可以使用UTF-8,GBK两种方式
        contentData.put("signMethod", SDKConfig.getConfig().getSignMethod()); //签名方法
        contentData.put("txnType", "79");                              //交易类型 79：开通交易
        contentData.put("txnSubType", "00");                           //交易子类型 00-默认开通
        contentData.put("bizType", "000902");                          //业务类型 Token支付
        contentData.put("channelType", "07");                          //渠道类型07-PC

        /***商户接入参数***/
        contentData.put("merId", merId);                               //商户号码（本商户号码仅做为测试调通交易使用，该商户号配置了需要对敏感信息加密）测试时请改成自己申请的商户号，【自己注册的测试777开头的商户号不支持代收产品】
        contentData.put("accessType", "0");                            //接入类型，商户接入固定填0，不需修改
        contentData.put("orderId", DemoBase.getOrderId());                           //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
        contentData.put("txnTime", DemoBase.getCurrentTime());         //订单发送时间，格式为yyyyMMddHHmmss，必须取当前时间，否则会报txnTime无效
        contentData.put("accType", "01");

        //生产环境由业务分配，测试环境可以使用99988877766
        contentData.put("tokenPayData", "{trId=" + trId + "&tokenType=01}");

        //选送卡号、手机号、证件类型+证件号、姓名
        Map<String, String> customerInfoMap = new HashMap<String, String>();
        customerInfoMap.put("certifTp", "01");            //证件类型
        customerInfoMap.put("certifId", certifId);        //证件号码
        customerInfoMap.put("customerNm", customerNm);    //姓名
        customerInfoMap.put("phoneNo", phoneNo);       //手机号

        //如果商户号开通了【商户对敏感信息加密】的权限那么需要对 accNo，pin和phoneNo，cvn2，expired加密（如果这些上送的话），对敏感信息加密使用：
        contentData.put("accNo", AcpService.encryptData(accNo, "UTF-8")); //银行卡号
        contentData.put("encryptCertId", AcpService.getEncryptCertId());       //加密证书的certId，配置在acp_sdk.properties文件 acpsdk.encryptCert.path属性下
        String customerInfoStr = AcpService.getCustomerInfoWithEncrypt(customerInfoMap, null, DemoBase.encoding);
        contentData.put("customerInfo", customerInfoStr);

        //前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”的时候将异步通知报文post到该地址
        //如果想要实现过几秒中自动跳转回商户页面权限，需联系银联业务申请开通自动返回商户权限
        //注：如果开通失败的“返回商户”按钮也是触发frontUrl地址，点击时是按照get方法返回的，没有通知数据返回商户
        contentData.put("frontUrl", DemoBase.frontUrl);

        //后台通知地址（需设置为【外网】能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
        //后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
        //注意:
        // 1.需设置为外网能访问，否则收不到通知
        // 2.http https均可
        // 3.收单后台通知后需要10秒内返回http200或302状态码
        // 4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
        // 5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
        contentData.put("backUrl", DemoBase.backUrl);

        // 订单超时时间。
        // 超过此时间后，除网银交易外，其他交易银联系统会拒绝受理，提示超时。 跳转银行网银交易如果超时后交易成功，会自动退款，大约5个工作日金额返还到持卡人账户。
        // 此时间建议取支付时的北京时间加15分钟。
        // 超过超时时间调查询接口应答origRespCode不是A6或者00的就可以判断为失败。
        contentData.put("payTimeout", new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis() + 15 * 60 * 1000));

        /**请求参数设置完毕，以下对请求参数进行签名并生成html表单，将表单写入浏览器跳转打开银联页面**/
        Map<String, String> reqData = AcpService.sign(contentData, DemoBase.encoding);             //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
        String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl();                             //获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
        String html = AcpService.createAutoFormHtml(requestFrontUrl, reqData, DemoBase.encoding);     //生成自动跳转的Html表单

        logger.error("打印请求HTML，此为请求报文，为联调排查问题的依据：" + html);
        //将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
        resp.getWriter().write(html);
    }


    /**
     * 前台通知
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/fontNotify")
    public void fontNotify(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String encoding = req.getParameter(SDKConstants.param_encoding);
        // 获取银联通知服务器发送的后台通知参数
        Map<String, String> reqParam = getAllRequestParam(req);
        logger.error(reqParam);
        Map<String, String> valideData = null;
        if (null != reqParam && !reqParam.isEmpty()) {
            Iterator<Map.Entry<String, String>> it = reqParam.entrySet().iterator();
            valideData = new HashMap<String, String>(reqParam.size());
            while (it.hasNext()) {
                Map.Entry<String, String> e = it.next();
                String key = (String) e.getKey();
                String value = (String) e.getValue();
                valideData.put(key, value);
            }
        }
        log.info("===银联前台通知，请求参数：" + JSON.toJSONString(valideData));
        //重要！验证签名前不要修改reqParam中的键值对的内容，否则会验签不过
        if (!AcpService.validate(valideData, encoding)) {
            logger.error("验证签名结果[失败].");
            //验签失败，需解决验签问题
        } else {
            //【注：为了安全验签成功才应该写商户的成功处理逻辑】交易成功，更新商户订单状态
            logger.error("验证签名结果[成功],暂不处理具体业务");
            /** 交易类型.*/
            String txnType = valideData.get("txnType");
            /** 接入类型，商户接入填0 ，不需修改（0：直连商户， 1： 收单机构 2：平台商户）*/
            String accessType = valideData.get("accessType");
            /** 业务类型.*/
            String bizType = valideData.get("bizType");
            /* * 应答码信息.*/
            String respMsg = valideData.get("respMsg");
            /** 签名方法.*/
            String signMethod = valideData.get("signMethod");//签名方法
            /* * 签名公钥证书*/
            //String signPubKeyCert = valideData.get("signPubKeyCert");
            /** 版本号.*/
            String version = valideData.get("version");
            //开通交易
            if ("79".equals(txnType)) {
                /** 持卡人信息.*/
                String customerInfo = valideData.get("customerInfo");
                /** 发卡机构代码.*/
                String issInsCode = valideData.get("issInsCode");
                /** 银行卡号.*/
                String accNo = valideData.get("accNo");
                /* * token信息.*/
                String tokenPayData = valideData.get("tokenPayData");

                String phoneNo = "";
                if (null != customerInfo) {
                    Map<String, String> map = AcpService.parseCustomerInfo(customerInfo, "UTF-8");
                    log.info("customerInfo明文:" + map);
                    phoneNo = map.get("phoneNo");
                }
                //如果是配置了敏感信息加密，如果需要获取卡号的明文，可以按以下方法解密卡号
                if (null != accNo) {
                    //返回的是银行卡号后四位
                    accNo = AcpService.decryptData(accNo, "UTF-8");
                    log.info("accNo明文:" + accNo);
                }

                if (null != tokenPayData) {
                    Map<String, String> tokenPayDataMap = SDKUtil.parseQString(tokenPayData.substring(1, tokenPayData.length() - 1), "UTF-8");
                    log.info("tokenPayDataMap明文:" + tokenPayDataMap);
                    String token = tokenPayDataMap.get("token");//这样取
                    log.info("token值:" + token);
                    //处理自己的业务
                }
            }
        }
        //返回给银联服务器http 200  状态码
        resp.getWriter().print("200");
    }

    /**
     * 获取请求参数中所有的信息
     * 当商户上送frontUrl或backUrl地址中带有参数信息的时候，
     * 这种方式会将url地址中的参数读到map中，会导多出来这些信息从而致验签失败，这个时候可以自行修改过滤掉url中的参数或者使用getAllRequestParamStream方法。
     *
     * @param request
     * @return
     */
    public static Map<String, String> getAllRequestParam(
            final HttpServletRequest request) {
        Map<String, String> res = new HashMap<String, String>();
        Enumeration<?> temp = request.getParameterNames();
        if (null != temp) {
            while (temp.hasMoreElements()) {
                String en = (String) temp.nextElement();
                String value = request.getParameter(en);
                res.put(en, value);
                // 在报文上送时，如果字段的值为空，则不上送<下面的处理为在获取所有参数数据时，判断若值为空，则删除这个字段>
                if (res.get(en) == null || "".equals(res.get(en))) {
                    res.remove(en);
                }
            }
        }
        return res;
    }
}
