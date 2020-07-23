package com.spring.baseSetting.service.impl;

import com.spring.baseSetting.dao.Com_replyDao;
import com.spring.baseSetting.dto.Com_reply;
import com.spring.baseSetting.service.ComReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("comReplyService")
public class ComReplyServiceImpl implements ComReplyService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	private final Com_replyDao com_replyDao;

	public ComReplyServiceImpl(Com_replyDao com_replyDao) {
		logger.info("ComReplyServiceImpl Init...");
		this.com_replyDao = com_replyDao;
	}

	// ��� ��ȸ
	@Override
	public List<Com_reply> ListreadReply2(int com_no) {
		List<Com_reply> reply2 = com_replyDao.ListreadReply(com_no);
		logger.info("����----" + reply2.toString());
		
		return reply2;
	}

	// ��� �ۼ�
	@Override
	public void writeReply(Com_reply comReply) {
		 com_replyDao.writeReply(comReply);
	}
	
	// ��� ����
	@Override
	public int updateReply(Com_reply comReply) {
		int cnt = com_replyDao.updateReply(comReply);
		return cnt;
	}
	
	// ��� ����
	@Override
	public int deleteReply(Com_reply comReply) {
		int cnt = com_replyDao.deleteReply(comReply);
		return cnt;
	}
	
	// ������ ��� ��ȸ
	@Override
	public Com_reply selectReply(int rno) {
		return com_replyDao.selectReply(rno);
	}

	
}
