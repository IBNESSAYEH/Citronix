package com.youcode.citronix.service.implementation;

import com.youcode.citronix.Util.DeterminSaison;
import com.youcode.citronix.dto.requestDto.ArbreRequestDto;
import com.youcode.citronix.dto.responseDto.ArbreResponseDto;
import com.youcode.citronix.entity.Arbre;
import com.youcode.citronix.entity.Champ;
import com.youcode.citronix.entity.enums.ArbreAge;
import com.youcode.citronix.entity.enums.Saison;
import com.youcode.citronix.exception.arbreException.ArbreNotFoundException;
import com.youcode.citronix.exception.arbreException.ArbreNullORFutureDAtePlantationException;
import com.youcode.citronix.exception.arbreException.NotAlowMonthException;
import com.youcode.citronix.exception.champExceptions.ChampNotFoundException;
import com.youcode.citronix.exception.champExceptions.ChampSuperficieInsuffisantException;
import com.youcode.citronix.repository.ArbreRepository;
import com.youcode.citronix.repository.ChampRepository;
import com.youcode.citronix.service.ArbreService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArbreServiceImpl implements ArbreService {


    private ArbreRepository arbreRepository;


    private ChampRepository champRepository;


    private ModelMapper modelMapper;

    @Override
    @Transactional
    public ArbreResponseDto createArbre(ArbreRequestDto arbreRequestDto) {
        Champ champ = champRepository.findById(arbreRequestDto.getChampId()).orElseThrow(() ->  new ChampNotFoundException("champ not found"));
         if(champ.getNbrArbre() < 1) throw new ChampSuperficieInsuffisantException("champ superficiee insuffisant ");
       if(DeterminSaison.defineSaisonFromDateRecolte(arbreRequestDto.getDateDePlantation()) != Saison.PRINTEMPS) throw new NotAlowMonthException("you should planting between MArch and MAi bro");
        return saveArbre( arbreRequestDto, champ);
    }

    @Override
    public ArbreResponseDto updateArbre(long id, ArbreRequestDto arbreRequestDto) {
        Champ champ = champRepository.findById(arbreRequestDto.getChampId()).orElseThrow(() ->  new ChampNotFoundException("champ not found"));
        if(champ.getNbrArbre() < 1) throw new ChampSuperficieInsuffisantException("champ superficie insuffisant ");
        if(DeterminSaison.defineSaisonFromDateRecolte(arbreRequestDto.getDateDePlantation()) != Saison.PRINTEMPS) throw new NotAlowMonthException("you should planting between MArch and MAi bro");
        Champ oldChamp = champRepository.findById(arbreRequestDto.getChampId()).orElseThrow(() ->  new ChampNotFoundException("champ not found"));
        oldChamp.setNbrArbre(oldChamp.getNbrArbre() + 1);
        return saveArbre( arbreRequestDto, champ);

    }

    private ArbreResponseDto saveArbre(ArbreRequestDto arbreRequestDto, Champ champ) {
        Arbre arbre = modelMapper.map(arbreRequestDto, Arbre.class);
        arbre.setChamp(champ);
        arbre.setAge(ArbreAgeCalculator(arbre.getDateDePlantation()));
        arbre.getChamp().setNbrArbre(arbre.getChamp().getNbrArbre() - 1);
        arbre.setId(0);
        arbre = arbreRepository.save(arbre);
        return modelMapper.map(arbre, ArbreResponseDto.class);
    }

    public static ArbreAge ArbreAgeCalculator(LocalDate datePlantation) {
        if (datePlantation == null || datePlantation.isAfter(LocalDate.now())) throw new ArbreNullORFutureDAtePlantationException("La date de plantation ne peut pas etre null ou au futur.");
         if(datePlantation.getMonth()  != Month.MARCH && datePlantation.getMonth()  != Month.APRIL && datePlantation.getMonth()  != Month.MAY) throw new NotAlowMonthException("you should planting just betwenn march and Mai");
        int age = Period.between(datePlantation, LocalDate.now()).getYears();
      if (age >= 0 && age < 3) {
            return ArbreAge.JEUNE;
         } else if (age < 10) {
            return ArbreAge.MATURE;
        } else if (age < 20) {
            return ArbreAge.VIEUX;
      } else {
            return ArbreAge.NON_PRODUCTIF;
        }
    }


    @Override
    public List<ArbreResponseDto> getAllArbres() {
          List<Arbre> arbres = arbreRepository.findAll();
        return arbres.stream()
                .map(arbre -> modelMapper.map(arbre, ArbreResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ArbreResponseDto getArbreById(long id) {

        Arbre arbre = arbreRepository.findById(id)
                .orElseThrow(() -> new ArbreNotFoundException("Arbre not found"));

        return modelMapper.map(arbre, ArbreResponseDto.class);
    }



    @Override
    @Transactional
    public void deleteArbre(long id) {
        Arbre arbre = arbreRepository.findById(id).orElseThrow(() -> new ArbreNotFoundException("Arbre not found"));
        arbre.getChamp().setNbrArbre(arbre.getChamp().getNbrArbre() + 1);
         champRepository.save(arbre.getChamp());
        arbreRepository.delete(arbre);
    }
}
