package site.boot.jeong;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.annotation.Resource;
import site.DTO.product_DTO;
import site.Service.product_service;

//DTO, VO, Controller, Service, ServiceImp 다 허용이지만 mapper는 불가능
@ComponentScan(basePackages = "site.*")
@Controller
public class jeong_controller {
	
	@Autowired
	product_service ps;
	
	@Resource(name="product_DTO")
	product_DTO pdto;

	@PostMapping("/search.do")
	public String search(product_DTO dto) {
		Map<String, String> keycode = new HashMap<>();
		keycode.put("part", dto.getPart());
		keycode.put("search", dto.getSearch());
	
		List<product_DTO> all = ps.all(keycode);
		
		System.out.println(all.get(0).getPcode());
		System.out.println(all.get(0).getPname());
	
		return null;
   }

}
