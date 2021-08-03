package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("owners/{ownerId}/pets/{petId}")
public class VisitController {

    private static final String VIEWS_VISITS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdateVisitForm";

    private final PetService petService;
    private final OwnerService ownerService;
    private final VisitService visitService;

    public VisitController(PetService petService, OwnerService ownerService,
                           VisitService visitService) {
        this.petService = petService;
        this.ownerService = ownerService;
        this.visitService = visitService;
    }

    @InitBinder
    public void setDisallowedFields(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
    }

    @ModelAttribute("owner")
    public Owner findOwner(@PathVariable Long ownerId) {
        return ownerService.findById(ownerId);
    }

    @ModelAttribute("pet")
    public Pet findPet(@PathVariable("petId") Long id) {
        return petService.findById(id);
    }

    @GetMapping("visits/new")
    public String initCreateVisit(Visit visit, ModelMap modelMap) {
        visit = new Visit();
        modelMap.put("visit", visit);
        return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("visits/new")
    public String processCreateVisit(@Valid Visit visit, Owner owner, Pet pet, BindingResult result) {
        if (result.hasErrors()){
            return VIEWS_VISITS_CREATE_OR_UPDATE_FORM;
        }
        return "redirect:/owners/"+owner.getId();
    }

}
