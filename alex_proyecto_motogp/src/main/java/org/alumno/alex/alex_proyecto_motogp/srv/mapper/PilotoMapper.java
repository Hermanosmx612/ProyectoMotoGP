package org.alumno.alex.alex_proyecto_motogp.srv.mapper;

import java.util.List;

import org.alumno.alex.alex_proyecto_motogp.model.dto.CorredorEdit;
import org.alumno.alex.alex_proyecto_motogp.model.dto.CorredorList;
import org.alumno.alex.alex_proyecto_motogp.model.ram.Corredor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PilotoMapper{
	PilotoMapper INSTANCE= Mappers.getMapper(PilotoMapper.class);

	List<CorredorList> corredorToCorredorList(List<Corredor> corredores);
	
	Corredor corredorEditToCorredor(CorredorEdit corredorEdit);
	
	CorredorEdit corredorToCorredorEdit(Corredor corredor);

}
