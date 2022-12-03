package com.epam.training.ticketservice.core.services.serviceimpl;

import com.epam.training.ticketservice.core.entity.RoomEntity;
import com.epam.training.ticketservice.core.model.RoomDTO;
import com.epam.training.ticketservice.core.repository.RoomRepository;
import com.epam.training.ticketservice.core.repository.ScreeningRepository;
import com.epam.training.ticketservice.core.services.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ScreeningRepository screeningRepository;

    @Override
    public Optional<RoomDTO> createRoom(String name, int rowCount, int columnCount){
        Optional<RoomDTO> roomDTO = convertEntityToDTO(roomRepository.findByName(name));
        if(roomDTO.isEmpty()){
            roomRepository.save(createEntity(name, rowCount, columnCount));
        }
        return roomDTO;
    }

    @Override
    public int updateRoom(String name, int rowCount, int columnCount){
        return roomRepository.updateRoom(name, rowCount, columnCount);
    }

    @Override
    public int deleteRoom(String name){
        int deletedRow = roomRepository.deleteByName(name);
        return deletedRow;
    }

    @Override
    public List<RoomDTO> getRoomList(){
        return roomRepository.findAll().stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    private RoomEntity createEntity(String name, int rowCount, int columnCount){
        return new RoomEntity(name,rowCount,columnCount);
    }

    public RoomDTO convertEntityToDTO(RoomEntity roomEntity){
        return RoomDTO.builder()
                .withName(roomEntity.getName())
                .withRowCount(roomEntity.getRowCount())
                .withColumnCount(roomEntity.getColumnCount())
                .build();
    }

    private Optional<RoomDTO> convertEntityToDTO(Optional<RoomEntity> roomEntity){
        return roomEntity.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDTO(roomEntity.get()));
    }
}
