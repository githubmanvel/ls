package test

import org.json.JSONObject
import org.spockframework.compiler.model.CleanupBlock

import spock.lang.Specification

class UpdateSpec extends Specification{

	def "Check status text can be updated"(){

		def timeStamp = System.currentTimeMillis();

		when:
		def response = TweeterAPI.sendStatusUpdateRequest(text+timeStamp);
		def httpCode = response.getCode();
		JSONObject obj = new JSONObject(response.getBody());

		def responseText =  obj.getString("text");

		then:
		httpCode == 200;
		responseText == text+timeStamp;


		cleanup:
		TweeterAPI.sendStatusDestroyRequest(obj.getString("id_str"));


		where:
		text << ["Ordinary text", "Кириллица"]

		//					"i`~!@#%^&*(><?/*"]
	}


	def "Check duplicated status causes 403 HTTP error"(){

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
