package com.spring.baseSetting.service.impl;

import com.spring.baseSetting.dao.TheaInfoDao;
import com.spring.baseSetting.dto.Mov_show;
import com.spring.baseSetting.dto.Theainfo;
import com.spring.baseSetting.service.FileService;
import com.spring.baseSetting.service.TheaInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TheaInfoServiceImpl implements TheaInfoService {
    private final TheaInfoDao theaInfoDao;
    private final FileService fileService;

    enum Region { //  ┗|｀O′|┛
        SU("11"),BS("26"),DG("27"),IC("28"),GJ("29"),DJ("30"),US("31")
        ,SJ("36"),GG("41"),GW("42"),CB("43"),CN("44"),JB("45"),JN("46")
        ,GB("47"),GN("48"),JJ("50");

        private final String code;
        private static final Map<String,Region> map = new HashMap<>();

        Region(String code) {
            this.code = code;
        }
        static {
            for(Region regionName : Region.values() ){
                map.put(regionName.code, regionName);
            }
        }
        public static Region getRegionName(String code){
            Region regionCode = map.get(code);
            if (regionCode == null){
                throw new IllegalArgumentException("존재하지 않는 코드에요.!! : " + code);
            }
            return regionCode;
        }
    }



    @Autowired
    public TheaInfoServiceImpl(TheaInfoDao theaInfoDao, FileService fileService) {
        log.info("TheaInfoServiceImpl Init...");
        this.theaInfoDao = theaInfoDao;
        this.fileService = fileService;
    }

    @Transactional(readOnly = true)
    @Override
    public Map<String, Object> getAllTheaInfoList(String field, String query, int page) {
        Map<String, Object> resultMap = new HashMap<>();

        Map<String,Object> map = new HashMap<>();
        map.put("field",field);
        map.put("query",query);
        int offset = (page-1)*10;
        int count = 10;
        map.put("offset",offset);
        map.put("count",count);
        resultMap.put("list",theaInfoDao.selectAllThea(map));
        resultMap.put("count",theaInfoDao.selectAllTheaCount(map));

        return resultMap;
    }

    /**
     *  ^-0-^
     */
    @Transactional
    @Override
    public int addTheaInfo(Theainfo theaInfo) {
        String regionCode = theaInfo.getThea_code();
        String regionName = Region.getRegionName(regionCode).toString();
        String lastRegionCode = theaInfoDao.selectLastTheaCode(regionName);
        if(lastRegionCode == null){
            theaInfo.setThea_code(regionName + "01");
        }else{
            int maxNumber = Integer.parseInt(lastRegionCode.replaceAll("[^0-9]",""));
            maxNumber++;
            String newTheaCode = maxNumber < 10 ? regionName + "0" + maxNumber : regionName + maxNumber;
            theaInfo.setThea_code(newTheaCode);
        }

        return theaInfoDao.insertTheaInfo(theaInfo);
    }

    @Transactional(readOnly = true)
    @Override
    public Theainfo getTheaInfo(String theaCode) {
        return theaInfoDao.selectTheaInfoByTheaCode(theaCode);
    }

    @Transactional
    @Override
    public int modifyTheaInfo(Theainfo theaInfo, MultipartFile mf) {
        String folderName = FileService.theaterPath; //   theater/영화코드
        int result = 0;
        boolean isSuccessFileUpdate = false;

        if(mf.getSize() != 0){  //파일 수정인경우
            // 먼저 업로드하기
            String safeName = fileService.upload(mf,folderName);
            theaInfo.setThea_img(safeName);
            // 기존 파일 삭제
            if(safeName != null){
                String fileName = theaInfoDao.selectTheaInfoImgByTheaCode(theaInfo.getThea_code());  // 기존 이미지 파일 이름
                isSuccessFileUpdate = fileService.deleteFile(folderName,fileName);
            }
        }else{
            isSuccessFileUpdate = true;
        }
        if(isSuccessFileUpdate){
            result = theaInfoDao.updateTheaInfo(theaInfo);
        }
        
        return result;
    }

    /**
     * @param theaCode 영화관 코드
     * @return
     */
    @Transactional
    @Override
    public int deleteTheaInfo(String theaCode) {
        int result;
        String folderName = FileService.theaterPath;
        String fileName = theaInfoDao.selectTheaInfoImgByTheaCode(theaCode);
        boolean isSuccessDelete =  fileService.deleteFile(folderName,fileName); //파일삭제
        if(isSuccessDelete){
            result = theaInfoDao.deleteTheaInfo(theaCode);
        }else{
            return 0;
        }
        return result;
    }

	@Override
	public List<Theainfo> getThea() {
		List<Theainfo> thea = theaInfoDao.selectThea();
		return thea;                   
	}
	
	@Override
	public String theacodeFind(String thea_name) {
		String theacode = theaInfoDao.theacodeFind(thea_name);
			
		
		return theacode;
	}
    
    

}
