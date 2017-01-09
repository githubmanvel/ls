package test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;


import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

public class TweeterAPI {
	public static void main(String[] arg) throws Exception {

		// String response = RequestSender.sendHomeTimeLineRequest();
		// JSONArray arr = new JSONArray(response);
		// for (int i = 0; i < arr.length(); i++) {
		// System.out.println( i);
		// System.out.println( arr.getJSONObject(i).getString("created_at"));
		// System.out.println( arr.getJSONObject(i).getString("retweet_count"));
		// System.out.println( arr.getJSONObject(i).getString("text"));
		// }

		// String response = TweeterAPI.sendStatusUpdateRequest("p");
		// JSONObject obj = new JSONObject(response);

		System.out.println(String.format(STATUS_DESTROT_URL, "000"));
	}

	static final String TIME_LINE_URL = "https://api.twitter.com/1.1/statuses/home_timeline.json";
	// Fields to verify
	// - created_at
	// - retweet_count
	// - text
	static final String STATUS_DESTROT_URL = "https://api.twitter.com/1.1/statuses/destroy/%s.json";
	// precondition create status -> get ID -> delete
	// verify status deleted - > verify really deleted
	static final String STATUS_UPDATE_URL = "https://api.twitter.com/1.1/statuses/update.json";
	// Fields to verify
	// - text (check that text can be updated)

	public static Response sendHomeTimeLineRequest() throws IOException {
		return sendRequest(Verb.GET, TIME_LINE_URL, null);
	}

	public static Response sendStatusDestroyRequest(String statusId) throws IOException {
		return sendRequest(Verb.POST, String.format(STATUS_DESTROT_URL, statusId), null);
	}

	public static Response sendStatusUpdateRequest(String statusText) throws IOException {
		// status=Maybe%20he%27ll%20finally%20find%20his%20keys.%20%23peterfalk
		Map<String, String> map = new HashMap<>();
		map.put("status", statusText);
		return sendRequest(Verb.POST, STATUS_UPDATE_URL, map);
	}

	public static Response sendRequest(Verb verb, String url, Map<String, String> params) throws IOException {
		final OAuth10aService service = new ServiceBuilder().apiKey("jIj0kRWDi7btL7KThsux98gz7")
				.apiSecret("Mk7zAN4tRFmHmaVuyBin5JxHXfZuKFPQ9YBdVEiwWt0fTqrL91").build(TwitterApi.instance());

		final OAuth1AccessToken accessToken = new OAuth1AccessToken(
				"2978797517-mpbvyf9FJD82xnF6z7OKoAb7eq115zmZx2FVnl0", "5gXSG0hDGy8Sqn7tPjK4rWQUTdqRySpH5PIs9JalGfnm4");
		

		final OAuthRequest request = new OAuthRequest(verb, url, service);

		if (params != null) {
			for (Entry<String, String> param : params.entrySet()) {
				request.addParameter(param.getKey(), param.getValue());
			}
		}

		service.signRequest(accessToken, request);

		final Response response = request.send();
		System.out.println(response.getCode());
		System.out.println(response.getBody());

		return response;
	}

}
