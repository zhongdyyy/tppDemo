

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import com.amix.util.Base64ImgUtil;
import com.amix.util.HttpsConnectUtil;



public class testDoPostReq {

	public static void main(String[] args) throws IOException {

		HttpsConnectUtil httpsConnectUtil = new HttpsConnectUtil();
		Map<String, String> paramMap = new HashMap<String, String>();
			
		/*两照对比***********************************/
		Map<String, String> paramMap2 = new HashMap<String, String>();
		paramMap.put("apiName", "metro_node_000004");
		paramMap.put("terminalNo", "OSSOTID1000015");
		paramMap.put("lineNo", "01");
		paramMap.put("stationNo", "01");
		paramMap.put("entryTime", "20180312101400");
		paramMap.put("frontPhoto", URLEncoder.encode(Base64ImgUtil.GetImageStr("D:/jiaduTest/A.jpg"),"UTF-8"));
		paramMap.put("selfPhoto", URLEncoder.encode(Base64ImgUtil.GetImageStr("D:/jiaduTest/B.jpg"),"UTF-8"));
		paramMap.put("checkInfo", "安检门安检信息:xxxxx");
		
		String jresMsg = httpsConnectUtil.doPost("http://localhost:8001/test",paramMap);
		
		System.out.println("resMsg->"+jresMsg);
		
	}
	

}
