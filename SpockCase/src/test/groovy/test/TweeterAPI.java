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

	// All below constants also can be stored in property file
	private static final String TIME_LINE_URL = "https://api.twitter.com/1.1/statuses/home_timeline.json";
	private static final String STATUS_DESTROY_URL = "https://api.twitter.com/1.1/statuses/destroy/%s.json";
	private static final String STATUS_RETWEET_URL = "https://api.twitter.com/1.1/statuses/retweet/%s.json";
	private static final String STATUS_UPDATE_URL = "https://api.twitter.com/1.1/statuses/update.json";
	private static final String STATUS_SHOW_URL = "https://api.twitter.com/1.1/statuses/show.json";

	// for oAuth authentication
	private static final String API_KEY = "jIj0kRWDi7btL7KThsux98gz7";
	private static final String API_SECRET = "Mk7zAN4tRFmHmaVuyBin5JxHXfZuKFPQ9YBdVEiwWt0fTqrL91";
	private static final String TOKEN = "2978797517-mpbvyf9FJD82xnF6z7OKoAb7eq115zmZx2FVnl0";
	private static final String TOKEN_SECRET = "5gXSG0hDGy8Sqn7tPjK4rWQUTdqRySpH5PIs9JalGfnm4";

	public static Response sendHomeTimeLineRequest() throws IOException {
		return sendRequest(Verb.GET, TIME_LINE_URL, null);
	}

	public static Response sendStatusDestroyRequest(String statusId) throws IOException {
		return sendRequest(Verb.POST, String.format(STATUS_DESTROY_URL, statusId), null);
	}

	public static Response sendStatusRetweetRequest(String statusId) throws IOException {
		return sendRequest(Verb.POST, String.format(STATUS_RETWEET_URL, statusId), null);
	}

	public static Response sendStatusUpdateRequest(String statusText) throws IOException {
		Map<String, String> map = new HashMap<>();
		map.put("status", statusText);
		return sendRequest(Verb.POST, STATUS_UPDATE_URL, map);
	}

	public static Response getStatusById(String id) throws IOException {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		return sendRequest(Verb.GET, STATUS_SHOW_URL, map);
	}

	private static Response sendRequest(Verb verb, String url, Map<String, String> params) throws IOException {
		OAuth10aService service = new ServiceBuilder().apiKey(API_KEY).apiSecret(API_SECRET)
				.build(TwitterApi.instance());

		OAuth1AccessToken accessToken = new OAuth1AccessToken(TOKEN, TOKEN_SECRET);

		OAuthRequest request = new OAuthRequest(verb, url, service);

		if (params != null) {
			for (Entry<String, String> param : params.entrySet()) {
				request.addParameter(param.getKey(), param.getValue());
			}
		}

		service.signRequest(accessToken, request);

		final Response response = request.send();

		// logging
		System.out.println("Request was sent to: " + url);
		System.out.println("HttpCode: " + response.getCode());
		System.out.println("ResponseBody: " + response.getBody());

		return response;
	}

}
