package org.v11.docker4j.container;
import org.junit.*;
import org.v11.docker4j.Containers;
public class InspectContainersTest {
	Containers cs = new Containers();
	String id = "7a7a720f0e657a1d25d26b8303449097ab756cebc4d1667c8df401a65d363699";
	@Test
	public void testInspect(){
		System.out.println(cs.inspectContainer(id));
	}
	@Test
	public void testTop(){
		System.out.println(cs.topContainer(id));
	}
}
