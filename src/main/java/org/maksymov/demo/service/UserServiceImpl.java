package org.maksymov.demo.service;

import lombok.RequiredArgsConstructor;
import org.maksymov.demo.controller.payload.UserWithCredentials;
import org.maksymov.demo.db.entity.AccountEntity;
import org.maksymov.demo.db.entity.UserProfileEntity;
import org.maksymov.demo.db.repository.AccountRepository;
import org.maksymov.demo.db.repository.UserProfileRepository;
import org.maksymov.demo.dto.UserProfileWithLoginDTO;
import org.maksymov.demo.exception.AlreadyExistException;
import org.maksymov.demo.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.maksymov.demo.ExceptionCode.USER_ALREADY_EXISTS;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @NotNull
    private final AccountRepository accountRepository;

    @NotNull
    private final UserProfileRepository userProfileRepository;

    @NotNull
    private final UserMapper userMapper;

    @Override
    public UserProfileWithLoginDTO create(final UserWithCredentials user) {
        if (!accountRepository.existsByLoginIgnoreCase(user.getUserName())) {
            final byte[] hashedPassword = hashPassword(user.getPassword().toCharArray(), "salt".getBytes(), 1, 255);
            final AccountEntity accountToCreate = userMapper.toAccountEntity(user, hashedPassword);
            final AccountEntity createdAccount = accountRepository.save(accountToCreate);
            final UserProfileEntity profileToCreate = userMapper.toProfileEntity(createdAccount.getId(), user);
            final UserProfileEntity createdProfile = userProfileRepository.save(profileToCreate);
            return userMapper.toDto(createdAccount, createdProfile);
        }
        throw new AlreadyExistException(USER_ALREADY_EXISTS, "A user with the given username already exists");
    }

    /**
     * @see <a href="https://www.owasp.org/index.php/Hashing_Java">OWASP site</a>
     */
    private byte[] hashPassword( final char[] password, final byte[] salt, final int iterations, final int keyLength ) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA512" );
            PBEKeySpec spec = new PBEKeySpec( password, salt, iterations, keyLength );
            SecretKey key = skf.generateSecret( spec );
            byte[] res = key.getEncoded( );
            return res;

        } catch( NoSuchAlgorithmException | InvalidKeySpecException e ) {
            throw new RuntimeException( e );
        }
    }

}
