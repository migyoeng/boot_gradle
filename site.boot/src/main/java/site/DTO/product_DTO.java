package site.DTO;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository("product_DTO")
public class product_DTO {
	//DB 필드명
	int pdix;
	String pcate, pcode, pname, pmoney, psales, puse, pdate;
	
	//검색 관련
	String part, search;
}
