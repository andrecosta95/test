package org.fiveware.test.web.util;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.runners.MethodSorters;


@RunWith(JUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class WebUtilsTest {

	@Test
	public void getValidationPropertyTest() {
		Assert.assertEquals("Name is required!", WebUtils.getValidationProperty("fiveware.book.name.empty"));
		Assert.assertNull(WebUtils.getValidationProperty("wrong"));
	}
	
}
