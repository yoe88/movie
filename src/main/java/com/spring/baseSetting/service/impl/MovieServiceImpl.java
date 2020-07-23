package com.spring.baseSetting.service.impl;


import com.spring.baseSetting.dao.MovieDao;
import com.spring.baseSetting.dto.Movie;
import com.spring.baseSetting.service.MovieService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final MovieDao movieDao;

    public MovieServiceImpl(MovieDao movieDao) {
        logger.info("MovieServiceImpl Init...");
        this.movieDao = movieDao;
    }

    
	@Override
	public Movie getOneMovieInfo(String mov_code) {
		Movie mov_info = movieDao.selectOneMovieInfo(mov_code);
		//System.out.println("mov_code(serviceim) : " + mov_code);
		//System.out.println("mov_info(serviceim) : " + mov_info);
		
		return mov_info;
	}

	@Override
	public List<Movie> getAllMovie() {
		List<Movie> allmovie = movieDao.selectAllMovie();
		
		return allmovie;
	}

	@Override
	public List<Movie> getAllMovieCard() {
		List<Movie> allcard = movieDao.selectAllMovieCard();
		
		return allcard;
	}

	@Override
	public List<Movie> getComeMovieCard() {
		List<Movie> comecard = movieDao.selectComeMovieCard();
		
		return comecard;
	}

	@Override
	public List<Movie> getNowMovieCard() {
		List<Movie> nowcard = movieDao.selectNowMovieCard();
		
		return nowcard;
	}

	@Override
	public String getCountMovCode(String compare_code) {
		String count_movcode = movieDao.countMovCode(compare_code);
		
		return count_movcode;
	}

	@Override
	public int addMovie(Movie movie) {
		int result = movieDao.insertMovie(movie);
		
		return result;
	}

	@Override
	public int removeMovie(String mov_code) {
		int result = movieDao.deleteMovie(mov_code);
		
		return result;
	}

	@Override
	public int modifyMovie(Movie movie) {
		int result = movieDao.updateMovie(movie);
		
		return result;
	}
	
	@Override
	public int getCountOneMovResv(String mov_code) {
		int cnt = movieDao.countOneMovResv(mov_code);
		
		return cnt;
	}


	@Override
	public int getCountAllMovResv() {
		int cnt = movieDao.countAllMovResv();
		
		return cnt;
	}


    
}
