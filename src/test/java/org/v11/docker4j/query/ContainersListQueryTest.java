package org.v11.docker4j.query;
import org.junit.*;
public class ContainersListQueryTest {
	@Test
	public void testEmpty(){
		ContainersListQuery query = new ContainersListQuery();
		System.out.println(query.generateQuery());
	}
	@Test
	public void testGenerateQuery(){
		ContainersListQuery query = new ContainersListQuery();
		query.all = true;
		query.limit = 4;
		query.size=true;
		System.out.println(query.generateQuery());
	}
}
