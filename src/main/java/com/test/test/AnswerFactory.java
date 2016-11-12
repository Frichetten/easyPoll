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
			return null; //new Radio();
		}
		else if(type.toLowerCase().equals("checkbox")){
			return null;//new Radio(); 
		}
		return null;
	}
}
