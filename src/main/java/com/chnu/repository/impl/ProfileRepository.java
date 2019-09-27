package com.chnu.repository.impl;

import com.chnu.model.Profile;
import com.chnu.repository.IProfileRepository;
import com.chnu.util.QueryUtility;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ProfileRepository extends AbstractRepository<Profile,Long> implements IProfileRepository {

}
