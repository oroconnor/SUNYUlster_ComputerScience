//Owen O'Connor
//CSC 201
//Assignment 2

package militarytimeconverter;

import static org.junit.jupiter.api.Assertions.*;
import java.text.NumberFormat;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/** 
 *  @author owenoconnor
 *  @since 08/31/21
 *  Unit test for militarytimeconverter package
 */
class Test1 {

	@Test //test case 1
	void test1() {
		int time = MilitaryTimeConverter.convertFromMilitaryTime(0);
		assertEquals(time,12);
	}
	
	@Test //test case 2
	void test2() {
		int time = MilitaryTimeConverter.convertFromMilitaryTime(1);
		assertEquals(time,1);
	}
	
	@Test //test case 3
	void test3() {
		int time = MilitaryTimeConverter.convertFromMilitaryTime(2);
		assertEquals(time,2);
	}
	
	@Test //test case 4
	void test4() {
		int time = MilitaryTimeConverter.convertFromMilitaryTime(11);
		assertEquals(time,11);
	}
	
	@Test //test case 5
	void test5() {
		int time = MilitaryTimeConverter.convertFromMilitaryTime(12);
		assertEquals(time,12);
	}
	
	@Test //test case 6
	void test6() {
		int time = MilitaryTimeConverter.convertFromMilitaryTime(13);
		assertEquals(time,1);
	}
	
	@Test //test case 7
	void test7() {
		int time = MilitaryTimeConverter.convertFromMilitaryTime(22);
		assertEquals(time,10);
	}
	
	@Test //test case 8
	void test8() {
		int time = MilitaryTimeConverter.convertFromMilitaryTime(23);
		assertEquals(time,11);
	}
	
	@Test //test case 9
	void test9() {
	 Assertions.assertThrows(IllegalArgumentException.class, () -> {
		 MilitaryTimeConverter.convertFromMilitaryTime(-1);
		  });
	}
	
	@Test //test case 10
	void test10() {
	 Assertions.assertThrows(IllegalArgumentException.class, () -> {
		 MilitaryTimeConverter.convertFromMilitaryTime(24);
		  });
	}
	
//	@Test //test case 11
//	void test11() {
//	 Assertions.assertThrows(IllegalArgumentException.class, () -> {
//		 MilitaryTimeConverter.convertFromMilitaryTime("dog");
//		  });
//	}
	
	
	
}
