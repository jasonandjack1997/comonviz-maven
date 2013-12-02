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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.metawidget.util.ClassUtils;
import org.metawidget.util.CollectionUtils;

import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.data.BasicRecord;
import database.service.GenericService;
import database.service.ServiceManager;

/**
 * TableModel for Lists of Objects.
 * <p>
 * As well as wrapping Lists of Objects, <code>ListTableModel</code> supports
 * dynamically adding a blank row to the model to accomodate entering new
 * Objects.
 * 
 * @author Richard Kennard
 */

public class PrimeryRecordsTableModel<T> extends ServiceTableModel {


	private List<Class<?>> mFKClassList;

	public PrimeryRecordsTableModel(Class<T> clazz) {
		super(clazz);

		List<String> columnList = new ArrayList<String>();
		columnList.add("name");
		columnList.add("discription");

		// before cast to array we should fetch the set members!

		List<BasicRecord> results = this.getService().findAll();
		
		List<BasicRecord> resultsWithSetsObjects = new ArrayList<BasicRecord>();
		for(BasicRecord r : results){
			BasicRecord record = r;
			r = (BasicRecord) this.getService().findByName(record.getName(), clazz);
			resultsWithSetsObjects.add(r);
		}

		
		List setFieldList = ReflectionUtils.getSetFieldList(clazz);
		for (int i = 0; i < setFieldList.size(); i++) {
			columnList.add("FK " + i);
		}

		String[] columns = new String[columnList.size()];

		for (int i = 0; i < columnList.size(); i++) {
			columns[i] = columnList.get(i).toString();
		}
		super.init(columns);
		
		super.importCollection(this.getService().findAll());
		
		return;
	}



	@Override
	public String getColumnName(int columnIndex) {

		if (columnIndex >= getColumnCount()) {
			return null;
		}

		return getmColumns()[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {

		String column = getColumnName(columnIndex);

		if (column == null) {
			return null;
		}

		if (columnIndex < 2) {// 0 is name; 1 is discription
			return ClassUtils.getReadMethod(getmClass(), column).getReturnType();

		} else {
			// these columns are fk names
			// remember t is a real object, not reflection types.
			// so I can get set list from it, and get the only one element in
			// each of it.
			// then just return the name of the element. so easy

			List<Field> setFieldList = ReflectionUtils
					.getSetFieldList(getmClass());
			Field field = setFieldList.get(columnIndex - 2);
			Class elementType = ReflectionUtils
					.getSetElementType(field);

			// acturally, just return string
			return String.class;

		}

	}

	public Object getValueAt(int rowIndex, int columnIndex) {

		// Sanity check

		if (columnIndex >= getColumnCount()) {
			return null;
		}

		// Fetch the object

		BasicRecord r = getValueAt(rowIndex);

		if (r == null) {
			return null;
		}

		if (columnIndex < 2) {// 0 is name; 1 is discription

			// Inspect it
			return ClassUtils.getProperty(r, getColumnName(columnIndex));

		} else {
			// these columns are fk names
			// remember t is a real object, not reflection types.
			// so I can get set list from it, and get the only one element in
			// each of it.
			// then just return the name of the element. so easy

			List<Set<?>> setObjectList = ReflectionUtils.getSetObjectList(r);
			Set<?> set = setObjectList.get(columnIndex - 2);
			if(set == null || set.size() == 0){
				return null;
			}
			return set.toArray()[0];

		}

	}
}
