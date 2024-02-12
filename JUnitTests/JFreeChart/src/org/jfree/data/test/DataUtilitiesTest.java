package org.jfree.data.test;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DataUtilitiesTest extends DataUtilities {

    private Mockery mockingContext;
    private Values2D values;
    private Values2D values2;
    private Values2D values3;
    private Values2D values4;
    private Values2D negativeValues;
    private Values2D valuesRow1;
    private Values2D valuesRow2;
    private Values2D valuesRow3;
    @Before
    public void setUp() {
    	// Creates mock object of Values2D.class with 2 rows and 1 column
        mockingContext = new Mockery();
        values = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {
            {
                allowing(values).getRowCount();
                will(returnValue(2));
                allowing(values).getValue(0, 0);
                will(returnValue(7.5));
                allowing(values).getValue(1, 0);
                will(returnValue(2.5));
            }
        });
        
        // Creates mock object of Values2D.class with 1 row and 2 columns
        mockingContext = new Mockery();
        values2 = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations(){
          {
             allowing(values2).getColumnCount(); will(returnValue(2));
             allowing(values2).getRowCount(); will(returnValue(1));
             allowing(values2).getValue(0, 1); will(returnValue(3.5));
          }
         });
        
        // Creates a mock object Values2D.class with 3 columns and 2 rows
        mockingContext = new Mockery();
        values3 = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {
        	{
            allowing(values3).getColumnCount(); will(returnValue(3));
            allowing(values3).getRowCount();will(returnValue(2));
            allowing(values3).getValue(0, 1); will(returnValue(2.5));
            allowing(values3).getValue(1, 1); will(returnValue(2.5));
        	}
        }); 
        
        // Creates mock object of Values2D.class with 0 rows and 0 columns
        mockingContext = new Mockery();
        values4 = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {
        	{
            allowing(values4).getColumnCount(); will(returnValue(0));
            allowing(values4).getRowCount();will(returnValue(0));
        	}
        }); 
        
        // Creates a mock object of Values2D.class with 2 rows and 1 column
        // with negative values
        mockingContext = new Mockery();
        negativeValues = mockingContext.mock(Values2D.class);
        
        mockingContext.checking(new Expectations() {
        	{
        		allowing(negativeValues).getRowCount(); will(returnValue(2));
        		allowing(negativeValues).getColumnCount(); will(returnValue(1));
        		allowing(negativeValues).getValue(0, 0); will(returnValue(-2.5));
        		allowing(negativeValues).getValue(1, 0); will(returnValue(-2.5));
        	}
        });
        
        //row data
        // Creates a mock object with 1 row and 2 columns
        mockingContext = new Mockery();
        valuesRow1 = mockingContext.mock(Values2D.class);

        mockingContext.checking(new Expectations() {
            {
                allowing(valuesRow1).getColumnCount();
                will(returnValue(2));
                allowing(valuesRow1).getRowCount(); will(returnValue(1));
                allowing(valuesRow1).getValue(0, 0);
                will(returnValue(7.5));
                allowing(valuesRow1).getValue(0, 1);
                will(returnValue(2.5));
            }
        });

        // Creates a mock object with 1 row and 3 columns
        mockingContext = new Mockery();
        valuesRow2 = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                allowing(valuesRow2).getColumnCount();
                will(returnValue(3));
                allowing(valuesRow2).getRowCount();
                will(returnValue(1));
                allowing(valuesRow2).getValue(0, 0);
                will(returnValue(3.5));
                allowing(valuesRow2).getValue(0, 1);
                will(returnValue(2.5));
                allowing(valuesRow2).getValue(0, 2);
                will(returnValue(1.5));
            }
        });

        // Creates mock object with 0 rows and 0 columns
        mockingContext = new Mockery();
        valuesRow3 = mockingContext.mock(Values2D.class);
        mockingContext.checking(new Expectations() {
            {
                allowing(valuesRow3).getColumnCount();
                will(returnValue(0));
                allowing(valuesRow3).getRowCount();
                will(returnValue(0));
            }
        });

        mockingContext = new Mockery();
        mockingContext.mock(Values2D.class);
    }
    
    // This test uses a mock object with 1 row and 2 columns and tests the output
    // of calculateColumnTotal given the highest column index for the matrix (column2).
    @Test
    public void calculateColumnTotalWithMaxColumns() {
        double result = DataUtilities.calculateColumnTotal(values2, 1);
        assertEquals(3.5, result, .000000001d);
    }
    
    
    // This test uses a mock object with 3 columns and 2 rows and tests the output
    // of calculateColumnTotal given a column index of 1, the middle column of 
    // the matrix (column2).
    @Test
    public void calculateColumnTotalWithThreeColumn() {
        double result = DataUtilities.calculateColumnTotal(values3, 1);
        assertEquals(5.0, result, .000000001d);
    }
    
    // This test uses a mock object with 2 rows and 1 column and tests output of
    // calculateColumnTotal given 2 values being added together
    @Test
    public void calculateColumnTotalForTwoValues() {
        // setup  
        double result = DataUtilities.calculateColumnTotal(values, 0);
        // verify
        assertEquals(10.0, result, .000000001d);
        // tear-down: None in this test method
    }
    
    // This test uses a mock object with 2 rows and 1 column to test output of
    // calculateColumnTotal method with two negative values
    @Test
    public void calculateColumnTotalForNegativeValues() {
    	double result = DataUtilities.calculateColumnTotal(negativeValues, 0);
    	assertEquals(-5.0, result, .000000001d);
    }
    
    
    // This test uses a mock object with 0 rows and 0 columns to test output
    // of calculateColumnTotal which should return 0 with invalid data.
    @Test
    public void calculateColumnTotalWithInvalidData() {
        // Pass null as the data object
        double result = DataUtilities.calculateColumnTotal(values4, 0);
        assertEquals(0.0, result, .000000001d);
    }
    
    //end of testing columns 
    
    //beginning of testing rows
    
	 // This test verifies the calculation of the total for a row with two columns using mock object valuesRow1.
	 // It ensures that the calculateRowTotal method correctly computes the total and matches the expected value (10.0).
	 @Test
	 public void calculateRowTotalWithTwoColumns() {
		 double result = DataUtilities.calculateRowTotal(valuesRow1, 0);
		 assertEquals(10.0, result, .000000001d);
	 }
	
	 // This test examines the calculation of the total for a row with three columns using mock object valuesRow2.
	 // It validates that the calculateRowTotal method accurately computes the total and matches the expected value (7.5).
	 @Test
	 public void calculateRowTotalWithThreeColumns() {
		 double result = DataUtilities.calculateRowTotal(valuesRow2, 0);
		 assertEquals(7.5, result, .000000001d);
	 }
	
	 // This test evaluates the behavior of calculateRowTotal when provided with invalid data using mock object valuesRow3.
	 // It ensures that the calculateRowTotal method correctly handles invalid data and returns 0.0 as the total.
	 @Test
	 public void calculateRowTotalWithInvalidData() {
		 double result = DataUtilities.calculateRowTotal(valuesRow3, 0);
		 assertEquals(0.0, result, .000000001d);
	 }

    //end of calculateRowTotal method
    
    //beginning of createNumberArray method
    
	// This test verifies the creation of a Number array from valid data.
	// It confirms that the createNumberArray method correctly converts the double array into a Number array.
	// However, this test fails due to a known issue where the last value in the array is always null.
	@Test
	public void createNumberArrayWithValidData() {
	    // Given
	    double[] validData = {1.5, 2.5, 3.5};
	    
	    // When
	    Number[] result = DataUtilities.createNumberArray(validData);
	    System.out.println(Arrays.toString(result));
	    
	    // Then
	    assertEquals(3, result.length);
	    assertNotNull(result[0]);
	    assertEquals(1.5, result[0].doubleValue(), 0.000000001d);
	    assertNotNull(result[1]);
	    assertEquals(2.5, result[1].doubleValue(), 0.000000001d);
	    assertNotNull(result[2]);
	    assertEquals(3.5, result[2].doubleValue(), 0.000000001d);
	}

    
	// This test verifies the creation of a Number array from empty data.
	// It ensures that the createNumberArray method correctly handles the case of an empty double array.
	@Test
	public void createNumberArrayWithEmptyData() {
	    // Given
	    double[] emptyData = {};
	    
	    // When
	    Number[] result = DataUtilities.createNumberArray(emptyData);
	    
	    // Then
	    assertEquals(0, result.length);
	}

	// This test validates the creation of a 2D Number array from valid data.
	// It confirms that the createNumberArray2D method properly converts the 2D double array into a 2D Number array.
	@Test
	public void createNumberArray2DWithValidData() {
	    // Given
	    double[][] validData = {{1.5, 2.5, 3.5}, {4.5, 5.5, 6.5}};
	    
	    // When
	    Number[][] result = DataUtilities.createNumberArray2D(validData);
	    System.out.println(Arrays.deepToString(result));
	    
	    // Then
	    assertEquals(2, result.length); // Check number of rows
	    assertEquals(3, result[0].length); // Check number of columns in the first row
	    assertEquals(3, result[1].length); // Check number of columns in the second row
	    
	    assertNotNull(result[0][0]);
	    assertEquals(1.5, result[0][0].doubleValue(), 0.000000001d);
	    assertNotNull(result[0][1]);
	    assertEquals(2.5, result[0][1].doubleValue(), 0.000000001d);
	    assertNotNull(result[0][2]);
	    assertEquals(3.5, result[0][2].doubleValue(), 0.000000001d);
	    
	    assertNotNull(result[1][0]);
	    assertEquals(4.5, result[1][0].doubleValue(), 0.000000001d);
	    assertNotNull(result[1][1]);
	    assertEquals(5.5, result[1][1].doubleValue(), 0.000000001d);
	    assertNotNull(result[1][2]);
	    assertEquals(6.5, result[1][2].doubleValue(), 0.000000001d);
	}

	// This test examines the calculation of cumulative percentages from valid data.
	// It ensures that the getCumulativePercentages method correctly computes cumulative percentages.
	@Test
	public void getCumulativePercentagesWithValidData() {
	    // Given
	    DefaultKeyedValues data = new DefaultKeyedValues();
	    
	    data.addValue("0", 5);
	    data.addValue("1", 9);
	    data.addValue("2", 2);
	    
	    // When
	    KeyedValues result = DataUtilities.getCumulativePercentages(data);
	    
	    // Then
	    assertEquals(3, result.getItemCount()); // Check number of items
	    
	    assertEquals(0.3125, result.getValue("0").doubleValue(), 0.000000001d);
	    assertEquals(0.875, result.getValue("1").doubleValue(), 0.000000001d);
	    assertEquals(1.0, result.getValue("2").doubleValue(), 0.000000001d);
	}

    
    @After
    public void tearDown() {
        mockingContext = null;
        values = null;
    }
    
    
    
}
