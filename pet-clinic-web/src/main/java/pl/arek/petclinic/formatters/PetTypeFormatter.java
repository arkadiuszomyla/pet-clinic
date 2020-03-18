package pl.arek.petclinic.formatters;

import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import pl.arek.petclinic.model.PetType;
import pl.arek.petclinic.services.PetTypeService;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {

    PetTypeService petTypeService;


    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetTypes = petTypeService.findAll();
        for(PetType type : findPetTypes) {
            if (type.getName().equals(text)) {
                return type;
            }
        } throw new ParseException("type not found: " + text, 0);
    }

}
