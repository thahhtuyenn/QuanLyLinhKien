package utils;

import java.text.DecimalFormat;
import java.text.ParseException;

public class PriceFormatter {
	public static String format(double price) {
		return new DecimalFormat("#,###Đ").format(price);
	}
	public static double parse(String price) {
		DecimalFormat decimalFormat = new DecimalFormat("#,###Đ");
		try {
			long pri = (long) decimalFormat.parseObject(price);
			return pri*1.0;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static void main(String[] args) {
		System.out.println(PriceFormatter.parse("100,000Đ"));
	}
}
