package com.springsecurity.Spring_Security.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room")
public class RoomController {


    @PostMapping
    @PreAuthorize("hasAuthority('ADD_ROOM')")
    public String addRoom(){
        return "Room added";
    }


    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROOM_VIEW')")
    public String getRoomById(@PathVariable Long id){

        return "Room fetched for id: " + id;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROOM_VIEW_ALL')")
    public String getRooms(){
        return "All Rooms";
    }
}
