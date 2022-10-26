package com.qa.banking;

import com.qa.utils.DBUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransactionCategoryTest {
	private final TransactionCategory TCAT = new TransactionCategory();
	
	@BeforeEach
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/schema.sql", "src/test/resources/data.sql");
	}
	
	@Test
	public void testTotalForLeisureCategoryIs10Pound50() {
		double expectedCost = TCAT.getTotalCostForCategory("Leisure");
		assertEquals(10.50, expectedCost);
	}
	
	@Test
	public void testTotalForGroceriesCategoryIs20Pound80() {
		double expectedCost = TCAT.getTotalCostForCategory("Groceries");
		assertEquals(20.80, expectedCost);
	}
	
	@Test
	public void testTotalForAllCategories() {
		String result = TCAT.getTotalCostPerCategory();
		String expected = "Leisure Total Spend: 10.50\nGroceries Total Spend: 20.80";
		assertEquals(result, expected);
	}
}