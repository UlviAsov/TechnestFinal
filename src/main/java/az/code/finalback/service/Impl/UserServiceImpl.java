package az.code.finalback.service.Impl;

import az.code.finalback.dto.LoginRequest;
import az.code.finalback.dto.UserDto;
import az.code.finalback.dto.UserResponseDto;
import az.code.finalback.dto.WatchlistMovieDto;
import az.code.finalback.model.Movie;
import az.code.finalback.model.User;
import az.code.finalback.model.Watchlist;
import az.code.finalback.repository.MovieRepository;
import az.code.finalback.repository.UserRepository;
import az.code.finalback.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {
    final UserRepository userRepository;
    final MovieRepository movieRepository;
    final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, MovieRepository movieRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean register(UserDto userDto) {
        User existingUser = userRepository.findByEmail(userDto.getEmail());
        if (existingUser != null) {
            return false;
        }

        User newUser = User.builder()
                .username(userDto.getUsername())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();

        userRepository.save(newUser);

        return true;
    }

    @Override
    public UserResponseDto login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(),user.getUserImg(),user.getWatchlistSize());
        }

        return null;
    }

//    @Override
//    public void uploadUserImage(long userId, MultipartFile file) throws IOException {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
//
//        // Convert MultipartFile to byte array
//        byte[] imageData = file.getBytes();
//
//        // Set userImg field with the byte array representation of the image
//        user.setUserImg(imageData);
//
//        // Save the updated user object
//        userRepository.save(user);
//    }


    @Override
    public boolean addToWatchlist(Long userId, Long movieId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);

        if (optionalUser.isPresent() && optionalMovie.isPresent()) {
            User user = optionalUser.get();
            Movie movie = optionalMovie.get();

            if (user.getWatchlist() != null && user.getWatchlist().getMovies().contains(movie)) {
                return false;
            }

            Watchlist watchlist = user.getWatchlist();
            if (watchlist == null) {
                watchlist = new Watchlist();
                watchlist.setUser(user);
                user.setWatchlist(watchlist);
            }

            if (!watchlist.getMovies().contains(movie)) {
                watchlist.getMovies().add(movie);
                user.setWatchlistSize(user.getWatchlistSize() + 1);
                userRepository.save(user);
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean removeFromWatchlist(Long userId, Long movieId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);

        if (optionalUser.isPresent() && optionalMovie.isPresent()) {
            User user = optionalUser.get();
            Movie movie = optionalMovie.get();

            Watchlist watchlist = user.getWatchlist();
            if (watchlist != null && watchlist.getMovies().contains(movie)) {
                watchlist.getMovies().remove(movie);
                user.setWatchlistSize(user.getWatchlistSize() - 1);
                userRepository.save(user);
                return true;
            }
        }

        return false;
    }

    @Override
    public List<WatchlistMovieDto> getUserWatchlist(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<Movie> watchlistMovies = user.getWatchlist().getMovies();
            return watchlistMovies.stream()
                    .map(movie -> modelMapper.map(movie, WatchlistMovieDto.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }




}
