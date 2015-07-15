package org.v11.docker4j.container;
import org.junit.*;
import org.v11.docker4j.Containers;
public class StartContainerTest {
	Containers cs = new Containers();
	String id = "7a7a720f0e657a1d25d26b8303449097ab756cebc4d1667c8df401a65d363699";
//	@Test
//	public void startTest(){
//		cs.startContainer(id);
//	}
	@Test
	public void stopTest(){
		cs.stopContainer(id,5);
	}
}
