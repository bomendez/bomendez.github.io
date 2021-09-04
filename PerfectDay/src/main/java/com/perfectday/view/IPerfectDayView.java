package com.perfectday.view;

import org.springframework.stereotype.Component;

/**
 * This interface represents the View of the Perfect Day application and
 * implements methods that generates a pop up whereby the user can upload the
 * file to parse. Depending on the type of file chosen by the user, relevant
 * methods to parse the file are called.
 */

@Component
public interface IPerfectDayView {

	void start();

}
