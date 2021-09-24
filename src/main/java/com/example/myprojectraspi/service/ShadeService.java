package com.example.myprojectraspi.service;

import com.example.myprojectraspi.model.ShadeEntity;
import com.example.myprojectraspi.repository.ShadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ShadeService {
    private final ShadeRepository shadeRepository;

    @Autowired
    public ShadeService(ShadeRepository shadeRepository){
        this.shadeRepository = shadeRepository;
    }
    public static void shadeOpening() {

    }

    public List<ShadeEntity> findAllShades() {
        return shadeRepository.findAll();
    }

    public ShadeEntity addShade(ShadeEntity shadeEntity){
        return shadeRepository.save(shadeEntity);
    }


    public void deleteShade(Long id){
        shadeRepository.deleteShadeEntityById(id);
    }

//    public Mono<ShadeEntity> moveShade(ShadeEntity shadeEntity) {
//        if (shadeEntity.getValue()> shadeEntity.getStatus()){
//            int finalValue = (shadeEntity.getTimeToOpenAndCloseShade()*shadeEntity.getValue())/100;
//            final var console = new Console();
//            var pi4j = Pi4J.newAutoContext();
//            var moveShade = DigitalOutput.newConfigBuilder(pi4j)
//                    .id(shadeEntity.getId())
//                    .name(shadeEntity.getName())
//                    .address(shadeEntity.getAddressClose())
//                    .shutdown(DigitalState.HIGH)
//                    .initial(DigitalState.HIGH)
//                    .provider(shadeEntity.getProvider());
//            var move = pi4j.create(moveShade);
//            move.pulse(finalValue, TimeUnit.SECONDS);
//            shadeEntity.setStatus(shadeEntity.getValue());
//        } else if (shadeEntity.getValue()< shadeEntity.getStatus()){
//            int finalValue = (shadeEntity.getTimeToOpenAndCloseShade()*shadeEntity.getValue())/100;
//            final var console = new Console();
//            var pi4j = Pi4J.newAutoContext();
//            var moveShade = DigitalOutput.newConfigBuilder(pi4j)
//                    .id(shadeEntity.getId())
//                    .name(shadeEntity.getName())
//                    .address(shadeEntity.getAddressOpen())
//                    .shutdown(DigitalState.HIGH)
//                    .initial(DigitalState.HIGH)
//                    .provider(shadeEntity.getProvider());
//            var move = pi4j.create(moveShade);
//            move.pulse(finalValue, TimeUnit.SECONDS);
//            shadeEntity.setStatus(shadeEntity.getValue());
//        }


//        return shadeEntity;
    }

