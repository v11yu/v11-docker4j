package org.v11.docker4j.container;
import org.junit.*;
import org.v11.docker4j.Containers;
import org.v11.docker4j.query.ContainersListQuery;
public class ListContainersTest {
	Containers cs = new Containers();
	String containerId = "7a7a720f0e657a1d25d26b8303449097ab756cebc4d1667c8df401a65d363699";
	@Test
	public void testListAll(){
		System.out.println(cs.listAllContainers());
	}
	@Test
	public void testListContainersByDefault(){
		System.out.println(cs.listContainersByDefault());
	}
	@Test
	public void testListContainersByLimit(){
		System.out.println(cs.listContainersByLimit(2));
	}
	@Test
	public void testListContainersBySinceID(){
		System.out.println(cs.listContainersBySinceID(containerId));
	}
	@Test
	public void testListContainersByBefore(){
		System.out.println(cs.listContainersByBeforeID(containerId));
	}
	@Test
	public void testListContainersByQuery(){
		ContainersListQuery query = new ContainersListQuery();
		query.setLimit(3);
		query.setSize(true);
		System.out.println(cs.listContainersByQuery(query));
	}
}
