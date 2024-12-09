package site.ServiceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.DTO.event_DTO;
import site.Service.event_service;
import site.boot.Mapper.event_mapper;

@Service
public class event_serviceimp implements event_service {
	
	@Autowired
	event_mapper em;

	@Override
	public Integer event_join(event_DTO dto) {
		Integer result = em.event_join(dto);
		return result;
	}

}
