package org.larinde.epay.common.binding;

import java.util.Calendar;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * 
 * @author olarinde.ajai@gmail.com
 *
 */
public class DateTimeAdapter extends XmlAdapter<Calendar, DateTime> {

	@Override
	public DateTime unmarshal(Calendar cal) throws Exception {
		return new DateTime(cal.get(Calendar.YEAR), 
				cal.get(Calendar.MONTH) + 1, 
				cal.get(Calendar.DATE), 
				cal.get(Calendar.HOUR_OF_DAY), 
				cal.get(Calendar.MINUTE), 
				cal.get(Calendar.MILLISECOND),
				DateTimeZone.forTimeZone(cal.getTimeZone()));
	}

	@Override
	public Calendar marshal(DateTime v) throws Exception {
		Calendar cal = Calendar.getInstance(v.getZone().toTimeZone());
		cal.setTime(v.toDate());
		return cal;
	}

}
