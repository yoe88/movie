package com.spring.baseSetting.service.impl;

import com.spring.baseSetting.dao.MytheaDao;
import com.spring.baseSetting.dto.Mythea;
import com.spring.baseSetting.service.MytheaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("mytheaService")
public class MytheaServiceImpl implements MytheaService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MytheaDao mytheaDao;

    public MytheaServiceImpl(MytheaDao mytheaDao) {
        logger.info("MytheaServiceImpl Init...");
        this.mytheaDao = mytheaDao;
    }

    
    
    
	@Override
	public List<Mythea> getMythea(String mem_id) {
		List<Mythea> mythea = mytheaDao.selectMythea(mem_id);
		
		return mythea;
	}

	@Override
	public int addMythea(Mythea mythea) {
		int result = mytheaDao.insertMythea(mythea);
		
		return result;
	}

	@Override
	public int removeMythea(Mythea mythea) {
		int result = mytheaDao.deleteMythea(mythea);
		
		return result;
	}




	@Override
	public List<Mythea> getAllthea() {
		List<Mythea> list = mytheaDao.selectAllthea();
		
		return list;
	}


	


	@Override
	public List<Mythea> getnotMythea(String mem_id) {
		List<Mythea> list = mytheaDao.selectnotMythea(mem_id);
		
		return list;
	}




	
}//MytheaServiceImpl class end
