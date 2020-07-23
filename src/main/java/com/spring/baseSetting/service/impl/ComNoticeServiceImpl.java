package com.spring.baseSetting.service.impl;

import com.spring.baseSetting.dao.Com_noticeDao;
import com.spring.baseSetting.dto.Com_notice;
import com.spring.baseSetting.dto.SearchCriteria;
import com.spring.baseSetting.service.ComNoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service("comNoticeService")
public class ComNoticeServiceImpl implements ComNoticeService {

	@Override
  public List<Com_notice> getmyQue(SearchCriteria scri) {
    List<Com_notice> list = com_noticeDao.selectmyQue(scri);
    
    return list;
  }

  private final Com_noticeDao com_noticeDao;

	public ComNoticeServiceImpl(Com_noticeDao com_noticeDao) {
		log.info("ComNoticeServiceImpl Init...");
		this.com_noticeDao = com_noticeDao;
	}

	// 1.5 list ��ü �Խù� ��ȸ ����Ʈ ������
	@Override
	public List<Com_notice> comAllList(SearchCriteria scri) {
		List<Com_notice> list = com_noticeDao.comNoticeList(scri);
		
		return list;
	}
	
	
	// �Խù� �� ����
	@Override
	public int listCount(SearchCriteria scri) {
		return com_noticeDao.listCount(scri);
	}
	

	//1.7 �Խù� ��ȸ
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public int upHit(int com_no) {
		int hit = com_noticeDao.upVisitnum(com_no);
		return hit;
	}

	// 3 �� �Խù��� ��ȸ�ϱ� selectOneComNotice
	@Override 
	public Com_notice getOneComNotice(int com_no) {
		Com_notice detail = com_noticeDao.selectOneComNotice(com_no);
		return detail; 
	}

	// 4putComNotice
	@Override
	public int putComNotice(Com_notice comNotice) {
		int result = com_noticeDao.insertComNotice(comNotice);
		System.out.println(result);
		return result;
	}

	// 5deleteCom_notice888888888888888
	@Override
	public int cutComNotice(int com_no) {
		int deleteCom = com_noticeDao.deleteComNotice(com_no);
		return deleteCom;
	}

	// 6updateCom_notice
	@Override
	public int goComNotice(Com_notice comNotice) {
		int updateCom = com_noticeDao.updateComNotice(comNotice);
		return updateCom;
	}

	@Override
	public List<Com_notice> getPopular() {
		List<Com_notice> list = com_noticeDao.selectPopular();
		
		return list;
	}

	@Override
	public List<Com_notice> getNew() {
		List<Com_notice> list = com_noticeDao.selectNew();
		
		return list;
	}

	@Override
	public List<Com_notice> getMovtalk(SearchCriteria scri) {
		List<Com_notice> list = com_noticeDao.selectMovtalk(scri);
		
		return list;
	}

	@Override
	public int getmovtalkCount(SearchCriteria scri) {
		return com_noticeDao.movtalkCount(scri);
	}

	@Override
	public List<Com_notice> getQue(SearchCriteria scri) {
		List<Com_notice> list = com_noticeDao.selectQue(scri);
		
		return list;
	}

	@Override
	public int getqueCount(SearchCriteria scri) {
		return com_noticeDao.queCount(scri);
	}
}
