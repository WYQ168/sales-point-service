package com.sales.app.service.impl;

import com.sales.app.domain.request.TradeInfoReq;
import com.sales.app.service.UnionpayService;
import com.sales.app.utils.unionpay.demo.DemoBase;
import com.sales.app.utils.unionpay.sdk.AcpService;
import com.sales.app.utils.unionpay.sdk.SDKConfig;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 银联支付业务层
 * @author: wuyingqiang
 * @date: 2022-08-22 9:23
 */

@Service
public class UnionpayServiceImpl implements UnionpayService {

    private Logger logger = Logger.getLogger(getClass());

    @Override
    public void openAndConsume(HttpServletRequest req, HttpServletResponse resp, TradeInfoReq tradeInfo) throws IOException {
        resp.setContentType("text/html; charset="+ DemoBase.encoding);

        String merId = req.getParameter("merId");
        String orderId = req.getParameter("orderId");
        String txnTime = req.getParameter("txnTime");
        String txnAmt = req.getParameter("txnAmt");
        String accNo = req.getParameter("accNo");
        String certifId = req.getParameter("certifId");
        String customerNm = req.getParameter("customerNm");
        String phoneNo = req.getParameter("phoneNo");

        Map<String, String> contentData = new HashMap<String, String>();
        /***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
        contentData.put("version", DemoBase.version);                   //版本号
        contentData.put("encoding", DemoBase.encoding);            		//字符集编码 可以使用UTF-8,GBK两种方式
        contentData.put("signMethod", SDKConfig.getConfig().getSignMethod()); //签名方法
        contentData.put("txnType", "01");                              //交易类型
        contentData.put("txnSubType", "01");                           //交易子类型
        contentData.put("bizType", "000902");                          //业务类型  000902
        contentData.put("channelType", "07");                          //渠道类型

        /***商户接入参数***/
        contentData.put("merId", merId);                   			   //商户号码（本商户号码仅做为测试调通交易使用，该商户号配置了需要对敏感信息加密）测试时请改成自己申请的商户号，【自己注册的测试777开头的商户号不支持代收产品】
        contentData.put("accessType", "0");                            //接入类型，商户接入固定填0，不需修改
        contentData.put("orderId", orderId);             			   //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则
        contentData.put("txnTime", txnTime);         				   //订单发送时间，格式为yyyyMMddHHmmss，必须取当前时间，否则会报txnTime无效
        contentData.put("currencyCode", "156");						   //交易币种（境内商户一般是156 人民币）
        contentData.put("txnAmt", txnAmt);							   //交易金额，单位分，不要带小数点
        contentData.put("accType", "01");                              //账号类型
        contentData.put("tokenPayData", "{trId=99988877766&tokenType=01}"); //测试环境固定trId=99988877766&tokenType=01，生产环境由业务分配。测试环境因为所有商户都使用同一个trId，所以同一个卡获取的token号都相同，任一人发起更新token或者解除token请求都会导致原token号失效，所以之前成功、突然出现3900002报错时请先尝试重新开通一下。
        contentData.put("certifTp", "01");            //证件类型
        contentData.put("certifId", certifId);        //证件号码
        contentData.put("customerNm", customerNm);    //姓名
        contentData.put("phoneNo", phoneNo);       //手机号
        //contentData.put("reserved", "{customPage=true}");         	//如果开通并支付页面需要使用嵌入页面的话，请上送此用法


        ////////////【开通并付款卡号必选】如果商户号开通了【商户对敏感信息加密】的权限那么需要对 accNo加密：
//        String accNo1 = AcpService.encryptData(accNo, "UTF-8"); 			//这里测试的时候使用的是测试卡号，正式环境请使用真实卡号
//        contentData.put("accNo", accNo1);
//        contentData.put("encryptCertId",AcpService.getEncryptCertId());    //加密证书的certId，配置在acp_sdk.properties文件 acpsdk.encryptCert.path属性下

        /////////【开通并付款卡号必选】如果商户号未开通【商户对敏感信息加密】权限那么不需对敏感信息加密使用：
        contentData.put("accNo", accNo);            				    //这里测试的时候使用的是测试卡号，正式环境请使用真实卡号


        //前台通知地址 （需设置为外网能访问 http https均可），支付成功后的页面 点击“返回商户”的时候将异步通知报文post到该地址
        //注：如果开通失败的“返回商户”按钮也是触发frontUrl地址，点击时是按照get方法返回的，没有通知数据返回商户
        contentData.put("frontUrl", DemoBase.frontUrl);

        //后台通知地址（需设置为【外网】能访问 http https均可），支付成功后银联会自动将异步通知报文post到商户上送的该地址，失败的交易银联不会发送后台通知
        //后台通知参数详见open.unionpay.com帮助中心 下载  产品接口规范  网关支付产品接口规范 消费交易 商户通知
        //注意:1.需设置为外网能访问，否则收不到通知
        // 	  2.http https均可
        //    3.收单后台通知后需要10秒内返回http200或302状态码
        //    4.如果银联通知服务器发送通知后10秒内未收到返回状态码或者应答码非http200，那么银联会间隔一段时间再次发送。总共发送5次，每次的间隔时间为0,1,2,4分钟。
        //    5.后台通知地址如果上送了带有？的参数，例如：http://abc/web?a=b&c=d 在后台通知处理程序验证签名之前需要编写逻辑将这些字段去掉再验签，否则将会验签失败
        contentData.put("backUrl", DemoBase.backUrl);

        // 订单超时时间。
        // 超过此时间后，除网银交易外，其他交易银联系统会拒绝受理，提示超时。 跳转银行网银交易如果超时后交易成功，会自动退款，大约5个工作日金额返还到持卡人账户。
        // 此时间建议取支付时的北京时间加15分钟。
        // 超过超时时间调查询接口应答origRespCode不是A6或者00的就可以判断为失败。
        contentData.put("payTimeout", new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis() + 15 * 60 * 1000));

        /**请求参数设置完毕，以下对请求参数进行签名并生成html表单，将表单写入浏览器跳转打开银联页面**/
        Map<String, String> reqData = AcpService.sign(contentData,DemoBase.encoding);  			 //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
        String requestFrontUrl = SDKConfig.getConfig().getFrontRequestUrl();    							 //获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
        String html = AcpService.createAutoFormHtml(requestFrontUrl, reqData,DemoBase.encoding);     //生成自动跳转的Html表单

        logger.info("打印请求HTML，此为请求报文，为联调排查问题的依据："+html);
        resp.getWriter().write(html);
    }
}
