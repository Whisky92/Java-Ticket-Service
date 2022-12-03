package com.epam.training.ticketservice.core.services.serviceimpl;

import com.epam.training.ticketservice.core.entity.MovieEntity;
import com.epam.training.ticketservice.core.entity.RoomEntity;
import com.epam.training.ticketservice.core.entity.ScreeningEntity;
import com.epam.training.ticketservice.core.model.MovieDTO;
import com.epam.training.ticketservice.core.model.RoomDTO;
import com.epam.training.ticketservice.core.model.ScreeningDTO;
import com.epam.training.ticketservice.core.repository.MovieRepository;
import com.epam.training.ticketservice.core.repository.RoomRepository;
import com.epam.training.ticketservice.core.repository.ScreeningRepository;
import com.epam.training.ticketservice.core.services.service.MovieService;
import com.epam.training.ticketservice.core.services.service.RoomService;
import com.epam.training.ticketservice.core.services.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScreeningServiceImpl implements ScreeningService {
    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;
    private final MovieService movieService;
    private final RoomRepository roomRepository;
    private final RoomService roomService;

    @Override
    public String createScreening(String movieTitle, String roomName, String startTime){
        MovieEntity movie = movieRepository.findByTitle(movieTitle).orElseThrow ( () ->new IllegalArgumentException("No such movie with this title"));
        RoomEntity room = roomRepository.findByName(roomName).orElseThrow ( () ->new IllegalArgumentException("No such room with that name"));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date;
        try {
            date = format.parse(startTime);
        } catch (ParseException e) {
            return "Invalid date";
        }
        String result=isRoomAndTimeWrong(date, movie.getLength(), room.getName());
        if(!result.equals("")){
            return result;
        }
        ScreeningEntity screeningEntity=createEntity(movie, room, date);
        screeningRepository.save(screeningEntity);
        return "Screening successfully created";
    }
    @Override
    public List<ScreeningDTO> getScreeningList(){
        return screeningRepository.findAll().stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }


    private ScreeningEntity createEntity(MovieEntity movie, RoomEntity room, Date startTime){
        ScreeningEntity screeningEntity = ScreeningEntity.builder()
                .withMovie(movie)
                .withRoom(room)
                .withTime(startTime)
                .build();
        return screeningEntity;
    }
    private ScreeningDTO convertEntityToDTO(ScreeningEntity screeningEntity){
        ScreeningDTO screeningDTO = ScreeningDTO.builder()
                .withMovie(movieService.convertEntityToDTO(screeningEntity.getMovie()))
                .withRoom(roomService.convertEntityToDTO(screeningEntity.getRoom()))
                .withTime(screeningEntity.getTime())
                .build();
        return screeningDTO;
    }
    private Optional<ScreeningDTO> convertEntityToDTO(Optional<ScreeningEntity> screeningEntity){
        return screeningEntity.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDTO(screeningEntity.get()));
    }
    private String isRoomAndTimeWrong(Date startTime, int length, String room) {
        List<ScreeningEntity> screenings=screeningRepository.findAll();
        for(int i=0; i<screenings.size(); i++){
            Date currentStart = screenings.get(i).getTime();
            int currentLength = screenings.get(i).getMovie().getLength();
            Date currentEnd = getEnd(currentStart, currentLength);
            String currentName = screenings.get(i).getRoom().getName();
            if(isOverLapping(startTime, getEnd(startTime, length), currentStart, currentEnd) && room.equals(currentName)){
                return "There is an overlapping screening";
            }else if(isDuringMovieBreak(startTime, currentEnd, getEnd(currentEnd, 10))){
                return "This would start in the break period after another screening in this room";
            }
        }
        return "";
    }

    private boolean isOverLapping(Date startOfFirst, Date endOfFirst, Date startOfSecond, Date endofSecond){
        return (!(endOfFirst.compareTo(startOfSecond)!=1 || startOfFirst.compareTo(endofSecond)!=-1));
    }

    private boolean isDuringMovieBreak(Date startOfMovie, Date startOfBreak, Date endOfBreak){
        if(startOfMovie.compareTo(startOfBreak)!=-1 && startOfMovie.compareTo(endOfBreak)!=1){
            return true;
        }
        return false;
    }

    private Date getEnd(Date startTime, Integer length){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(calendar.MINUTE, length);
        return calendar.getTime();
    }
}
