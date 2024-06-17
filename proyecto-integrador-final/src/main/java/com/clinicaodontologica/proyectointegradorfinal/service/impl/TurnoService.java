package com.clinicaodontologica.proyectointegradorfinal.service.impl;
import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.TurnoEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.OdontologoSalidaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.PacienteSalidaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.TurnoSalidaDto;
import com.clinicaodontologica.proyectointegradorfinal.entity.Odontologo;
import com.clinicaodontologica.proyectointegradorfinal.entity.Paciente;
import com.clinicaodontologica.proyectointegradorfinal.entity.Turno;
import com.clinicaodontologica.proyectointegradorfinal.exceptions.BadRequestException;
import com.clinicaodontologica.proyectointegradorfinal.exceptions.ResourceNotFoundException;
import com.clinicaodontologica.proyectointegradorfinal.repository.TurnoRepository;
import com.clinicaodontologica.proyectointegradorfinal.service.ITurnoService;
import com.clinicaodontologica.proyectointegradorfinal.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService{

    private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
    private TurnoRepository turnoRepository;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;
    private final ModelMapper modelMapper;

    public TurnoService(TurnoRepository turnoRepository, PacienteService pacienteService, OdontologoService odontologoService, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
        this.modelMapper = modelMapper;
        configureMapping();
    }
@Override
public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) {
    LOGGER.info("TurnoEntradaDto: " + turnoEntradaDto);
    Turno turno = modelMapper.map(turnoEntradaDto, Turno.class);
    // Buscar el paciente por ID
    PacienteSalidaDto pacienteSalidaDto = pacienteService.buscarPacientePorId(turno.getPaciente().getId());
    if (pacienteSalidaDto == null) {
        throw new IllegalArgumentException("Paciente no encontrado con ID: " + turno.getPaciente().getId());
    }

    // Buscar el odontólogo por ID
    OdontologoSalidaDto odontologoSalidaDto = odontologoService.buscarOdontologoPorId(turno.getOdontologo().getId());
    if (odontologoSalidaDto == null) {
        throw new IllegalArgumentException("Odontólogo no encontrado con ID: " + turno.getOdontologo().getId());
    }

    // Mapear TurnoEntradaDto a la entidad Turno

    turno.setPaciente(modelMapper.map(pacienteSalidaDto, Paciente.class));
    turno.setOdontologo(modelMapper.map(odontologoSalidaDto, Odontologo.class));

    LOGGER.info("Turno Entidad: " + turno);

    // Guardar el turno (asume que tienes un repositorio o servicio para esto)
    Turno turnoGuardado = turnoRepository.save(turno);

    // Mapear la entidad guardada a TurnoSalidaDto
    TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoGuardado, TurnoSalidaDto.class);

    return turnoSalidaDto;
}

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<TurnoSalidaDto> turnos = turnoRepository.findAll()
                .stream()
                .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
                .toList();
        LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnos));
        return turnos;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if (turnoBuscado !=null){
            turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
            LOGGER.info("Turno encontrado {}", JsonPrinter.toString(turnoEncontrado));
        }else {
            throw new BadRequestException("No se ha encontrado el turno con id " + id);
        }

        return turnoEncontrado;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (buscarTurnoPorId(id) !=null){
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id {}", id);
        }else {
            throw new ResourceNotFoundException("No existe registro del turno con id " + id);
        }
    }

    @Override
    public TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id) {
        Turno turnoRecibido = modelMapper.map(turnoEntradaDto, Turno.class);
        Turno turnoAActualizar = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoSalidaDto = null;

        if (turnoAActualizar !=null){
            turnoRecibido.setId(turnoAActualizar.getId());
            turnoRecibido.getPaciente().getId();
            turnoRecibido.getOdontologo().getId();
            turnoAActualizar = turnoRecibido;

            turnoRepository.save(turnoAActualizar);
            turnoSalidaDto = modelMapper.map(turnoAActualizar, TurnoSalidaDto.class);
            LOGGER.warn("Turno actualizado: {}", JsonPrinter.toString(turnoSalidaDto));
        }else {
            throw new BadRequestException("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
        }
        return turnoSalidaDto;
    }

    private void configureMapping(){
        modelMapper.typeMap(TurnoEntradaDto.class, Turno.class)
                .addMappings(mapper -> mapper.map(TurnoEntradaDto::getOdontologoEntradaDto, Turno::setOdontologo))
                .addMappings(mapper -> mapper.map(TurnoEntradaDto::getPacienteEntradaDto,Turno::setPaciente));
        modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
                .addMappings(mapper -> mapper.map(Turno::getOdontologo, TurnoSalidaDto::setOdontologoSalidaDto))
                .addMappings(mapper -> mapper.map(Turno::getPaciente,TurnoSalidaDto::setPacienteSalidaDto));
    }
}
