package au.uq.dke.comonviz.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReflectionUtil {
	
	Set<String> testSet = new HashSet<String>();

	public Set<String> getTestSet() {
		return testSet;
	}

	public void setTestSet(Set<String> testSet) {
		this.testSet = testSet;
	}

	public static Class getSetCollectionElementType(Field setField) {
		Field field = setField;

		Type genericFieldType = field.getGenericType();

		if (genericFieldType instanceof ParameterizedType) {
			ParameterizedType aType = (ParameterizedType) genericFieldType;
			Type[] fieldArgTypes = aType.getActualTypeArguments();
			for (Type fieldArgType : fieldArgTypes) {
				Class fieldArgClass = (Class) fieldArgType;
				System.out.println("fieldArgClass = " + fieldArgClass);
				return fieldArgClass;
			}
		}
		return null;

	}
	
	public static List<Field> getSetCollectionFieldList(Class clazz){
		
		List<Field> setFieldList = new ArrayList<Field>();
		for(Field field : clazz.getDeclaredFields()){
			if(Set.class.isAssignableFrom(field.getType())){
				setFieldList.add(field);
			}
		}
		return setFieldList;
		
	}

	/**
	 * get the sets of this record object we can do anything once we have the
	 * sets
	 * 
	 * @param mainObject
	 * @return
	 */
	public static List<Set<?>> getSetObjectList(Object mainObject) {

		List<Set<?>> setList = new ArrayList<Set<?>>();

		Class<?> clazz = mainObject.getClass();

		for (Method method : clazz.getDeclaredMethods()) {
			if (method.getReturnType() == Set.class) {
				Set<?> set = null;
				try {
					set = (Set<?>) method.invoke(mainObject, new Object[0]);
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setList.add(set);
			}
		}
		return setList;

	}
	
	public static void main(String args[]){
		ReflectionUtil ru = new ReflectionUtil();
		ru.getTestSet().add("hehe");
		
		List<Set<?>> setList = ReflectionUtil.getSetObjectList(ru);
		
		
		List<Field> setFieldList = getSetCollectionFieldList(ru.getClass());
		return;
		
	}

}
