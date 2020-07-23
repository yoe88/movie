package com.spring.baseSetting.dao.impl;

import com.spring.baseSetting.dao.Com_noticeDao;
import com.spring.baseSetting.dto.Com_notice;
import com.spring.baseSetting.dto.SearchCriteria;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("com_noticeDao")
public class Com_noticeDaoImpl implements Com_noticeDao {

    @Override
  public List<Com_notice> selectmyQue(SearchCriteria scri) {
    List<Com_notice> list = sqlSession.selectList(MAPPER + "selectmyQue", scri);
    return list;
  }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //마이바티스 mapper xml에서 설정한 mapper 이름
    private final String MAPPER = "mapper.com_notice.";

    private final SqlSession sqlSession;
    public Com_noticeDaoImpl(SqlSession sqlSession) {
        logger.info("Com_noticeDaoImpl Init...");
        this.sqlSession = sqlSession;
    }
    
    //*1.5list 목록
    @Override
    public List<Com_notice> comNoticeList(SearchCriteria scri) {
        List<Com_notice> list = sqlSession.selectList(MAPPER + "aaaPage", scri);
        return list;
    }//1.5end
    
    //게시물 총 갯수
    @Override
    public int listCount(SearchCriteria scri) {
    	return sqlSession.selectOne(MAPPER +"listCount", scri);
    }
    
 
    //1.7
    @Override
    public int upVisitnum(int com_no) {
      int upvisit = sqlSession.update(MAPPER+"upVisitnum", com_no);
      return upvisit;
    }    
    
    //2selectCom_noticeById
    @Override
    public Com_notice selectComNoticeById(String com_title) {
    	Com_notice com_notice = sqlSession.selectOne(MAPPER + "selectComNoticeById", com_title);
        return com_notice;
    }//2end

    //3selectOneCom_notice
    @Override 
    public Com_notice selectOneComNotice(int com_no){
    	Com_notice detail = sqlSession.selectOne(MAPPER +"selectOneComNotice", com_no); 
    	logger.info("-------------" + detail.toString());
    	return detail; 
    }//3end
 
    //3.5 replyOne
	@Override
	public int replyOne(Com_notice comNotice) {
		int reply = sqlSession.insert(MAPPER + "replyOne", comNotice);
		return reply;
	}//3.5end
    
    //3.7replyCount
    @Override
    public int repnoCount(int com_no) {
      int repcount = sqlSession.update(MAPPER+"repnoCount", com_no);
      return repcount;
    }//3.7end
    

    
    //4insertCom_notice
	@Override
	public int insertComNotice(Com_notice comNotice) {
		int result = sqlSession.insert(MAPPER + "insertComNotice", comNotice);
		return result;
	}//4end

	//5deleteCom_notice
	public int deleteComNotice(int com_no) {
		int result = sqlSession.delete(MAPPER + "deleteComNotice", com_no);
		return result;
	}//5end

	//6updateCom_notice
	@Override
	public int updateComNotice(Com_notice comNotice) {
		int result = sqlSession.update(MAPPER + "updateComNotice", comNotice);
		return result;
	}

	@Override
	public List<Com_notice> selectPopular() {
		List<Com_notice> list = sqlSession.selectList(MAPPER + "selectPopular");
		
		return list;
	}

	@Override
	public List<Com_notice> selectNew() {
		List<Com_notice> list = sqlSession.selectList(MAPPER + "selectNew");
		
		return list;
	}

	@Override
	public List<Com_notice> selectMovtalk(SearchCriteria scri) {
		List<Com_notice> list = sqlSession.selectList(MAPPER + "selectMovtalk", scri);
        return list;
	}

	@Override
	public int movtalkCount(SearchCriteria scri) {
		return sqlSession.selectOne(MAPPER +"movtalkCount", scri);
	}

	@Override
	public List<Com_notice> selectQue(SearchCriteria scri) {
		List<Com_notice> list = sqlSession.selectList(MAPPER + "selectQue", scri);
        return list;
	}

	@Override
	public int queCount(SearchCriteria scri) {
		return sqlSession.selectOne(MAPPER +"queCount", scri);
	}
    

    
    /*
    // Com_notice �겢�옒�뒪�뿉 com_notice �뀒�씠釉� 遺덈윭���씪 homecontroller濡� �쟾�떖�빐�씪
    
    @Override
    public int insertCom_notice(Com_notice com_notice) {
        int result = sqlSession.insert(MAPPER + "insertCom_notice", com_notice);
        return result;
    }
    */
}
