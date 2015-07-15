package org.v11.docker4j.container;

import org.junit.Test;
import org.v11.docker4j.Containers;

public class RemoveContainerTest {
	String id = "ece66f6169683d0ad4061e926cce0d34cf8712e463b0a245c0e9c02b097b1533";
	Containers cs = new Containers();
	@Test
	public void delete(){
		cs.removeContainer(id, 1, 1);
	}
}
