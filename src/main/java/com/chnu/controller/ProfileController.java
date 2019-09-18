package com.chnu.controller;

import com.chnu.model.Profile;
import com.chnu.service.ICourierService;
import com.chnu.service.IProfileService;
import com.chnu.service.impl.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/profile")
public class ProfileController {

    private final IProfileService profileService;

    @Autowired
    public ProfileController(IProfileService profileService) {
        this.profileService = profileService;
    }

    @PostMapping("")
    public Profile save(@RequestBody Profile profile) {
        return profileService.save(profile).orElse(null);
    }

    @GetMapping("/{id}")
    public Profile findById(@PathVariable(name = "id", required = true) Long pk) {
        return profileService.findById(pk).orElse(null);
    }

    @PutMapping("")
    public Profile update(@RequestBody Profile object) {
        return profileService.update(object);
    }

    @DeleteMapping("")
    public void delete(@RequestBody Profile object) {
        profileService.delete(object);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(name = "id", required = true) Long pk) {
        profileService.deleteById(pk);
    }
}
