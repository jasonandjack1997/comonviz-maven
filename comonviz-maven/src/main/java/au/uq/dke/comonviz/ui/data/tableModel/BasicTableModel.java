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

package au.uq.dke.comonviz.ui.data.tableModel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.table.AbstractTableModel;

import org.metawidget.util.ClassUtils;
import org.metawidget.util.CollectionUtils;

import database.model.data.BasicRecord;
import database.model.data.businessProcessManagement.ProcessObjective;
import database.service.GenericService;
import database.service.ServiceManager;

public class BasicTableModel<T extends BasicRecord> extends
		AbstractTableModel {

	//
	// Private members
	//

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Class<T> mClass;

	public Class<T> getmClass() {
		return mClass;
	}

	private List<T> mList;

	private String[] mColumns;

	private boolean mEditable;

	private boolean mExtraBlankRow;

	//
	// Constructor
	//

	/**
	 * only set the clazz and service
	 * <P>
	 * data population is done in {@link #init(String...)}
	 * 
	 * @param clazz
	 */
	public BasicTableModel(Class<T> clazz) {
		mClass = clazz;
	}

	public void initColumns(String... columns) {
		mColumns = columns;
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

	public void add(T record) {
		mList.add(record);
		fireTableDataChanged();
	}

	public void updateRecord(T newRecord) {
		// search for the old record using id;
		for (T record : this.mList) {
			if (record.getId().equals(newRecord.getId())) {
				// update the record(assigne all the fields)
				record.update(newRecord);
			}
		}
		fireTableDataChanged();

	}

	public void delete(T record) {

		mList.remove(record);
		fireTableDataChanged();
	}

	public void importCollection(Collection<T> collection) {

		if (collection == null) {
			mList = CollectionUtils.newArrayList();
		} else {
			mList = CollectionUtils.newArrayList(collection);
			Collections.sort(mList);
		}

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

		return getmColumns().length;
	}

	@Override
	public String getColumnName(int columnIndex) {

		if (columnIndex >= getColumnCount()) {
			return null;
		}

		return getmColumns()[columnIndex];
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

		return ClassUtils.getReadMethod(mClass, column).getReturnType();
	}

	public T getValueAt(int rowIndex) {
		if(rowIndex < 0){
			return null;
		}

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

		// Inspect it

		return ClassUtils.getProperty(t, getColumnName(columnIndex));
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

	public String[] getmColumns() {
		return mColumns;
	}

	public void setmColumns(String[] mColumns) {
		this.mColumns = mColumns;
	}

}
