/**
 * 
 */
package com.test.test;

public class AnswerFactory {
	
	//Unused, don't mess with it though
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
