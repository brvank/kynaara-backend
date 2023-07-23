package com.retail.kynaara.controller;

import com.retail.kynaara.service.ChannelService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/v1/channel")
@ResponseBody
public class ChannelController extends ParentController{
    @Autowired
    private ChannelService channelService;

    @PostMapping("/add")
    public ResponseEntity<Object> addChannel(@RequestBody Map<String, Object> channelMap, HttpServletRequest header){
        return channelService.addChannel(channelMap, headerToUser(header));
    }

    @GetMapping("/get")
    public ResponseEntity<Object> getChannels(@RequestParam int start, @RequestParam int size, @RequestParam(required = false) String q, HttpServletRequest header){
        return channelService.getChannels(start, size, q, headerToUser(header));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getChannelByChannelId(@PathVariable(value = "id") int channelId, HttpServletRequest header){
        return channelService.getChannelByChannelId(channelId, headerToUser(header));
    }

    @PutMapping("/update")
    public ResponseEntity<Object> updateChannel(@RequestBody Map<String, Object> channelMap, HttpServletRequest header){
        return channelService.updateChannel(channelMap, headerToUser(header));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteChannel(@PathVariable(value = "id") int channelId, HttpServletRequest header){
        return channelService.deleteChannel(channelId, headerToUser(header));
    }
}
