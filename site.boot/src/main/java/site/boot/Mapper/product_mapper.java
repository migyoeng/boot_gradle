package site.boot.Mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import site.DTO.product_DTO;

@Mapper
public interface product_mapper {

	public List<product_DTO> all(Map<String, String> keycode);
}
