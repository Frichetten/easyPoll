/**
 * 
 */
package com.test.test;

/**
 * @author dalle
 *
 */
public class AnswerFactory {
	
	public static Answer getAnswerType(String type){
		if (type.toLowerCase().equals("radio")){
			return new Radio();
		}
		else if(type.toLowerCase().equals("checkbox")){
			return new Radio(); 
		}
		return null;
	}
}
