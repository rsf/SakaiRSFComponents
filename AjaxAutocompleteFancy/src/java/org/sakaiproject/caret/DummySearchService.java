/**
 * DummySearchService.java - 2007 Jul 13, 2007 3:51:05 PM - AZ
 */

package org.sakaiproject.caret;

import java.util.ArrayList;
import java.util.List;

/**
 * A search service to let us pretend to be searching for a string among many
 *
 * @author Aaron Zeckoski (aaronz@caret.cam.ac.uk)
 */
public class DummySearchService {

	private String[] searchValues = {
		"aaronz",
		"alan",
		"andy",
		"antranig",
		"catharine",
		"dan",
		"daniel",
		"emel",
		"guy",
		"harriet",
		"ian",
		"john",
		"katy",
		"matthew",
		"mike",
		"raad",
		"raymond",
		"rhiannon",
		"rob",
		"sarah",
		"stephanie",
		"steve",
		"sue",
		"sultan"
	};

	/**
	 * Return the array of all near matches for a search value
	 * @param search a searhc string
	 * @return the array of all near matches of the search prefix
	 */
	public String[] doSearch(String search) {
		List<String> l = new ArrayList<String>();
		for (int i = 0; i < searchValues.length; i++) {
			if (searchValues[i].startsWith(search)) {
				l.add( searchValues[i] );
			}
		}
		return l.toArray( new String[] {} );
	}

}
