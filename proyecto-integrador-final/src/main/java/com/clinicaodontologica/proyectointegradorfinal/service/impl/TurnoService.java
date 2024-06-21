package com.clinicaodontologica.proyectointegradorfinal.service.impl;
import com.clinicaodontologica.proyectointegradorfinal.dto.entrada.TurnoEntradaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.DomicilioSalidaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.OdontologoSalidaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.PacienteSalidaDto;
import com.clinicaodontologica.proyectointegradorfinal.dto.salida.TurnoSalidaDto;
import com.clinicaodontologica.proyectointegradorfinal.entity.Domicilio;
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
public class TurnoService implements ITurnoService {

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
    }

    @Override
    public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto) {
        LOGGER.info("TurnoEntradaDto: " + JsonPrinter.toString(turnoEntradaDto));

        Long pacienteId = turnoEntradaDto.getPacienteId();
        if (pacienteId == null) {
            throw new BadRequestException("ID de paciente es nulo en la solicitud.");
        }
        Long odontologoId = turnoEntradaDto.getOdontologoId();
        if (odontologoId == null) {
            throw new BadRequestException("ID de odontólogo es nulo en la solicitud.");
        }
        PacienteSalidaDto pacienteSalidaDto = pacienteService.buscarPacientePorId(pacienteId);
        if (pacienteSalidaDto == null) {
            throw new IllegalArgumentException("Paciente no encontrado con ID: " + pacienteId);
        }
        OdontologoSalidaDto odontologoSalidaDto = odontologoService.buscarOdontologoPorId(odontologoId);
        if (odontologoSalidaDto == null) {
            throw new IllegalArgumentException("Odontólogo no encontrado con ID: " + odontologoId);
        }

        Turno turno = convertToTurno(turnoEntradaDto);
        turno.setFechaYHora(turnoEntradaDto.getFechaYHora());
        turno.setPaciente(modelMapper.map(pacienteSalidaDto, Paciente.class));
        turno.setOdontologo(modelMapper.map(odontologoSalidaDto, Odontologo.class));
        LOGGER.info("Turno Entidad: " + JsonPrinter.toString(turno));
        Turno turnoGuardado = turnoRepository.save(turno);
        TurnoSalidaDto turnoSalidaDto = convertToTurnoSalidaDto(turnoGuardado);
        LOGGER.info("TurnoSalidaDto: " + JsonPrinter.toString(turnoSalidaDto));
        return turnoSalidaDto;
    }

    @Override
    public List<TurnoSalidaDto> listarTurnos() {
        List<Turno> turnos = turnoRepository.findAll();

        List<TurnoSalidaDto> turnosSalidaDto = turnos.stream().map(turno -> {
            TurnoSalidaDto turnoSalidaDto = new TurnoSalidaDto();
            turnoSalidaDto.setId(turno.getId());
            turnoSalidaDto.setFechaYHora(turno.getFechaYHora());

            Odontologo odontologo = turno.getOdontologo();
            OdontologoSalidaDto odontologoSalidaDto = new OdontologoSalidaDto();
            if (odontologo != null) {
                odontologoSalidaDto.setId(odontologo.getId());
                odontologoSalidaDto.setNombre(odontologo.getNombre());
                odontologoSalidaDto.setApellido(odontologo.getApellido());
                odontologoSalidaDto.setMatricula(odontologo.getMatricula());
            }
            turnoSalidaDto.setOdontologoSalidaDto(odontologoSalidaDto);

            Paciente paciente = turno.getPaciente();
            PacienteSalidaDto pacienteSalidaDto = new PacienteSalidaDto();
            if (paciente != null) {
                pacienteSalidaDto.setId(paciente.getId());
                pacienteSalidaDto.setNombre(paciente.getNombre());
                pacienteSalidaDto.setApellido(paciente.getApellido());
                pacienteSalidaDto.setDni(paciente.getDni());
                pacienteSalidaDto.setFechaIngreso(paciente.getFechaIngreso());

                Domicilio domicilio = paciente.getDomicilio();
                DomicilioSalidaDto domicilioSalidaDto = new DomicilioSalidaDto();
                if (domicilio != null) {
                    domicilioSalidaDto.setId(domicilio.getId());
                    domicilioSalidaDto.setCalle(domicilio.getCalle());
                    domicilioSalidaDto.setNumero(domicilio.getNumero());
                    domicilioSalidaDto.setLocalidad(domicilio.getLocalidad());
                    domicilioSalidaDto.setProvincia(domicilio.getProvincia());
                }
                pacienteSalidaDto.setDomicilioSalidaDto(domicilioSalidaDto);
            }
            turnoSalidaDto.setPacienteSalidaDto(pacienteSalidaDto);

            return turnoSalidaDto;
        }).toList();

        LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnosSalidaDto));
        return turnosSalidaDto;
    }

    @Override
    public TurnoSalidaDto buscarTurnoPorId(Long id) {
        Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
        TurnoSalidaDto turnoEncontrado = null;

        if (turnoBuscado != null) {
            turnoEncontrado = convertToTurnoSalidaDto(turnoBuscado);
            LOGGER.info("Turno encontrado {}", JsonPrinter.toString(turnoEncontrado));
        } else {
            throw new BadRequestException("No se ha encontrado el turno con id " + id);
        }

        return turnoEncontrado;
    }

    @Override
    public void eliminarTurno(Long id) throws ResourceNotFoundException {
        if (buscarTurnoPorId(id) != null) {
            turnoRepository.deleteById(id);
            LOGGER.warn("Se ha eliminado el turno con id {}", id);
        } else {
            throw new ResourceNotFoundException("No existe registro del turno con id " + id);
        }
    }

    @Override
    public TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id) {
        Turno turnoRecibido = convertToTurno(turnoEntradaDto);
        Turno turnoAActualizar = turnoRepository.findById(id)
                .orElse(null);
        TurnoSalidaDto turnoSalidaDto = null;

        if (turnoAActualizar !=null){

//            modelMapper.map(turnoEntradaDto, turnoAActualizar);

            OdontologoSalidaDto odontologoSalidaDto = odontologoService.buscarOdontologoPorId(turnoEntradaDto.getOdontologoId());
            PacienteSalidaDto pacienteSalidaDto = pacienteService.buscarPacientePorId(turnoEntradaDto.getPacienteId());

            if (odontologoSalidaDto == null) {
                throw new BadRequestException("Odontólogo no encontrado con ID: " + turnoEntradaDto.getOdontologoId());
            }
            if (pacienteSalidaDto == null) {
                throw new BadRequestException("Paciente no encontrado con ID: " + turnoEntradaDto.getPacienteId());
            }

            Odontologo odontologo = modelMapper.map(odontologoSalidaDto, Odontologo.class);
            Paciente paciente = modelMapper.map(pacienteSalidaDto, Paciente.class);

            turnoRecibido.setId(turnoAActualizar.getId());
            turnoRecibido.setFechaYHora(turnoAActualizar.getFechaYHora());
            turnoRecibido.setOdontologo(odontologo);
            turnoRecibido.setPaciente(paciente);
            turnoAActualizar = turnoRecibido;

            turnoRepository.save(turnoAActualizar);
            turnoSalidaDto = convertToTurnoSalidaDto(turnoAActualizar);
            LOGGER.warn("Turno actualizado: {}", JsonPrinter.toString(turnoSalidaDto));
        }else{
            throw new BadRequestException("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
        }

        return turnoSalidaDto;
    }

    private Turno convertToTurno(TurnoEntradaDto dto) {
        Turno turno = new Turno();
        turno.setFechaYHora(dto.getFechaYHora());
        turno.setOdontologo(new Odontologo(dto.getOdontologoId()));
        turno.setPaciente(new Paciente(dto.getPacienteId()));
        return turno;
    }


    private TurnoSalidaDto convertToTurnoSalidaDto(Turno turno) {
        TurnoSalidaDto turnoSalidaDto = new TurnoSalidaDto();
        turnoSalidaDto.setId(turno.getId());
        turnoSalidaDto.setFechaYHora(turno.getFechaYHora());
        turnoSalidaDto.setOdontologoSalidaDto(convertToOdontologoSalidaDto(turno.getOdontologo()));
        turnoSalidaDto.setPacienteSalidaDto(convertToPacienteSalidaDto(turno.getPaciente()));
        return turnoSalidaDto;
    }

    private OdontologoSalidaDto convertToOdontologoSalidaDto(Odontologo odontologo) {
        if (odontologo == null) return null;
        OdontologoSalidaDto odontologoSalidaDto = new OdontologoSalidaDto();
        odontologoSalidaDto.setId(odontologo.getId());
        odontologoSalidaDto.setNombre(odontologo.getNombre());
        odontologoSalidaDto.setApellido(odontologo.getApellido());
        odontologoSalidaDto.setMatricula(odontologo.getMatricula());
        return odontologoSalidaDto;
    }

    private PacienteSalidaDto convertToPacienteSalidaDto(Paciente paciente) {
        if (paciente == null) return null;

        PacienteSalidaDto pacienteSalidaDto = new PacienteSalidaDto();
        pacienteSalidaDto.setId(paciente.getId());
        pacienteSalidaDto.setNombre(paciente.getNombre());
        pacienteSalidaDto.setApellido(paciente.getApellido());
        pacienteSalidaDto.setDni(paciente.getDni());
        pacienteSalidaDto.setFechaIngreso(paciente.getFechaIngreso());

        if (paciente.getDomicilio() != null) {
            DomicilioSalidaDto domicilioSalidaDto = new DomicilioSalidaDto();
            domicilioSalidaDto.setId(paciente.getDomicilio().getId());
            domicilioSalidaDto.setCalle(paciente.getDomicilio().getCalle());
            domicilioSalidaDto.setNumero(paciente.getDomicilio().getNumero());
            domicilioSalidaDto.setLocalidad(paciente.getDomicilio().getLocalidad());
            domicilioSalidaDto.setProvincia(paciente.getDomicilio().getProvincia());

            pacienteSalidaDto.setDomicilioSalidaDto(domicilioSalidaDto);
        }else {
            pacienteSalidaDto.setDomicilioSalidaDto(null);
            LOGGER.warn("El domicilio del paciente con ID {} es nulo", paciente.getId());
        }

        return pacienteSalidaDto;
    }

}