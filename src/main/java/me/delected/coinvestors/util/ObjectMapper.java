package me.delected.coinvestors.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ObjectMapper {


	/**
	 * The result of any decode request. Contains either a AbstractObject
	 * or a Object[] representing the decoded JSON.
	 * The corresponding getter returns a Optional containing the result, the other one returns an empty Optional
	 */
	public static class JsonObject {
		private final AbstractObject object;
		private final Object[] array;

		/**
		 * @return an Optional containing the AbstractObject of the decoded JSON if it was one
		 */
		public Optional<AbstractObject> getObject() {
			return Optional.ofNullable(object);
		}

		/**
		 * @return an Optional containing an Array of the decoded JSON if it was one
		 */
		public Optional<Object[]> getArray() {
			return Optional.ofNullable(array);
		}

		/***
		 * @param object The Object representation or null if is an array
		 * @param array The Array representation or null if it is an Object
		 */
		public JsonObject(final AbstractObject object, final Object[] array) {
			this.object = object;
			this.array = array;
		}

		@Override
		public String toString() {
			return "JsonObject{" +
				   "object=" + object +
				   ", array=" + Arrays.toString(array) +
				   '}';
		}
	}


	/**
	 * Represents an Object of any class.
	 * Its fields are separated into objectFields and primitiveFields
	 * Both getters for them returns a map for making the fields accessible by their names
	 */
	public static class AbstractObject {
		private final Map<String, AbstractObject> objectFields = new HashMap<>();
		private final Map<String, Object> primitiveFields = new HashMap<>();

		public Map<String, AbstractObject> getObjects() {
			return Collections.unmodifiableMap(objectFields);
		}

		public Map<String, Object> getPrimitives() {
			return Collections.unmodifiableMap(primitiveFields);
		}
	}

	public static JsonObject decode(String input) {
		input = input.trim();
		if (input.charAt(0) == '{')
			return new JsonObject(parseObject(input), null);
		else if (input.charAt(0) == '[')
			return new JsonObject(null, parseArray(input));
		throw new IllegalArgumentException("The passed String " + input + " is no valid JSON-String");
	}

	private static AbstractObject parseObject(String input) {
		Map<String, String> buffer = new HashMap<>();
		AbstractObject result = new AbstractObject();
		input = input.substring(1, input.length() - 1);
		int start = 0;
		while (start < input.length()) {
			int stringStart = getNextIndexOfStringSymbol(input, start);
			int stringEnd = getNextIndexOfStringSymbol(input, stringStart + 1);
			String key = input.substring(stringStart + 1, stringEnd);
			int doublePoint = skipUntilDoublePoint(input, stringEnd);
			doublePoint++;
			int endOfValue = getEndOfSequence(input, doublePoint);
			String value = input.substring(doublePoint, endOfValue);
			buffer.put(key, value.trim());
			start = endOfValue + 1;
		}

		buffer.forEach((k, v) -> {
			if (v.matches("\".*\""))
				v = v.substring(1, v.length() - 1);
			if (v.matches("(\\d*\\.?\\d*)")) {
				result.primitiveFields.put(k, parseNumber(v));
			} else if (v.startsWith("{")) {
				result.objectFields.put(k, parseObject(v));
			} else if (v.startsWith("[")) {
				result.primitiveFields.put(k, parseArray(v));
			} else if (v.startsWith("\""))
				result.primitiveFields.put(k, v.substring(1, v.length() - 1));
			else
				result.primitiveFields.put(k, v);
		});

		return result;
	}

	private static Number parseNumber(String s) {
		if (s.matches("\\d*")) {
			long l = Long.parseLong(s);
			if (l < Integer.MAX_VALUE && l > Integer.MIN_VALUE) {
				return (int) l;
			}
			return l;
		}
		return Double.parseDouble(s);
	}

	private static Object[] parseArray(String input) {
		input = input.substring(1, input.length() - 1);
		List<String> buffer = new LinkedList<>();
		List<Object> objects = new LinkedList<>();
		int branchCount = 0;
		int lastElemStart;
		for (int i = 0; i < input.length(); i++) {
			lastElemStart = i;
			for (char c = input.charAt(i); (c != ',' || branchCount > 0); c = input.charAt(i)) {
				switch (c) {
					case '[':
					case '{':
						branchCount++;
						break;
					case ']':
					case '}':
						branchCount--;
						break;
				}
				i++;
				if (i == input.length())
					break;
			}
			buffer.add(input.substring(lastElemStart, i).trim());
		}
		buffer.forEach(s -> {
			if (s.matches("\".*\""))
				s = s.substring(1, s.length() - 1);
			if (s.matches("(\\d*\\.?\\d*)")) {
				objects.add(parseNumber(s));
			} else if (s.startsWith("{")) {
				objects.add(parseObject(s));
			} else if (s.startsWith("[")) {
				objects.add(parseArray(s));
			} else objects.add(s);
		});
		return objects.toArray(new Object[0]);
	}

	private static int getEndOfSequence(String toIterate, int start) {
		int escapeCounter = 0;
		for (; start < toIterate.length(); start++) {
			switch (toIterate.charAt(start)) {
				case '\\':
					start++;
					break;
				case '[':
				case '{':
					escapeCounter++;
					break;
				case ']':
				case '}':
					escapeCounter--;
					break;
				case ',':
					if (escapeCounter == 0)
						return start;
					break;
			}
		}
		return toIterate.length();
	}

	private static int skipUntilDoublePoint(String toIterate, int start) {
		for (; start < toIterate.length(); start++) {
			switch (toIterate.charAt(start)) {
				case '\\':
					start++;
					break;
				case ':':
					return start;
			}
		}
		return toIterate.length();
	}

	private static int getNextIndexOfStringSymbol(String toCheck, int start) {

		for (; start < toCheck.length(); start++) {
			char actual = toCheck.charAt(start);
			switch (actual) {
				case '\\':
					start++;
					break;
				case '"':
					return start;
			}
		}
		throw new IllegalArgumentException();
	}

}




