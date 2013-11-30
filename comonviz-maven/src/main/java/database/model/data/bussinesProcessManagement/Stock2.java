package database.model.data.bussinesProcessManagement;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import database.model.data.DataModel;

@Entity
//@Table(name = "stock", uniqueConstraints = {
//		@UniqueConstraint(columnNames = "STOCK_NAME"),
//		@UniqueConstraint(columnNames = "STOCK_CODE") })
public class Stock2  extends DataModel implements java.io.Serializable {
 
//	private Integer stockId;
	private Set<StockDailyRecord> stockDailyRecords = new HashSet<StockDailyRecord>(
			0);
 
//	@Id
//	@GeneratedValue(strategy = IDENTITY)
//	@Column(name = "STOCK_ID", unique = true, nullable = false)
//	public Integer getStockId() {
//		return this.stockId;
//	}
// 
//	public void setStockId(Integer stockId) {
//		this.stockId = stockId;
//	}
// 
	//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "stock")
	@OneToMany(fetch = FetchType.LAZY)
	public Set<StockDailyRecord> getStockDailyRecords() {
		return this.stockDailyRecords;
	}
 
	public void setStockDailyRecords(Set<StockDailyRecord> stockDailyRecords) {
		this.stockDailyRecords = stockDailyRecords;
	}
 
}