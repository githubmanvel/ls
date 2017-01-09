package test



import spock.lang.Specification

import java.rmi.activation.ActivationSystem

import org.codehaus.groovy.control.customizers.ImportCustomizer.Import
import org.json.JSONArray
import org.json.JSONObject
import groovy.util.logging.Slf4j


class TimeLineSpec extends Specification {

def notSharedField = new TweeterAPI();
 

	def setup(){
		System.out.println("Setup");
	}
// run before every feature method
	def cleanup() {}        // run after every feature method
	def setupSpec() {}     // run before the first feature method
	def cleanupSpec() {}   // run after the last feature method
	
	def "Check created time, retweet count and text fields of Home timeline API response "(){

		setup:
//		def statusText = "Status " + System.currentTimeMillis();
		TweeterAPI.sendStatusUpdateRequest(a);
		
		when:
		def response = TweeterAPI.sendHomeTimeLineRequest();
        JSONArray arr = new JSONArray(response);
		def time = arr.getJSONObject(0).getString("created_at");
		def retweet =arr.getJSONObject(0).getString("retweet_count");
		def text =  arr.getJSONObject(0).getString("text");

		then:
		text == a;
		
		
		where:
		a << ["Ordinary text"]
//			"Кириллица",
//			"sdsd",
//			"i`~!@#%^&*(><?/*"]
	


	}
}