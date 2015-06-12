package com.jjb.util;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.io.InputStream;
import java.util.Map;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.DataOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class Communicator {
	public static final String HOST_NAME = "115.28.84.22/lineonline/app/";

	public static Bitmap GetLocalOrNetBitmap(String url) {
		Log.e("getlocalbitmap", url);
		Bitmap bitmap = null;
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		try {
			in = new BufferedInputStream(new URL(url).openStream(), 2048);
			final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
			out = new BufferedOutputStream(dataStream, 2048);
			while (in.available() > 0) {
				out.write(in.read());
			}
			out.flush();
			byte[] data = dataStream.toByteArray();
			bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			data = null;
			return bitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Bitmap getImage(String url) throws Exception {
		final DefaultHttpClient client = new DefaultHttpClient();
		final HttpGet getRequest = new HttpGet(url);

		HttpResponse response = client.execute(getRequest);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != HttpStatus.SC_OK) {
			Log.e("PicShow", "Request URL failed, error code =" + statusCode);
		}

		HttpEntity entity = response.getEntity();
		if (entity == null) {
			Log.e("PicShow", "HttpEntity is null");
		}
		InputStream is = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			is = entity.getContent();
			byte[] buf = new byte[1024];
			int readBytes = -1;
			while ((readBytes = is.read(buf)) != -1) {
				baos.write(buf, 0, readBytes);
			}
		} finally {
			if (baos != null) {
				baos.close();
			}
			if (is != null) {
				is.close();
			}
		}
		byte[] imageArray = baos.toByteArray();
		return BitmapFactory.decodeByteArray(imageArray, 0, imageArray.length);
	}

	public static String sendGet(String method) throws SocketTimeoutException {
		String result = null;

		URL url = null;
		HttpURLConnection connection = null;
		InputStream in = null;
		try {
			url = new URL("http://" + HOST_NAME + method);
			Log.e("get url", "http://" + HOST_NAME + method);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setDoInput(true);
			connection.setConnectTimeout(6000);
			connection.setDoOutput(true);
			in = connection.getInputStream();
			InputStreamReader r = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(r);
			String line = null;
			while ((line = br.readLine()) != null) {
				result = line + "\n";
			}
			if (result == null)
				Log.e("noresponse", "nores");
			r.close();
			connection.disconnect();
			Log.e("get result", result);
			return result;

		} catch (SocketTimeoutException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
		Log.e("get result", result);
		return result;

	}

	public static String sendPost(String method, String content)
			throws SocketTimeoutException {
		String result = null;
		URL url = null;
		HttpURLConnection connection = null;
		InputStreamReader in = null;
		try {
			url = new URL("http://" + HOST_NAME + method);
			Log.e("post url", "http://" + HOST_NAME + method + content);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.setRequestProperty("Charset", "utf-8");
			OutputStream os = connection.getOutputStream();
			DataOutputStream dop = new DataOutputStream(os);
			// String md5Pass = getMD5Str("lalala");
			// dop.writeBytes("username=844526975@qq.com&password="+md5Pass);
			byte[] buffer = content.getBytes();
			dop.write(buffer);
			dop.flush();
			dop.close();
			in = new InputStreamReader(connection.getInputStream());
			BufferedReader bufferedReader = new BufferedReader(in);
			StringBuffer strBuffer = new StringBuffer();
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				strBuffer.append(line);
			}
			result = strBuffer.toString();
		} catch (SocketTimeoutException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			Log.e("error", e.toString());
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
					Log.e("error1", e.toString());
				}
			}
		}
		Log.e("post result", result);
		return result;

	}

	public static String sendPostMulti(String method,
			Map<String, Object> params, byte[] image) {
		String result = "";

		String end = "\r\n";
		String uploadUrl = "http://" + HOST_NAME + method;
		String MULTIPART_FORM_DATA = "multipart/form-data";
		String BOUNDARY = "---------7d4a6d158c9";

		try {
			URL url = new URL(uploadUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			// conn.setUseCaches(false);
			conn.setConnectTimeout(6000);
			conn.setReadTimeout(6000);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", MULTIPART_FORM_DATA
					+ "; boundary=" + BOUNDARY);

			StringBuilder sb = new StringBuilder();

			for (Map.Entry<String, Object> entry : params.entrySet()) {
				sb.append("--");
				sb.append(BOUNDARY);
				sb.append("\r\n");
				sb.append("Content-Disposition: form-data; name=\""
						+ entry.getKey() + "\"\r\n\r\n");
				sb.append(entry.getValue());
				sb.append("\r\n");
			}

			sb.append("--");
			sb.append(BOUNDARY);
			sb.append("\r\n");

			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			dos.write(sb.toString().getBytes());

			dos.writeBytes("Content-Disposition: form-data; name=\"picture\"\r\n\r\n");
			dos.write(image, 0, image.length);
			dos.writeBytes(end);

			dos.writeBytes("--" + BOUNDARY + "--\r\n");
			dos.flush();

			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			result = br.readLine();

		} catch (Exception e) {
			result = e.toString();
		}

		return result;

	}

	public static String sendPostMulti_again(String method,
			Map<String, Object> params, byte[] image, String type) {
		String result = "";

		String end = "\r\n";
		String uploadUrl = "http://" + HOST_NAME + "/" + method;
		String MULTIPART_FORM_DATA = "multipart/form-data";
		String BOUNDARY = "---------7d4a6d158c9";

		try {
			URL url = new URL(uploadUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoInput(true);
			conn.setDoOutput(true);
			// conn.setUseCaches(false);
			conn.setConnectTimeout(6000);
			conn.setReadTimeout(6000);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			conn.setRequestProperty("Content-Type", MULTIPART_FORM_DATA
					+ "; boundary=" + BOUNDARY);

			StringBuilder sb = new StringBuilder();

			for (Map.Entry<String, Object> entry : params.entrySet()) {
				sb.append("--");
				sb.append(BOUNDARY);
				sb.append("\r\n");
				sb.append("Content-Disposition: form-data; name=\""
						+ entry.getKey() + "\"\r\n\r\n");
				sb.append(entry.getValue());
				sb.append("\r\n");
			}

			sb.append("--");
			sb.append(BOUNDARY);
			sb.append("\r\n");

			DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
			dos.write(sb.toString().getBytes());

			dos.writeBytes("Content-Disposition: form-data; name=\"" + type
					+ "\"\r\n\r\n");
			dos.write(image, 0, image.length);
			dos.writeBytes(end);

			dos.writeBytes("--" + BOUNDARY + "--\r\n");
			dos.flush();

			InputStream is = conn.getInputStream();
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			result = br.readLine();

		} catch (Exception e) {
			result = e.toString();
		}

		return result;

	}

}
