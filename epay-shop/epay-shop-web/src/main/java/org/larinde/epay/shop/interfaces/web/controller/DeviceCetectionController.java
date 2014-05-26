package org.larinde.epay.shop.interfaces.web.controller;

import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author olarinde.ajai@gmail.com
 *
 */
@Controller
public class DeviceCetectionController {

	/**
	 * 
	 */
	@RequestMapping("/")
	public String deviceRedirection(Device device) {
		if (device.isNormal()) {
			return "desktop";
		}
		if (device.isMobile()) {
			return "mobile";
		}
		return "desktop";
	}

}
