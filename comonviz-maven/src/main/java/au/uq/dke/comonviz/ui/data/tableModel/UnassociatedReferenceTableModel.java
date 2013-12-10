package au.uq.dke.comonviz.ui.data.tableModel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import au.uq.dke.comonviz.utils.DatabaseUtils;
import au.uq.dke.comonviz.utils.ReflectionUtils;
import database.model.data.BasicRecord;

public class UnassociatedReferenceTableModel extends FullFieldsRecordsTableModel{
	private Class masterRecordType;

	public UnassociatedReferenceTableModel(Class masterRecordType, Class associatedReferenceType) {
		super(associatedReferenceType);
		this.masterRecordType = masterRecordType;
		List<String> columnList = new ArrayList<String>();
		columnList.add("name");
		
		List<Field> associatedRecordSetFieldList = ReflectionUtils.getSetFieldList(associatedReferenceType);
		for (int i = 0; i < associatedRecordSetFieldList.size(); i++) {
			columnList.add(associatedRecordSetFieldList.get(i).getName());
		}

		String[] columns = new String[columnList.size()];

		for (int i = 0; i < columnList.size(); i++) {
			columns[i] = columnList.get(i).toString();
		}
		
		//find unassociated records
		
		super.initColumns(columns);
		updateList();
		
		
		return;
	}
	
	public void updateList(){
		
		List<BasicRecord> masterRecords = DatabaseUtils.findAll(masterRecordType);
		List<BasicRecord> referedRecords = DatabaseUtils.findAll(this.getmClass());
		List<BasicRecord> associatedRecords = new ArrayList<BasicRecord>();

		for(BasicRecord masterRecord : masterRecords){
			//get every record
			//get sets of every record
			//get every element of the set, add them to a set
			//remove the idential element from the asociatedRecords list
			List masterSetObjects = ReflectionUtils.getSetObjectList(masterRecord);
			for( Object object : masterSetObjects){
				Set associatedRecordSet = (Set) object;
				if(associatedRecordSet == null){
					continue;
				}
//				for(Object associatedRecord : associatedRecordSet){
//					associatedRecords.add((BasicRecord)associatedRecord);
//				}
				
			}
		}
		
		//remove records
		for(BasicRecord associatedRecord : associatedRecords){
			Iterator it = referedRecords.iterator();
			while(it.hasNext()){
				BasicRecord referedRecord = (BasicRecord) it.next();
				if(associatedRecord.equals(referedRecord)){
					it.remove();
				}
			
			}
		}

		super.importCollection(referedRecords);
	}

}
