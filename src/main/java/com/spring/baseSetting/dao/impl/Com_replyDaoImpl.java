package com.spring.baseSetting.dao.impl;

import com.spring.baseSetting.dao.Com_replyDao;
import com.spring.baseSetting.dto.Com_reply;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("com_replyDao")
public class Com_replyDaoImpl implements Com_replyDao {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    // 마이바티스 mapper xml에서 설정한 mapper 이름
    private final String MAPPER = "mapper.com_reply.";

    private final SqlSession sqlSession;
    public Com_replyDaoImpl(SqlSession sqlSession) {
        logger.info("Com_ReplyDaoImpl Init...");
        this.sqlSession = sqlSession;
    }
    
    
    // 댓글 조회
    @Override
    public List<Com_reply> ListreadReply(int com_no){
    	List<Com_reply> reply = sqlSession.selectList(MAPPER + "readReply", com_no);
    	logger.info("dao----" + reply.toString());
    	return reply;
    }
    
    // 댓글 작성
    @Override
    public void writeReply(Com_reply comReply) {
    	sqlSession.insert(MAPPER + "writeReply", comReply);
    }
 
    // 댓글 수정
    @Override
    public int updateReply(Com_reply comReply) {
    	int cnt = sqlSession.update(MAPPER + "updateReply", comReply);
    	return cnt;
    }
    
    // 댓글 삭제
    @Override
    public int deleteReply(Com_reply comReply) {
    	int cnt = sqlSession.update(MAPPER + "deleteReply", comReply);
    	return cnt;
    }
    
    // 선택 댓글 조회
    @Override
    public Com_reply selectReply(int rno) {
    	return sqlSession.selectOne(MAPPER + "selectReply", rno);
    }
    
   
}
