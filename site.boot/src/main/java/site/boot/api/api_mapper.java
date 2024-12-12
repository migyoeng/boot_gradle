package site.boot.api;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface api_mapper {
	
	//전체 데이터 조회
	List<api_DTO> api_alldata();
	
	//데이터 수정
	int api_update(api_DTO dto);
}
