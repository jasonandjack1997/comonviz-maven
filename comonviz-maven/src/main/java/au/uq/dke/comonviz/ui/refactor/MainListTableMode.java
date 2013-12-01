// Metawidget Examples (licensed under BSD License)
//
// Copyright (c) Richard Kennard
// All rights reserved
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions
// are met:
//
// * Redistributions of source code must retain the above copyright notice,
//   this list of conditions and the following disclaimer.
// * Redistributions in binary form must reproduce the above copyright notice,
//   this list of conditions and the following disclaimer in the documentation
//   and/or other materials provided with the distribution.
// * Neither the name of Richard Kennard nor the names of its contributors may
//   be used to endorse or promote products derived from this software without
//   specific prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
// ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
// FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
// (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS
// OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
// OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE
// OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED
// OF THE POSSIBILITY OF SUCH DAMAGE.

package au.uq.dke.comonviz.ui.refactor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import org.metawidget.util.ClassUtils;
import org.metawidget.util.CollectionUtils;

import au.uq.dke.comonviz.utils.ReflectionUtil;

/**
 * TableModel for Lists of Objects.
 * <p>
 * As well as wrapping Lists of Objects, <code>ListTableModel</code> supports
 * dynamically adding a blank row to the model to accomodate entering new
 * Objects.
 * 
 * @author Richard Kennard
 */

public class MainListTableMode<T> extends AbstractTableModel {

	//
	// Private members
	//

	private Class<T> mClass;

	private List<Class<?>> mFKClassList;

	private List<T> mList;

	private String[] mColumns;

	private boolean mEditable;

	private boolean mExtraBlankRow;

	//
	// Constructor
	//

	public MainListTableMode(Class<T> clazz, Collection<T> collection) {

		mClass = clazz;
		mFKClassList = new ArrayList();

		List<String> columnList = new ArrayList<String>();
		columnList.add("name");
		columnList.add("discription");

		List setFieldList = ReflectionUtil.getSetCollectionFieldList(clazz);
		for (int i = 0; i < setFieldList.size(); i++) {
			columnList.add("FK " + i);
		}

		mColumns = new String[columnList.size()];

		for (int i = 0; i < columnList.size(); i++) {
			mColumns[i] = columnList.get(i).toString();
		}


		importCollection(collection);
	}

	//
	// Public methods
	//

	public int findRowNumber(T t) {
		for (int i = 0; i < mList.size(); i++) {
			if (mList.get(i).equals(t)) {
				return i;
			}
		}

		return -1;
	}

	public void importCollection(Collection<T> collection) {

		if (collection == null) {
		} else {
			mList = CollectionUtils.newArrayList();
			mList = CollectionUtils.newArrayList(collection);
			// Collections.sort(mList );
		}

		fireTableDataChanged();
	}

	public void addRecord(T record) {
		mList.add(record);

		fireTableDataChanged();
	}

	public List<T> exportList() {

		return CollectionUtils.newArrayList(mList);
	}

	public void setEditable(boolean editable) {

		mEditable = editable;
	}

	public void setExtraBlankRow(boolean extraBlankRow) {

		mExtraBlankRow = extraBlankRow;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {

		return mEditable;
	}

	public int getColumnCount() {

		// (mColumns can never be null)

		return mColumns.length;
	}

	@Override
	public String getColumnName(int columnIndex) {

		if (columnIndex >= getColumnCount()) {
			return null;
		}

		return mColumns[columnIndex];
	}

	public int getRowCount() {

		// (mList can never be null)

		int rows = mList.size();

		if (mExtraBlankRow) {
			rows++;
		}

		return rows;
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		String column = getColumnName(columnIndex);

		if (column == null) {
			return null;
		}

		if (columnIndex < 2) {// 0 is name; 1 is discription
			return ClassUtils.getReadMethod(mClass, column).getReturnType();

		} else {
			// these columns are fk names
			// remember t is a real object, not reflection types.
			// so I can get set list from it, and get the only one element in
			// each of it.
			// then just return the name of the element. so easy

			List<Field> setFieldList = ReflectionUtil
					.getSetCollectionFieldList(mClass);
			Field field = setFieldList.get(columnIndex - 2);
			Class elementType = ReflectionUtil
					.getSetCollectionElementType(field);

			// acturally, just return string
			return String.class;

		}

	}

	public T getValueAt(int rowIndex) {

		// Sanity check

		if (rowIndex >= mList.size()) {
			return null;
		}

		return mList.get(rowIndex);
	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		// Sanity check

		if (columnIndex >= getColumnCount()) {
			return null;
		}

		// Fetch the object

		T t = getValueAt(rowIndex);

		if (t == null) {
			return null;
		}

		if (columnIndex < 2) {// 0 is name; 1 is discription

			// Inspect it
			return ClassUtils.getProperty(t, getColumnName(columnIndex));

		} else {
			// these columns are fk names
			// remember t is a real object, not reflection types.
			// so I can get set list from it, and get the only one element in
			// each of it.
			// then just return the name of the element. so easy

			List<Set<?>> setObjectList = ReflectionUtil.getSetObjectList(t);
			Set<?> set = setObjectList.get(columnIndex - 2);
			if(set == null || set.size() == 0){
				return null;
			}
			return set.toArray()[0];

		}

	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {

		// Sanity check

		if (columnIndex >= getColumnCount()) {
			return;
		}

		// Just-in-time creation

		if (rowIndex == (getRowCount() - 1) && mExtraBlankRow) {
			if (value == null || "".equals(value)) {
				return;
			}

			try {
				mList.add(mClass.newInstance());
				fireTableRowsInserted(rowIndex, rowIndex);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}

		// Fetch the object

		T t = getValueAt(rowIndex);

		if (t == null) {
			return;
		}

		// Update it

		ClassUtils.setProperty(t, getColumnName(columnIndex), value);
	}
}
