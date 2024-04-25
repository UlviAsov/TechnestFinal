package az.code.finalback.controller;

import az.code.finalback.dto.LoginRequest;
import az.code.finalback.dto.UserDto;
import az.code.finalback.dto.UserResponseDto;
import az.code.finalback.dto.WatchlistMovieDto;
import az.code.finalback.model.User;
import az.code.finalback.service.Impl.UserServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/users")
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserController {
    final UserServiceImpl userService;


//    @PostMapping("/{userId}/upload-image")
//    public ResponseEntity<String> uploadImage(@PathVariable long userId, @RequestParam("file") MultipartFile file) {
//        try {
//            userService.uploadUserImage(userId, file);
//            return ResponseEntity.ok("User image uploaded successfully.");
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading user image.");
//        }
//    }
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        boolean isRegistered = userService.register(userDto);
        if (isRegistered) {
            return ResponseEntity.ok("User registered successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to register user");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<UserResponseDto> login(@RequestBody LoginRequest loginRequest) {
        UserResponseDto userResponseDto = userService.login(loginRequest);
        if (userResponseDto != null) {
            return ResponseEntity.ok(userResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/{userId}/watchlist/movies")
    public ResponseEntity<String> addToWatchlist(@PathVariable Long userId, @RequestParam Long movieId) {
        boolean isAdded = userService.addToWatchlist(userId, movieId);
        if (isAdded) {
            return ResponseEntity.ok("Movie added to watchlist successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add movie to watchlist");
        }
    }

    @DeleteMapping("/{userId}/watchlist/movies/remove")
    public ResponseEntity<String> removeFromWatchlist(@PathVariable Long userId, @RequestParam Long movieId) {
        boolean isRemoved = userService.removeFromWatchlist(userId, movieId);
        if (isRemoved) {
            return ResponseEntity.ok("Movie removed from watchlist successfully");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to remove movie from watchlist");
        }
    }

    @GetMapping("/{userId}/watchlist")
    public ResponseEntity<List<WatchlistMovieDto>> getUserWatchlist(@PathVariable Long userId) {
        List<WatchlistMovieDto> watchlistMovies = userService.getUserWatchlist(userId);
        if (watchlistMovies != null && !watchlistMovies.isEmpty()) {
            return ResponseEntity.ok(watchlistMovies);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
