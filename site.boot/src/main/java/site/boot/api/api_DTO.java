package site.boot.api;

import org.springframework.stereotype.Repository;

import lombok.Data;

@Data
@Repository("api_DTO")
public class api_DTO {
	Integer EIDX;
	String COUPON_NO, MNAME, MEMAIL, TEL_CORP, TEL_NO;
	String ADD_POST, ADD_LOAD, ADD_ADDRESS, SMS, EMAIL, EVENT_DATE;
}
