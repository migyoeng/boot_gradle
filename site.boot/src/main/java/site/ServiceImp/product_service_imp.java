package site.ServiceImp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.DTO.product_DTO;
import site.Service.product_service;

@Service
public class product_service_imp implements product_service {

	@Autowired
	site.boot.Mapper.product_mapper product_mapper;
	
	@Override
	public List<product_DTO> all(Map<String, String> keycode) {
		List<product_DTO> all = null;
		try {
			all = product_mapper.all(keycode);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return all;
	}
}
