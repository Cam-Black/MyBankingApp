package com.qa.banking;

import com.qa.utils.DBUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionCategoryTest {
	private final TransactionCategory TCAT = new TransactionCategory();
	
	@BeforeEach
	public void setup() {
		DBUtils.connect();
		DBUtils.getInstance().init("src/test/resources/sql-schema.sql", "src/test/resources/sql-data.sql");
	}
	
	@Test
	public void testTotalForLeisureCategoryIs10Pound50() {
		BigDecimal expectedCost = TCAT.getTotalCostForCategory("Leisure");
		assertEquals(BigDecimal.valueOf(10.50), expectedCost);
	}
	
	@Test
	public void testTotalForGroceriesCategoryIs20Pound80() {
		BigDecimal expectedCost = TCAT.getTotalCostForCategory("Groceries");
		assertEquals(BigDecimal.valueOf(20.80), expectedCost);
	}
	
	@Test
	public void testTotalForAllCategories() {
		String result = TCAT.getTotalCostPerCategory();
		String expected = """
				Leisure Total Spend: 10.50
				Groceries Total Spend: 20.80
				Direct Debit Total Spend: 512.43
				""";
		assertEquals(result, expected);
	}
	
	@Test
	public void testAllTransactionsForLeisure() {
		List<Transaction> transactions = TCAT.getAllTransactionsByCategory("Leisure");
		List<Transaction> expected = new ArrayList<>();
		expected.add(new Transaction(LocalDate.of(2022, 3, 15), "WHSmiths", new BigDecimal("10.50"), "Leisure"));
		assertEquals(expected.toString(), transactions.toString());
	}
	
	@Test
	public void testAllTransactionsForGroceries() {
		List<Transaction> transactions = TCAT.getAllTransactionsByCategory("Groceries");
		List<Transaction> expected = new ArrayList<>();
		expected.add(new Transaction(LocalDate.of(2022, 8, 21), "Tesco", BigDecimal.valueOf(10.30), "Groceries"));
		expected.add(new Transaction(LocalDate.of(2022, 5, 16), "Co-Op", BigDecimal.valueOf(10.50), "Groceries"));
		assertEquals(expected.toString(), transactions.toString());
	}
	
	@Test
	public void testAvgInAMonthForDirectDebit() {
		BigDecimal directDebitAvg = TCAT.getAvgSpendInAMonth("Direct Debit");
		assertEquals(new BigDecimal("150.00", new MathContext(2)), directDebitAvg);
	}
	
	@Test
	public void testMinExpenseFor2022InGroceries() {
		BigDecimal minSpendFor2022InGroceries = TCAT.getMinSpend("Groceries", "2022");
		assertEquals(BigDecimal.valueOf(10.30), minSpendFor2022InGroceries);
	}
	
	@Test
	public void testMinExpenseFor2022InLeisure() {
		BigDecimal minSpendFor2022InLeisure = TCAT.getMinSpend("Leisure", "2022");
		assertEquals(BigDecimal.valueOf(10.50), minSpendFor2022InLeisure);
	}
	
	@Test
	public void testMaxExpenseFor2022InGroceries() {
		BigDecimal minSpendFor2022InGroceries = TCAT.getMaxSpend("Groceries", "2022");
		assertEquals(BigDecimal.valueOf(10.50), minSpendFor2022InGroceries);
	}
	
	@Test
	public void testMaxExpenseFor2022InDirectDebit() {
		BigDecimal maxSpendFor2022InDirectDebit = TCAT.getMaxSpend("Direct Debit", "2022");
		assertEquals(BigDecimal.valueOf(212.43), maxSpendFor2022InDirectDebit);
	}
}