package com.its.orientaTest.mapper;

import com.its.orientaTest.model.dto.PagoBeneficioResponseDTO;
import com.its.orientaTest.model.dto.PagoRequestDTO;
import com.its.orientaTest.model.dto.PagoResponseDTO;
import com.its.orientaTest.model.entities.Pago;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class PagoMapper {
    private final ModelMapper modelMapper;

    public Pago toEntity(PagoRequestDTO pagoRequestDTO){
        return modelMapper.map(pagoRequestDTO, Pago.class);
    }

    public PagoResponseDTO toDTO(Pago pago){
        return modelMapper.map(pago, PagoResponseDTO.class);
    }

    public List<PagoResponseDTO> toListDTO(List<Pago> pagos){
        return pagos.stream().map(this::toDTO).toList();
    }

    public PagoBeneficioResponseDTO toDTOBeneficio(Pago pago){
        return modelMapper.map(pago, PagoBeneficioResponseDTO.class);
    }

    public List<PagoBeneficioResponseDTO> toListBeneficioDTO(List<Pago> pagos){
        return pagos.stream().map(this::toDTOBeneficio).toList();
    }
}