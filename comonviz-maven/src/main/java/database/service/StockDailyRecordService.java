package database.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import database.dao.StockDailyRecordDAO;
import database.model.StockDailyRecord;

@Service
@Transactional
public class StockDailyRecordService extends GenericService<StockDailyRecord, StockDailyRecordDAO>{

}
