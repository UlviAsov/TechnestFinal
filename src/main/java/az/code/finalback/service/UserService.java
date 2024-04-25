package az.code.finalback.service;

import az.code.finalback.dto.LoginRequest;
import az.code.finalback.dto.UserDto;
import az.code.finalback.dto.UserResponseDto;
import az.code.finalback.dto.WatchlistMovieDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface UserService {
    boolean register(UserDto userDto);

    boolean addToWatchlist(Long userId, Long movieId);

    boolean removeFromWatchlist(Long userId, Long movieId);

    List<WatchlistMovieDto> getUserWatchlist(Long userId);

    UserResponseDto login(LoginRequest loginRequest);

//    void uploadUserImage(long userId, MultipartFile file) throws IOException;
}
