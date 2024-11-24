package com.youcode.citronix.service.implementation;

import com.youcode.citronix.dto.requestDto.ChampRequestDto;
import com.youcode.citronix.dto.responseDto.ChampResponseDto;
import com.youcode.citronix.entity.Champ;
import com.youcode.citronix.entity.Ferme;
import com.youcode.citronix.exception.champExceptions.ChampNotFoundException;
import com.youcode.citronix.exception.fermeExceptions.FermeNotFoundException;
import com.youcode.citronix.exception.fermeExceptions.NombreChampInsuffisant;
import com.youcode.citronix.exception.fermeExceptions.SuperficieInsuffisanteException;
import com.youcode.citronix.repository.ChampRepository;
import com.youcode.citronix.repository.FermeRepository;
import com.youcode.citronix.service.ChampService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChampServiceImpl implements ChampService {

    private final ChampRepository champRepository;

    private final FermeRepository fermeRepository;

    private final ModelMapper modelMapper;

    @Override
    public ChampResponseDto createChamp(ChampRequestDto champRequestDto) {
        long fermeIdFromRequest = champRequestDto.getFermeId();
        boolean checkFermeConditions= fermeCreationValidation(fermeIdFromRequest,champRequestDto);
    if (checkFermeConditions) {
        Champ champCreated = modelMapper.map(champRequestDto, Champ.class);
        champCreated.setId(0);
        champCreated.setNbrArbre(champCreated.getSuperficie() * 100);
        Champ createdChamp = champRepository.save(champCreated);
        return modelMapper.map(createdChamp, ChampResponseDto.class);
    }else{
        throw new SuperficieInsuffisanteException("superficie insuffisante e");
    }
}
    @Transactional
    public boolean fermeCreationValidation(long fermeIdFromRequest, ChampRequestDto champRequestDto){
        Optional<Ferme> optionalFerme = fermeRepository.findById(fermeIdFromRequest);
        if (optionalFerme.isEmpty()) {
            throw new FermeNotFoundException("ferme not found");
        }else if(optionalFerme.get().getSuperficie() / 2 < champRequestDto.getSuperficie() ){
            throw new SuperficieInsuffisanteException("superficie should not pass the 50% of the ferme");
        }else if(optionalFerme.get().getNombreChamp() < 1){
            throw new NombreChampInsuffisant("le nombre de champ dans la ferme et insuffisant");
        }
        else if(optionalFerme.get().getSuperficie() > optionalFerme.get().getSuperficieExploitee() + champRequestDto.getSuperficie() ){
            optionalFerme.get().setSuperficieExploitee(optionalFerme.get().getSuperficieExploitee() + champRequestDto.getSuperficie());
            optionalFerme.get().setNombreChamp(optionalFerme.get().getNombreChamp()- 1);
            fermeRepository.save(optionalFerme.get());
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public List<ChampResponseDto> getAllChamps() {
        List<Champ> champs = champRepository.findAll();
        return champs.stream()
                .map(champ -> modelMapper.map(champ, ChampResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ChampResponseDto getChampById(long id) {
        Champ champ = champRepository.findById(id).orElseThrow(() -> new ChampNotFoundException("Champ not found"));

        return modelMapper.map(champ, ChampResponseDto.class);
    }

    @Override
    @Transactional
    public ChampResponseDto updateChamp(long id, ChampRequestDto champRequestDto) {
        Champ champ = champRepository.findById(id)
                .orElseThrow(() -> new ChampNotFoundException("Champ not found"));
         boolean checkFermee = fermeRepository.existsById(champRequestDto.getFermeId());
        if(!checkFermee) throw new FermeNotFoundException("ferme not found");

        updateSuperficieAndFerme(champ, champRequestDto);

       champ.setNom(champRequestDto.getNom());
        champ.setSuperficie(champRequestDto.getSuperficie());
        champ = champRepository.save(champ);

        return modelMapper.map(champ, ChampResponseDto.class);
    }

    private void updateSuperficieAndFerme(Champ champ, ChampRequestDto champRequestDto) {
         Double oldSuperficie = champ.getSuperficie();
        Double newSuperficie = champRequestDto.getSuperficie();
         Double updatedSuperficieExploitee = champ.getFerme().getSuperficieExploitee() - oldSuperficie + newSuperficie;
        if (newSuperficie > updatedSuperficieExploitee) {
            throw new SuperficieInsuffisanteException("Superficie insuffisante");
        }
        champ.getFerme().setSuperficieExploitee(updatedSuperficieExploitee);
        if (!fermeCreationValidation(champ.getFerme().getId(), champRequestDto)) {
            throw new SuperficieInsuffisanteException("Superficie insuffisante");
        }

        fermeRepository.save(champ.getFerme());
    }

    @Override
    @Transactional
    public void deleteChamp(long id) {
        Champ champ = champRepository.findById(id).orElseThrow(() -> new ChampNotFoundException("Champ not found"));
         Ferme ferme = champ.getFerme();
        ferme.setSuperficieExploitee(ferme.getSuperficieExploitee() - champ.getSuperficie());
         fermeRepository.save(ferme);
        champRepository.delete(champ);
    }


}
