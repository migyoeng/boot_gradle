package site.boot.Mapper;

import org.apache.ibatis.annotations.Mapper;

import site.DTO.event_DTO;

@Mapper
public interface event_mapper {
	Integer event_join(event_DTO dto);
}
