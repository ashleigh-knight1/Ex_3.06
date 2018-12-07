package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {
    @Autowired
    AlbumRepository albumRepository;

    @RequestMapping("/")
    public String index(Model model) {
        // First let's create an album
        Album album = new Album();

        album.setName("Invasion of Privacy");
        album.setArtist("Cardi B");
        album.setGenre("Hip Hop");


        // create a song
        Song song = new Song();
        song.setTitle("I like it like that");


        //Add the song to an empty list
        Set<Song> songs = new HashSet<Song>();
        songs.add(song);

        song = new Song();
        song.setTitle("Bodak Yellow");
        songs.add(song);

        //Add the list of songs to the album's movie list
        album.setSongs(songs);

        //Save the album to the database
        albumRepository.save(album);

        //Get all the albums from the database and send them to the template
        model.addAttribute("albums", albumRepository.findAll());
        return "index";

    }
}