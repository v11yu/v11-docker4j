package org.v11.docker4j.image;

import org.junit.Test;
import org.v11.docker4j.Images;

public class ImagesTest {
	String name = "centos";
	Images is = new Images();
	@Test
	public void history(){
		System.out.println(is.hostoryImages(name));
	}
}
