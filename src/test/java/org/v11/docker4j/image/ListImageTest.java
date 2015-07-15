package org.v11.docker4j.image;

import org.junit.Test;
import org.v11.docker4j.Images;

public class ListImageTest {
	Images is = new Images();
	@Test
	public void testListImage(){
		System.out.println(is.listImages(0));
	}
}
