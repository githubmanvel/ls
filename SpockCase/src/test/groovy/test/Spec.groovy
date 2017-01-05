package test



import spock.lang.Specification

import org.codehaus.groovy.control.customizers.ImportCustomizer.Import

import groovy.util.logging.Slf4j


class Spec extends Specification {
	
	

	def setup(){
	System.out.print("Setup");
		
	}
	
	def "retrieved book object is null"(){
		def t = new one();
		t.doThis();
		int i  =0 ;
		expect :
			i == 0
		
		
	}
			
}