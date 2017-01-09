package test

import org.json.JSONObject
import spock.lang.Specification

class DestroySpec extends Specification{

	def "Check status was really removed"(){

		def text="Status for removing";

		setup:
		def statusId = new JSONObject(TweeterAPI.sendStatusUpdateRequest(text).getBody()).getString("id_str");

		when:
		def response = TweeterAPI.sendStatusDestroyRequest(statusId);

		then:
		response.getCode() == 200;

		when:
		response = TweeterAPI.getStatusById(statusId);

		then:
		response.getCode()==404;
	}
}

