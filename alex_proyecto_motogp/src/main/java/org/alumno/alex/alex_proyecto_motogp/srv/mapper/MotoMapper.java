package org.alumno.alex.alex_proyecto_motogp.srv.mapper;

import org.alumno.alex.alex_proyecto_motogp.model.dto.MotoInfo;
import org.alumno.alex.alex_proyecto_motogp.model.ram.Moto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MotoMapper {
	MotoMapper INSTANCE= Mappers.getMapper(MotoMapper.class);
	Moto motoInfoToMoto(MotoInfo moto);
}
