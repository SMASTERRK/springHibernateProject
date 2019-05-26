package com.census.utils;

import java.lang.reflect.Field;

import org.springframework.stereotype.Component;

@Component("copynewPojo")
public class PojoToPojo {
	// Copying values for one POJO to another POJO using reflection concept
	@SuppressWarnings("rawtypes")
	public static void copyObject(Object source, Object target) {
		Class s = source.getClass();
		Class t = target.getClass();

		Field[] sourceField = s.getDeclaredFields();
		Field[] targetField = t.getDeclaredFields();
		Object value = null;
		for (Field field : sourceField) {
			field.setAccessible(true);
			try {
				value = field.get(source);
			} catch (Exception e) {
				e.printStackTrace();
			}
			for (Field field1 : targetField) {
				if (field1.getName().equals(field.getName())) {
					field1.setAccessible(true);
					try {
						field1.set(target, value);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
