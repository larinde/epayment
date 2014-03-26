package org.larinde.epay.common.binding;


import org.joda.time.DateTime;

/**
 * 
 * @author olarinde.ajai@gmail.com
 *
 */
public class JaxbCustomDataTypeConverter {

	public static DateTime unmarshal(String dateTime) {
		return new DateTime(dateTime);
	}

	public static String marshal(DateTime dateTime) {
		
		return dateTime.toString();
	}

}
