package com.spring.baseSetting.dao;

import java.util.List;

import com.spring.baseSetting.dto.Movie;

public interface MovieDao {
	// 영화 정보(카드 클릭하고 보여지는거),  업데이트 폼에도 사용
	Movie selectOneMovieInfo(String mov_code);
	

	// 영화정보 전부 다 가져오기
	// 관리자 영화 관리 페이지에 불러올거임 
	List<Movie> selectAllMovie();

	// 영화카드 전부다 가져오기
	// 가져올때 영화코드, 이름, 날짜만
	List<Movie> selectAllMovieCard();

	// 상영예정작 카드 가져오기(날짜로 비교해서 가져오기) (정렬 날짜-> 이름)
	List<Movie> selectComeMovieCard();

	// 나중에 예매랑 합치고 하기
	// 현재개봉작 카드 가져오기(날짜로 비교해서 가져오기) (정렬 인기순 -> 이름)
	// select 2번하기 각 영화당 예매 갯수 세서 count 하고 예매율 구하는거랑
	// 카드 select
	// 나중에 할때는 mov_code 넘겨서 해야지 count 가능~
	// List<Movie> selectNowMovieCard(String mov_code);

	// 현재 상영중인거 실험용
	List<Movie> selectNowMovieCard();

	// 영화코드 비교 할거 compare_code에 넣어서 보내고 ex) M200623
	// like로 몇개인지 갯수 알아오는거
	// compare_code 뒤에 01 02 03 저거 하려고
	String countMovCode(String compare_code);

	// movie insert
	int insertMovie(Movie movie);

	// movie delete
	int deleteMovie(String mov_code);

	// movie update
	int updateMovie(Movie movie);
	
	
	
	//현재 상영중인 영화별 예매된 좌석 개수
	int countOneMovResv(String mov_code);
		
	//현재 상영중인 모든 영화 예매된 좌석 개수
	int countAllMovResv();

}// MovieDao class end
