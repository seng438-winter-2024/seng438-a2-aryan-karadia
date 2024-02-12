package org.jfree.data.test;

import static org.junit.Assert.*; import org.jfree.data.Range; import org.junit.*;

public class RangeTest {
    
	private Range range;
	private Range positiveRange;
	private Range negativeRange;


    @Before
    public void setUp() throws Exception { 
    	range = new Range(-1, 1);
    	positiveRange = new Range(1,4);
    	negativeRange = new Range(-4,-1);
    	
    }
   
    
    // Tests for getLowerBound
    
    @Test
    public void getLowerBoundReturnsCorrectValue() {
        assertEquals("Lower bound should be -1", -1, range.getLowerBound(), 0.000000001d);
    }

    @Test
    public void correctLowerBoundForPositiveRange() {
        assertEquals("Lower bound should for postive range be 1", 1, positiveRange.getLowerBound(), 0.000000001d);
    }
    
    @Test
    public void correctLowerBoundForNegativeRange() {
        assertEquals("Lower bound should for negative range be -4", -4, negativeRange.getLowerBound(), 0.000000001d);
    }

    
    
    
    // Tests for getUppedBound
    
    @Test
    public void getUpperBoundReturnsCorrectValue() {
        assertEquals("Upper bound should be 1", 1, range.getUpperBound(), 0.000000001d);
    }

    @Test
    public void correctUpperBoundForPositiveRange() {
        assertEquals("Upper bound for postive range should be 4", 4, positiveRange.getUpperBound(), 0.000000001d);
    }
    
    @Test
    public void correctUpperBoundForNegativeRange() {
        assertEquals("Upper bound for negative range should be -1", -1, negativeRange.getUpperBound(), 0.000000001d);
    }
    
    
    
    
    // Tests for contains
    
    @Test
    public void valueWithinRange() {
    	assertTrue("0 falls within the range (-1,1)", 
    			range.contains(0));
    }
    
    @Test
    public void valueOnLowerBoundOfRange() {
    	assertTrue("-1 is the upper bound of the range (-1,1)", 
    			range.contains(-1));
    }
    
    @Test
    public void valueOnUpperBoundOfRange() {
     	assertTrue("1 is the upper bound of the range (-1,1)", 
     			range.contains(1));
    }

    @Test
    public void valueLowerThanLowerBound() {
     	assertFalse("-1.5 is outside of the range (-1,1)", 
     			range.contains(-1.5));
    }
    
    @Test
    public void valueHigherThanUpperBound() {
    	assertFalse("1.5 is outside of the range (-1,1)", 
     			range.contains(1.5));
    }
    
    
    
    
    // Tests for constrain
    
    @Test
    public void constrainValueWithinRange() {
        assertEquals("Constraining a value within the range should return the same value",
                0, range.constrain(0), 0.000000001d);
    }
    
    @Test
    public void constrainValueOnLowerBoundOfRange() {
        assertEquals("Constraining a value on the lower bound should return the lower bound",
                -1, range.constrain(-1), 0.000000001d);
    }
    
    @Test
    public void constrainValueOnUpperBoundOfRange() {
        assertEquals("Constraining a value on the upper bound should return the upper bound",
                1, range.constrain(1), 0.000000001d);
    }
    
    @Test
    public void constrainValueBelowLowerBound() {
        assertEquals("Constraining a value below the lower bound should return the lower bound",
                -1, range.constrain(-1.4), 0.000000001d);
    }

    @Test
    public void constrainValueAboveUpperBound() {
        assertEquals("Constraining a value above the upper bound should return the upper bound",
                1, range.constrain(1.6), 0.000000001d);
    }    
    
    
    
    
    // Tests for intersects
    
    @Test
    public void rangesWithSameBoundariesIntersect() {
        assertTrue("Ranges (-1,1) and (-1,1) should intersect", 
        		range.intersects(-1, 1));
    }
    
    @Test
    public void rangeNestingAnotherRangeIntersect() {
        assertTrue("Ranges (-1,1) and (-0.5,0.5) should intersect", 
        		range.intersects(-0.5, 0.5));
    }
    
    @Test
    public void rangeNestedByAnotherRangeIntersect() {
        assertTrue("Ranges (-1,1) and (-2,2) should intersect", 
        		range.intersects(-2, 2));
    }
    
    @Test
    public void rangeIntesectingFromUpperBoundOnly() {
        assertTrue("Ranges (-1,1) and (0,2) should intersect", 
        		range.intersects(0, 2));
    }    
    
    @Test
    public void rangeIntesectingFromLowerBoundOnly() {
        assertTrue("Ranges (-1,1) and (-2,0) should intersect", 
        		range.intersects(-2, 0));
    }    
    
    
    @Test
    public void notIntersectingIfOnlyUpperBoundTouchingAnotherRange() {
        assertFalse("Ranges (-1,1) and (1,3) should not intersect", 
        		range.intersects(1, 3));
    }
    
    @Test
    public void notIntersectingIfOnlyLowerBoundTouchingAnotherRange() {
        assertFalse("Ranges (-1,1) and (1,3) should not intersect", 
        		range.intersects(-3, -1));
    }
    
    @Test
    public void rangeSeperatedByGapNearUpperrBoundDoNotIntersect() {
        assertFalse("Ranges (-1,1) and (3,5) should not intersect", 
        		range.intersects(3, 5));
    }
    
    @Test
    public void rangeSeperatedByGapNearLowerBoundDoNotIntersect() {
        assertFalse("Ranges (-1,1) and (-5,-3) should not intersect", 
        		range.intersects(-5, -3));
    }
    
}