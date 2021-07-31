package guru.springframework.sfgpetclinic.formatters;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.List;
import java.util.Locale;

/**
 * Instructs Spring MVC on how to parse and print elements of type 'PetType'. Starting
 * from Spring 3.0, Formatters have come as an improvement in comparison to legacy
 * PropertyEditors. See the following links for more details: - The Spring ref doc:
 * https://docs.spring.io/spring-framework/docs/current/spring-framework-reference/core.html#format
 *
 * @author Mark Fisher
 * @author Juergen Hoeller
 * @author Michael Isvy
 *
 * Modified by Pierrot on 7/31/21
 */
@Component
public class PetTypeFormatter implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    @Autowired
    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {

        List<PetType> findPetTypesByName = petTypeService.findByName(text);

        return findPetTypesByName.stream()
                .findFirst()
                .orElseThrow(() -> new ParseException("type not found: " + text, 0));

    }

}
