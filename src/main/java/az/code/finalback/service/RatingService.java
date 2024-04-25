package az.code.finalback.service;

import az.code.finalback.dto.RatingDto;
import az.code.finalback.model.Movie;
import az.code.finalback.model.Rating;
import az.code.finalback.model.User;

public interface RatingService {
    Rating addRating(User user, Movie movie, int rating);
    void updateIMDBRating(Movie movie);
}
