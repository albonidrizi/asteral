package com.nasa.asteral.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.nasa.asteral.model.Authority;
import com.nasa.asteral.model.db.MyUser;
import com.nasa.asteral.repository.MyUserRepository;

@ExtendWith(MockitoExtension.class)
class MyUserDetailsServiceTest {

    @Mock
    private MyUserRepository myUserRepository;

    @InjectMocks
    private MyUserDetailsService myUserDetailsService;

    private MyUser testUser;

    @BeforeEach
    void setUp() {
        testUser = new MyUser();
        testUser.setUsername("testuser");
        testUser.setPassword("password123");
        testUser.setRole(Authority.USER.getValue());
    }

    @Test
    void loadUserByUsername_withValidUsername_shouldReturnUserDetails() {
        // Arrange
        when(myUserRepository.findUserByUsernameIgnoreCase("testuser"))
                .thenReturn(Optional.of(testUser));

        // Act
        UserDetails result = myUserDetailsService.loadUserByUsername("testuser");

        // Assert
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        assertEquals("password123", result.getPassword());
        verify(myUserRepository, times(1)).findUserByUsernameIgnoreCase("testuser");
    }

    @Test
    void loadUserByUsername_withCaseInsensitiveUsername_shouldReturnUserDetails() {
        // Arrange
        when(myUserRepository.findUserByUsernameIgnoreCase("TESTUSER"))
                .thenReturn(Optional.of(testUser));

        // Act
        UserDetails result = myUserDetailsService.loadUserByUsername("TESTUSER");

        // Assert
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        verify(myUserRepository, times(1)).findUserByUsernameIgnoreCase("TESTUSER");
    }

    @Test
    void loadUserByUsername_withNonExistentUsername_shouldThrowException() {
        // Arrange
        when(myUserRepository.findUserByUsernameIgnoreCase(anyString()))
                .thenReturn(Optional.empty());

        // Act & Assert
        UsernameNotFoundException exception = assertThrows(
            UsernameNotFoundException.class,
            () -> myUserDetailsService.loadUserByUsername("nonexistent")
        );

        assertEquals("Username not found.", exception.getMessage());
        verify(myUserRepository, times(1)).findUserByUsernameIgnoreCase("nonexistent");
    }

    @Test
    void loadUserByUsername_shouldReturnUserWithCorrectAuthority() {
        // Arrange
        when(myUserRepository.findUserByUsernameIgnoreCase("testuser"))
                .thenReturn(Optional.of(testUser));

        // Act
        UserDetails result = myUserDetailsService.loadUserByUsername("testuser");

        // Assert
        assertNotNull(result);
        assertTrue(result.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals(Authority.USER.getValue())));
    }
}
