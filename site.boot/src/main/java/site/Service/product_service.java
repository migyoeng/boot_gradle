package site.Service;

import java.util.List;
import java.util.Map;

import site.DTO.product_DTO;

public interface product_service {

	public List<product_DTO> all(Map<String, String> keycode);
}
