package com.epita.service;

import com.epita.repository.AuthRepository;
import com.epita.controller.contracts.UserContract;
import com.epita.repository.entity.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import org.jboss.logging.Logger;

@ApplicationScoped
public class AuthService {

    private static final Logger LOGGER = Logger.getLogger(AuthService.class);

    @Inject
    AuthRepository authRepository;

    public Response createUser(String username, String password) {
        LOGGER.infof("[AUTH] Create User : Trying to create : %s", username);
        if (authRepository.userExists(username)) {
            LOGGER.warnf("[AUTH] Create User : Failure : The user %s already exists", username);
            return Response.status(Response.Status.CONFLICT).build();
        }
        UserEntity user = new UserEntity(username, hashPassword(password), new Timestamp(new Date().getTime()), new Timestamp(new Date().getTime()));
        authRepository.createUser(user);
        LOGGER.infof("[AUTH] Create User : User created with success : %s", username);
        return Response.ok(user).build();
    }

    public Response login(String username, String password) {
        LOGGER.infof("[AUTH] Login : Trying to connect the user : %s", username);
        UserEntity user = authRepository.getUserByUsernameAndHashPassword(username, hashPassword(password));
        if (user == null) {
            LOGGER.warnf("[AUTH] Login : Failure : User %s not found or wrong password", username);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        UserContract userContract = new UserContract(user.getId(), user.username, user.password, user.created, user.updated);
        LOGGER.infof("[AUTH] Login : Connection successful for user : %s", username);
        return Response.ok(userContract).build();
    }

    public Response updatePassword(UUID id, String password) {
        LOGGER.infof("[AUTH] Password update : Trying to update password for : %s", id);
        UserEntity user = authRepository.getUser(id);
        if (user == null) {
            LOGGER.warnf("[AUTH] Password update : USER (%s) NOT FOUND", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        user.password = hashPassword(password);
        authRepository.updateUser(user);
        LOGGER.infof("[AUTH] Password updated for : %s", id);
        return Response.ok().build();
    }

    public Response deleteUser(UUID id) {
        LOGGER.infof("[AUTH] Delete User : Trying to Delete user for : %s", id);
        UserEntity user = authRepository.getUser(id);
        if (user == null) {
            LOGGER.warnf("[AUTH] Delete User : USER (%s) NOT FOUND", id);
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        authRepository.deleteUser(id);

        LOGGER.warnf("[AUTH] Deleted User : %s", id);
        return Response.ok().build();
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("[AUTH] Error in the hashing of the password", e);
            throw new RuntimeException("[AUTH] Error in the hashing of the password", e);
        }
    }
}
