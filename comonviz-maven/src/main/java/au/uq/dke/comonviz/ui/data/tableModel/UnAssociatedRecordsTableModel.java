package au.uq.dke.comonviz.ui.data.tableModel;

import database.model.data.BasicRecord;

public class UnAssociatedRecordsTableModel extends BasicTableModel{

	/**
	 * this model is to display the unAssociated fk records
	 * <p> i.e. those refered records are not connected with the main record set
	 * @param mainRecord
	 */
	public UnAssociatedRecordsTableModel(Class clazz) {
		super(clazz);
		// TODO Auto-generated constructor stub
	}

}
