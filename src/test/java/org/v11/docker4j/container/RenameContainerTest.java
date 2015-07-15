package org.v11.docker4j.container;

import org.junit.Test;
import org.v11.docker4j.Containers;

public class RenameContainerTest {
	String id = "5e22e16a7742d362055bf019a1292cb1c6e047551d426e829d4b6efaaf1bda25";
	Containers cs = new Containers();
	@Test
	public void rename(){
		cs.renameContainer(id, "cenos_ssh_c");
	}
}
