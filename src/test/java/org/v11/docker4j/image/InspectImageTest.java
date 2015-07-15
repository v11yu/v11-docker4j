package org.v11.docker4j.image;

import org.junit.Test;
import org.v11.docker4j.Images;

public class InspectImageTest {
	Images is = new Images();
	@Test
	public void testInspect(){
		System.out.println(is.inspectImage("centos"));
	}
}
