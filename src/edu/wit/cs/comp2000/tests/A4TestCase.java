package edu.wit.cs.comp2000.tests;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.security.Permission;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import edu.wit.cs.comp2000.A4;

public class A4TestCase{


	@Rule
	public Timeout globalTimeout = Timeout.seconds(5);

	@SuppressWarnings("serial")
	private static class ExitException extends SecurityException {}

	private static class NoExitSecurityManager extends SecurityManager 
	{
		@Override
		public void checkPermission(Permission perm) {}

		@Override
		public void checkPermission(Permission perm, Object context) {}

		@Override
		public void checkExit(int status) { super.checkExit(status); throw new ExitException(); }
	}

	@Before
	public void setUp() throws Exception 
	{
		System.setSecurityManager(new NoExitSecurityManager());
	}

	@After
	public void tearDown() throws Exception 
	{
		System.setSecurityManager(null);
	}

	@Test
	public void testShort() {
		String[] files = {"texts/short.txt"};

		String[] actual = A4.FindLongestCommonWords(files);

		String[] expected = {"evenlongerword", "longword"};

		assertEquals("Wrong number of results", expected.length, actual.length);
		assertArrayEquals("Word list does not match", expected, actual);
	}


	@Test
	public void testOneTwoThree() {
		String[] files = {"texts/test1.txt", "texts/test2.txt", "texts/test3.txt"};

		String[] actual = A4.FindLongestCommonWords(files);

		String[] expected = {"absorptances", "abstractness", "favoritisms", "noisemaker", "nominators"};

		assertEquals("Wrong number of results", expected.length, actual.length);
		assertArrayEquals("Word list does not match", expected, actual);
	}

	@Test
	public void testOneTwo() {
		String[] files = {"texts/test1.txt", "texts/test2.txt"};

		String[] actual = A4.FindLongestCommonWords(files);

		String[] expected = {"absorbencies", "absorptances", "absorptivity", "absquatulate",
				"abstemiously","abstractable", "abstractedly", "abstractions", "abstractness",
				"abstruseness", "abstrusities", "absurdnesses"};

		assertEquals("Wrong number of results", expected.length, actual.length);
		assertArrayEquals("Word list does not match", expected, actual);
	}

	@Test
	public void testTwoThree() {
		String[] files = {"texts/test3.txt", "texts/test2.txt"};

		String[] actual = A4.FindLongestCommonWords(files);

		String[] expected = {"absorptances", "abstractness", "favoritisms", "noisemaker", "nomarchies",
				"nominalism", "nominalist", "nominating", "nomination", "nominative", "nominators",
				"nomographs", "nomography"};

		assertEquals("Wrong number of results", expected.length, actual.length);
		assertArrayEquals("Word list does not match", expected, actual);
	}

	@Test
	public void testFull() {
		String[] files = {"texts/TheOdyssey.txt", "texts/TheScarletLetter.txt", "texts/AgnesGrey.txt",
				"texts/PrideAndPrejudice.txt", "texts/filter.txt", "texts/PracticeOfSurgery.txt"};

		String[] actual = A4.FindLongestCommonWords(files);

		String[] expected = {"afterwards", "conducted", "considering", "elsewhere", "exceedingly",
				"excellent", "necessity", "perceived", "themselves"};

		assertEquals("Wrong number of results", expected.length, actual.length);
		assertArrayEquals("Word list does not match", expected, actual);
	}

}
