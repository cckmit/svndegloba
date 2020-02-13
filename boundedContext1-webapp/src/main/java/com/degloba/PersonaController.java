package com.degloba;


import com.degloba.persones.cqrs.commands.CreaPersonaCommand;
import com.degloba.persones.cqrs.commands.RequestPrivateAddressAssignmentCommand;
import com.degloba.persones.facade.dtos.AdrecaDto;
import com.degloba.persones.facade.dtos.PersonaDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;
import java.util.concurrent.Future;

/**
 * 
 * @author degloba
 *
 * @category controlador Rest/Spring Persona
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class PersonaController {

    private final CommandGateway commandGateway;

    @PostMapping("/persons")
    public Future<UUID> createPerson(@RequestBody @Valid PersonaDto personDto) {
        log.debug("[Person][API] Creating a person: {}", personDto);
        return commandGateway.send(new CreaPersonaCommand(UUID.randomUUID().toString(), personDto.getNomComplert()));
    }

    @PostMapping("/person/{personId}/address")
    public Future<String> assignPrivateAddress(@RequestParam String personId, @RequestBody @Valid AdrecaDto dto) {
        log.debug("[Person][API] Request to assign new private address, person: {}, address: {}", personId, dto);
        return commandGateway.send(new RequestPrivateAddressAssignmentCommand(personId,
                dto.getCarrerINumero(),
                dto.getZipCode()));
    }

}
