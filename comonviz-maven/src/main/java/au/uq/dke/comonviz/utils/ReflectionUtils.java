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

import org.metawidget.util.ClassUtils;

import au.uq.dke.comonviz.misc.CustomRuntimeException;
import database.model.data.BasicRecord;

public class ReflectionUtils{
	
	Set<String> testSet = new HashSet<String>();

	public Set<String> getTestSet() {
		return testSet;
	}

	public void setTestSet(Set<String> testSet) {
		this.testSet = testSet;
	}
	
	

	public static Class getSetElementType(Field setField) {
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
	
	
	
	/**get a specifi set of whith the element type is @elementType
	 * @param mainRecord
	 * @param elementType
	 * @return
	 */
	public static Set<BasicRecord> getSpecificSetByElementType(BasicRecord mainRecord, Class<BasicRecord> elementType){
		//get all set fields
		//find element type, get the set field, then get the set it self!
		List<Field> setFields = getSetFieldList(mainRecord.getClass());
		for(Field setField : setFields){
			Class type = getSetElementType(setField);
			if(type == elementType){
				String property = setField.getName();
				Set<BasicRecord> set = ClassUtils.getProperty(mainRecord, property);
				if(set == null){
					ClassUtils.setProperty(mainRecord, property, new HashSet<BasicRecord>());
				}
				
				return set;
			}
		}
		
		throw new CustomRuntimeException("error");
	}
	
	/**
	 * get an associated record, we need it to assign values 
	 * @param mainRecord
	 * @param associatedRecordType
	 * @return
	 */
	@Deprecated
	public static BasicRecord getSpecificAssociatedRecord(BasicRecord mainRecord, Class<BasicRecord> associatedRecordType){
		//first get all BasicRecord field
		//then match type, then get the object
		Field[] fields = mainRecord.getClass().getDeclaredFields();
		for(Field field : fields){
			if(field.getType() == associatedRecordType){
				try {
					return (BasicRecord) field.get(mainRecord);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	public static String getFieldNameByType(Class<BasicRecord> recordClass, Class<?> associatedRecordType){
		//first get all BasicRecord field
		//then match type, then get the object
		//only get the first field name, so a BasicRecord should only have one field of the type
		Field[] fields = recordClass.getDeclaredFields();
		for(Field field : fields){
			if(field.getType() == associatedRecordType){
				return field.getName();
			}
		}
		return null;
	}
	
	public static Method getSetMethod(Class<BasicRecord> recordClass, String fieldName, Class<?>... parameterTypes){
		String setMethodName = "set" + fieldName.substring(0,1).toUpperCase() + fieldName.substring(1, fieldName.length());
		Method setMethod = null;
		try {
			setMethod = recordClass.getDeclaredMethod(setMethodName, parameterTypes);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return setMethod;
	}
	
	
	
	public static void setFieldValue(BasicRecord mainRecord,  Class<?> fieldType, BasicRecord fieldValue){
		//get spefic field name
		String fieldName = getFieldNameByType((Class<BasicRecord>)mainRecord.getClass(), fieldType);
		
		//get the set method
		Method setMethod = getSetMethod((Class<BasicRecord>)mainRecord.getClass(), fieldName, fieldType);
		//set value
		try {
			setMethod.invoke(mainRecord, fieldValue);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return;
		
	}

	public static BasicRecord getSpecificAssociatedRecord2(BasicRecord mainRecord, Class<BasicRecord> associatedRecordType){
		//first get all BasicRecord set Method
		//then match parameter type, then get the object
		Field[] fields = mainRecord.getClass().getDeclaredFields();
		for(Field field : fields){
			if(field.getType() == associatedRecordType){
				try {
					return (BasicRecord) field.get(mainRecord);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	public static List<Field> getSetFieldList(Class clazz){
		
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
		
		
		testField();
		
		ReflectionUtils ru = new ReflectionUtils();
		ru.getTestSet().add("hehe");
		
		List<Set<?>> setList = ReflectionUtils.getSetObjectList(ru);
		
		
		List<Field> setFieldList = getSetFieldList(ru.getClass());
		return;
		
	}
	
	public static void testField(){
		ReflectionUtils r = new ReflectionUtils();
		Field field = null;
		try {
			field = ReflectionUtils.class.getDeclaredField("testSet");
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object object = null;
		try {
			object = field.get(r);
			((Set)object).add("hehe");
		} catch (IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return;
	}

}
