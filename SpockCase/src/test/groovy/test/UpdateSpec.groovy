package test

import org.json.JSONObject
import org.spockframework.compiler.model.CleanupBlock

import spock.lang.Specification

class UpdateSpec extends Specification{

	def "Status text can be updated"(){

		when:
		def response = TweeterAPI.sendStatusUpdateRequest(text);
		JSONObject obj = new JSONObject(response.getBody());
		def responseText =  obj.getString("text");

		then:
		response.getCode() == 200;
		responseText == text;

		cleanup:
		TweeterAPI.sendStatusDestroyRequest(obj.getString("id_str"));

		where:
		text << ["Ordinary text", "Кириллица"]
		// Also can be checked:	"i`~!@#^&*(><?/*"

	}


	def "Duplicated status causes 403 HTTP error"(){

		def text="duplicated status";
		setup:
		def statusId = new JSONObject(TweeterAPI.sendStatusUpdateRequest(text).getBody()).getString("id_str");

		when:
		def httpCode = TweeterAPI.sendStatusUpdateRequest(text).getCode();

		then:
		httpCode == 403;

		cleanup:
		TweeterAPI.sendStatusDestroyRequest(statusId);
	}
}
