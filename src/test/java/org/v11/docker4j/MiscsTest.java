package org.v11.docker4j;

import org.junit.Test;

public class MiscsTest {
	Miscs mis = new Miscs();
	@Test
	public void infoTest(){
		System.out.println(mis.info());
	}
	@Test
	public void versionTest(){
		System.out.println(mis.version());
	}
	@Test
	public void pingTest(){
		System.out.println(mis.ping());
	}
}
