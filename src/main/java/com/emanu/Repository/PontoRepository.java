package com.emanu.Repository;

import com.emanu.DTO.PontoResponseDTO;
import com.emanu.Domain.DiaDaSemana;
import com.emanu.Domain.Funcionario;
import com.emanu.Domain.Ponto;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@ApplicationScoped
public class PontoRepository implements PanacheRepository<Ponto> {

    public List<PontoResponseDTO> pontosDoFuncionario(Funcionario funcionario) {
        return listAll().stream().filter(p -> p.getFuncionario().equals(funcionario)).map(p -> {
            PontoResponseDTO pontoResponseDTO = new PontoResponseDTO();
            pontoResponseDTO.setFuncionarioId(p.getFuncionario().getId());
            pontoResponseDTO.setHora(p.getHora());
            pontoResponseDTO.setData(p.getData());
            pontoResponseDTO.setDiaDaSemana(DiaDaSemana.fromDayOfWeek(p.getData().getDayOfWeek()));

            return pontoResponseDTO;
        }).toList();
    }
}