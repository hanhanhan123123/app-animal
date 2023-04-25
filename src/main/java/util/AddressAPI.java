package util;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;

import data.address.AddressDocument;
import data.address.AddressResponseResult;

/*
 * 주소에 해당하는 상세값들을 얻어올때 사용할 API
 */
public class AddressAPI {
	public static AddressDocument getAddress(String query) {
		
		try {
			String target = "http://dapi.kakao.com/v2/local/search/address";
			System.out.println(query);
			System.out.println(URLEncoder.encode(query,"utf-8"));
			//같은 내용인데 %%%로 바뀜. 한글로 바로 보내면 인식을 못해서 이렇게 바꿔서 보내야 할 수 있다. 
			//한글로 보내서 바로 안된다면 이런식으로 변환해서 보내줘야 한다.
			String queryString = "query=" + URLEncoder.encode(query,"utf-8");

			URI uri = new URI(target + "?" + queryString);

			// HttpClient 객체를 활용하는 방식
			HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = HttpRequest.newBuilder(uri)
					.header("Authorization","KakaoAK 0a0b1b959c63a041acb1c26cbba23472").GET().build();
			//헤더설정
			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			Gson gson = new Gson();
			System.out.println(response.body());
			AddressResponseResult result = gson.fromJson(response.body(), AddressResponseResult.class);
			return result.getDocuments()[0];
			//랭스가 0이면 안나오겠지.  null이면 에러가 터질것이지만 트라이캐치 해놓았음.
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}