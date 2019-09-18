package com.chnu.service.impl;

import com.chnu.model.Profile;
import com.chnu.repository.IProfileRepository;
import com.chnu.repository.IUserRepository;
import com.chnu.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProfileService implements IProfileService {

    private final IProfileRepository profileRepository;
    private final IUserRepository userRepository;

    @Autowired
    public ProfileService(IProfileRepository profileRepository, IUserRepository userRepository){
        this.profileRepository=profileRepository;
        this.userRepository=userRepository;
    }

    @Override
    public Optional<Profile> save(Profile profile) {
        return Optional.ofNullable(profileRepository.save(profile));
    }

    @Override
    public Optional<Profile> findById(Long pk) {
        return profileRepository.findById(pk);
    }

    @Override
    public Profile update(Profile object) {
        return profileRepository.update(object);
    }

    @Override
    public void delete(Profile object) {
            profileRepository.delete(object);
    }

    @Override
    public void deleteById(Long pk) {
         profileRepository.deleteById(pk);
    }
}
