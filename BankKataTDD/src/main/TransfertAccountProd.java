package main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class TransfertAccountProd implements TransfertAccount{

	@Override
	public int transferPost(String ibanFrom, String ibanTo, Integer amount) {
		HttpPost post = new HttpPost("http://kata-bank-back.2ss5iq3sva.eu-west-3.elasticbeanstalk.com/api/transfer");

		// add request parameter, form parameters
		List<NameValuePair> urlParameters = new ArrayList<>();
		urlParameters.add(new BasicNameValuePair("ibanFrom", ibanFrom));
		urlParameters.add(new BasicNameValuePair("ibanTo", ibanTo));
		urlParameters.add(new BasicNameValuePair("amount", amount.toString()));

		try {
			post.setEntity(new UrlEncodedFormEntity(urlParameters));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(post)) {

			System.out.println("response : "+response.getStatusLine().getStatusCode());
			return response.getStatusLine().getStatusCode();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 400;
	}
}