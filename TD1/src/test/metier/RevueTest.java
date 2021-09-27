package test.metier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import metier.Revue;

public class RevueTest {
	private Revue r1;
	private Revue r2;

	@Before
	public void setUp() {
		r1 = new Revue(1, "test", "test", 1.0, "test", 1);
		r2 = new Revue(1, "test", "test", 1.0, "test", 1);
	}

	@Test
	public void testVerifEquals() {
		assertTrue(r1.equals(r2));
	}

	@Test
	public void testHashCode() {
		assertEquals(r1.hashCode(), r2.hashCode());
	}
}
