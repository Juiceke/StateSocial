package com.website.StateSocial.controllers;

import com.website.StateSocial.entity.User;
import com.website.StateSocial.repository.UserRepository;
import com.website.StateSocial.service.StateService;
//import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
public class UserApiController {

    @Autowired
    UserRepository userRepository;

    private StateService stateService;

    public UserApiController (StateService stateService) {
        super();
        this.stateService = stateService;
    }


    private boolean in(char[] s) {
        boolean pin = false;
        boolean _break = false;
        for(int i=0; s.length > i; i++) {

            if (s[i] == ' ' && !pin) {
                pin = true;
                _break = true;
            } else if (_break) {
                break;
            }
            System.out.println(pin);
        }
        return pin;
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return stateService.getAllUsers();
    }

    private boolean insert(char[] password) {
        boolean insert = false;
        boolean _break = false;
        for(int i=0; password.length > i; i++) {

            System.out.println(password[i]);
            if(password[i] != ' ') {
                insert = true;
                _break = true;
            }
            if(_break) {
                break;
            }
            System.out.println(insert);
        }
        return insert;
    }

    //    Hash the users password
    private String generateStrongPasswordHash(char[] password)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        int iterations = 1000;
        char[] chars = password;
        byte[] salt = getSalt();

        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }

    //    get salt for password hash
    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    //    function that will be used to validate the password
    private boolean validatePassword(String originalPassword, String storedPassword) throws
            NoSuchAlgorithmException, InvalidKeySpecException {
        String[] parts = storedPassword.split(":");



        int iterations = Integer.parseInt(parts[0]);


        byte[] salt = fromHex(parts[1]);

        byte[] hash = fromHex(parts[2]);

        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();

        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;

    }

    //   turn byte array into a hex
    private static String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);

        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

    private static byte[] fromHex(String hex) throws NoSuchAlgorithmException
    {
        int len = hex.length();
        byte[] bytes = new byte[len / 2];
        for(int i = 0; i < len ; i += 2) {

            bytes[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }
        return bytes;
    }

//    return to string if user for when user is sent back to log in screen
    private static String fromChartoString(char[] e) {
        String string = new String(e);

        return string;
    }
}
