package az.code.finalback.service.Impl;

import az.code.finalback.model.Movie;
import az.code.finalback.model.Rating;
import az.code.finalback.model.User;
import az.code.finalback.repository.MovieRepository;
import az.code.finalback.repository.RatingRepository;
import az.code.finalback.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public RatingServiceImpl(RatingRepository ratingRepository, MovieRepository movieRepository) {
        this.ratingRepository = ratingRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public Rating addRating(User user, Movie movie, int userRating) {
        Optional<Rating> existingRating = ratingRepository.findByUserAndMovie(user, movie);
        Rating rating;
        if (existingRating.isPresent()) {
            rating = existingRating.get();
            rating.setRating(userRating);
            rating.setRatedAt(LocalDateTime.now());
        } else {
            rating = new Rating();
            rating.setUser(user);
            rating.setMovie(movie);
            rating.setRating(userRating);
            rating.setRatedAt(LocalDateTime.now());
        }
        Rating savedRating = ratingRepository.save(rating);
        updateIMDBRating(movie);
        return savedRating;
    }

    @Override
    public void updateIMDBRating(Movie movie) {
        Optional<Movie> optionalMovie = movieRepository.findById(movie.getId());
        if (optionalMovie.isPresent()) {
            Movie foundMovie = optionalMovie.get();
            List<Rating> ratings = ratingRepository.findByMovieId(foundMovie.getId());
            if (ratings.isEmpty()) {
                foundMovie.setImdbRating("0.0");
                return;
            }

            double totalRating = 0;
            for (Rating rating : ratings) {
                totalRating += rating.getRating();
            }
            double averageRating = totalRating / ratings.size();

            double roundedRating = Math.round(averageRating * 10) / 10.0;

            foundMovie.setImdbRating(String.valueOf(roundedRating));
            movieRepository.save(foundMovie);
        } else {
            throw new IllegalArgumentException("Movie not found");
        }
    }
}
