package org.v11.docker4j.container;

import org.junit.Test;
import org.v11.docker4j.Containers;

public class CreateContainerTest {
	Containers cs = new Containers();
	@Test
	public void testCreate(){
		System.out.println(cs.createContainer("test_reg_3", "registry:2"));
	}
}
