package com.epam.training.ticketservice.core.services.serviceimpl;

import com.epam.training.ticketservice.core.entity.RoomEntity;
import com.epam.training.ticketservice.core.model.RoomDto;
import com.epam.training.ticketservice.core.repository.RoomRepository;
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

    @Override
    public Optional<RoomDto> createRoom(String name, int rowCount, int columnCount) {
        Optional<RoomDto> roomDto = convertEntityToDto(roomRepository.findByName(name));
        if (roomDto.isEmpty()) {
            roomRepository.save(createEntity(name, rowCount, columnCount));
        }
        return roomDto;
    }

    @Override
    public int updateRoom(String name, int rowCount, int columnCount) {
        return roomRepository.updateRoom(name, rowCount, columnCount);
    }

    @Override
    public int deleteRoom(String name) {
        return roomRepository.deleteByName(name);
    }

    @Override
    public List<RoomDto> getRoomList() {
        return roomRepository.findAll()
                .stream()
                .map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private RoomEntity createEntity(String name, int rowCount, int columnCount) {
        return new RoomEntity(name, rowCount, columnCount);
    }

    public RoomDto convertEntityToDto(RoomEntity roomEntity) {
        return RoomDto.builder()
                .withName(roomEntity.getName())
                .withRowCount(roomEntity.getRowCount())
                .withColumnCount(roomEntity.getColumnCount()).build();
    }

    private Optional<RoomDto> convertEntityToDto(Optional<RoomEntity> roomEntity) {
        return roomEntity.isEmpty() ? Optional.empty() : Optional.of(convertEntityToDto(roomEntity.get()));
    }
}
