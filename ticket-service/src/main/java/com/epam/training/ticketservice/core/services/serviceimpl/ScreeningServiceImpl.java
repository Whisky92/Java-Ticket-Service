package com.epam.training.ticketservice.core.services.serviceimpl;

import com.epam.training.ticketservice.core.dateformatter.DateFormatter;
import com.epam.training.ticketservice.core.entity.MovieEntity;
import com.epam.training.ticketservice.core.entity.RoomEntity;
import com.epam.training.ticketservice.core.entity.ScreeningEntity;
import com.epam.training.ticketservice.core.model.ScreeningDto;
import com.epam.training.ticketservice.core.repository.MovieRepository;
import com.epam.training.ticketservice.core.repository.RoomRepository;
import com.epam.training.ticketservice.core.repository.ScreeningRepository;
import com.epam.training.ticketservice.core.services.MovieService;
import com.epam.training.ticketservice.core.services.RoomService;
import com.epam.training.ticketservice.core.services.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreeningServiceImpl implements ScreeningService {

    private final ScreeningRepository screeningRepository;

    private final MovieRepository movieRepository;

    private final MovieService movieService;

    private final RoomRepository roomRepository;

    private final RoomService roomService;

    private final DateFormatter dateFormatter;

    @Override
    public String createScreening(String movieTitle, String roomName, String startTime) {
        MovieEntity movie = movieRepository.findByTitle(movieTitle)
                .orElseThrow(() -> new IllegalArgumentException("No such movie with this title"));
        RoomEntity room = roomRepository.findByName(roomName)
                .orElseThrow(() -> new IllegalArgumentException("No such room with that name"));
        Date date = (dateFormatter.formatStringToDate(startTime)
                .orElseThrow(() -> new IllegalArgumentException("Invalid date")));
        String result = isRoomAndTimeWrong(date, movie.getLength(), room.getName());
        if (!result.equals("")) {
            return result;
        }
        ScreeningEntity screeningEntity = createEntity(movie, room, date);
        screeningRepository.save(screeningEntity);
        return "Screening successfully created";
    }

    @Override
    public List<ScreeningDto> getScreeningList() {
        return screeningRepository.findAll().stream()
                .map(this::convertEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public int deleteScreening(String movieTitle, String roomName, String startTime) {
        MovieEntity movie = movieRepository.findByTitle(movieTitle)
                .orElseThrow(() -> new IllegalArgumentException("No such movie with that title"));
        RoomEntity room = roomRepository.findByName(roomName)
                .orElseThrow(() -> new IllegalArgumentException("No such room with that name"));
        Date date = dateFormatter.formatStringToDate(startTime)
                .orElseThrow(() -> new IllegalArgumentException("Invalid date"));
        return screeningRepository.deleteByMovieAndRoomAndTime(movie, room, date);

    }

    private ScreeningEntity createEntity(MovieEntity movie, RoomEntity room, Date startTime) {
        return ScreeningEntity.builder()
                .withMovie(movie)
                .withRoom(room)
                .withTime(startTime)
                .build();
    }

    private ScreeningDto convertEntityToDto(ScreeningEntity screeningEntity) {
        return ScreeningDto.builder()
                .withMovie(movieService.convertEntityToDto(screeningEntity.getMovie()))
                .withRoom(roomService.convertEntityToDto(screeningEntity.getRoom()))
                .withTime(dateFormatter.formatDateToString(screeningEntity.getTime()))
                .build();
    }

    private String isRoomAndTimeWrong(Date startTime, int length, String room) {
        List<ScreeningEntity> screenings = screeningRepository.findAll();
        Date endTime = getTime(startTime, length);
        for (ScreeningEntity screening : screenings) {
            Date currentStart = screening.getTime();
            int currentLength = screening.getMovie().getLength();
            Date currentEnd = getTime(currentStart, currentLength);
            String currentName = screening.getRoom().getName();
            if (isOverLapping(startTime, endTime, currentStart, currentEnd)
                    && room.equals(currentName)) {
                return "There is an overlapping screening";
            } else if ((isDuringMovieBreak(endTime, getTime(currentStart, -10), currentStart)
                    || isDuringMovieBreak(startTime, currentEnd, getTime(currentEnd, 10)))
                    && room.equals(currentName)) {
                return "This would start in the break period after another screening in this room";
            }
        }
        return "";
    }

    private boolean isOverLapping(Date startOfFirst, Date endOfFirst, Date startOfSecond, Date endOfSecond) {
        final int plus = 1;
        return (startOfFirst.compareTo(endOfSecond) != plus && startOfSecond.compareTo(endOfFirst) != plus);
    }

    private boolean isDuringMovieBreak(Date startOrEndOfMovie, Date startOfBreak, Date endOfBreak) {
        if (startOrEndOfMovie.compareTo(startOfBreak) != -1 && startOrEndOfMovie.compareTo(endOfBreak) != 1) {
            return true;
        }
        return false;
    }

    private Date getTime(Date startTime, Integer length) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(calendar.MINUTE, length);
        return calendar.getTime();
    }

}
