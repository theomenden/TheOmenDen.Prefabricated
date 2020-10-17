package com.wuest.prefab;

import net.minecraft.text.LiteralText;
import org.apache.commons.lang3.text.WordUtils;

import java.util.ArrayList;

public class Utils {
	public static String[] WrapString(String value) {
		return Utils.WrapString(value, 50);
	}

	public static String[] WrapString(String value, int width) {
		String result = WordUtils.wrap(value, width);
		String[] results = result.split("\n");

		String[] returnValue = new String[results.length];

		for (int i = 0; i < results.length; i++) {
			returnValue[i] = results[i].trim();
		}

		return returnValue;
	}

	public static ArrayList<LiteralText> WrapStringToLiterals(String value) {
		return Utils.WrapStringToLiterals(value,50);
	}

	public static ArrayList<LiteralText> WrapStringToLiterals(String value, int width) {
		String[] values = Utils.WrapString(value, width);
		ArrayList<LiteralText> returnValue = new ArrayList<>();

		for (String stringValue : values) {
			returnValue.add(new LiteralText(stringValue));
		}

		return returnValue;
	}
}
