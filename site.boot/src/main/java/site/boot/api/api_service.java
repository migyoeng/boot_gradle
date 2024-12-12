package site.boot.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class api_service {
	@Autowired
	api_mapper am;
	
	public List<api_DTO> api_alldata(){
		List<api_DTO> all = am.api_alldata();
		System.out.println(all.size());
		return all;
	}
	
	public int api_update(api_DTO dto) {
		int result = am.api_update(dto);
		return result;
	}
	
}
