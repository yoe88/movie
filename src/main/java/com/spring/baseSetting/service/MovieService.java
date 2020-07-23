package com.spring.baseSetting.service;

import com.spring.baseSetting.dto.Movie;

import java.util.List;

public interface MovieService {
    // 영화 정보(카드 클릭하고 보여지는거),  업데이트 폼에도 사용
 	Movie getOneMovieInfo(String mov_code);

 	// 영화정보 전부 다 가져오기
 	// 관리자 영화 관리 페이지에 불러올거임 
 	List<Movie> getAllMovie();

 	// 영화카드 전부다 가져오기
 	// 가져올때 영화코드, 이름, 날짜만
 	List<Movie> getAllMovieCard();

 	// 상영예정작 카드 가져오기(날짜로 비교해서 가져오기) (정렬 날짜-> 이름)
 	List<Movie> getComeMovieCard();

 	// 나중에 예매랑 합치고 하기
 	//List<Movie> selectNowMovieCard(String mov_code);

 	// 현재 상영중인거 실험용
 	List<Movie> getNowMovieCard();

 	// 영화코드 비교 할거 compare_code에 넣어서 보내고 ex) M200623
 	// like로 몇개인지 갯수 알아오는거
 	// compare_code 뒤에 01 02 03 저거 하려고
 	String getCountMovCode(String compare_code);

 	// movie insert
 	int addMovie(Movie movie);

 	// movie delete
 	int removeMovie(String mov_code);

 	// movie update
 	int modifyMovie(Movie movie);
 	
 	
 	//현재 상영중인 영화별 예매된 좌석 개수
 	int getCountOneMovResv(String mov_code);
 	 		
 	//현재 상영중인 모든 영화 예매된 좌석 개수
 	int getCountAllMovResv();
}
