

import com.amix.util.Base64ImgUtil;
import com.amix.util.HttpRequestUtil;
import com.huadun.monitoring.GateInfor;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.IOUtils;

public class Main {

    public static void main(String[] arg) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8001), 0);
        server.createContext("/test", new TestHandler());
        server.start();
    }

    static class TestHandler implements HttpHandler{
        @Override
        public void handle(HttpExchange exchange) {
            String response = "hello world";
//            final String saveGateInforURL = "http://localhost:8989/monitoringsystem/shieldDoor/saveGateData";
            final String saveGateInforURL = "http://localhost:8989/monitoringsystem/shieldDoor/saveGateData";
            

            try{
//                //��ò�ѯ�ַ���(get)
//                String queryString =  exchange.getRequestURI().getQuery();
//                Map<String,String> queryStringInfo = formData2Dic(queryString);
//                System.out.println(queryStringInfo.toString());
                //��ñ��ύ����(post)
                String postString = IOUtils.toString(exchange.getRequestBody());
                Map<String,String> postInfo = formData2Dic(postString);
                String oldfrontPhoto = postInfo.get("frontPhoto");
                String frontPhoto  = URLDecoder.decode(oldfrontPhoto, "UTF-8");//获得编码后的Base64字符
                String oldselfPhoto = postInfo.get("selfPhoto");
                String selfPhoto = URLDecoder.decode(oldselfPhoto, "UTF-8");//获得编码后的Base64字符
//                String imgFilePath = System.getProperty("user.dir") ;
//                String imgFilePath ="../"
//                System.out.println(imgFilePath);
//                System.out.println(frontPhoto);
                String picPath = "D:/jiaduPicture";
                File file = new File(picPath);
                if(!file.exists()){
                	file.mkdirs();
                }
                String realPicName = UUID.randomUUID().toString();
                String realPicPath = picPath+"/"+realPicName+".jpeg";
                Base64ImgUtil base64ImgUtil =  new Base64ImgUtil();
                base64ImgUtil.GenerateImage(frontPhoto,realPicPath);
                
                String livePicName = UUID.randomUUID().toString();
                String livePicPath = picPath+"/"+realPicName+".jpeg";
                base64ImgUtil.GenerateImage(selfPhoto,livePicPath);
                
//                HttpRequestUtil httpRequestUtil = new HttpRequestUtil();
                /**封装成一个类，用于保存数据**/
                GateInfor gateInfor = new GateInfor();
                gateInfor.setApiName(postInfo.get("apiName"));
                gateInfor.setCheckInfo(postInfo.get("checkInfo"));
                gateInfor.setEntryTime(postInfo.get("entryTime"));
                gateInfor.setFrontPhoto(realPicName);
                gateInfor.setLineNo(postInfo.get("lineNo"));
                gateInfor.setSelfPhoto(livePicName);
                gateInfor.setStationNo(postInfo.get("stationNo"));
                gateInfor.setTerminalNo(postInfo.get("terminalNo"));
                /**发送http Post请求保存数据**/
                System.out.println(gateInfor.toString());
                HttpRequestUtil.sendPost(saveGateInforURL, gateInfor.toString());
                exchange.sendResponseHeaders(200,0);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }catch (IOException ie) {

            } catch (Exception e) {

            }
        }
    }

    public static Map<String,String> formData2Dic(String formData ) {
        Map<String,String> result = new HashMap<>();
        if(formData== null || formData.trim().length() == 0) {
            return result;
        }
        final String[] items = formData.split("&");
        Arrays.stream(items).forEach(item ->{
            final String[] keyAndVal = item.split("=");
            if( keyAndVal.length == 2) {
                try{
                    final String key = URLDecoder.decode( keyAndVal[0],"utf8");
                    final String val = URLDecoder.decode( keyAndVal[1],"utf8");
                    result.put(key,val);
                }catch (UnsupportedEncodingException e) {}
            }
        });
        return result;
    }
}