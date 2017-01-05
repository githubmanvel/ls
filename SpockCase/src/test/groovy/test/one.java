package test;

import java.io.IOException;

import com.github.scribejava.apis.TwitterApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth10aService;

public class one {
	
	static String defaultUrl = "https://api.twitter.com/1.1/account/verify_credentials.json";
	
	
	static String timeLineGet = "https://api.twitter.com/1.1/statuses/home_timeline.json";
//	Fields to verify
//	- created_at
//	- retweet_count
//	- text
	static String statusDestroyPost = "https://api.twitter.com/1.1/statuses/destroy/817102280583475206.json";
	//precondition create status -> get ID -> delete
//	verify status deleted - > verify really deleted
	
	static String statusUpdatePost = "POST https://api.twitter.com/1.1/statuses/update.json?status=Maybe%20he%27ll%20finally%20find%20his%20keys.%20%23peterfalk";
//	Fields to verify
//	- text (check that text can be updated)
	
	public static void main(String[] arg) throws IOException {
		final OAuth10aService service = new ServiceBuilder().apiKey("jIj0kRWDi7btL7KThsux98gz7")
				.apiSecret("Mk7zAN4tRFmHmaVuyBin5JxHXfZuKFPQ9YBdVEiwWt0fTqrL91").build(TwitterApi.instance());


		final OAuth1AccessToken accessToken = new OAuth1AccessToken(
				"2978797517-mpbvyf9FJD82xnF6z7OKoAb7eq115zmZx2FVnl0", "5gXSG0hDGy8Sqn7tPjK4rWQUTdqRySpH5PIs9JalGfnm4");
//		final OAuthRequest request = new OAuthRequest(Verb.GET,
//				timeLine, service);
		
		final OAuthRequest request = new OAuthRequest(Verb.POST,
				statusDestroyPost, service);
		
		service.signRequest(accessToken, request); // the access token from step
													// 4
		final com.github.scribejava.core.model.Response response = request.send();
		System.out.println(response.getBody());
	}

}
