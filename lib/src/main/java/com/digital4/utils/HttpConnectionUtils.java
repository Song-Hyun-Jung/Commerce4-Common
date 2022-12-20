package com.digital4.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpConnectionUtils {
	
	/** HttpURLConnection GET 방식 */
	public static String getRequest(String targetUrl) throws Exception{
		
		String response = "";
		
		try {
			String newUrl = "http://192.168.137.10:" + targetUrl;
			//String newUrl = "http://192.168.1.113:" + targetUrl;
			URL url = new URL(newUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET"); // 전송 방식
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			conn.setConnectTimeout(5000); // 연결 타임아웃 설정(5초) 
			conn.setReadTimeout(5000); // 읽기 타임아웃 설정(5초)
			conn.setDoOutput(true);
			
	       // System.out.println("getContentType():" + conn.getContentType()); // 응답 콘텐츠 유형 구하기
	       // System.out.println("getResponseCode():"    + conn.getResponseCode()); // 응답 코드 구하기
	       // System.out.println("getResponseMessage():" + conn.getResponseMessage()); // 응답 메시지 구하기

			Charset charset = Charset.forName("UTF-8");
			BufferedReader br;
			if(conn.getResponseCode() != 200) {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), charset));
				String inputLine;			
	  			StringBuffer sb = new StringBuffer();
	  			while ((inputLine = br.readLine()) != null) {
	  				sb.append(inputLine);
	  			}
	  			br.close();
	  			response = sb.toString();
	  			throw new Exception(response);
	        }
	        else {
	        	br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
	        	String inputLine;			
	  			StringBuffer sb = new StringBuffer();
	  			while ((inputLine = br.readLine()) != null) {
	  				sb.append(inputLine);
	  			}
	  			br.close();
	  			response = sb.toString();
	        }	
			
		} catch (Exception e) {
			throw e;
		}

		
		return response;
	}
	
	/** HttpURLConnection GET 방식 */
	//header 있음
	public static String getRequest(String targetUrl, String authHeader) throws Exception{
		
		String response = "";
		
		try {
			
			String newUrl = "http://192.168.137.10:" + targetUrl;
			//String newUrl = "http://192.168.1.113:" + targetUrl;
			URL url = new URL(newUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET"); // 전송 방식
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			conn.addRequestProperty("Authorization", authHeader);
			conn.setConnectTimeout(5000); // 연결 타임아웃 설정(5초) 
			conn.setReadTimeout(5000); // 읽기 타임아웃 설정(5초)
			conn.setDoOutput(true);
			
	       // System.out.println("getContentType():" + conn.getContentType()); // 응답 콘텐츠 유형 구하기
	       // System.out.println("getResponseCode():"    + conn.getResponseCode()); // 응답 코드 구하기
	       // System.out.println("getResponseMessage():" + conn.getResponseMessage()); // 응답 메시지 구하기

			Charset charset = Charset.forName("UTF-8");
			BufferedReader br;
			if(conn.getResponseCode() != 200) {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), charset));
				String inputLine;			
	  			StringBuffer sb = new StringBuffer();
	  			while ((inputLine = br.readLine()) != null) {
	  				sb.append(inputLine);
	  			}
	  			br.close();
	  			response = sb.toString();
	  			throw new Exception(response);
	        }
	        else {
	        	br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
	        	String inputLine;			
	  			StringBuffer sb = new StringBuffer();
	  			while ((inputLine = br.readLine()) != null) {
	  				sb.append(inputLine);
	  			}
	  			br.close();
	  			response = sb.toString();
	        }	
		} catch (Exception e) {
			throw e;
		}

		
		return response;
	}
	
	/** HttpURLConnection POST 방식 */
	//header 없음-Map
	public static String postRequest(String targetUrl, Map<String, Object> requestMap) throws Exception {

		String response = "";

		try {
			String newUrl = "http://192.168.137.10:" + targetUrl;
			//String newUrl = "http://192.168.1.113:" + targetUrl;
			URL url = new URL(newUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST"); // 전송 방식
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			conn.setConnectTimeout(5000); // 연결 타임아웃 설정(5초) 
			conn.setReadTimeout(5000); // 읽기 타임아웃 설정(5초)
			conn.setDoOutput(true);	// URL 연결을 출력용으로 사용(true)
			
			String requestBody = getJsonStringFromMap(requestMap);
			System.out.println("requestBody:" + requestBody);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bw.write(requestBody);
			bw.flush();
			bw.close();

			System.out.println("getContentType():" + conn.getContentType()); // 응답 콘텐츠 유형 구하기
	        System.out.println("getResponseCode():"    + conn.getResponseCode()); // 응답 코드 구하기
	        System.out.println("getResponseMessage():" + conn.getResponseMessage()); // 응답 메시지 구하기

			Charset charset = Charset.forName("UTF-8");
			BufferedReader br;
			
			if(conn.getResponseCode() != 200) {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), charset));
				String inputLine;			
	  			StringBuffer sb = new StringBuffer();
	  			while ((inputLine = br.readLine()) != null) {
	  				sb.append(inputLine);
	  			}
	  			br.close();
	  			response = sb.toString();
	  			throw new Exception(response);
	        }
	        else {
	        	br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
	        	String inputLine;			
	  			StringBuffer sb = new StringBuffer();
	  			while ((inputLine = br.readLine()) != null) {
	  				sb.append(inputLine);
	  			}
	  			br.close();
	  			response = sb.toString();
	        }	
		} catch (Exception e) {
			throw e;
		}


		return response;
	}
	
	/** HttpURLConnection POST 방식 */
	//header 있음-Map
	public static String postRequest(String targetUrl, Map<String, Object> requestMap, String authHeader) throws Exception{

		String response = "";

		try {
			String newUrl = "http://192.168.137.10:" + targetUrl;
			//String newUrl = "http://192.168.1.113:" + targetUrl;
			URL url = new URL(newUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST"); // 전송 방식
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			conn.addRequestProperty("Authorization", authHeader);
			conn.setConnectTimeout(5000); // 연결 타임아웃 설정(5초) //sync~establish
			conn.setReadTimeout(5000); // 읽기 타임아웃 설정(5초) //establish 이후
			conn.setDoOutput(true);	// URL 연결을 출력용으로 사용(true)
			
			String requestBody = getJsonStringFromMap(requestMap);
			System.out.println("requestBody:" + requestBody);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bw.write(requestBody);
			bw.flush();
			bw.close();

			System.out.println("getContentType():" + conn.getContentType()); // 응답 콘텐츠 유형 구하기
	        System.out.println("getResponseCode():"    + conn.getResponseCode()); // 응답 코드 구하기
	        System.out.println("getResponseMessage():" + conn.getResponseMessage()); // 응답 메시지 구하기

			Charset charset = Charset.forName("UTF-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
			
			String inputLine;			
			StringBuffer sb = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}
			br.close();
			
			response = sb.toString();

		} catch (Exception e) {
			throw e;
		}

		return response;
	}
	
	/** Map을 jsonString으로 변환 */
	@SuppressWarnings("unchecked")
	public static String getJsonStringFromMap(Map<String, Object> map) {
    
		JSONObject json = new JSONObject();
		
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			
			String key = entry.getKey();
            Object value = entry.getValue();
            
            json.put(key, value);
        }
        
        return json.toJSONString();
	}
	
	
	/** HttpURLConnection POST 방식 */
	//header 없음-Object
	public static String postRequest(String targetUrl, Object object) throws Exception{

		String response = "";

		try {

			String newUrl = "http://192.168.137.10:" + targetUrl;
			//String newUrl = "http://192.168.1.113:" + targetUrl;
			URL url = new URL(newUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST"); // 전송 방식
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			conn.setConnectTimeout(5000); // 연결 타임아웃 설정(5초) 
			conn.setReadTimeout(5000); // 읽기 타임아웃 설정(5초)
			conn.setDoOutput(true);	// URL 연결을 출력용으로 사용(true)
			
			ObjectMapper objectMapper = new ObjectMapper();
			String requestBody = objectMapper.writeValueAsString(object);
			System.out.println("requestBody:" + requestBody);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bw.write(requestBody);
			bw.flush();
			bw.close();

			System.out.println("getContentType():" + conn.getContentType()); // 응답 콘텐츠 유형 구하기
	        System.out.println("getResponseCode():"    + conn.getResponseCode()); // 응답 코드 구하기
	        System.out.println("getResponseMessage():" + conn.getResponseMessage()); // 응답 메시지 구하기

			Charset charset = Charset.forName("UTF-8");
			BufferedReader br;
			if(conn.getResponseCode() != 200) {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), charset));
				String inputLine;			
	  			StringBuffer sb = new StringBuffer();
	  			while ((inputLine = br.readLine()) != null) {
	  				sb.append(inputLine);
	  			}
	  			br.close();
	  			response = sb.toString();
	  			throw new Exception(response);
	        }
	        else {
	        	br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
	        	String inputLine;			
	  			StringBuffer sb = new StringBuffer();
	  			while ((inputLine = br.readLine()) != null) {
	  				sb.append(inputLine);
	  			}
	  			br.close();
	  			response = sb.toString();
	        }	
		} catch (Exception e) {
			throw e;
		}

		return response;
	}
	
	/** HttpURLConnection POST 방식 */
	//header 있음-Object
	public static String postRequest(String targetUrl, Object object, String authHeader) throws Exception{

		String response = "";

		try {
			String newUrl = "http://192.168.137.10:" + targetUrl;
			//String newUrl = "http://192.168.1.113:" + targetUrl;
			URL url = new URL(newUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST"); // 전송 방식
			conn.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			conn.addRequestProperty("Authorization", authHeader);
			conn.setConnectTimeout(5000); // 연결 타임아웃 설정(5초) //sync~establish
			conn.setReadTimeout(5000); // 읽기 타임아웃 설정(5초) //establish 이후
			conn.setDoOutput(true);	// URL 연결을 출력용으로 사용(true)
			
			ObjectMapper objectMapper = new ObjectMapper();
			String requestBody = objectMapper.writeValueAsString(object);
			System.out.println("requestBody:" + requestBody);
			
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			bw.write(requestBody);
			bw.flush();
			bw.close();

			System.out.println("getContentType():" + conn.getContentType()); // 응답 콘텐츠 유형 구하기
	        System.out.println("getResponseCode():"    + conn.getResponseCode()); // 응답 코드 구하기
	        System.out.println("getResponseMessage():" + conn.getResponseMessage()); // 응답 메시지 구하기
	        
	        Charset charset = Charset.forName("UTF-8");
	        BufferedReader br;
	        if(conn.getResponseCode() != 200) {
				br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), charset));
				String inputLine;			
	  			StringBuffer sb = new StringBuffer();
	  			while ((inputLine = br.readLine()) != null) {
	  				sb.append(inputLine);
	  			}
	  			br.close();
	  			response = sb.toString();
	  			throw new Exception(response);
	        }
	        else {
	        	br = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
	        	String inputLine;			
	  			StringBuffer sb = new StringBuffer();
	  			while ((inputLine = br.readLine()) != null) {
	  				sb.append(inputLine);
	  			}
	  			br.close();
	  			response = sb.toString();
	        }	

		}catch (Exception e) {
			throw e;
		}


		return response;
	}
}

