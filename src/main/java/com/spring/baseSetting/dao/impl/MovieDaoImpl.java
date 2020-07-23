package com.spring.baseSetting.dao.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.spring.baseSetting.dao.MovieDao;
import com.spring.baseSetting.dto.Movie;


@Repository("movieDao")
public class MovieDaoImpl implements MovieDao {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	//마이바티스 mapper xml에서 설정한 mapper 이름
    private final String MAPPER = "mapper.movie.";

    private final SqlSession sqlSession;
    public MovieDaoImpl(SqlSession sqlSession) {
        logger.info("MemberDaoImpl Init...");
        this.sqlSession = sqlSession;
    }
    
    
	@Override
	public Movie selectOneMovieInfo(String mov_code) {
		Movie mov_info = sqlSession.selectOne(MAPPER + "selectOneMovieInfo", mov_code);
		//System.out.println("mov_code(daoim) : " + mov_code);
		//System.out.println("mov_info(daoim) : " + mov_info);
		
        return mov_info;
	}
	
	
	@Override
	public List<Movie> selectAllMovie() {
		List<Movie> allmovie = sqlSession.selectList(MAPPER + "selectAllMovie");
	       
		return allmovie;
	}
	
	
	@Override
	public List<Movie> selectAllMovieCard() {
		List<Movie> allcard = sqlSession.selectList(MAPPER + "selectAllMovieCard");
       
		return allcard;
	}
	
	@Override
	public List<Movie> selectComeMovieCard() {
		List<Movie> comecard = sqlSession.selectList(MAPPER + "selectComeMovieCard");
	       
		return comecard;
	}
	
	
	//실험용
	@Override
	public List<Movie> selectNowMovieCard() {
		List<Movie> nowcard = sqlSession.selectList(MAPPER + "selectNowMovieCard");
	       
		return nowcard;
	}
	
	
	/*
	@Override
	public List<Movie> selectNowMovieCard(String mov_code) {
		List<Movie> nowcard = sqlSession.selectList(MAPPER + "selectNowMovieCard", mov_code);
	       
		return nowcard;
	}
	*/

	@Override
	public String countMovCode(String compare_code) {
		String count_movcode = sqlSession.selectOne(MAPPER + "countMovCode", compare_code);
		
		return count_movcode;
	}


	@Override
	public int insertMovie(Movie movie) {
		int result = sqlSession.insert(MAPPER + "insertMovie", movie);
		
		return result;
	}


	@Override
	public int deleteMovie(String mov_code) {
		int result = sqlSession.delete(MAPPER + "deleteMovie", mov_code);
		
		return result;
	}


	@Override
	public int updateMovie(Movie movie) {
		int result = sqlSession.update(MAPPER + "updateMovie", movie);
		
		return result;
	}


	
	@Override
	public int countOneMovResv(String mov_code) {
		int cnt = sqlSession.selectOne(MAPPER + "countOneMovResv", mov_code);
		
		return cnt;
	}


	@Override
	public int countAllMovResv() {
		int cnt = sqlSession.selectOne(MAPPER + "countAllMovResv");
		
		return cnt;
	}


	
	
	
}//MovieDaoImpl class end

