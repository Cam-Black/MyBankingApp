package com.qa.banking;

import com.qa.utils.DBUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionCategory {
	
	public TransactionCategory() {
		super();
	}
	
	public double getTotalCostForCategory(String category) {
		try (Connection conn = DBUtils.getInstance().getConnection();
		     PreparedStatement ps = conn.prepareStatement("SELECT SUM(amount) FROM transactions where category = ?")) {
			ps.setString(1, category);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				return rs.getDouble(1);
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}
		return 0.0;
	}
	
	public String getTotalCostPerCategory() {
		try (Connection conn = DBUtils.getInstance().getConnection();
		Statement stmnt = conn.createStatement()) {
			List<String> categories = new ArrayList<>();
			ResultSet rs = stmnt.executeQuery("SELECT category FROM transactions");
			StringBuilder sb = new StringBuilder();
			while (rs.next()) {
				categories.add(rs.getString(1));
			}
			
			categories.stream().distinct().forEach(el -> {
				double cost = getTotalCostForCategory(el);
				sb.append(el);
				sb.append(" Total Spend: ");
				sb.append(String.format("%.2f", cost));
				sb.append("\n");
			});
			return sb.toString();
		} catch (Exception e) {
			System.err.println(e);
		}
		return "";
	}
}
