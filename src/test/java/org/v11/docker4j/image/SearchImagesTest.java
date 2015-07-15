package org.v11.docker4j.image;

import org.junit.Test;
import org.v11.docker4j.Images;

public class SearchImagesTest {
	String term = "sshd";
	Images is = new Images();
	@Test
	public void search(){
		System.out.println(is.searchImages(term));
	}
}
