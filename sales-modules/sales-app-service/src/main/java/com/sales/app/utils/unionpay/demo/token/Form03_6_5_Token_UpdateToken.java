package com.sales.app.utils.unionpay.demo.token;

import com.sales.app.utils.unionpay.demo.DemoBase;
import com.sales.app.utils.unionpay.sdk.AcpService;
import com.sales.app.utils.unionpay.sdk.SDKConfig;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 重要：联调测试时请仔细阅读注释！
 * 
 * 产品：无跳转token产品<br>
 * 交易：更新token号：后台交易，无通知<br>
 * 日期： 2015-09<br>

 * 版权： 中国银联<br>
 * 声明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考，不提供编码，性能，规范性等方面的保障<br>
 * 交易说明: 同步应答确定交易成功。
 *        
 */


@SuppressWarnings("serial")
public class Form03_6_5_Token_UpdateToken  extends HttpServlet  {
	
	private Logger logger = Logger.getLogger(getClass());

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String merId = req.getParameter("merId");
		String orderId = req.getParameter("orderId");
		String txnTime = req.getParameter("txnTime");
		String token = req.getParameter("token");
		
		Map<String, String> contentData = new HashMap<String, String>();

		/***银联全渠道系统，产品参数，除了encoding自行选择外其他不需修改***/
		contentData.put("version", DemoBase.version);                  //版本号
		contentData.put("encoding", DemoBase.encoding);                //字符集编码 可以使用UTF-8,GBK两种方式
		contentData.put("signMethod", SDKConfig.getConfig().getSignMethod()); //签名方法
		contentData.put("txnType", "79");                              //交易类型 79-开通交易
		contentData.put("txnSubType", "03");                           //交易子类型 03-更新标记
		contentData.put("bizType", "000902");                          //业务类型 认证支付2.0
		contentData.put("channelType", "07");                          //渠道类型07-PC
		
		/***商户接入参数***/
		contentData.put("merId", merId);                   			   //商户号码（本商户号码仅做为测试调通交易使用，该商户号配置了需要对敏感信息加密）测试时请改成自己申请的商户号，【自己注册的测试777开头的商户号不支持代收产品】
		contentData.put("accessType", "0");                            //接入类型，商户接入固定填0，不需修改	
		contentData.put("orderId", orderId);             			   //商户订单号，8-40位数字字母，不能含“-”或“_”，可以自行定制规则	
		contentData.put("txnTime", txnTime);         				   //订单发送时间，格式为yyyyMMddHHmmss，必须取当前时间，否则会报txnTime无效
		
		//只支持贷记卡 必送：卡号、手机号、CVN2、有效期；验证码看业务配置（默认不要短信验证码,本测试商户777290058110097配置了需要）。
		Map<String,String> customerInfoMap = new HashMap<String,String>();
		//customerInfoMap.put("certifTp", "01");						//证件类型
		//customerInfoMap.put("certifId", "341126197709218366");		//证件号码
		//customerInfoMap.put("customerNm", "全渠道");					//姓名
		customerInfoMap.put("phoneNo", "13552535506");			        //手机号
		customerInfoMap.put("cvn2", "123");           			        //卡背面的cvn2三位数字
		customerInfoMap.put("expired", "2311");  				        //有效期 年在前月在后
		customerInfoMap.put("smsCode", "111111");  				        //短信验证码 测试环境固定111111
		
		contentData.put("encryptCertId",AcpService.getEncryptCertId());       //加密证书的certId，配置在acp_sdk.properties文件 acpsdk.encryptCert.path属性下
		String customerInfoStr = AcpService.getCustomerInfoWithEncrypt(customerInfoMap,null,DemoBase.encoding);
		
		contentData.put("customerInfo", customerInfoStr);
		//token号（从前台开通的后台通知中获取或者后台开通的返回报文中获取）
		contentData.put("tokenPayData", "{token="+token+"&trId=99988877766&tokenType=01}");
		
		/**对请求参数进行签名并发送http post请求，接收同步应答报文**/
		Map<String, String> reqData = AcpService.sign(contentData,DemoBase.encoding);			   //报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。
		String requestBackUrl = SDKConfig.getConfig().getBackRequestUrl();   						   //交易请求url从配置文件读取对应属性文件acp_sdk.properties中的 acpsdk.backTransUrl
		Map<String, String> rspData = AcpService.post(reqData,requestBackUrl,DemoBase.encoding);   //发送请求报文并接受同步应答（默认连接超时时间30秒，读取返回结果超时时间30秒）;这里调用signData之后，调用submitUrl之前不能对submitFromData中的键值对做任何修改，如果修改会导致验签不通过
		
		/**对应答码的处理，请根据您的业务逻辑来编写程序,以下应答码处理逻辑仅供参考------------->**/
		//应答码规范参考open.unionpay.com帮助中心 下载  产品接口规范  《平台接入接口规范-第5部分-附录》
		StringBuffer parseStr = new StringBuffer("");
		if(!rspData.isEmpty()){
			if(AcpService.validate(rspData, DemoBase.encoding)){
				logger.info("验证签名成功");
				String respCode = rspData.get("respCode") ;
				if(("00").equals(respCode)){
					//成功
					parseStr.append("<br>解析敏感信息加密信息如下（如果有）:<br>");
					String customerInfo = rspData.get("customerInfo");
					if(null!=customerInfo){
						Map<String,String>  cm = AcpService.parseCustomerInfo(customerInfo, "UTF-8");
						parseStr.append("customerInfo明文: " + cm+"<br>");
					}
					//如果是配置了敏感信息加密，如果需要获取卡号的明文，可以按以下方法解密卡号
					String an = rspData.get("accNo");
					if(null!=an){
						an = AcpService.decryptData(an, "UTF-8");
						parseStr.append("accNo明文: " + an);
					}
					//TODO
				}else{
					//其他应答码为失败请排查原因或做失败处理
					//TODO
				}
			}else{
				logger.error("验证签名失败");
				//TODO 检查验证签名失败的原因
			}
		}else{
			//未返回正确的http状态
			logger.error("未获取到返回报文或返回http状态码非200");
		}
		String reqMessage = DemoBase.genHtmlResult(reqData);
		String rspMessage = DemoBase.genHtmlResult(rspData);
		resp.getWriter().write("请求报文:<br/>"+reqMessage+"<br/>" + "应答报文:</br>"+rspMessage+parseStr);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
}
