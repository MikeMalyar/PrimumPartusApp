package com.chnu.service;

import com.chnu.model.Profile;

import java.util.Optional;

public interface IProfileService {

 Optional<Profile> save (Profile profile);

 Optional<Profile> findById (Long pk);

 Profile update (Profile object);

 void delete (Profile object);

 void deleteById(Long pk);
}
