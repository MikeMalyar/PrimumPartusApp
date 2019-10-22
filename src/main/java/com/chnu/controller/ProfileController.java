package com.chnu.controller;

import com.chnu.controller.base.BaseController;
import com.chnu.model.Profile;
import com.chnu.rest.GenericResponse;
import com.chnu.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.chnu.util.PropertiesUtil.getMessage;

@RestController
@RequestMapping(value = "/profile")
public class ProfileController extends BaseController {

    private final IProfileService profileService;

    @Autowired
    public ProfileController(IProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("")
    public GenericResponse<Profile> save(@RequestBody Profile profile) {
        validate(profile);
        GenericResponse<Profile> response = GenericResponse
                .of(profileService.save(profile).orElse(null));
        return response.getResult() != null ? response :
                GenericResponse.error(getMessage("msg.profile.exit"));
    }

    @GetMapping("/{id}")
    public GenericResponse<Profile> findById(@PathVariable(name = "id", required = true) Long pk) {
        GenericResponse<Profile> response = GenericResponse
                .of(profileService.findById(pk).orElse(null));
        return response.getResult() != null ? response :
                GenericResponse.error(getMessage("msg.profile.dont.id"));
    }

    @PutMapping("")
    public GenericResponse<Profile> update(@RequestBody Profile object) {
        validate(object);
        if(object != null) {
            return GenericResponse.of(profileService.update(object));
        }
        return GenericResponse.error(getMessage("msg.profile.null"));
    }



    @DeleteMapping("/{id}")
    public GenericResponse<Void> deleteById(@PathVariable(name = "id", required = true) Long pk) {
        profileService.deleteById(pk);
        return GenericResponse.empty();
    }
}
